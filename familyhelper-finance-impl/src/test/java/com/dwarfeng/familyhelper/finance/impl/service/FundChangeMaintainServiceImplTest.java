package com.dwarfeng.familyhelper.finance.impl.service;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.AccountBook;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.BankCard;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.FundChange;
import com.dwarfeng.familyhelper.finance.stack.service.AccountBookMaintainService;
import com.dwarfeng.familyhelper.finance.stack.service.BankCardMaintainService;
import com.dwarfeng.familyhelper.finance.stack.service.FundChangeMaintainService;
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

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class FundChangeMaintainServiceImplTest {

    @Autowired
    private FundChangeMaintainService fundChangeMaintainService;
    @Autowired
    private AccountBookMaintainService accountBookMaintainService;
    @Autowired
    private BankCardMaintainService bankCardMaintainService;

    private List<FundChange> fundChanges;
    private AccountBook accountBook;
    private BankCard bankCard;

    @Before
    public void setUp() {
        fundChanges = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            FundChange fundChange = new FundChange(
                    null,
                    null,
                    null,
                    "change_type",
                    new Date(),
                    "remark"
            );
            fundChanges.add(fundChange);
        }
        accountBook = new AccountBook(
                null,
                "name",
                new Date(),
                BigDecimal.ZERO,
                "remark"
        );
        bankCard = new BankCard(
                null,
                null,
                "name",
                "card_type",
                new Date(),
                BigDecimal.ZERO,
                true,
                new Date(),
                BigDecimal.ZERO,
                "remark"
        );
    }

    @After
    public void tearDown() {
        fundChanges.clear();
        accountBook = null;
        bankCard = null;
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            for (FundChange fundChange : fundChanges) {
                fundChange.setKey(fundChangeMaintainService.insert(fundChange));
                fundChangeMaintainService.update(fundChange);
                FundChange testFundChange = fundChangeMaintainService.get(fundChange.getKey());
                assertEquals(BeanUtils.describe(fundChange), BeanUtils.describe(testFundChange));
            }
        } finally {
            for (FundChange fundChange : fundChanges) {
                fundChangeMaintainService.deleteIfExists(fundChange.getKey());
            }
        }
    }

    @Test
    public void testForAccountBookCascade() throws Exception {
        try {
            accountBook.setKey(accountBookMaintainService.insertOrUpdate(accountBook));
            for (FundChange fundChange : fundChanges) {
                fundChange.setAccountBookKey(accountBook.getKey());
                fundChange.setKey(fundChangeMaintainService.insert(fundChange));
            }

            assertEquals(fundChanges.size(), fundChangeMaintainService.lookup(
                    FundChangeMaintainService.CHILD_FOR_ACCOUNT_BOOK, new Object[]{accountBook.getKey()}
            ).getCount());

            accountBookMaintainService.deleteIfExists(accountBook.getKey());

            assertEquals(0, fundChangeMaintainService.lookup(
                    FundChangeMaintainService.CHILD_FOR_ACCOUNT_BOOK, new Object[]{accountBook.getKey()}
            ).getCount());
        } finally {
            accountBookMaintainService.deleteIfExists(accountBook.getKey());
            for (FundChange fundChange : fundChanges) {
                fundChangeMaintainService.deleteIfExists(fundChange.getKey());
            }
        }
    }

    @Test
    public void testForBankCardCascade() throws Exception {
        try {
            bankCard.setKey(bankCardMaintainService.insertOrUpdate(bankCard));
            for (FundChange fundChange : fundChanges) {
                fundChange.setBankCardKey(bankCard.getKey());
                fundChange.setKey(fundChangeMaintainService.insert(fundChange));
            }

            assertEquals(fundChanges.size(), fundChangeMaintainService.lookup(
                    FundChangeMaintainService.CHILD_FOR_BANK_CARD, new Object[]{bankCard.getKey()}
            ).getCount());

            bankCardMaintainService.deleteIfExists(bankCard.getKey());

            assertEquals(0, fundChangeMaintainService.lookup(
                    FundChangeMaintainService.CHILD_FOR_BANK_CARD, new Object[]{bankCard.getKey()}
            ).getCount());
        } finally {
            bankCardMaintainService.deleteIfExists(bankCard.getKey());
            for (FundChange fundChange : fundChanges) {
                fundChangeMaintainService.deleteIfExists(fundChange.getKey());
            }
        }
    }
}
