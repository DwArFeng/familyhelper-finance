package com.dwarfeng.familyhelper.finance.stack.service;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

/**
 * 提醒服务。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
public interface RemindService extends Service {

    /**
     * 执行提醒动作。
     *
     * @param remindDriverInfoKey 被触发的提醒驱动信息的主键。
     * @throws ServiceException 服务异常。
     */
    void remind(LongIdKey remindDriverInfoKey) throws ServiceException;
}
