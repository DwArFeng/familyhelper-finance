package com.dwarfeng.familyhelper.finance.stack.handler;

import com.dwarfeng.familyhelper.finance.stack.bean.dto.AccountBookCreateInfo;
import com.dwarfeng.familyhelper.finance.stack.bean.dto.AccountBookPermissionRemoveInfo;
import com.dwarfeng.familyhelper.finance.stack.bean.dto.AccountBookPermissionUpsertInfo;
import com.dwarfeng.familyhelper.finance.stack.bean.dto.AccountBookUpdateInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 账本处理器。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public interface AccountBookOperateHandler extends Handler {

    /**
     * 创建账本。
     *
     * @param userKey               账本的所有者的主键。
     * @param accountBookCreateInfo 账本的创建信息。
     * @return 生成的账本的主键。
     * @throws HandlerException 处理器异常。
     */
    LongIdKey createAccountBook(StringIdKey userKey, AccountBookCreateInfo accountBookCreateInfo)
            throws HandlerException;

    /**
     * 更新账本。
     *
     * @param userKey               账本的所有者的主键。
     * @param accountBookUpdateInfo 账本的更新信息。
     * @throws HandlerException 处理器异常。
     */
    void updateAccountBook(StringIdKey userKey, AccountBookUpdateInfo accountBookUpdateInfo) throws HandlerException;

    /**
     * 删除账本。
     *
     * @param userKey        账本的所有者的主键。
     * @param accountBookKey 账本的主键。
     * @throws HandlerException 处理器异常。
     */
    void removeAccountBook(StringIdKey userKey, LongIdKey accountBookKey) throws HandlerException;

    /**
     * 添加或更新账本的访客权限。
     *
     * @param ownerUserKey                    操作者的主键。
     * @param accountBookPermissionUpsertInfo 权限添加信息。
     * @throws HandlerException 处理器异常。
     */
    void upsertPermission(
            StringIdKey ownerUserKey, AccountBookPermissionUpsertInfo accountBookPermissionUpsertInfo
    ) throws HandlerException;

    /**
     * 移除账本的访客权限。
     *
     * @param ownerUserKey                    操作者的主键。
     * @param accountBookPermissionRemoveInfo 权限移除信息。
     * @throws HandlerException 处理器异常。
     */
    void removePermission(
            StringIdKey ownerUserKey, AccountBookPermissionRemoveInfo accountBookPermissionRemoveInfo
    ) throws HandlerException;
}
