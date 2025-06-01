package com.dwarfeng.familyhelper.finance.sdk.handler;

/**
 * 提醒驱动器支持器。
 *
 * @author DwArFeng
 * @since 1.6.0
 */
public interface RemindDriverSupporter {

    /**
     * 提供类型。
     *
     * @return 类型。
     */
    String provideType();

    /**
     * 提供标签。
     *
     * @return 标签。
     */
    String provideLabel();

    /**
     * 提供描述。
     *
     * @return 描述。
     */
    String provideDescription();

    /**
     * 提供示例参数。
     *
     * @return 示例参数。
     */
    String provideExampleParam();
}
