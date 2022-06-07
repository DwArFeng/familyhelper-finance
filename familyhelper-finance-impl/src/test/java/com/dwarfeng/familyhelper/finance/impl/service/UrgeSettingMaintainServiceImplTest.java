package com.dwarfeng.familyhelper.finance.impl.service;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.AccountBook;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.UrgeSetting;
import com.dwarfeng.familyhelper.finance.stack.service.AccountBookMaintainService;
import com.dwarfeng.familyhelper.finance.stack.service.UrgeSettingMaintainService;
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

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class UrgeSettingMaintainServiceImplTest {

    @Autowired
    private AccountBookMaintainService accountBookMaintainService;
    @Autowired
    private UrgeSettingMaintainService urgeSettingMaintainService;

    private AccountBook accountBook;
    private UrgeSetting urgeSetting;

    @Before
    public void setUp() {
        accountBook = new AccountBook(null, "name", new Date(), BigDecimal.ZERO, "remark");
        urgeSetting = new UrgeSetting(null, "cron", "remark", 0, true);
    }

    @After
    public void tearDown() {
        accountBook = null;
        urgeSetting = null;
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            accountBook.setKey(accountBookMaintainService.insert(accountBook));
            urgeSetting.setKey(accountBook.getKey());
            urgeSettingMaintainService.insert(urgeSetting);

            UrgeSetting testUrgeSetting = urgeSettingMaintainService.get(urgeSetting.getKey());
            assertEquals(BeanUtils.describe(urgeSetting), BeanUtils.describe(testUrgeSetting));
            urgeSettingMaintainService.update(urgeSetting);
            testUrgeSetting = urgeSettingMaintainService.get(urgeSetting.getKey());
            assertEquals(BeanUtils.describe(urgeSetting), BeanUtils.describe(testUrgeSetting));
        } finally {
            urgeSettingMaintainService.deleteIfExists(urgeSetting.getKey());
            accountBookMaintainService.deleteIfExists(accountBook.getKey());
        }
    }

    @Test
    public void testForAccountBookCascade() throws Exception {
        try {
            accountBook.setKey(accountBookMaintainService.insert(accountBook));
            urgeSetting.setKey(accountBook.getKey());
            urgeSettingMaintainService.insert(urgeSetting);

            assertTrue(urgeSettingMaintainService.exists(urgeSetting.getKey()));
            accountBookMaintainService.deleteIfExists(accountBook.getKey());
            assertFalse(urgeSettingMaintainService.exists(urgeSetting.getKey()));
        } finally {
            urgeSettingMaintainService.deleteIfExists(urgeSetting.getKey());
            accountBookMaintainService.deleteIfExists(accountBook.getKey());
        }
    }
}
