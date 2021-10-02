package com.dwarfeng.familyhelper.finance.stack.handler;

import com.dwarfeng.familyhelper.finance.stack.bean.dto.AccountBookCreateInfo;
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
     * 添加账本的访客权限。
     *
     * @param ownerUserKey   账本的所有者的主键。
     * @param guestUserKey   访客的主键。
     * @param accountBookKey 账本的主键
     * @throws HandlerException 处理器异常。
     */
    void addGuestPermission(StringIdKey ownerUserKey, StringIdKey guestUserKey, LongIdKey accountBookKey)
            throws HandlerException;

    /**
     * 移除账本的访客权限。
     *
     * @param ownerUserKey   账本的所有者的主键。
     * @param guestUserKey   访客的主键。
     * @param accountBookKey 账本的主键
     * @throws HandlerException 处理器异常。
     */
    void removeGuestPermission(StringIdKey ownerUserKey, StringIdKey guestUserKey, LongIdKey accountBookKey)
            throws HandlerException;
}
