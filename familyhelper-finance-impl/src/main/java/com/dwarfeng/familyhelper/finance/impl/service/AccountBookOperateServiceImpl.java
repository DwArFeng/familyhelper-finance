package com.dwarfeng.familyhelper.finance.impl.service;

import com.dwarfeng.familyhelper.finance.stack.bean.dto.AccountBookCreateInfo;
import com.dwarfeng.familyhelper.finance.stack.bean.dto.AccountBookPermissionRemoveInfo;
import com.dwarfeng.familyhelper.finance.stack.bean.dto.AccountBookPermissionUpsertInfo;
import com.dwarfeng.familyhelper.finance.stack.bean.dto.AccountBookUpdateInfo;
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
            throw ServiceExceptionHelper.logParse("创建账本时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void updateAccountBook(StringIdKey userKey, AccountBookUpdateInfo accountBookUpdateInfo)
            throws ServiceException {
        try {
            accountBookOperateHandler.updateAccountBook(userKey, accountBookUpdateInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("更新账本时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void removeAccountBook(StringIdKey userKey, LongIdKey accountBookKey) throws ServiceException {
        try {
            accountBookOperateHandler.removeAccountBook(userKey, accountBookKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("删除账本时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void upsertPermission(
            StringIdKey ownerUserKey, AccountBookPermissionUpsertInfo accountBookPermissionUpsertInfo
    ) throws ServiceException {
        try {
            accountBookOperateHandler.upsertPermission(ownerUserKey, accountBookPermissionUpsertInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("添加账本的访客权限时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void removePermission(
            StringIdKey ownerUserKey, AccountBookPermissionRemoveInfo accountBookPermissionRemoveInfo
    ) throws ServiceException {
        try {
            accountBookOperateHandler.removePermission(ownerUserKey, accountBookPermissionRemoveInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("移除账本的访客权限时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
