package com.dwarfeng.familyhelper.finance.stack.service;

import com.dwarfeng.familyhelper.finance.stack.bean.dto.AccountBookCreateInfo;
import com.dwarfeng.familyhelper.finance.stack.bean.dto.AccountBookUpdateInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

import java.util.Collection;

/**
 * 账本操作服务。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public interface AccountBookOperateService extends Service {

    /**
     * 创建账本。
     *
     * @param userKey               账本的所有者的主键。
     * @param accountBookCreateInfo 账本的创建信息。
     * @return 生成的账本的主键。
     * @throws ServiceException 服务异常。
     */
    LongIdKey createAccountBook(StringIdKey userKey, AccountBookCreateInfo accountBookCreateInfo)
            throws ServiceException;

    /**
     * 更新账本。
     *
     * @param userKey               账本的所有者的主键。
     * @param accountBookKey        账本的主键。
     * @param accountBookUpdateInfo 账本的更新信息。
     * @throws ServiceException 服务异常。
     */
    void updateAccountBook(
            StringIdKey userKey, LongIdKey accountBookKey, AccountBookUpdateInfo accountBookUpdateInfo
    ) throws ServiceException;

    /**
     * 删除账本。
     *
     * @param userKey        账本的所有者的主键。
     * @param accountBookKey 账本的主键。
     * @throws ServiceException 服务异常。
     */
    void removeAccountBook(StringIdKey userKey, LongIdKey accountBookKey) throws ServiceException;

    /**
     * 添加账本的访客权限。
     *
     * @param ownerUserKey   账本的所有者的主键。
     * @param accountBookKey 账本的主键
     * @param guestUserKey   访客的主键。
     * @throws ServiceException 服务异常。
     */
    void addGuestPermission(StringIdKey ownerUserKey, LongIdKey accountBookKey, StringIdKey guestUserKey)
            throws ServiceException;

    /**
     * 移除账本的访客权限。
     *
     * @param ownerUserKey   账本的所有者的主键。
     * @param accountBookKey 账本的主键
     * @param guestUserKey   访客的主键。
     * @throws ServiceException 服务异常。
     */
    void removeGuestPermission(StringIdKey ownerUserKey, LongIdKey accountBookKey, StringIdKey guestUserKey)
            throws ServiceException;

    /**
     * 重置账本的访客权限。
     *
     * @param ownerUserKey   账本的所有者的主键。
     * @param accountBookKey 账本的主键
     * @param guestUserKeys  访客的主键组成的集合。
     * @throws ServiceException 服务异常。
     */
    void resetGuestPermission(StringIdKey ownerUserKey, LongIdKey accountBookKey, Collection<StringIdKey> guestUserKeys)
            throws ServiceException;
}
