package com.dwarfeng.familyhelper.finance.impl.handler.redriver;

import com.dwarfeng.familyhelper.finance.impl.handler.RemindDriverProvider;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.RemindDriverInfo;
import com.dwarfeng.familyhelper.finance.stack.exception.RemindDriverException;
import com.dwarfeng.familyhelper.finance.stack.handler.RemindDriver;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Cron提醒驱动提供器。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
@Component
public class CronRemindDriverProvider implements RemindDriverProvider {

    public static final String SUPPORT_TYPE = "cron_remind_driver";

    private final CronRemindDriver cronDriver;

    public CronRemindDriverProvider(CronRemindDriver cronDriver) {
        this.cronDriver = cronDriver;
    }

    @Override
    public boolean supportType(String type) {
        return Objects.equals(SUPPORT_TYPE, type);
    }

    @Override
    public RemindDriver provide() {
        return cronDriver;
    }

    @Component
    public static class CronRemindDriver extends AbstractRemindDriver {

        private final ThreadPoolTaskScheduler scheduler;

        private final Lock lock = new ReentrantLock();
        private final Set<ScheduledFuture<?>> scheduledFutures = new HashSet<>();
        private final Set<CronProcessor> cronProcessors = new HashSet<>();

        public CronRemindDriver(ThreadPoolTaskScheduler scheduler) {
            this.scheduler = scheduler;
        }

        @Override
        public void register(RemindDriverInfo remindDriverInfo) throws RemindDriverException {
            lock.lock();
            try {
                LongIdKey remindDriverInfoKey = remindDriverInfo.getKey();
                String cron = remindDriverInfo.getParam();
                CronProcessor cronProcessor = new CronProcessor(context, remindDriverInfoKey);
                CronTrigger cronTrigger = new CronTrigger(cron);
                ScheduledFuture<?> scheduledFuture = scheduler.schedule(cronProcessor, cronTrigger);
                cronProcessors.add(cronProcessor);
                scheduledFutures.add(scheduledFuture);
            } catch (Exception e) {
                throw new RemindDriverException(e);
            } finally {
                lock.unlock();
            }
        }

        @Override
        public void unregisterAll() {
            lock.lock();
            try {
                for (ScheduledFuture<?> scheduledFuture : scheduledFutures) {
                    scheduledFuture.cancel(true);
                }
                for (CronProcessor cronProcessor : cronProcessors) {
                    cronProcessor.shutdown();
                }
            } finally {
                lock.unlock();
            }
        }

        @Override
        public String toString() {
            return "CronRemindDriver{" +
                    "context=" + context +
                    '}';
        }
    }

    private static class CronProcessor implements Runnable {

        private static final Logger LOGGER = LoggerFactory.getLogger(CronProcessor.class);

        private final RemindDriver.Context context;
        private final LongIdKey remindDriverInfoKey;

        private final Lock lock = new ReentrantLock();
        private boolean runningFlag = true;

        private CronProcessor(RemindDriver.Context context, LongIdKey remindDriverInfoKey) {
            this.context = context;
            this.remindDriverInfoKey = remindDriverInfoKey;
        }

        @Override
        public void run() {
            lock.lock();
            try {
                if (!runningFlag) {
                    return;
                }

                LOGGER.debug("计划时间已到达, cron 提醒驱动器 " + remindDriverInfoKey + " 执行提醒动作...");
                context.remind(remindDriverInfoKey);
            } catch (Exception e) {
                LOGGER.warn("Cron 提醒驱动器 " + remindDriverInfoKey + " 执行提醒动作时出现异常, 放弃本次提醒", e);
            } finally {
                lock.unlock();
            }
        }

        void shutdown() {
            lock.lock();
            try {
                runningFlag = false;
            } finally {
                lock.unlock();
            }
        }
    }
}
