package com.dwarfeng.familyhelper.finance.impl.handler;

import com.dwarfeng.familyhelper.finance.stack.handler.RemindDriveHandler;
import com.dwarfeng.familyhelper.finance.stack.handler.RemindDriveLocalCacheHandler;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;

/**
 * 重置处理器。
 *
 * @author DwArFeng
 * @since 1.4.2
 */
@Component
class ResetProcessor {

    private final RemindDriveHandler remindDriveHandler;
    private final RemindDriveLocalCacheHandler remindDriveLocalCacheHandler;

    public ResetProcessor(
            RemindDriveHandler remindDriveHandler,
            RemindDriveLocalCacheHandler remindDriveLocalCacheHandler
    ) {
        this.remindDriveHandler = remindDriveHandler;
        this.remindDriveLocalCacheHandler = remindDriveLocalCacheHandler;
    }

    public void resetRemindDrive() throws HandlerException {
        remindDriveHandler.stop();
        remindDriveLocalCacheHandler.clear();
        remindDriveHandler.start();
    }
}
