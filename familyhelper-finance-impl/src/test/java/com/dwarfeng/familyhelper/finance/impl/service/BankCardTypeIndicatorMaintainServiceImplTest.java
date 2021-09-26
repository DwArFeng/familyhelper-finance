package com.dwarfeng.familyhelper.finance.impl.service;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.BankCardTypeIndicator;
import com.dwarfeng.familyhelper.finance.stack.service.BankCardTypeIndicatorMaintainService;
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

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class BankCardTypeIndicatorMaintainServiceImplTest {

    @Autowired
    private BankCardTypeIndicatorMaintainService bankCardTypeIndicatorMaintainService;

    private List<BankCardTypeIndicator> bankCardTypeIndicators;

    @Before
    public void setUp() {
        bankCardTypeIndicators = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            BankCardTypeIndicator bankCardTypeIndicator = new BankCardTypeIndicator(
                    new StringIdKey("fund_change_type_indicator_test" + i),
                    "label",
                    "remark"
            );
            bankCardTypeIndicators.add(bankCardTypeIndicator);
        }
    }

    @After
    public void tearDown() {
        bankCardTypeIndicators.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            for (BankCardTypeIndicator bankCardTypeIndicator : bankCardTypeIndicators) {
                bankCardTypeIndicator.setKey(bankCardTypeIndicatorMaintainService.insert(bankCardTypeIndicator));
                bankCardTypeIndicatorMaintainService.update(bankCardTypeIndicator);
                BankCardTypeIndicator testBankCardTypeIndicator = bankCardTypeIndicatorMaintainService.get(bankCardTypeIndicator.getKey());
                assertEquals(BeanUtils.describe(bankCardTypeIndicator), BeanUtils.describe(testBankCardTypeIndicator));
            }
        } finally {
            for (BankCardTypeIndicator bankCardTypeIndicator : bankCardTypeIndicators) {
                bankCardTypeIndicatorMaintainService.deleteIfExists(bankCardTypeIndicator.getKey());
            }
        }
    }
}
