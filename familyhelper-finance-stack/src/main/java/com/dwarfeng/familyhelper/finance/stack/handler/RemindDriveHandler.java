package com.dwarfeng.familyhelper.finance.stack.handler;

import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 提醒驱动处理器。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
public interface RemindDriveHandler {

    /**
     * 提醒驱动处理器是否启动。
     *
     * @return 提醒驱动处理器是否启动。
     * @throws HandlerException 处理器异常。
     */
    boolean isStarted() throws HandlerException;

    /**
     * 开启提醒驱动处理器。
     *
     * @throws HandlerException 处理器异常。
     */
    void start() throws HandlerException;

    /**
     * 关闭提醒驱动处理器。
     *
     * @throws HandlerException 处理器异常。
     */
    void stop() throws HandlerException;

    /**
     * 提醒驱动处理器是否为领导者。
     *
     * @return 提醒驱动处理器是否为领导者。
     * @throws HandlerException 处理器异常。
     */
    boolean isLeader() throws HandlerException;


    /**
     * 重新加载数据。
     *
     * @throws HandlerException 处理器异常。
     */
    void reload() throws HandlerException;
}
