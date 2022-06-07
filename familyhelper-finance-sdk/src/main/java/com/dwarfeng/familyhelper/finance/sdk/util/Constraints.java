package com.dwarfeng.familyhelper.finance.sdk.util;

/**
 * 约束类。
 *
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public final class Constraints {

    /**
     * 名称的长度约束。
     */
    public static final int LENGTH_NAME = 50;

    /**
     * 标签的长度约束。
     */
    public static final int LENGTH_LABEL = 50;

    /**
     * 备注的长度约束。
     */
    public static final int LENGTH_REMARK = 100;

    /**
     * 消息的长度约束。
     */
    public static final int LENGTH_MESSAGE = 100;

    /**
     * 过滤器、触发器类型的长度约束。
     */
    public static final int LENGTH_TYPE = 50;

    /**
     * 用户主键的长度约束。
     */
    public static final int LENGTH_USER = 50;

    /**
     * CRON表达式的长度约束。
     */
    public static final int LENGTH_CRON = 30;

    private Constraints() {
        throw new IllegalStateException("禁止实例化");
    }
}
