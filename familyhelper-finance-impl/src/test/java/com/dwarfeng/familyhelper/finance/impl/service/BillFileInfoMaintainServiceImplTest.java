package com.dwarfeng.familyhelper.finance.impl.service;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.BillFileInfo;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.FundChange;
import com.dwarfeng.familyhelper.finance.stack.service.BillFileInfoMaintainService;
import com.dwarfeng.familyhelper.finance.stack.service.FundChangeMaintainService;
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
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class BillFileInfoMaintainServiceImplTest {

    @Autowired
    private BillFileInfoMaintainService billFileInfoMaintainService;
    @Autowired
    private FundChangeMaintainService fundChangeMaintainService;

    private List<BillFileInfo> billFileInfos;
    private FundChange fundChange;

    @Before
    public void setUp() {
        billFileInfos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            BillFileInfo billFileInfo = new BillFileInfo(
                    null, null, "originName", i, 12450L, new Date(), "remark"
            );
            billFileInfos.add(billFileInfo);
        }
        fundChange = new FundChange(
                null, null, BigDecimal.ZERO, "change_type", new Date(), "remark"
        );
    }

    @After
    public void tearDown() {
        billFileInfos.clear();
        fundChange = null;
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            fundChange.setKey(fundChangeMaintainService.insert(fundChange));
            for (BillFileInfo billFileInfo : billFileInfos) {
                billFileInfo.setFundChangeKey(fundChange.getKey());
                billFileInfo.setKey(billFileInfoMaintainService.insert(billFileInfo));

                BillFileInfo testBillFileInfo = billFileInfoMaintainService.get(billFileInfo.getKey());
                assertEquals(BeanUtils.describe(billFileInfo), BeanUtils.describe(testBillFileInfo));
                billFileInfoMaintainService.update(billFileInfo);
                testBillFileInfo = billFileInfoMaintainService.get(billFileInfo.getKey());
                assertEquals(BeanUtils.describe(billFileInfo), BeanUtils.describe(testBillFileInfo));
            }
        } finally {
            for (BillFileInfo billFileInfo : billFileInfos) {
                billFileInfoMaintainService.deleteIfExists(billFileInfo.getKey());
            }
            fundChangeMaintainService.deleteIfExists(fundChange.getKey());
        }
    }

    @Test
    public void testForFundChangeCascade() throws Exception {
        try {
            fundChange.setKey(fundChangeMaintainService.insert(fundChange));
            for (BillFileInfo billFileInfo : billFileInfos) {
                billFileInfo.setFundChangeKey(fundChange.getKey());
                billFileInfo.setKey(billFileInfoMaintainService.insert(billFileInfo));
            }

            fundChangeMaintainService.deleteIfExists(fundChange.getKey());

            assertTrue(billFileInfoMaintainService.nonExists(
                    billFileInfos.stream().map(BillFileInfo::getKey).collect(Collectors.toList()))
            );
        } finally {
            for (BillFileInfo billFileInfo : billFileInfos) {
                billFileInfoMaintainService.deleteIfExists(billFileInfo.getKey());
            }
            fundChangeMaintainService.deleteIfExists(fundChange.getKey());
        }
    }
}
