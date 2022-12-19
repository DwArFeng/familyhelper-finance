package com.dwarfeng.familyhelper.finance.stack.handler;

import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.StartableHandler;

/**
 * 重置处理器。
 *
 * @author DwArFeng
 * @since 1.4.2
 */
public interface ResetHandler extends StartableHandler {

    /**
     * 重置提醒驱动。
     *
     * @throws HandlerException 处理器异常。
     */
    void resetRemindDrive() throws HandlerException;
}
