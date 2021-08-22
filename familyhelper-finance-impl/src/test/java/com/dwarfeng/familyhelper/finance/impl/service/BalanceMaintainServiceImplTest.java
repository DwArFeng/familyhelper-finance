package com.dwarfeng.familyhelper.finance.impl.service;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.AccountBook;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.Balance;
import com.dwarfeng.familyhelper.finance.stack.service.AccountBookMaintainService;
import com.dwarfeng.familyhelper.finance.stack.service.BalanceMaintainService;
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
public class BalanceMaintainServiceImplTest {

    @Autowired
    private BalanceMaintainService balanceMaintainService;
    @Autowired
    private AccountBookMaintainService accountBookMaintainService;

    private List<Balance> balances;
    private AccountBook accountBook;

    @Before
    public void setUp() {
        balances = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Balance balance = new Balance(
                    null,
                    null,
                    new Date(),
                    BigDecimal.ZERO,
                    "remark"
            );
            balances.add(balance);
        }
        accountBook = new AccountBook(
                null,
                "name",
                "remark"
        );
    }

    @After
    public void tearDown() {
        balances.clear();
        accountBook = null;
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            for (Balance balance : balances) {
                balance.setKey(balanceMaintainService.insert(balance));
                balanceMaintainService.update(balance);
                Balance testBalance = balanceMaintainService.get(balance.getKey());
                assertEquals(BeanUtils.describe(balance), BeanUtils.describe(testBalance));
            }
        } finally {
            for (Balance balance : balances) {
                balanceMaintainService.deleteIfExists(balance.getKey());
            }
        }
    }

    @Test
    public void testForAccountBookCascade() throws Exception {
        try {
            accountBook.setKey(accountBookMaintainService.insertOrUpdate(accountBook));
            for (Balance balance : balances) {
                balance.setAccountBookKey(accountBook.getKey());
                balance.setKey(balanceMaintainService.insert(balance));
            }

            assertEquals(balances.size(), balanceMaintainService.lookup(
                    BalanceMaintainService.CHILD_FOR_ACCOUNT_BOOK, new Object[]{accountBook.getKey()}
            ).getCount());

            accountBookMaintainService.deleteIfExists(accountBook.getKey());

            assertEquals(0, balanceMaintainService.lookup(
                    BalanceMaintainService.CHILD_FOR_ACCOUNT_BOOK, new Object[]{accountBook.getKey()}
            ).getCount());
        } finally {
            accountBookMaintainService.deleteIfExists(accountBook.getKey());
            for (Balance balance : balances) {
                balanceMaintainService.deleteIfExists(balance.getKey());
            }
        }
    }
}
