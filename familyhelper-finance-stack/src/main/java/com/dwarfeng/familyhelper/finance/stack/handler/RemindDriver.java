package com.dwarfeng.familyhelper.finance.stack.handler;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.RemindDriverInfo;
import com.dwarfeng.familyhelper.finance.stack.exception.RemindDriverException;

/**
 * 提醒驱动器。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
public interface RemindDriver {

    /**
     * 注册指定的提醒驱动器信息。
     *
     * @param remindDriverInfo 指定的提醒驱动器信息。
     * @throws RemindDriverException 提醒驱动器异常。
     */
    void register(RemindDriverInfo remindDriverInfo) throws RemindDriverException;

    /**
     * 解除注册所有的提醒驱动器信息。
     *
     * @throws RemindDriverException 提醒驱动器异常。
     */
    void unregisterAll() throws RemindDriverException;
}
