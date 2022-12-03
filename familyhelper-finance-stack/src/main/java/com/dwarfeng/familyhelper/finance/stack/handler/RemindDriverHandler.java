package com.dwarfeng.familyhelper.finance.stack.handler;

import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 提醒驱动器处理器。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
public interface RemindDriverHandler extends Handler {

    /**
     * 寻找指定的提醒驱动器。
     *
     * @param type 提醒驱动器的类型。
     * @return 符合驱动类型的指定的提醒驱动器。
     * @throws HandlerException 执行器。
     */
    RemindDriver find(String type) throws HandlerException;
}
