package com.dwarfeng.familyhelper.finance.impl.handler;

import com.dwarfeng.familyhelper.finance.stack.bean.dto.AccountBookCreateInfo;
import com.dwarfeng.familyhelper.finance.stack.bean.dto.AccountBookUpdateInfo;
import com.dwarfeng.familyhelper.finance.stack.bean.dto.PermissionRemoveInfo;
import com.dwarfeng.familyhelper.finance.stack.bean.dto.PermissionUpsertInfo;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.AccountBook;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.Poab;
import com.dwarfeng.familyhelper.finance.stack.bean.key.PoabKey;
import com.dwarfeng.familyhelper.finance.stack.exception.AccountBookNotExistsException;
import com.dwarfeng.familyhelper.finance.stack.exception.InvalidPermissionLevelException;
import com.dwarfeng.familyhelper.finance.stack.exception.UserNotExistsException;
import com.dwarfeng.familyhelper.finance.stack.exception.UserNotPermittedException;
import com.dwarfeng.familyhelper.finance.stack.handler.AccountBookOperateHandler;
import com.dwarfeng.familyhelper.finance.stack.service.*;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Component
public class AccountBookOperateHandlerImpl implements AccountBookOperateHandler {

    private final UserMaintainService userMaintainService;
    private final AccountBookMaintainService accountBookMaintainService;
    private final PoabMaintainService poabMaintainService;

    public AccountBookOperateHandlerImpl(
            UserMaintainService userMaintainService, BankCardMaintainService bankCardMaintainService,
            AccountBookMaintainService accountBookMaintainService, PoabMaintainService poabMaintainService,
            BankCardBalanceHistoryMaintainService bankCardBalanceHistoryMaintainService,
            TotalBalanceHistoryMaintainService totalBalanceHistoryMaintainService
    ) {
        this.userMaintainService = userMaintainService;
        this.accountBookMaintainService = accountBookMaintainService;
        this.poabMaintainService = poabMaintainService;
    }

    @Override
    public LongIdKey createAccountBook(StringIdKey userKey, AccountBookCreateInfo accountBookCreateInfo)
            throws HandlerException {
        try {
            // 1. 确认用户存在。
            makeSureUserExists(userKey);

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
                    Poab.PERMISSION_LEVEL_OWNER,
                    "创建账本时自动插入，赋予创建人所有者权限"
            );
            poabMaintainService.insert(poab);

            // 5. 返回生成的主键。
            return accountBookKey;
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public void updateAccountBook(StringIdKey userKey, AccountBookUpdateInfo accountBookUpdateInfo)
            throws HandlerException {
        try {
            LongIdKey accountBookKey = accountBookUpdateInfo.getAccountBookKey();

            // 1. 确认用户存在。
            makeSureUserExists(userKey);

            // 2. 确认账本存在。
            makeSureAccountBookExists(accountBookKey);

            // 3. 确认用户有权限操作指定的账本。
            makeSureUserPermittedForAccountBook(userKey, accountBookKey);

            // 4. 根据 accountBookUpdateInfo 以及更新的规则设置 账本 实体。
            AccountBook accountBook = accountBookMaintainService.get(accountBookKey);
            accountBook.setName(accountBookUpdateInfo.getName());
            accountBook.setRemark(accountBookUpdateInfo.getRemark());

            // 5. 更新账本实体。
            accountBookMaintainService.update(accountBook);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public void removeAccountBook(StringIdKey userKey, LongIdKey accountBookKey) throws HandlerException {
        try {
            // 1. 确认用户存在。
            makeSureUserExists(userKey);

            // 2. 确认账本存在。
            makeSureAccountBookExists(accountBookKey);

            // 3. 确认用户有权限操作指定的账本。
            makeSureUserPermittedForAccountBook(userKey, accountBookKey);

            // 4. 删除指定主键的账本。
            accountBookMaintainService.delete(accountBookKey);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public void upsertPermission(StringIdKey ownerUserKey, PermissionUpsertInfo permissionUpsertInfo)
            throws HandlerException {
        try {
            LongIdKey accountBookKey = permissionUpsertInfo.getAccountBookKey();
            StringIdKey targetUserKey = permissionUpsertInfo.getUserKey();
            int permissionLevel = permissionUpsertInfo.getPermissionLevel();

            // 1. 如果用户主键与目标主键一致，则什么也不做。
            if (Objects.equals(ownerUserKey, targetUserKey)) {
                return;
            }

            // 2. 确认 permissionLevel 有效。
            makeSurePermissionLevelValid(permissionLevel);

            // 3. 确认用户存在。
            makeSureUserExists(ownerUserKey);
            makeSureUserExists(targetUserKey);

            // 4. 确认账本存在。
            makeSureAccountBookExists(accountBookKey);

            // 5. 确认用户有权限操作指定的账本。
            makeSureUserPermittedForAccountBook(ownerUserKey, accountBookKey);

            // 6. 通过入口信息组合权限实体，并进行插入或更新操作。
            String permissionLabel;
            switch (permissionLevel) {
                case Poab.PERMISSION_LEVEL_GUEST:
                    permissionLabel = "目标";
                    break;
                case Poab.PERMISSION_LEVEL_OWNER:
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
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public void removePermission(StringIdKey ownerUserKey, PermissionRemoveInfo permissionRemoveInfo)
            throws HandlerException {
        try {
            LongIdKey accountBookKey = permissionRemoveInfo.getAccountBookKey();
            StringIdKey targetUserKey = permissionRemoveInfo.getUserKey();

            // 1. 如果用户主键与目标主键一致，则什么也不做。
            if (Objects.equals(ownerUserKey, targetUserKey)) {
                return;
            }

            // 2. 确认用户存在。
            makeSureUserExists(ownerUserKey);
            makeSureUserExists(targetUserKey);

            // 3. 确认账本存在。
            makeSureAccountBookExists(accountBookKey);

            // 4. 确认用户有权限操作指定的账本。
            makeSureUserPermittedForAccountBook(ownerUserKey, accountBookKey);

            // 5. 通过入口信息组合权限实体主键，并进行存在删除操作。
            PoabKey poabKey = new PoabKey(accountBookKey.getLongId(), targetUserKey.getStringId());
            poabMaintainService.deleteIfExists(poabKey);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    private void makeSureUserExists(StringIdKey userKey) throws HandlerException {
        try {
            if (!userMaintainService.exists(userKey)) {
                throw new UserNotExistsException(userKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    private void makeSureAccountBookExists(LongIdKey accountBookKey) throws HandlerException {
        try {
            if (!accountBookMaintainService.exists(accountBookKey)) {
                throw new AccountBookNotExistsException(accountBookKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void makeSureUserPermittedForAccountBook(StringIdKey userKey, LongIdKey accountBookKey)
            throws HandlerException {
        try {
            // 1. 构造 Poab 主键。
            PoabKey poabKey = new PoabKey(accountBookKey.getLongId(), userKey.getStringId());

            // 2. 查看 Poab 实体是否存在，如果不存在，则没有权限。
            if (!poabMaintainService.exists(poabKey)) {
                throw new UserNotPermittedException(userKey, accountBookKey);
            }

            // 3. 查看 Poab.permissionLevel 是否为 Poab.PERMISSION_LEVEL_OWNER，如果不是，则没有权限。
            Poab poab = poabMaintainService.get(poabKey);
            if (poab.getPermissionLevel() != Poab.PERMISSION_LEVEL_OWNER) {
                throw new UserNotPermittedException(userKey, accountBookKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    private void makeSurePermissionLevelValid(int permissionLevel) throws HandlerException {
        if (permissionLevel == Poab.PERMISSION_LEVEL_GUEST) {
            return;
        }
        throw new InvalidPermissionLevelException(permissionLevel);
    }
}
