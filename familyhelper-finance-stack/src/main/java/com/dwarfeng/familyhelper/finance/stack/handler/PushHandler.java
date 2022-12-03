package com.dwarfeng.familyhelper.finance.stack.handler;

import com.dwarfeng.familyhelper.finance.stack.bean.dto.RemindInfo;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 推送处理器。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
public interface PushHandler extends Handler {

    /**
     * 提醒动作发生时执行的调度。
     *
     * @throws HandlerException 处理器异常。
     */
    void remindHappened(RemindInfo remindInfo) throws HandlerException;
}
