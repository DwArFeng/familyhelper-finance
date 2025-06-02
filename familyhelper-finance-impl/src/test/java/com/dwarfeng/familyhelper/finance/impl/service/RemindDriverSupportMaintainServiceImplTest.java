package com.dwarfeng.familyhelper.finance.impl.service;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.RemindDriverSupport;
import com.dwarfeng.familyhelper.finance.stack.service.RemindDriverSupportMaintainService;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class RemindDriverSupportMaintainServiceImplTest {

    @Autowired
    private RemindDriverSupportMaintainService remindDriverSupportMaintainService;

    private final List<RemindDriverSupport> remindDriverSupports = new ArrayList<>();

    @Before
    public void setUp() {
        for (int i = 0; i < 5; i++) {
            RemindDriverSupport remindDriverSupport = new RemindDriverSupport(
                    new StringIdKey("remindDriver-support-" + (i + 1)), "label", "description", "exampleParam"
            );
            remindDriverSupports.add(remindDriverSupport);
        }
    }

    @After
    public void tearDown() {
        remindDriverSupports.clear();
    }

    @Test
    public void test() throws Exception {
        try {
            for (RemindDriverSupport remindDriverSupport : remindDriverSupports) {
                remindDriverSupport.setKey(remindDriverSupportMaintainService.insert(remindDriverSupport));
                remindDriverSupportMaintainService.update(remindDriverSupport);
                RemindDriverSupport testRemindDriverSupport = remindDriverSupportMaintainService.get(
                        remindDriverSupport.getKey()
                );
                assertEquals(BeanUtils.describe(remindDriverSupport), BeanUtils.describe(testRemindDriverSupport));
            }
        } finally {
            for (RemindDriverSupport remindDriverSupport : remindDriverSupports) {
                if (Objects.isNull(remindDriverSupport.getKey())) {
                    continue;
                }
                remindDriverSupportMaintainService.delete(remindDriverSupport.getKey());
            }
        }
    }
}
