package com.dwarfeng.familyhelper.finance.impl.service;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.AccountBook;
import com.dwarfeng.familyhelper.finance.stack.service.AccountBookMaintainService;
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
public class AccountBookMaintainServiceImplTest {

    @Autowired
    private AccountBookMaintainService accountBookMaintainService;

    private List<AccountBook> accountBooks;

    @Before
    public void setUp() {
        accountBooks = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            AccountBook accountBook = new AccountBook(
                    null,
                    "name",
                    new Date(),
                    BigDecimal.ZERO,
                    "remark"
            );
            accountBooks.add(accountBook);
        }
    }

    @After
    public void tearDown() {
        accountBooks.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            for (AccountBook accountBook : accountBooks) {
                accountBook.setKey(accountBookMaintainService.insert(accountBook));

                AccountBook testAccountBook = accountBookMaintainService.get(accountBook.getKey());
                assertEquals(BeanUtils.describe(accountBook), BeanUtils.describe(testAccountBook));
                accountBookMaintainService.update(accountBook);
                testAccountBook = accountBookMaintainService.get(accountBook.getKey());
                assertEquals(BeanUtils.describe(accountBook), BeanUtils.describe(testAccountBook));
            }
        } finally {
            for (AccountBook accountBook : accountBooks) {
                accountBookMaintainService.deleteIfExists(accountBook.getKey());
            }
        }
    }
}
