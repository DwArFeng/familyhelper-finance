package com.dwarfeng.familyhelper.finance.impl.service;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.AccountBook;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.Balance;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.BalanceItem;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.FundRepository;
import com.dwarfeng.familyhelper.finance.stack.service.AccountBookMaintainService;
import com.dwarfeng.familyhelper.finance.stack.service.BalanceItemMaintainService;
import com.dwarfeng.familyhelper.finance.stack.service.BalanceMaintainService;
import com.dwarfeng.familyhelper.finance.stack.service.FundRepositoryMaintainService;
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
public class BalanceItemMaintainServiceImplTest {

    @Autowired
    private BalanceItemMaintainService balanceItemMaintainService;
    @Autowired
    private BalanceMaintainService balanceMaintainService;
    @Autowired
    private FundRepositoryMaintainService fundRepositoryMaintainService;
    @Autowired
    private AccountBookMaintainService accountBookMaintainService;

    private List<BalanceItem> balanceItems;
    private Balance balance;
    private FundRepository fundRepository;
    private AccountBook accountBook;

    @Before
    public void setUp() {
        balanceItems = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            BalanceItem balanceItem = new BalanceItem(
                    null,
                    null,
                    null,
                    BigDecimal.ZERO,
                    "remark"
            );
            balanceItems.add(balanceItem);
        }
        balance = new Balance(
                null,
                null,
                new Date(),
                BigDecimal.ZERO,
                "remark"
        );
        fundRepository = new FundRepository(
                null,
                null,
                "name",
                true,
                "remark"
        );
        accountBook = new AccountBook(
                null,
                "name",
                "remark"
        );
    }

    @After
    public void tearDown() {
        balanceItems.clear();
        balance = null;
        fundRepository = null;
        accountBook = null;
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            for (BalanceItem balanceItem : balanceItems) {
                balanceItem.setKey(balanceItemMaintainService.insert(balanceItem));
                balanceItemMaintainService.update(balanceItem);
                BalanceItem testBalanceItem = balanceItemMaintainService.get(balanceItem.getKey());
                assertEquals(BeanUtils.describe(balanceItem), BeanUtils.describe(testBalanceItem));
            }
        } finally {
            for (BalanceItem balanceItem : balanceItems) {
                balanceItemMaintainService.deleteIfExists(balanceItem.getKey());
            }
        }
    }

    @Test
    public void testForBalanceCascade() throws Exception {
        try {
            balance.setKey(balanceMaintainService.insertOrUpdate(balance));
            for (BalanceItem balanceItem : balanceItems) {
                balanceItem.setBalanceKey(balance.getKey());
                balanceItem.setKey(balanceItemMaintainService.insert(balanceItem));
            }

            assertEquals(balanceItems.size(), balanceItemMaintainService.lookup(
                    BalanceItemMaintainService.CHILD_FOR_BALANCE, new Object[]{balance.getKey()}
            ).getCount());

            balanceMaintainService.deleteIfExists(balance.getKey());

            assertEquals(0, balanceItemMaintainService.lookup(
                    BalanceItemMaintainService.CHILD_FOR_BALANCE, new Object[]{balance.getKey()}
            ).getCount());
        } finally {
            balanceMaintainService.deleteIfExists(balance.getKey());
            for (BalanceItem balanceItem : balanceItems) {
                balanceItemMaintainService.deleteIfExists(balanceItem.getKey());
            }
        }
    }

    @Test
    public void testForFundRepositoryCascade() throws Exception {
        try {
            fundRepository.setKey(fundRepositoryMaintainService.insertOrUpdate(fundRepository));
            for (BalanceItem balanceItem : balanceItems) {
                balanceItem.setFundRepositoryKey(fundRepository.getKey());
                balanceItem.setKey(balanceItemMaintainService.insert(balanceItem));
            }

            assertEquals(balanceItems.size(), balanceItemMaintainService.lookup(
                    BalanceItemMaintainService.CHILD_FOR_FUND_REPOSITORY, new Object[]{fundRepository.getKey()}
            ).getCount());

            fundRepositoryMaintainService.deleteIfExists(fundRepository.getKey());

            assertEquals(0, balanceItemMaintainService.lookup(
                    BalanceItemMaintainService.CHILD_FOR_FUND_REPOSITORY, new Object[]{fundRepository.getKey()}
            ).getCount());
        } finally {
            fundRepositoryMaintainService.deleteIfExists(fundRepository.getKey());
            for (BalanceItem balanceItem : balanceItems) {
                balanceItemMaintainService.deleteIfExists(balanceItem.getKey());
            }
        }
    }

    @Test
    public void testForAccountBookBalanceCascade() throws Exception {
        try {
            accountBook.setKey(accountBookMaintainService.insertOrUpdate(accountBook));
            balance.setAccountBookKey(accountBook.getKey());
            balance.setKey(balanceMaintainService.insertOrUpdate(balance));
            for (BalanceItem balanceItem : balanceItems) {
                balanceItem.setBalanceKey(balance.getKey());
                balanceItem.setKey(balanceItemMaintainService.insert(balanceItem));
            }

            assertEquals(balanceItems.size(), balanceItemMaintainService.lookup(
                    BalanceItemMaintainService.CHILD_FOR_BALANCE, new Object[]{balance.getKey()}
            ).getCount());

            accountBookMaintainService.deleteIfExists(accountBook.getKey());

            assertEquals(0, balanceItemMaintainService.lookup(
                    BalanceItemMaintainService.CHILD_FOR_BALANCE, new Object[]{balance.getKey()}
            ).getCount());
        } finally {
            accountBookMaintainService.deleteIfExists(accountBook.getKey());
            balanceMaintainService.deleteIfExists(balance.getKey());
            for (BalanceItem balanceItem : balanceItems) {
                balanceItemMaintainService.deleteIfExists(balanceItem.getKey());
            }
        }
    }

    @Test
    public void testForAccountBookFundRepositoryCascade() throws Exception {
        try {
            accountBook.setKey(accountBookMaintainService.insertOrUpdate(accountBook));
            fundRepository.setAccountBookKey(accountBook.getKey());
            fundRepository.setKey(fundRepositoryMaintainService.insertOrUpdate(fundRepository));
            for (BalanceItem balanceItem : balanceItems) {
                balanceItem.setFundRepositoryKey(fundRepository.getKey());
                balanceItem.setKey(balanceItemMaintainService.insert(balanceItem));
            }

            assertEquals(balanceItems.size(), balanceItemMaintainService.lookup(
                    BalanceItemMaintainService.CHILD_FOR_FUND_REPOSITORY, new Object[]{fundRepository.getKey()}
            ).getCount());

            accountBookMaintainService.deleteIfExists(accountBook.getKey());

            assertEquals(0, balanceItemMaintainService.lookup(
                    BalanceItemMaintainService.CHILD_FOR_FUND_REPOSITORY, new Object[]{fundRepository.getKey()}
            ).getCount());
        } finally {
            accountBookMaintainService.deleteIfExists(accountBook.getKey());
            fundRepositoryMaintainService.deleteIfExists(fundRepository.getKey());
            for (BalanceItem balanceItem : balanceItems) {
                balanceItemMaintainService.deleteIfExists(balanceItem.getKey());
            }
        }
    }
}
