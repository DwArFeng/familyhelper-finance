package com.dwarfeng.familyhelper.finance.impl.service;

import com.dwarfeng.familyhelper.finance.stack.bean.dto.AccountBookCreateInfo;
import com.dwarfeng.familyhelper.finance.stack.bean.dto.AccountBookUpdateInfo;
import com.dwarfeng.familyhelper.finance.stack.bean.dto.PermissionCreateInfo;
import com.dwarfeng.familyhelper.finance.stack.bean.dto.PermissionRemoveInfo;
import com.dwarfeng.familyhelper.finance.stack.handler.AccountBookOperateHandler;
import com.dwarfeng.familyhelper.finance.stack.service.AccountBookOperateService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.stereotype.Service;

@Service
public class AccountBookOperateServiceImpl implements AccountBookOperateService {

    private final AccountBookOperateHandler accountBookOperateHandler;

    private final ServiceExceptionMapper sem;

    public AccountBookOperateServiceImpl(
            AccountBookOperateHandler accountBookOperateHandler, ServiceExceptionMapper sem
    ) {
        this.accountBookOperateHandler = accountBookOperateHandler;
        this.sem = sem;
    }

    @Override
    public LongIdKey createAccountBook(StringIdKey userKey, AccountBookCreateInfo accountBookCreateInfo)
            throws ServiceException {
        try {
            return accountBookOperateHandler.createAccountBook(userKey, accountBookCreateInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("创建账本时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    public void updateAccountBook(StringIdKey userKey, AccountBookUpdateInfo accountBookUpdateInfo)
            throws ServiceException {
        try {
            accountBookOperateHandler.updateAccountBook(userKey, accountBookUpdateInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("更新账本时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    public void removeAccountBook(StringIdKey userKey, LongIdKey accountBookKey) throws ServiceException {
        try {
            accountBookOperateHandler.removeAccountBook(userKey, accountBookKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("删除账本时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    public void addPermission(StringIdKey ownerUserKey, PermissionCreateInfo permissionCreateInfo)
            throws ServiceException {
        try {
            accountBookOperateHandler.addPermission(ownerUserKey, permissionCreateInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("添加账本的访客权限时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    public void removePermission(StringIdKey ownerUserKey, PermissionRemoveInfo permissionRemoveInfo)
            throws ServiceException {
        try {
            accountBookOperateHandler.removePermission(ownerUserKey, permissionRemoveInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("移除账本的访客权限时发生异常", LogLevel.WARN, sem, e);
        }
    }
}
