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
        remindDriveHandler.stop();
        remindDriveLocalCacheHandler.clear();
        remindDriveHandler.start();

        try {
            pushHandler.remindDriveReset();
        } catch (Exception e) {
            LOGGER.warn("推送提醒驱动重置消息时发生异常, 本次消息将不会被推送, 异常信息如下: ", e);
        }
    }
}
