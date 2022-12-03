package com.dwarfeng.familyhelper.finance.stack.handler;

import com.dwarfeng.familyhelper.finance.stack.exception.RemindDriverException;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 提醒处理器。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
public interface RemindHandler extends Handler {

    /**
     * 执行提醒动作。
     *
     * @param remindDriverInfoKey 被触发的提醒驱动信息的主键。
     * @throws RemindDriverException 提醒驱动器异常。
     */
    void remind(LongIdKey remindDriverInfoKey) throws HandlerException;
}
