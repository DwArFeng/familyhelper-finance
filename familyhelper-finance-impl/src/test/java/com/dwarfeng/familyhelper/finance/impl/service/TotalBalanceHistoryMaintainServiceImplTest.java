package com.dwarfeng.familyhelper.finance.impl.service;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.AccountBook;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.TotalBalanceHistory;
import com.dwarfeng.familyhelper.finance.stack.service.AccountBookMaintainService;
import com.dwarfeng.familyhelper.finance.stack.service.TotalBalanceHistoryMaintainService;
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
public class TotalBalanceHistoryMaintainServiceImplTest {

    @Autowired
    private TotalBalanceHistoryMaintainService totalBalanceHistoryMaintainService;
    @Autowired
    private AccountBookMaintainService accountBookMaintainService;

    private List<TotalBalanceHistory> accountBookBalanceHistories;
    private AccountBook accountBook;

    @Before
    public void setUp() {
        accountBookBalanceHistories = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            TotalBalanceHistory totalBalanceHistory = new TotalBalanceHistory(
                    null,
                    null,
                    BigDecimal.ZERO,
                    new Date(),
                    "remark"
            );
            accountBookBalanceHistories.add(totalBalanceHistory);
        }
        accountBook = new AccountBook(
                null,
                "name",
                new Date(),
                BigDecimal.ZERO,
                "remark"
        );
    }

    @After
    public void tearDown() {
        accountBookBalanceHistories.clear();
        accountBook = null;
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            for (TotalBalanceHistory totalBalanceHistory : accountBookBalanceHistories) {
                totalBalanceHistory.setKey(totalBalanceHistoryMaintainService.insert(totalBalanceHistory));
                totalBalanceHistoryMaintainService.update(totalBalanceHistory);
                TotalBalanceHistory testTotalBalanceHistory = totalBalanceHistoryMaintainService.get(totalBalanceHistory.getKey());
                assertEquals(BeanUtils.describe(totalBalanceHistory), BeanUtils.describe(testTotalBalanceHistory));
            }
        } finally {
            for (TotalBalanceHistory totalBalanceHistory : accountBookBalanceHistories) {
                totalBalanceHistoryMaintainService.deleteIfExists(totalBalanceHistory.getKey());
            }
        }
    }

    @Test
    public void testForAccountBookCascade() throws Exception {
        try {
            accountBook.setKey(accountBookMaintainService.insertOrUpdate(accountBook));
            for (TotalBalanceHistory totalBalanceHistory : accountBookBalanceHistories) {
                totalBalanceHistory.setAccountBookKey(accountBook.getKey());
                totalBalanceHistory.setKey(totalBalanceHistoryMaintainService.insert(totalBalanceHistory));
            }

            assertEquals(accountBookBalanceHistories.size(), totalBalanceHistoryMaintainService.lookup(
                    TotalBalanceHistoryMaintainService.CHILD_FOR_ACCOUNT_BOOK, new Object[]{accountBook.getKey()}
            ).getCount());

            accountBookMaintainService.deleteIfExists(accountBook.getKey());

            assertEquals(0, totalBalanceHistoryMaintainService.lookup(
                    TotalBalanceHistoryMaintainService.CHILD_FOR_ACCOUNT_BOOK, new Object[]{accountBook.getKey()}
            ).getCount());
        } finally {
            accountBookMaintainService.deleteIfExists(accountBook.getKey());
            for (TotalBalanceHistory totalBalanceHistory : accountBookBalanceHistories) {
                totalBalanceHistoryMaintainService.deleteIfExists(totalBalanceHistory.getKey());
            }
        }
    }
}
