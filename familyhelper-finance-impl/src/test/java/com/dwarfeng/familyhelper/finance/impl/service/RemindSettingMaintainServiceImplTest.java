package com.dwarfeng.familyhelper.finance.impl.service;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.AccountBook;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.RemindSetting;
import com.dwarfeng.familyhelper.finance.stack.service.AccountBookMaintainService;
import com.dwarfeng.familyhelper.finance.stack.service.RemindSettingMaintainService;
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
public class RemindSettingMaintainServiceImplTest {

    @Autowired
    private AccountBookMaintainService accountBookMaintainService;
    @Autowired
    private RemindSettingMaintainService remindSettingMaintainService;

    private AccountBook accountBook;
    private RemindSetting remindSetting;

    @Before
    public void setUp() {
        accountBook = new AccountBook(null, "name", new Date(), BigDecimal.ZERO, "remark");
        remindSetting = new RemindSetting(null, "cron", "remark", 0, true);
    }

    @After
    public void tearDown() {
        accountBook = null;
        remindSetting = null;
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            accountBook.setKey(accountBookMaintainService.insert(accountBook));
            remindSetting.setKey(accountBook.getKey());
            remindSettingMaintainService.insert(remindSetting);

            RemindSetting testRemindSetting = remindSettingMaintainService.get(remindSetting.getKey());
            assertEquals(BeanUtils.describe(remindSetting), BeanUtils.describe(testRemindSetting));
            remindSettingMaintainService.update(remindSetting);
            testRemindSetting = remindSettingMaintainService.get(remindSetting.getKey());
            assertEquals(BeanUtils.describe(remindSetting), BeanUtils.describe(testRemindSetting));
        } finally {
            remindSettingMaintainService.deleteIfExists(remindSetting.getKey());
            accountBookMaintainService.deleteIfExists(accountBook.getKey());
        }
    }

    @Test
    public void testForAccountBookCascade() throws Exception {
        try {
            accountBook.setKey(accountBookMaintainService.insert(accountBook));
            remindSetting.setKey(accountBook.getKey());
            remindSettingMaintainService.insert(remindSetting);

            assertTrue(remindSettingMaintainService.exists(remindSetting.getKey()));
            accountBookMaintainService.deleteIfExists(accountBook.getKey());
            assertFalse(remindSettingMaintainService.exists(remindSetting.getKey()));
        } finally {
            remindSettingMaintainService.deleteIfExists(remindSetting.getKey());
            accountBookMaintainService.deleteIfExists(accountBook.getKey());
        }
    }
}
