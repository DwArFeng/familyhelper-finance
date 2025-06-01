package com.dwarfeng.familyhelper.finance.sdk.handler;

import com.dwarfeng.familyhelper.finance.stack.handler.RemindDriver;

/**
 * 提醒驱动器提供器。
 *
 * @author DwArFeng
 * @since 1.6.0
 */
public interface RemindDriverProvider {

    /**
     * 返回提供器是否支持指定的类型。
     *
     * @param type 指定的类型。
     * @return 提供器是否支持指定的类型。
     */
    boolean supportType(String type);

    /**
     * 提供提醒驱动器。
     *
     * @return 被提供的提醒驱动器。
     */
    RemindDriver provide();
}
