package com.dwarfeng.familyhelper.finance.stack.handler;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.RemindDriverInfo;
import com.dwarfeng.familyhelper.finance.stack.exception.RemindDriverException;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 提醒驱动器。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
public interface RemindDriver {

    /**
     * 初始化执行服务。
     *
     * <p>
     * 该方法会在驱动器初始化后调用，请将 context 存放在驱动器的字段中。<br>
     * 当驱动器被触发后，执行 {@link Context#remind(LongIdKey)} 方法即可。
     *
     * @param context 驱动器的上下文。
     */
    void init(Context context);

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

    /**
     * 提醒驱动器上下文。
     *
     * @author DwArFeng
     * @since 1.4.0
     */
    interface Context {

        /**
         * 执行提醒动作。
         *
         * @param remindDriverInfoKey 被触发的提醒驱动信息的主键。
         * @throws RemindDriverException 提醒驱动器异常。
         */
        void remind(LongIdKey remindDriverInfoKey) throws RemindDriverException;
    }
}
