package com.dwarfeng.familyhelper.finance.stack.service;

import com.dwarfeng.familyhelper.finance.stack.bean.dto.AccountBookCreateInfo;
import com.dwarfeng.familyhelper.finance.stack.bean.dto.AccountBookUpdateInfo;
import com.dwarfeng.familyhelper.finance.stack.bean.dto.PermissionCreateInfo;
import com.dwarfeng.familyhelper.finance.stack.bean.dto.PermissionRemoveInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

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
     * @param userKey               操作者的主键。
     * @param accountBookCreateInfo 账本的创建信息。
     * @return 生成的账本的主键。
     * @throws ServiceException 服务异常。
     */
    LongIdKey createAccountBook(StringIdKey userKey, AccountBookCreateInfo accountBookCreateInfo)
            throws ServiceException;

    /**
     * 更新账本。
     *
     * @param userKey               操作者的主键。
     * @param accountBookUpdateInfo 账本的更新信息。
     * @throws ServiceException 服务异常。
     */
    void updateAccountBook(StringIdKey userKey, AccountBookUpdateInfo accountBookUpdateInfo) throws ServiceException;

    /**
     * 删除账本。
     *
     * @param userKey        操作者的主键。
     * @param accountBookKey 账本的主键。
     * @throws ServiceException 服务异常。
     */
    void removeAccountBook(StringIdKey userKey, LongIdKey accountBookKey) throws ServiceException;

    /**
     * 添加账本的访客权限。
     *
     * @param ownerUserKey         操作者的主键。
     * @param permissionCreateInfo 权限添加信息。
     * @throws ServiceException 服务异常。
     */
    void addPermission(StringIdKey ownerUserKey, PermissionCreateInfo permissionCreateInfo) throws ServiceException;

    /**
     * 移除账本的访客权限。
     *
     * @param ownerUserKey         操作者的主键。
     * @param permissionRemoveInfo 权限移除信息。
     * @throws ServiceException 服务异常。
     */
    void removePermission(StringIdKey ownerUserKey, PermissionRemoveInfo permissionRemoveInfo) throws ServiceException;
}
