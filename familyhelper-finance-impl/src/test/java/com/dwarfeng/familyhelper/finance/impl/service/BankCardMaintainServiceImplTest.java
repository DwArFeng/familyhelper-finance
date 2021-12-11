package com.dwarfeng.familyhelper.finance.impl.service;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.AccountBook;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.BankCard;
import com.dwarfeng.familyhelper.finance.stack.service.AccountBookMaintainService;
import com.dwarfeng.familyhelper.finance.stack.service.BankCardMaintainService;
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
public class BankCardMaintainServiceImplTest {

    @Autowired
    private BankCardMaintainService bankCardMaintainService;
    @Autowired
    private AccountBookMaintainService accountBookMaintainService;

    private List<BankCard> bankCards;
    private AccountBook accountBook;

    @Before
    public void setUp() {
        bankCards = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            BankCard bankCard = new BankCard(
                    null,
                    null,
                    "name",
                    "card_type",
                    new Date(),
                    BigDecimal.ZERO,
                    true,
                    new Date(),
                    BigDecimal.ZERO,
                    "remark"
            );
            bankCards.add(bankCard);
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
        bankCards.clear();
        accountBook = null;
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            for (BankCard bankCard : bankCards) {
                bankCard.setKey(bankCardMaintainService.insert(bankCard));

                BankCard testBankCard = bankCardMaintainService.get(bankCard.getKey());
                assertEquals(BeanUtils.describe(bankCard), BeanUtils.describe(testBankCard));
                bankCardMaintainService.update(bankCard);
                testBankCard = bankCardMaintainService.get(bankCard.getKey());
                assertEquals(BeanUtils.describe(bankCard), BeanUtils.describe(testBankCard));
            }
        } finally {
            for (BankCard bankCard : bankCards) {
                bankCardMaintainService.deleteIfExists(bankCard.getKey());
            }
        }
    }

    @Test
    public void testForAccountBookCascade() throws Exception {
        try {
            accountBook.setKey(accountBookMaintainService.insertOrUpdate(accountBook));
            for (BankCard bankCard : bankCards) {
                bankCard.setAccountBookKey(accountBook.getKey());
                bankCard.setKey(bankCardMaintainService.insert(bankCard));
            }

            assertEquals(bankCards.size(), bankCardMaintainService.lookup(
                    BankCardMaintainService.CHILD_FOR_ACCOUNT_BOOK, new Object[]{accountBook.getKey()}
            ).getCount());

            accountBookMaintainService.deleteIfExists(accountBook.getKey());

            assertEquals(0, bankCardMaintainService.lookup(
                    BankCardMaintainService.CHILD_FOR_ACCOUNT_BOOK, new Object[]{accountBook.getKey()}
            ).getCount());
        } finally {
            accountBookMaintainService.deleteIfExists(accountBook.getKey());
            for (BankCard bankCard : bankCards) {
                bankCardMaintainService.deleteIfExists(bankCard.getKey());
            }
        }
    }
}
