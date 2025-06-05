package com.dwarfeng.familyhelper.finance.impl.handler.redriver;

import com.dwarfeng.familyhelper.finance.sdk.handler.RemindDriverProvider;
import com.dwarfeng.familyhelper.finance.sdk.handler.redriver.AbstractRemindDriver;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.RemindDriverInfo;
import com.dwarfeng.familyhelper.finance.stack.exception.RemindDriverException;
import com.dwarfeng.familyhelper.finance.stack.handler.RemindDriver;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 固定间隔提醒驱动提供器。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
@Component
public class FixedRateRemindDriverProvider implements RemindDriverProvider {

    public static final String SUPPORT_TYPE = "fixed_rate_remind_driver";

    private final FixedRateRemindDriver fixedRateDriver;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public FixedRateRemindDriverProvider(FixedRateRemindDriver fixedRateDriver) {
        this.fixedRateDriver = fixedRateDriver;
    }

    @Override
    public boolean supportType(String type) {
        return Objects.equals(SUPPORT_TYPE, type);
    }

    @Override
    public RemindDriver provide() {
        return fixedRateDriver;
    }

    @Component
    public static class FixedRateRemindDriver extends AbstractRemindDriver {

        private final ThreadPoolTaskScheduler scheduler;

        private final Lock lock = new ReentrantLock();
        private final Set<ScheduledFuture<?>> scheduledFutures = new HashSet<>();
        private final Set<FixedRateProcessor> fixedRateProcessors = new HashSet<>();

        public FixedRateRemindDriver(ThreadPoolTaskScheduler scheduler) {
            this.scheduler = scheduler;
        }

        @Override
        public void register(RemindDriverInfo remindDriverInfo) throws RemindDriverException {
            lock.lock();
            try {
                LongIdKey remindDriverInfoKey = remindDriverInfo.getKey();
                long rate = Long.parseLong(remindDriverInfo.getParam());
                FixedRateProcessor fixedRateProcessor = new FixedRateProcessor(context, remindDriverInfoKey);
                ScheduledFuture<?> scheduledFuture =
                        scheduler.scheduleAtFixedRate(fixedRateProcessor, rate);
                fixedRateProcessors.add(fixedRateProcessor);
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
                for (FixedRateProcessor fixedRateProcessor : fixedRateProcessors) {
                    fixedRateProcessor.shutdown();
                }
            } finally {
                lock.unlock();
            }
        }

        @Override
        public String toString() {
            return "FixedRateRemindDriver{" +
                    "context=" + context +
                    '}';
        }
    }

    private static class FixedRateProcessor implements Runnable {

        private static final Logger LOGGER = LoggerFactory.getLogger(FixedRateProcessor.class);

        private final RemindDriver.Context context;
        private final LongIdKey remindDriverInfoKey;

        private final Lock lock = new ReentrantLock();
        private boolean runningFlag = true;

        private FixedRateProcessor(RemindDriver.Context context, LongIdKey remindDriverInfoKey) {
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

                LOGGER.debug("计划时间已到达, fixed rate 提醒驱动器 {} 执行提醒动作...", remindDriverInfoKey);
                context.remind(remindDriverInfoKey);
            } catch (Exception e) {
                LOGGER.warn("Fixed rate 提醒驱动器 {} 执行提醒动作时出现异常, 放弃本次提醒", remindDriverInfoKey, e);
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
