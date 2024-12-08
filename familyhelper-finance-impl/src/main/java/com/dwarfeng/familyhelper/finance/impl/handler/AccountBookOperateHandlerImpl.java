package com.dwarfeng.familyhelper.finance.impl.handler;

import com.dwarfeng.familyhelper.finance.sdk.util.Constants;
import com.dwarfeng.familyhelper.finance.stack.bean.dto.AccountBookCreateInfo;
import com.dwarfeng.familyhelper.finance.stack.bean.dto.AccountBookPermissionRemoveInfo;
import com.dwarfeng.familyhelper.finance.stack.bean.dto.AccountBookPermissionUpsertInfo;
import com.dwarfeng.familyhelper.finance.stack.bean.dto.AccountBookUpdateInfo;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.AccountBook;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.Poab;
import com.dwarfeng.familyhelper.finance.stack.bean.key.PoabKey;
import com.dwarfeng.familyhelper.finance.stack.handler.AccountBookOperateHandler;
import com.dwarfeng.familyhelper.finance.stack.service.AccountBookMaintainService;
import com.dwarfeng.familyhelper.finance.stack.service.PoabMaintainService;
import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Component
public class AccountBookOperateHandlerImpl implements AccountBookOperateHandler {

    private final AccountBookMaintainService accountBookMaintainService;
    private final PoabMaintainService poabMaintainService;

    private final HandlerValidator handlerValidator;

    public AccountBookOperateHandlerImpl(
            AccountBookMaintainService accountBookMaintainService,
            PoabMaintainService poabMaintainService,
            HandlerValidator handlerValidator
    ) {
        this.accountBookMaintainService = accountBookMaintainService;
        this.poabMaintainService = poabMaintainService;
        this.handlerValidator = handlerValidator;
    }

    @Override
    public LongIdKey createAccountBook(StringIdKey userKey, AccountBookCreateInfo accountBookCreateInfo)
            throws HandlerException {
        try {
            // 1. 确认用户存在。
            handlerValidator.makeSureUserExists(userKey);

            // 2. 根据 accountBookCreateInfo 以及创建的规则组合 账本 实体。
            AccountBook accountBook = new AccountBook(
                    null, accountBookCreateInfo.getName(), new Date(), BigDecimal.ZERO,
                    accountBookCreateInfo.getRemark()
            );

            // 3. 插入账本实体，并获取生成的主键。
            LongIdKey accountBookKey = accountBookMaintainService.insert(accountBook);

            // 4. 由账本实体生成的主键和用户主键组合权限信息，并插入。
            Poab poab = new Poab(
                    new PoabKey(accountBookKey.getLongId(), userKey.getStringId()),
                    Constants.PERMISSION_LEVEL_OWNER,
                    "创建账本时自动插入，赋予创建人所有者权限"
            );
            poabMaintainService.insert(poab);

            // 5. 返回生成的主键。
            return accountBookKey;
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public void updateAccountBook(StringIdKey userKey, AccountBookUpdateInfo accountBookUpdateInfo)
            throws HandlerException {
        try {
            LongIdKey accountBookKey = accountBookUpdateInfo.getAccountBookKey();

            // 1. 确认用户存在。
            handlerValidator.makeSureUserExists(userKey);

            // 2. 确认账本存在。
            handlerValidator.makeSureAccountBookExists(accountBookKey);

            // 3. 确认用户有权限操作指定的账本。
            handlerValidator.makeSureUserModifyPermittedForAccountBook(userKey, accountBookKey);

            // 4. 根据 accountBookUpdateInfo 以及更新的规则设置 账本 实体。
            AccountBook accountBook = accountBookMaintainService.get(accountBookKey);
            accountBook.setName(accountBookUpdateInfo.getName());
            accountBook.setRemark(accountBookUpdateInfo.getRemark());

            // 5. 更新账本实体。
            accountBookMaintainService.update(accountBook);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public void removeAccountBook(StringIdKey userKey, LongIdKey accountBookKey) throws HandlerException {
        try {
            // 1. 确认用户存在。
            handlerValidator.makeSureUserExists(userKey);

            // 2. 确认账本存在。
            handlerValidator.makeSureAccountBookExists(accountBookKey);

            // 3. 确认用户有权限操作指定的账本。
            handlerValidator.makeSureUserModifyPermittedForAccountBook(userKey, accountBookKey);

            // 4. 删除指定主键的账本。
            accountBookMaintainService.delete(accountBookKey);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    // 为了程序的可读性，此处不做代码提取。
    @SuppressWarnings("ExtractMethodRecommender")
    @Override
    public void upsertPermission(
            StringIdKey ownerUserKey, AccountBookPermissionUpsertInfo accountBookPermissionUpsertInfo
    ) throws HandlerException {
        try {
            LongIdKey accountBookKey = accountBookPermissionUpsertInfo.getAccountBookKey();
            StringIdKey targetUserKey = accountBookPermissionUpsertInfo.getUserKey();
            int permissionLevel = accountBookPermissionUpsertInfo.getPermissionLevel();

            // 1. 如果用户主键与目标主键一致，则什么也不做。
            if (Objects.equals(ownerUserKey, targetUserKey)) {
                return;
            }

            // 2. 确认 permissionLevel 有效。
            handlerValidator.makeSurePermissionLevelValid(permissionLevel);

            // 3. 确认用户存在。
            handlerValidator.makeSureUserExists(ownerUserKey);
            handlerValidator.makeSureUserExists(targetUserKey);

            // 4. 确认账本存在。
            handlerValidator.makeSureAccountBookExists(accountBookKey);

            // 5. 确认用户有权限操作指定的账本。
            handlerValidator.makeSureUserModifyPermittedForAccountBook(ownerUserKey, accountBookKey);

            // 6. 通过入口信息组合权限实体，并进行插入或更新操作。
            String permissionLabel;
            switch (permissionLevel) {
                case Constants.PERMISSION_LEVEL_GUEST:
                    permissionLabel = "目标";
                    break;
                case Constants.PERMISSION_LEVEL_OWNER:
                    permissionLabel = "所有者";
                    break;
                default:
                    permissionLabel = "（未知）";
            }
            Poab poab = new Poab(
                    new PoabKey(accountBookKey.getLongId(), targetUserKey.getStringId()),
                    permissionLevel,
                    "赋予用户 " + targetUserKey.getStringId() + " " + permissionLabel + "权限"
            );
            poabMaintainService.insertOrUpdate(poab);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public void removePermission(
            StringIdKey ownerUserKey, AccountBookPermissionRemoveInfo accountBookPermissionRemoveInfo
    ) throws HandlerException {
        try {
            LongIdKey accountBookKey = accountBookPermissionRemoveInfo.getAccountBookKey();
            StringIdKey targetUserKey = accountBookPermissionRemoveInfo.getUserKey();

            // 1. 如果用户主键与目标主键一致，则什么也不做。
            if (Objects.equals(ownerUserKey, targetUserKey)) {
                return;
            }

            // 2. 确认用户存在。
            handlerValidator.makeSureUserExists(ownerUserKey);
            handlerValidator.makeSureUserExists(targetUserKey);

            // 3. 确认账本存在。
            handlerValidator.makeSureAccountBookExists(accountBookKey);

            // 4. 确认用户有权限操作指定的账本。
            handlerValidator.makeSureUserModifyPermittedForAccountBook(ownerUserKey, accountBookKey);

            // 5. 通过入口信息组合权限实体主键，并进行存在删除操作。
            PoabKey poabKey = new PoabKey(accountBookKey.getLongId(), targetUserKey.getStringId());
            poabMaintainService.deleteIfExists(poabKey);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }
}
