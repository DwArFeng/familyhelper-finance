package com.dwarfeng.familyhelper.finance.impl.service;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.AccountBook;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.FundRepository;
import com.dwarfeng.familyhelper.finance.stack.service.AccountBookMaintainService;
import com.dwarfeng.familyhelper.finance.stack.service.FundRepositoryMaintainService;
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
public class FundRepositoryMaintainServiceImplTest {

    @Autowired
    private FundRepositoryMaintainService fundRepositoryMaintainService;
    @Autowired
    private AccountBookMaintainService accountBookMaintainService;

    private List<FundRepository> fundRepositories;
    private AccountBook accountBook;

    @Before
    public void setUp() {
        fundRepositories = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            FundRepository fundRepository = new FundRepository(
                    null,
                    null,
                    "name",
                    true,
                    "remark"
            );
            fundRepositories.add(fundRepository);
        }
        accountBook = new AccountBook(
                null,
                "name",
                "remark"
        );
    }

    @After
    public void tearDown() {
        fundRepositories.clear();
        accountBook = null;
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            for (FundRepository fundRepository : fundRepositories) {
                fundRepository.setKey(fundRepositoryMaintainService.insert(fundRepository));
                fundRepositoryMaintainService.update(fundRepository);
                FundRepository testFundRepository = fundRepositoryMaintainService.get(fundRepository.getKey());
                assertEquals(BeanUtils.describe(fundRepository), BeanUtils.describe(testFundRepository));
            }
        } finally {
            for (FundRepository fundRepository : fundRepositories) {
                fundRepositoryMaintainService.deleteIfExists(fundRepository.getKey());
            }
        }
    }

    @Test
    public void testForAccountBookCascade() throws Exception {
        try {
            accountBook.setKey(accountBookMaintainService.insertOrUpdate(accountBook));
            for (FundRepository fundRepository : fundRepositories) {
                fundRepository.setAccountBookKey(accountBook.getKey());
                fundRepository.setKey(fundRepositoryMaintainService.insert(fundRepository));
            }

            assertEquals(fundRepositories.size(), fundRepositoryMaintainService.lookup(
                    FundRepositoryMaintainService.CHILD_FOR_ACCOUNT_BOOK, new Object[]{accountBook.getKey()}
            ).getCount());

            accountBookMaintainService.deleteIfExists(accountBook.getKey());

            assertEquals(0, fundRepositoryMaintainService.lookup(
                    FundRepositoryMaintainService.CHILD_FOR_ACCOUNT_BOOK, new Object[]{accountBook.getKey()}
            ).getCount());
        } finally {
            accountBookMaintainService.deleteIfExists(accountBook.getKey());
            for (FundRepository fundRepository : fundRepositories) {
                fundRepositoryMaintainService.deleteIfExists(fundRepository.getKey());
            }
        }
    }
}
