package com.dwarfeng.familyhelper.finance.impl.handler;

import com.dwarfeng.familyhelper.finance.stack.bean.dto.BankCardCreateInfo;
import com.dwarfeng.familyhelper.finance.stack.bean.dto.BankCardUpdateInfo;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.AccountBook;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.BankCard;
import com.dwarfeng.familyhelper.finance.stack.handler.BankCardOperateHandler;
import com.dwarfeng.familyhelper.finance.stack.service.AccountBookMaintainService;
import com.dwarfeng.familyhelper.finance.stack.service.BankCardMaintainService;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Component
public class BankCardOperateHandlerImpl implements BankCardOperateHandler {

    private final BankCardMaintainService bankCardMaintainService;
    private final AccountBookMaintainService accountBookMaintainService;

    private final OperateHandlerValidator operateHandlerValidator;

    public BankCardOperateHandlerImpl(
            BankCardMaintainService bankCardMaintainService,
            AccountBookMaintainService accountBookMaintainService,
            OperateHandlerValidator operateHandlerValidator
    ) {
        this.bankCardMaintainService = bankCardMaintainService;
        this.accountBookMaintainService = accountBookMaintainService;
        this.operateHandlerValidator = operateHandlerValidator;
    }

    @Override
    public LongIdKey createBankCard(StringIdKey userKey, BankCardCreateInfo bankCardCreateInfo)
            throws HandlerException {
        try {
            LongIdKey accountBookKey = bankCardCreateInfo.getAccountBookKey();

            // 1. 确认用户存在。
            operateHandlerValidator.makeSureUserExists(userKey);

            // 2. 确认账本存在。
            operateHandlerValidator.makeSureAccountBookExists(accountBookKey);

            // 3. 确认用户有权限操作指定的账本。
            operateHandlerValidator.makeSureUserPermittedForAccountBook(userKey, accountBookKey);

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
            operateHandlerValidator.makeSureUserExists(userKey);

            // 2. 确认银行卡存在。
            operateHandlerValidator.makeSureBankCardExists(bankCardKey);

            // 3. 确认用户有权限操作指定的银行卡。
            operateHandlerValidator.makeSureUserPermittedForBankCard(userKey, bankCardKey);

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
            operateHandlerValidator.makeSureUserExists(userKey);

            // 2. 确认银行卡存在。
            operateHandlerValidator.makeSureBankCardExists(bankCardKey);

            // 3. 确认用户有权限操作指定的银行卡。
            operateHandlerValidator.makeSureUserPermittedForBankCard(userKey, bankCardKey);

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
}
