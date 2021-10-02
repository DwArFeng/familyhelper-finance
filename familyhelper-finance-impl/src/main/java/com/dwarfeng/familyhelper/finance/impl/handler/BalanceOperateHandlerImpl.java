package com.dwarfeng.familyhelper.finance.impl.handler;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.*;
import com.dwarfeng.familyhelper.finance.stack.bean.key.PoabKey;
import com.dwarfeng.familyhelper.finance.stack.exception.*;
import com.dwarfeng.familyhelper.finance.stack.handler.BalanceOperateHandler;
import com.dwarfeng.familyhelper.finance.stack.service.*;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class BalanceOperateHandlerImpl implements BalanceOperateHandler {

    private final UserMaintainService userMaintainService;
    private final BankCardMaintainService bankCardMaintainService;
    private final AccountBookMaintainService accountBookMaintainService;
    private final PoabMaintainService poabMaintainService;
    private final BankCardBalanceHistoryMaintainService bankCardBalanceHistoryMaintainService;
    private final TotalBalanceHistoryMaintainService totalBalanceHistoryMaintainService;

    public BalanceOperateHandlerImpl(
            UserMaintainService userMaintainService, BankCardMaintainService bankCardMaintainService,
            AccountBookMaintainService accountBookMaintainService, PoabMaintainService poabMaintainService,
            BankCardBalanceHistoryMaintainService bankCardBalanceHistoryMaintainService,
            TotalBalanceHistoryMaintainService totalBalanceHistoryMaintainService
    ) {
        this.userMaintainService = userMaintainService;
        this.bankCardMaintainService = bankCardMaintainService;
        this.accountBookMaintainService = accountBookMaintainService;
        this.poabMaintainService = poabMaintainService;
        this.bankCardBalanceHistoryMaintainService = bankCardBalanceHistoryMaintainService;
        this.totalBalanceHistoryMaintainService = totalBalanceHistoryMaintainService;
    }

    @Override
    public void recordBankCardBalance(StringIdKey userKey, LongIdKey bankCardKey, BigDecimal balance) throws HandlerException {
        try {
            // 1. 确认用户存在。
            makeSureUserExists(userKey);

            // 2. 确认银行卡存在。
            makeSureBankCardExists(bankCardKey);

            // 3. 确认用户有权限操作指定的银行卡。
            makeSureUserPermittedForBankCard(userKey, bankCardKey);

            // 4. 将临时区的字段设置为指定的余额。
            BankCard bankCard = bankCardMaintainService.get(bankCardKey);
            bankCard.setTempBalanceValue(balance);
            bankCard.setTempLastRecordedDate(new Date());
            bankCard.setTempFlag(true);
            bankCardMaintainService.update(bankCard);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public void recordCommit(StringIdKey userKey, LongIdKey accountBookKey) throws HandlerException {
        try {
            // 1. 确认用户存在。
            makeSureUserExists(userKey);

            // 2. 确认账本存在。
            makeSureAccountBookExists(accountBookKey);

            // 3. 确认用户有权限操作指定的账本。
            makeSureUserPermittedForAccountBook(userKey, accountBookKey);

            // 4. 查询账本下的所有银行卡。
            List<BankCard> bankCards = bankCardMaintainService.lookup(
                    BankCardMaintainService.CHILD_FOR_ACCOUNT_BOOK, new Object[]{accountBookKey}
            ).getData();

            // 5. 将每个银行卡的临时区的余额写入到正式区，并将每个银行卡设置临时使能为 false。
            for (BankCard bankCard : bankCards) {
                bankCard.setBalanceValue(bankCard.getBalanceValue());
                bankCard.setLastRecordedDate(bankCard.getLastRecordedDate());
                bankCard.setTempFlag(false);
            }

            // 6. 累加所有的银行卡的余额, 算出总余额，记录到账本的总余额中。
            AccountBook accountBook = accountBookMaintainService.get(accountBookKey);
            BigDecimal totalBalance = BigDecimal.ZERO;
            for (BankCard bankCard : bankCards) {
                totalBalance = totalBalance.add(bankCard.getBalanceValue());
            }
            accountBook.setTotalValue(totalBalance);
            accountBook.setLastRecordedDate(new Date());
            accountBookMaintainService.update(accountBook);

            // 7. 创建余额记录历史。
            List<BankCardBalanceHistory> bankCardBalanceHistories = bankCards.stream().map(
                    b -> new BankCardBalanceHistory(
                            null, b.getKey(), b.getBalanceValue(), b.getLastRecordedDate(),
                            "记录提交时自动创建的银行卡余额历史"
                    )
            ).collect(Collectors.toList());
            bankCardBalanceHistoryMaintainService.batchInsert(bankCardBalanceHistories);
            TotalBalanceHistory totalBalanceHistory = new TotalBalanceHistory(
                    null, accountBookKey, accountBook.getTotalValue(), accountBook.getLastRecordedDate(),
                    "记录提交时自动创建的银行卡余额历史"
            );
            totalBalanceHistoryMaintainService.insert(totalBalanceHistory);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public void rollbackBankCard(StringIdKey userKey, LongIdKey bankCardKey) throws HandlerException {
        try {
            // 1. 确认用户存在。
            makeSureUserExists(userKey);

            // 2. 确认银行卡存在。
            makeSureBankCardExists(bankCardKey);

            // 3. 确认用户有权限操作指定的银行卡。
            makeSureUserPermittedForBankCard(userKey, bankCardKey);

            // 4. 设置临时使能为 false。
            BankCard bankCard = bankCardMaintainService.get(bankCardKey);
            bankCard.setTempFlag(false);
            bankCardMaintainService.update(bankCard);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public void rollbackAll(StringIdKey userKey, LongIdKey accountBookKey) throws HandlerException {
        try {
            // 1. 确认用户存在。
            makeSureUserExists(userKey);

            // 2. 确认账本存在。
            makeSureAccountBookExists(accountBookKey);

            // 3. 确认用户有权限操作指定的账本。
            makeSureUserPermittedForAccountBook(userKey, accountBookKey);

            // 4. 查询账本下的所有银行卡，将每个银行卡设置临时使能为 false。
            List<BankCard> bankCards = bankCardMaintainService.lookup(
                    BankCardMaintainService.CHILD_FOR_ACCOUNT_BOOK, new Object[]{accountBookKey}
            ).getData();
            for (BankCard bankCard : bankCards) {
                bankCard.setTempFlag(false);
            }
            bankCardMaintainService.batchUpdate(bankCards);
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

    private void makeSureBankCardExists(LongIdKey bankCardKey) throws HandlerException {
        try {
            if (!bankCardMaintainService.exists(bankCardKey)) {
                throw new BankCardNotExistsException(bankCardKey);
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
            if (poab.getPermissionLevel() != Poab.PERMISSION_LEVEL_OWNER) {
                throw new UserNotPermittedException(userKey, accountBookKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }
}
