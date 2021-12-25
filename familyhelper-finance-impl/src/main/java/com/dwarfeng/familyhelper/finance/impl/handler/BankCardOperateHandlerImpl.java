package com.dwarfeng.familyhelper.finance.impl.handler;

import com.dwarfeng.familyhelper.finance.sdk.util.Constants;
import com.dwarfeng.familyhelper.finance.stack.bean.dto.BankCardCreateInfo;
import com.dwarfeng.familyhelper.finance.stack.bean.dto.BankCardUpdateInfo;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.AccountBook;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.BankCard;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.Poab;
import com.dwarfeng.familyhelper.finance.stack.bean.key.PoabKey;
import com.dwarfeng.familyhelper.finance.stack.exception.*;
import com.dwarfeng.familyhelper.finance.stack.handler.BankCardOperateHandler;
import com.dwarfeng.familyhelper.finance.stack.service.AccountBookMaintainService;
import com.dwarfeng.familyhelper.finance.stack.service.BankCardMaintainService;
import com.dwarfeng.familyhelper.finance.stack.service.PoabMaintainService;
import com.dwarfeng.familyhelper.finance.stack.service.UserMaintainService;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Component
public class BankCardOperateHandlerImpl implements BankCardOperateHandler {

    private final UserMaintainService userMaintainService;
    private final BankCardMaintainService bankCardMaintainService;
    private final AccountBookMaintainService accountBookMaintainService;
    private final PoabMaintainService poabMaintainService;

    public BankCardOperateHandlerImpl(
            UserMaintainService userMaintainService, BankCardMaintainService bankCardMaintainService,
            AccountBookMaintainService accountBookMaintainService, PoabMaintainService poabMaintainService
    ) {
        this.userMaintainService = userMaintainService;
        this.bankCardMaintainService = bankCardMaintainService;
        this.accountBookMaintainService = accountBookMaintainService;
        this.poabMaintainService = poabMaintainService;
    }

    @Override
    public LongIdKey createBankCard(StringIdKey userKey, BankCardCreateInfo bankCardCreateInfo)
            throws HandlerException {
        try {
            LongIdKey accountBookKey = bankCardCreateInfo.getAccountBookKey();

            // 1. 确认用户存在。
            makeSureUserExists(userKey);

            // 2. 确认账本存在。
            makeSureAccountBookExists(accountBookKey);

            // 3. 确认用户有权限操作指定的账本。
            makeSureUserPermittedForAccountBook(userKey, accountBookKey);

            // 4. 根据 bankCardCreateInfo 以及创建的规则组合 银行卡 实体。
            BankCard bankCard = new BankCard(
                    null, accountBookKey, bankCardCreateInfo.getName(), bankCardCreateInfo.getCardType(), new Date(),
                    BigDecimal.ZERO, false, new Date(), BigDecimal.ZERO, bankCardCreateInfo.getRemark()
            );

            // 4. 插入银行卡实体，并返回生成的主键。
            return bankCardMaintainService.insert(bankCard);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public void updateBankCard(StringIdKey userKey, BankCardUpdateInfo bankCardUpdateInfo) throws HandlerException {
        try {
            LongIdKey bankCardKey = bankCardUpdateInfo.getBankCardKey();

            // 1. 确认用户存在。
            makeSureUserExists(userKey);

            // 2. 确认银行卡存在。
            makeSureBankCardExists(bankCardKey);

            // 3. 确认用户有权限操作指定的银行卡。
            makeSureUserPermittedForBankCard(userKey, bankCardKey);

            // 4. 根据 bankCardUpdateInfo 以及更新的规则设置 银行卡 实体。
            BankCard bankCard = bankCardMaintainService.get(bankCardKey);
            bankCard.setName(bankCardUpdateInfo.getName());
            bankCard.setCardType(bankCardUpdateInfo.getCardType());
            bankCard.setRemark(bankCardUpdateInfo.getRemark());

            // 5. 更新银行卡实体。
            bankCardMaintainService.update(bankCard);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public void removeBankCard(StringIdKey userKey, LongIdKey bankCardKey) throws HandlerException {
        try {
            // 1. 确认用户存在。
            makeSureUserExists(userKey);

            // 2. 确认银行卡存在。
            makeSureBankCardExists(bankCardKey);

            // 3. 确认用户有权限操作指定的银行卡。
            makeSureUserPermittedForBankCard(userKey, bankCardKey);

            // 4. 获取银行卡所属的账本。
            BankCard bankCard = bankCardMaintainService.get(bankCardKey);
            AccountBook accountBook = accountBookMaintainService.get(bankCard.getAccountBookKey());

            // 5. 存在删除指定的银行卡。
            bankCardMaintainService.deleteIfExists(bankCardKey);

            // 6. 重新统计银行卡的总余额，并更新。
            BigDecimal totalBalance = bankCardMaintainService.lookup(
                    BankCardMaintainService.CHILD_FOR_ACCOUNT_BOOK, new Object[]{accountBook.getKey()}
            ).getData().stream().map(BankCard::getBalanceValue).reduce(BigDecimal.ZERO, BigDecimal::add);
            accountBook.setTotalValue(totalBalance);
            accountBookMaintainService.update(accountBook);
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

    private void makeSureBankCardExists(LongIdKey bankCardKey) throws HandlerException {
        try {
            if (!bankCardMaintainService.exists(bankCardKey)) {
                throw new BankCardNotExistsException(bankCardKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    private void makeSureUserPermittedForBankCard(StringIdKey userKey, LongIdKey bankCardKey) throws HandlerException {
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
            if (poab.getPermissionLevel() != Constants.PERMISSION_LEVEL_OWNER) {
                throw new UserNotPermittedException(userKey, accountBookKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }
}
