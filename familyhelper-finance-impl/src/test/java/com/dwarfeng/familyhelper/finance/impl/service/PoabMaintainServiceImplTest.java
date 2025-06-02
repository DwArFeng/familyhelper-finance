package com.dwarfeng.familyhelper.finance.impl.service;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.AccountBook;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.Poab;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.User;
import com.dwarfeng.familyhelper.finance.stack.bean.key.PoabKey;
import com.dwarfeng.familyhelper.finance.stack.service.AccountBookMaintainService;
import com.dwarfeng.familyhelper.finance.stack.service.PoabMaintainService;
import com.dwarfeng.familyhelper.finance.stack.service.UserMaintainService;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class PoabMaintainServiceImplTest {

    private static final long ACCOUNT_BOOK_ID = 12450;
    private static final String USER_ID = "test_user";

    @Autowired
    private AccountBookMaintainService accountBookMaintainService;
    @Autowired
    private UserMaintainService userMaintainService;
    @Autowired
    private PoabMaintainService poabMaintainService;

    private AccountBook accountBook;
    private User user;
    private Poab poab;

    @Before
    public void setUp() {
        accountBook = new AccountBook(new LongIdKey(ACCOUNT_BOOK_ID), "name", new Date(), BigDecimal.ZERO, "remark");
        user = new User(new StringIdKey(USER_ID), "remark");
        poab = new Poab(new PoabKey(ACCOUNT_BOOK_ID, USER_ID), 233, "remark");
    }

    @After
    public void tearDown() {
        accountBook = null;
        user = null;
        poab = null;
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            accountBookMaintainService.insertOrUpdate(accountBook);
            userMaintainService.insertOrUpdate(user);
            poabMaintainService.insert(poab);
            poabMaintainService.update(poab);

            Poab testPoab = poabMaintainService.get(poab.getKey());
            assertEquals(BeanUtils.describe(poab), BeanUtils.describe(testPoab));
            testPoab = poabMaintainService.get(poab.getKey());
            assertEquals(BeanUtils.describe(poab), BeanUtils.describe(testPoab));
        } finally {
            if (Objects.nonNull(poab.getKey())) {
                poabMaintainService.deleteIfExists(poab.getKey());
            }
            if (Objects.nonNull(user.getKey())) {
                userMaintainService.deleteIfExists(user.getKey());
            }
            if (Objects.nonNull(accountBook.getKey())) {
                accountBookMaintainService.deleteIfExists(accountBook.getKey());
            }
        }
    }

    @Test
    public void testForAccountBookCascade() throws Exception {
        try {
            accountBookMaintainService.insertOrUpdate(accountBook);
            userMaintainService.insertOrUpdate(user);
            poabMaintainService.insert(poab);

            accountBookMaintainService.deleteIfExists(accountBook.getKey());
            assertFalse(poabMaintainService.exists(poab.getKey()));
        } finally {
            if (Objects.nonNull(poab.getKey())) {
                poabMaintainService.deleteIfExists(poab.getKey());
            }
            if (Objects.nonNull(user.getKey())) {
                userMaintainService.deleteIfExists(user.getKey());
            }
            if (Objects.nonNull(accountBook.getKey())) {
                accountBookMaintainService.deleteIfExists(accountBook.getKey());
            }
        }
    }

    @Test
    public void testForUserCascade() throws Exception {
        try {
            accountBookMaintainService.insertOrUpdate(accountBook);
            userMaintainService.insertOrUpdate(user);
            poabMaintainService.insert(poab);

            userMaintainService.deleteIfExists(user.getKey());
            assertFalse(poabMaintainService.exists(poab.getKey()));
        } finally {
            if (Objects.nonNull(poab.getKey())) {
                poabMaintainService.deleteIfExists(poab.getKey());
            }
            if (Objects.nonNull(user.getKey())) {
                userMaintainService.deleteIfExists(user.getKey());
            }
            if (Objects.nonNull(accountBook.getKey())) {
                accountBookMaintainService.deleteIfExists(accountBook.getKey());
            }
        }
    }
}
