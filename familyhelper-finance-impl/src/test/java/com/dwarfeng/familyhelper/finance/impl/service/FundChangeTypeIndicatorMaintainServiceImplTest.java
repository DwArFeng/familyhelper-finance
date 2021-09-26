package com.dwarfeng.familyhelper.finance.impl.service;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.FundChangeTypeIndicator;
import com.dwarfeng.familyhelper.finance.stack.service.FundChangeTypeIndicatorMaintainService;
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
public class FundChangeTypeIndicatorMaintainServiceImplTest {

    @Autowired
    private FundChangeTypeIndicatorMaintainService fundChangeTypeIndicatorMaintainService;

    private List<FundChangeTypeIndicator> fundChangeTypeIndicators;

    @Before
    public void setUp() {
        fundChangeTypeIndicators = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            FundChangeTypeIndicator fundChangeTypeIndicator = new FundChangeTypeIndicator(
                    new StringIdKey("fund_change_type_indicator_test" + i),
                    "label",
                    "remark"
            );
            fundChangeTypeIndicators.add(fundChangeTypeIndicator);
        }
    }

    @After
    public void tearDown() {
        fundChangeTypeIndicators.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            for (FundChangeTypeIndicator fundChangeTypeIndicator : fundChangeTypeIndicators) {
                fundChangeTypeIndicator.setKey(fundChangeTypeIndicatorMaintainService.insert(fundChangeTypeIndicator));
                fundChangeTypeIndicatorMaintainService.update(fundChangeTypeIndicator);
                FundChangeTypeIndicator testFundChangeTypeIndicator = fundChangeTypeIndicatorMaintainService.get(fundChangeTypeIndicator.getKey());
                assertEquals(BeanUtils.describe(fundChangeTypeIndicator), BeanUtils.describe(testFundChangeTypeIndicator));
            }
        } finally {
            for (FundChangeTypeIndicator fundChangeTypeIndicator : fundChangeTypeIndicators) {
                fundChangeTypeIndicatorMaintainService.deleteIfExists(fundChangeTypeIndicator.getKey());
            }
        }
    }
}
