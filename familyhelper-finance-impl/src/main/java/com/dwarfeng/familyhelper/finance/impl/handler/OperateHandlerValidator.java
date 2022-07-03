package com.dwarfeng.familyhelper.finance.impl.handler;

import com.dwarfeng.familyhelper.finance.sdk.util.Constants;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.BankCard;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.FundChange;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.Poab;
import com.dwarfeng.familyhelper.finance.stack.bean.key.PoabKey;
import com.dwarfeng.familyhelper.finance.stack.exception.*;
import com.dwarfeng.familyhelper.finance.stack.service.*;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 操作处理器验证器。
 *
 * <p>
 * 为操作处理器提供公共的验证方法。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
@Component
public class OperateHandlerValidator {

    public static final Logger LOGGER = LoggerFactory.getLogger(OperateHandlerValidator.class);

    private final UserMaintainService userMaintainService;
    private final AccountBookMaintainService accountBookMaintainService;
    private final PoabMaintainService poabMaintainService;
    private final BankCardMaintainService bankCardMaintainService;
    private final FundChangeMaintainService fundChangeMaintainService;

    public OperateHandlerValidator(
            UserMaintainService userMaintainService,
            AccountBookMaintainService accountBookMaintainService,
            PoabMaintainService poabMaintainService,
            BankCardMaintainService bankCardMaintainService,
            FundChangeMaintainService fundChangeMaintainService
    ) {
        this.userMaintainService = userMaintainService;
        this.accountBookMaintainService = accountBookMaintainService;
        this.poabMaintainService = poabMaintainService;
        this.bankCardMaintainService = bankCardMaintainService;
        this.fundChangeMaintainService = fundChangeMaintainService;
    }

    public void makeSureUserExists(StringIdKey userKey) throws HandlerException {
        try {
            if (!userMaintainService.exists(userKey)) {
                throw new UserNotExistsException(userKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureAccountBookExists(LongIdKey accountBookKey) throws HandlerException {
        try {
            if (!accountBookMaintainService.exists(accountBookKey)) {
                throw new AccountBookNotExistsException(accountBookKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    public void makeSureUserPermittedForAccountBook(StringIdKey userKey, LongIdKey accountBookKey)
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
            if (poab.getPermissionLevel() != Constants.PERMISSION_LEVEL_OWNER) {
                throw new UserNotPermittedException(userKey, accountBookKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSurePermissionLevelValid(int permissionLevel) throws HandlerException {
        if (permissionLevel == Constants.PERMISSION_LEVEL_GUEST) {
            return;
        }
        throw new InvalidPermissionLevelException(permissionLevel);
    }

    public void makeSureBankCardExists(LongIdKey bankCardKey) throws HandlerException {
        try {
            if (!bankCardMaintainService.exists(bankCardKey)) {
                throw new BankCardNotExistsException(bankCardKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureUserPermittedForBankCard(StringIdKey userKey, LongIdKey bankCardKey) throws HandlerException {
        try {
            // 1. 查找指定的银行卡是否绑定账本，如果不绑定账本，则抛出银行卡状态异常。
            BankCard bankCard = bankCardMaintainService.get(bankCardKey);
            if (Objects.isNull(bankCard.getAccountBookKey())) {
                throw new IllegalBankCardStateException(bankCardKey);
            }

            // 2. 取出银行卡的账本外键，判断用户是否拥有该账本的权限。
            makeSureUserPermittedForAccountBook(userKey, bankCard.getAccountBookKey());
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureFundChangeExists(LongIdKey fundChangeKey) throws HandlerException {
        try {
            if (!fundChangeMaintainService.exists(fundChangeKey)) {
                throw new FundChangeNotExistsException(fundChangeKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureUserPermittedForFundChange(StringIdKey userKey, LongIdKey fundChangeKey)
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
}
