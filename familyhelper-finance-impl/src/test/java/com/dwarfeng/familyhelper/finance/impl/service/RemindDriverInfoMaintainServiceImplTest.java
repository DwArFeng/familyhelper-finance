package com.dwarfeng.familyhelper.finance.impl.service;

import com.dwarfeng.familyhelper.finance.sdk.util.Constants;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.AccountBook;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.RemindDriverInfo;
import com.dwarfeng.familyhelper.finance.stack.service.AccountBookMaintainService;
import com.dwarfeng.familyhelper.finance.stack.service.RemindDriverInfoMaintainService;
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
public class RemindDriverInfoMaintainServiceImplTest {

    @Autowired
    private RemindDriverInfoMaintainService remindDriverInfoMaintainService;
    @Autowired
    private AccountBookMaintainService accountBookMaintainService;

    private List<RemindDriverInfo> remindDriverInfos;
    private AccountBook accountBook;

    @Before
    public void setUp() {
        remindDriverInfos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            RemindDriverInfo remindDriverInfo = new RemindDriverInfo(
                    null, null, true, "type", "param", Constants.PERMISSION_LEVEL_OWNER, "remark"
            );
            remindDriverInfos.add(remindDriverInfo);
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
        remindDriverInfos.clear();
        accountBook = null;
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            for (RemindDriverInfo remindDriverInfo : remindDriverInfos) {
                remindDriverInfo.setKey(remindDriverInfoMaintainService.insert(remindDriverInfo));

                RemindDriverInfo testRemindDriverInfo = remindDriverInfoMaintainService.get(remindDriverInfo.getKey());
                assertEquals(BeanUtils.describe(remindDriverInfo), BeanUtils.describe(testRemindDriverInfo));
                remindDriverInfoMaintainService.update(remindDriverInfo);
                testRemindDriverInfo = remindDriverInfoMaintainService.get(remindDriverInfo.getKey());
                assertEquals(BeanUtils.describe(remindDriverInfo), BeanUtils.describe(testRemindDriverInfo));
            }
        } finally {
            for (RemindDriverInfo remindDriverInfo : remindDriverInfos) {
                remindDriverInfoMaintainService.deleteIfExists(remindDriverInfo.getKey());
            }
        }
    }

    @Test
    public void testForAccountBookCascade() throws Exception {
        try {
            accountBook.setKey(accountBookMaintainService.insertOrUpdate(accountBook));
            for (RemindDriverInfo remindDriverInfo : remindDriverInfos) {
                remindDriverInfo.setAccountBookKey(accountBook.getKey());
                remindDriverInfo.setKey(remindDriverInfoMaintainService.insert(remindDriverInfo));
            }

            assertEquals(remindDriverInfos.size(), remindDriverInfoMaintainService.lookup(
                    RemindDriverInfoMaintainService.CHILD_FOR_ACCOUNT_BOOK, new Object[]{accountBook.getKey()}
            ).getCount());

            accountBookMaintainService.deleteIfExists(accountBook.getKey());

            assertEquals(0, remindDriverInfoMaintainService.lookup(
                    RemindDriverInfoMaintainService.CHILD_FOR_ACCOUNT_BOOK, new Object[]{accountBook.getKey()}
            ).getCount());
        } finally {
            accountBookMaintainService.deleteIfExists(accountBook.getKey());
            for (RemindDriverInfo remindDriverInfo : remindDriverInfos) {
                remindDriverInfoMaintainService.deleteIfExists(remindDriverInfo.getKey());
            }
        }
    }
}
