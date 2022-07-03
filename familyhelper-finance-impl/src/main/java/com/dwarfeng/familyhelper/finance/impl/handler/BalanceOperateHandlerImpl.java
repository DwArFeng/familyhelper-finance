package com.dwarfeng.familyhelper.finance.impl.handler;

import com.dwarfeng.familyhelper.finance.stack.bean.dto.BankCardBalanceRecordInfo;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.AccountBook;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.BankCard;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.BankCardBalanceHistory;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.TotalBalanceHistory;
import com.dwarfeng.familyhelper.finance.stack.handler.BalanceOperateHandler;
import com.dwarfeng.familyhelper.finance.stack.service.AccountBookMaintainService;
import com.dwarfeng.familyhelper.finance.stack.service.BankCardBalanceHistoryMaintainService;
import com.dwarfeng.familyhelper.finance.stack.service.BankCardMaintainService;
import com.dwarfeng.familyhelper.finance.stack.service.TotalBalanceHistoryMaintainService;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BalanceOperateHandlerImpl implements BalanceOperateHandler {

    private final BankCardMaintainService bankCardMaintainService;
    private final AccountBookMaintainService accountBookMaintainService;
    private final BankCardBalanceHistoryMaintainService bankCardBalanceHistoryMaintainService;
    private final TotalBalanceHistoryMaintainService totalBalanceHistoryMaintainService;

    private final OperateHandlerValidator operateHandlerValidator;

    public BalanceOperateHandlerImpl(
            BankCardMaintainService bankCardMaintainService,
            AccountBookMaintainService accountBookMaintainService,
            BankCardBalanceHistoryMaintainService bankCardBalanceHistoryMaintainService,
            TotalBalanceHistoryMaintainService totalBalanceHistoryMaintainService,
            OperateHandlerValidator operateHandlerValidator
    ) {
        this.bankCardMaintainService = bankCardMaintainService;
        this.accountBookMaintainService = accountBookMaintainService;
        this.bankCardBalanceHistoryMaintainService = bankCardBalanceHistoryMaintainService;
        this.totalBalanceHistoryMaintainService = totalBalanceHistoryMaintainService;
        this.operateHandlerValidator = operateHandlerValidator;
    }

    @Override
    public void recordBankCardBalance(StringIdKey userKey, BankCardBalanceRecordInfo bankCardBalanceRecordInfo)
            throws HandlerException {
        try {
            LongIdKey bankCardKey = bankCardBalanceRecordInfo.getBankCardKey();
            BigDecimal balance = bankCardBalanceRecordInfo.getBalance();

            // 1. 确认用户存在。
            operateHandlerValidator.makeSureUserExists(userKey);

            // 2. 确认银行卡存在。
            operateHandlerValidator.makeSureBankCardExists(bankCardKey);

            // 3. 确认用户有权限操作指定的银行卡。
            operateHandlerValidator.makeSureUserPermittedForBankCard(userKey, bankCardKey);

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
            operateHandlerValidator.makeSureUserExists(userKey);

            // 2. 确认账本存在。
            operateHandlerValidator.makeSureAccountBookExists(accountBookKey);

            // 3. 确认用户有权限操作指定的账本。
            operateHandlerValidator.makeSureUserPermittedForAccountBook(userKey, accountBookKey);

            // 4. 查询账本下的所有银行卡。
            List<BankCard> bankCards = bankCardMaintainService.lookup(
                    BankCardMaintainService.CHILD_FOR_ACCOUNT_BOOK, new Object[]{accountBookKey}
            ).getData();

            // 5. 如果银行的临时设置使能是 true，则将每个银行卡的临时区的余额写入到正式区，
            // 并将每个银行卡设置临时使能为 false，并且将这些临时设置使能是 true 的银行卡记录在一个列表中，供记录历史使用。
            List<BankCard> bankCardToRecordHistory = new ArrayList<>();
            for (BankCard bankCard : bankCards) {
                if (bankCard.isTempFlag()) {
                    bankCardToRecordHistory.add(bankCard);
                    bankCard.setBalanceValue(bankCard.getTempBalanceValue());
                    bankCard.setLastRecordedDate(bankCard.getTempLastRecordedDate());
                    bankCard.setTempFlag(false);
                }
            }
            bankCardMaintainService.batchUpdate(bankCards);

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
            List<BankCardBalanceHistory> bankCardBalanceHistories = bankCardToRecordHistory.stream().map(
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
            operateHandlerValidator.makeSureUserExists(userKey);

            // 2. 确认银行卡存在。
            operateHandlerValidator.makeSureBankCardExists(bankCardKey);

            // 3. 确认用户有权限操作指定的银行卡。
            operateHandlerValidator.makeSureUserPermittedForBankCard(userKey, bankCardKey);

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
            operateHandlerValidator.makeSureUserExists(userKey);

            // 2. 确认账本存在。
            operateHandlerValidator.makeSureAccountBookExists(accountBookKey);

            // 3. 确认用户有权限操作指定的账本。
            operateHandlerValidator.makeSureUserPermittedForAccountBook(userKey, accountBookKey);

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
}
