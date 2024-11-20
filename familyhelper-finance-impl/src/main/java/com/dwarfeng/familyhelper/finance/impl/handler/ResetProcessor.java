package com.dwarfeng.familyhelper.finance.impl.handler;

import com.dwarfeng.familyhelper.finance.stack.handler.PushHandler;
import com.dwarfeng.familyhelper.finance.stack.handler.RemindDriveHandler;
import com.dwarfeng.familyhelper.finance.stack.handler.RemindDriveLocalCacheHandler;
import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 重置处理器。
 *
 * @author DwArFeng
 * @since 1.4.2
 */
@Component
class ResetProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResetProcessor.class);

    private final RemindDriveHandler remindDriveHandler;
    private final RemindDriveLocalCacheHandler remindDriveLocalCacheHandler;

    private final PushHandler pushHandler;

    private final Lock lock = new ReentrantLock();

    public ResetProcessor(
            RemindDriveHandler remindDriveHandler,
            RemindDriveLocalCacheHandler remindDriveLocalCacheHandler,
            PushHandler pushHandler
    ) {
        this.remindDriveHandler = remindDriveHandler;
        this.remindDriveLocalCacheHandler = remindDriveLocalCacheHandler;
        this.pushHandler = pushHandler;
    }

    public void resetRemindDrive() throws HandlerException {
        lock.lock();
        try {
            doResetRemindDrive();
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        } finally {
            lock.unlock();
        }
    }

    private void doResetRemindDrive() throws HandlerException {
        // 获取当前的提醒驱动处理器的状态。
        boolean started = remindDriveHandler.isStarted();

        // 提醒驱动处理器停止，且清空本地缓存。
        remindDriveHandler.stop();
        remindDriveLocalCacheHandler.clear();

        // 如果提醒驱动处理器之前是启动的，则重新启动。
        if (started) {
            remindDriveHandler.start();
        }

        // 推送提醒驱动重置消息。
        try {
            pushHandler.remindDriveReset();
        } catch (Exception e) {
            LOGGER.warn("推送提醒驱动重置消息时发生异常, 本次消息将不会被推送, 异常信息如下: ", e);
        }
    }
}
