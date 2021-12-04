package com.dwarfeng.familyhelper.finance.impl.handler;

import com.dwarfeng.familyhelper.finance.stack.bean.dto.FundChangeRecordInfo;
import com.dwarfeng.familyhelper.finance.stack.bean.dto.FundChangeUpdateInfo;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.FundChange;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.Poab;
import com.dwarfeng.familyhelper.finance.stack.bean.key.PoabKey;
import com.dwarfeng.familyhelper.finance.stack.exception.*;
import com.dwarfeng.familyhelper.finance.stack.handler.FundChangeOperateHandler;
import com.dwarfeng.familyhelper.finance.stack.service.AccountBookMaintainService;
import com.dwarfeng.familyhelper.finance.stack.service.FundChangeMaintainService;
import com.dwarfeng.familyhelper.finance.stack.service.PoabMaintainService;
import com.dwarfeng.familyhelper.finance.stack.service.UserMaintainService;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

@Component
public class FundChangeOperateHandlerImpl implements FundChangeOperateHandler {

    private final UserMaintainService userMaintainService;
    private final FundChangeMaintainService fundChangeMaintainService;
    private final AccountBookMaintainService accountBookMaintainService;
    private final PoabMaintainService poabMaintainService;

    public FundChangeOperateHandlerImpl(
            UserMaintainService userMaintainService, FundChangeMaintainService fundChangeMaintainService,
            AccountBookMaintainService accountBookMaintainService, PoabMaintainService poabMaintainService
    ) {
        this.userMaintainService = userMaintainService;
        this.fundChangeMaintainService = fundChangeMaintainService;
        this.accountBookMaintainService = accountBookMaintainService;
        this.poabMaintainService = poabMaintainService;
    }

    @Override
    public LongIdKey recordFundChange(
            StringIdKey userKey, LongIdKey accountBookKey, FundChangeRecordInfo fundChangeRecordInfo
    ) throws HandlerException {
        try {
            // 1. 确认用户存在。
            makeSureUserExists(userKey);

            // 2. 确认账本存在。
            makeSureAccountBookExists(accountBookKey);

            // 3. 确认用户有权限操作指定的账本。
            makeSureUserPermittedForAccountBook(userKey, accountBookKey);

            // 4. 根据 fundChangeRecordInfo 以及创建的规则组合 资金变更 实体。
            FundChange fundChange = new FundChange(
                    null, accountBookKey, fundChangeRecordInfo.getDelta(), fundChangeRecordInfo.getChangeType(),
                    new Date(), fundChangeRecordInfo.getRemark()
            );

            // 4. 插入资金变更实体，并返回生成的主键。
            return fundChangeMaintainService.insert(fundChange);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public void updateFundChange(StringIdKey userKey, LongIdKey fundChangeKey, FundChangeUpdateInfo fundChangeUpdateInfo)
            throws HandlerException {
        try {
            // 1. 确认用户存在。
            makeSureUserExists(userKey);

            // 2. 确认资金变更存在。
            makeSureFundChangeExists(fundChangeKey);

            // 3. 确认用户有权限操作指定的资金变更记录。
            makeSureUserPermittedForFundChange(userKey, fundChangeKey);

            // 4. 根据 fundChangeUpdateInfo 以及更新的规则设置 资金变更 实体。
            FundChange fundChange = fundChangeMaintainService.get(fundChangeKey);
            fundChange.setDelta(fundChangeUpdateInfo.getDelta());
            fundChange.setChangeType(fundChangeUpdateInfo.getChangeType());
            fundChange.setRemark(fundChangeUpdateInfo.getRemark());

            // 5. 更新资金变更实体。
            fundChangeMaintainService.update(fundChange);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public void removeFundChange(StringIdKey userKey, LongIdKey fundChangeKey) throws HandlerException {
        try {
            // 1. 确认用户存在。
            makeSureUserExists(userKey);

            // 2. 确认资金变更存在。
            makeSureFundChangeExists(fundChangeKey);

            // 3. 确认用户有权限操作指定的资金变更。
            makeSureUserPermittedForFundChange(userKey, fundChangeKey);

            // 4. 存在删除指定的资金变更。
            fundChangeMaintainService.deleteIfExists(fundChangeKey);
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

    private void makeSureFundChangeExists(LongIdKey fundChangeKey) throws HandlerException {
        try {
            if (!fundChangeMaintainService.exists(fundChangeKey)) {
                throw new FundChangeNotExistsException(fundChangeKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    private void makeSureUserPermittedForFundChange(StringIdKey userKey, LongIdKey fundChangeKey)
            throws HandlerException {
        try {
            // 1. 查找指定的资金变更是否绑定账本，如果不绑定账本，则抛出资金变更状态异常。
            FundChange fundChange = fundChangeMaintainService.get(fundChangeKey);
            if (Objects.isNull(fundChange.getAccountBookKey())) {
                throw new IllegalFundChangeStateException(fundChangeKey);
            }

            // 2. 取出资金变更的账本外键，判断用户是否拥有该账本的权限。
            makeSureUserPermittedForAccountBook(userKey, fundChange.getAccountBookKey());
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
}
