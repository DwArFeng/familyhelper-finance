package com.dwarfeng.familyhelper.finance.impl.service;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.AccountBook;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.BankCard;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.BankCardBalanceHistory;
import com.dwarfeng.familyhelper.finance.stack.service.AccountBookMaintainService;
import com.dwarfeng.familyhelper.finance.stack.service.BankCardBalanceHistoryMaintainService;
import com.dwarfeng.familyhelper.finance.stack.service.BankCardMaintainService;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class BankCardBalanceHistoryMaintainServiceImplTest {

    @Autowired
    private BankCardBalanceHistoryMaintainService bankCardBalanceHistoryMaintainService;
    @Autowired
    private AccountBookMaintainService accountBookMaintainService;
    @Autowired
    private BankCardMaintainService bankCardMaintainService;

    private List<BankCardBalanceHistory> bankCardBalanceHistories;
    private AccountBook accountBook;
    private BankCard bankCard;

    @Before
    public void setUp() {
        bankCardBalanceHistories = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            BankCardBalanceHistory bankCardBalanceHistory = new BankCardBalanceHistory(
                    null, null, BigDecimal.ZERO, new Date(), "remark"
            );
            bankCardBalanceHistories.add(bankCardBalanceHistory);
        }
        accountBook = new AccountBook(null, "name", new Date(), BigDecimal.ZERO, "remark");
        bankCard = new BankCard(
                null, null, "name", "card_type", new Date(), BigDecimal.ZERO, true, new Date(), BigDecimal.ZERO,
                "remark"
        );
    }

    @After
    public void tearDown() {
        bankCardBalanceHistories.clear();
        accountBook = null;
        bankCard = null;
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            for (BankCardBalanceHistory bankCardBalanceHistory : bankCardBalanceHistories) {
                bankCardBalanceHistory.setKey(bankCardBalanceHistoryMaintainService.insert(bankCardBalanceHistory));

                BankCardBalanceHistory testBankCardBalanceHistory = bankCardBalanceHistoryMaintainService.get(
                        bankCardBalanceHistory.getKey());
                assertEquals(BeanUtils.describe(bankCardBalanceHistory), BeanUtils.describe(testBankCardBalanceHistory));
                bankCardBalanceHistoryMaintainService.update(bankCardBalanceHistory);
                testBankCardBalanceHistory = bankCardBalanceHistoryMaintainService.get(bankCardBalanceHistory.getKey());
                assertEquals(BeanUtils.describe(bankCardBalanceHistory), BeanUtils.describe(testBankCardBalanceHistory));
            }
        } finally {
            for (BankCardBalanceHistory bankCardBalanceHistory : bankCardBalanceHistories) {
                bankCardBalanceHistoryMaintainService.deleteIfExists(bankCardBalanceHistory.getKey());
            }
        }
    }

    @Test
    public void testForAccountBookCascade() throws Exception {
        try {
            accountBook.setKey(accountBookMaintainService.insertOrUpdate(accountBook));
            bankCard.setAccountBookKey(accountBook.getKey());
            bankCard.setKey(bankCardMaintainService.insertOrUpdate(bankCard));
            for (BankCardBalanceHistory bankCardBalanceHistory : bankCardBalanceHistories) {
                bankCardBalanceHistory.setBankCardKey(bankCard.getKey());
                bankCardBalanceHistory.setKey(bankCardBalanceHistoryMaintainService.insert(bankCardBalanceHistory));
            }

            assertEquals(bankCardBalanceHistories.size(), bankCardBalanceHistoryMaintainService.lookup(
                    BankCardBalanceHistoryMaintainService.CHILD_FOR_BANK_CARD, new Object[]{bankCard.getKey()}
            ).getCount());

            accountBookMaintainService.deleteIfExists(accountBook.getKey());

            assertEquals(0, bankCardBalanceHistoryMaintainService.lookup(
                    BankCardBalanceHistoryMaintainService.CHILD_FOR_BANK_CARD, new Object[]{bankCard.getKey()}
            ).getCount());
        } finally {
            for (BankCardBalanceHistory bankCardBalanceHistory : bankCardBalanceHistories) {
                if (Objects.isNull(bankCardBalanceHistory.getKey())) {
                    continue;
                }
                bankCardBalanceHistoryMaintainService.deleteIfExists(bankCardBalanceHistory.getKey());
            }
            if (Objects.nonNull(bankCard.getKey())) {
                bankCardMaintainService.deleteIfExists(bankCard.getKey());
            }
            if (Objects.nonNull(accountBook.getKey())) {
                accountBookMaintainService.deleteIfExists(accountBook.getKey());
            }
        }
    }

    @Test
    public void testForBankCardCascade() throws Exception {
        try {
            bankCard.setKey(bankCardMaintainService.insertOrUpdate(bankCard));
            for (BankCardBalanceHistory bankCardBalanceHistory : bankCardBalanceHistories) {
                bankCardBalanceHistory.setBankCardKey(bankCard.getKey());
                bankCardBalanceHistory.setKey(bankCardBalanceHistoryMaintainService.insert(bankCardBalanceHistory));
            }

            assertEquals(bankCardBalanceHistories.size(), bankCardBalanceHistoryMaintainService.lookup(
                    BankCardBalanceHistoryMaintainService.CHILD_FOR_BANK_CARD, new Object[]{bankCard.getKey()}
            ).getCount());

            bankCardMaintainService.deleteIfExists(bankCard.getKey());

            assertEquals(0, bankCardBalanceHistoryMaintainService.lookup(
                    BankCardBalanceHistoryMaintainService.CHILD_FOR_BANK_CARD, new Object[]{bankCard.getKey()}
            ).getCount());
        } finally {
            for (BankCardBalanceHistory bankCardBalanceHistory : bankCardBalanceHistories) {
                if (Objects.isNull(bankCardBalanceHistory.getKey())) {
                    continue;
                }
                bankCardBalanceHistoryMaintainService.deleteIfExists(bankCardBalanceHistory.getKey());
            }
            if (Objects.nonNull(bankCard.getKey())) {
                bankCardMaintainService.deleteIfExists(bankCard.getKey());
            }
        }
    }
}
