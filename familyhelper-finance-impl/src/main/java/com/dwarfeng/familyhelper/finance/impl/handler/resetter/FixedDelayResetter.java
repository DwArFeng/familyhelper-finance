package com.dwarfeng.familyhelper.finance.impl.handler.resetter;

import com.dwarfeng.familyhelper.finance.sdk.handler.resetter.AbstractResetter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import java.util.concurrent.ScheduledFuture;

/**
 * 固定间隔的重置器。
 *
 * @author DwArFeng
 * @since 1.4.2
 */
@Component
public class FixedDelayResetter extends AbstractResetter {

    private static final Logger LOGGER = LoggerFactory.getLogger(FixedDelayResetter.class);

    private final ThreadPoolTaskScheduler scheduler;

    @Value("${resetter.fixed_delay.delay}")
    private long delay;

    private final ResetTask resetTask = new ResetTask();

    private ScheduledFuture<?> resetTaskFuture;

    public FixedDelayResetter(ThreadPoolTaskScheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    public void start() {
        resetTaskFuture = scheduler.scheduleWithFixedDelay(resetTask, delay);
    }

    @Override
    public void stop() {
        resetTaskFuture.cancel(true);
    }

    @Override
    public String toString() {
        return "FixedDelayResetter{" +
                "delay=" + delay +
                '}';
    }

    private class ResetTask implements Runnable {

        @Override
        public void run() {
            try {
                LOGGER.info("计划时间已到, 重置提醒驱动...");
                context.resetRemindDrive();
            } catch (Exception e) {
                String message = "重置器 " + FixedDelayResetter.this +
                        " 执行重置调度时发生异常, 提醒驱动将不会重置, 异常信息如下: ";
                LOGGER.warn(message, e);
            }
        }
    }
}
