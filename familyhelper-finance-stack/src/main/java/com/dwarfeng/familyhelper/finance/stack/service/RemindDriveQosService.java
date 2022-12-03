package com.dwarfeng.familyhelper.finance.stack.service;

import com.dwarfeng.familyhelper.finance.stack.handler.RemindDriver;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

/**
 * 提醒驱动 QOS 服务。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
public interface RemindDriveQosService extends Service {

    /**
     * 提醒驱动服务是否上线。
     *
     * @return 是否上线。
     * @throws ServiceException 服务异常。
     */
    boolean isOnline() throws ServiceException;

    /**
     * 上线提醒驱动服务。
     *
     * @throws ServiceException 服务异常。
     */
    void online() throws ServiceException;

    /**
     * 下线提醒驱动服务。
     *
     * @throws ServiceException 服务异常。
     */
    void offline() throws ServiceException;

    /**
     * 提醒驱动服务是否正在持有锁。
     *
     * @return 提醒驱动服务是否正在持有锁。
     * @throws ServiceException 服务异常。
     */
    boolean isLockHolding() throws ServiceException;

    /**
     * 提醒驱动服务是否启动。
     *
     * @return 提醒驱动服务是否启动。
     * @throws ServiceException 服务异常。
     */
    boolean isStarted() throws ServiceException;

    /**
     * 提醒驱动服务启动。
     *
     * @throws ServiceException 服务异常。
     */
    void start() throws ServiceException;

    /**
     * 提醒驱动服务停止。
     *
     * @throws ServiceException 服务异常。
     */
    void stop() throws ServiceException;

    /**
     * 提醒驱动服务是否正在工作。
     *
     * @return 提醒驱动服务是否正在工作。
     * @throws ServiceException 服务异常。
     */
    boolean isWorking() throws ServiceException;

    /**
     * 获取指定的提醒驱动器信息对应的提醒驱动器。
     *
     * @param remindDriverInfoKey 指定的提醒驱动器信息对应的主键。
     * @return 指定的提醒驱动器信息对应的提醒驱动器。
     * @throws ServiceException 服务异常。
     */
    RemindDriver getDriver(LongIdKey remindDriverInfoKey) throws ServiceException;

    /**
     * 清除本地缓存。
     *
     * @throws ServiceException 服务异常。
     */
    void clearLocalCache() throws ServiceException;
}
