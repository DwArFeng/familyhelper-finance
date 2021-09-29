package com.dwarfeng.familyhelper.finance.impl.service.operation;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.BankCard;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.FundChange;
import com.dwarfeng.familyhelper.finance.stack.cache.BankCardCache;
import com.dwarfeng.familyhelper.finance.stack.cache.FundChangeCache;
import com.dwarfeng.familyhelper.finance.stack.dao.BankCardDao;
import com.dwarfeng.familyhelper.finance.stack.dao.FundChangeDao;
import com.dwarfeng.familyhelper.finance.stack.service.FundChangeMaintainService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BankCardCrudOperation implements BatchCrudOperation<LongIdKey, BankCard> {

    private final BankCardDao bankCardDao;
    private final FundChangeDao fundChangeDao;

    private final BankCardCache bankCardCache;
    private final FundChangeCache fundChangeCache;

    @Value("${cache.timeout.entity.account_book}")
    private long bankCardTimeout;

    public BankCardCrudOperation(
            BankCardDao bankCardDao, FundChangeDao fundChangeDao, BankCardCache bankCardCache,
            FundChangeCache fundChangeCache
    ) {
        this.bankCardDao = bankCardDao;
        this.fundChangeDao = fundChangeDao;
        this.bankCardCache = bankCardCache;
        this.fundChangeCache = fundChangeCache;
    }

    @Override
    public boolean exists(LongIdKey key) throws Exception {
        return bankCardCache.exists(key) || bankCardDao.exists(key);
    }

    @Override
    public BankCard get(LongIdKey key) throws Exception {
        if (bankCardCache.exists(key)) {
            return bankCardCache.get(key);
        } else {
            if (!bankCardDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            BankCard bankCard = bankCardDao.get(key);
            bankCardCache.push(bankCard, bankCardTimeout);
            return bankCard;
        }
    }

    @Override
    public LongIdKey insert(BankCard bankCard) throws Exception {
        bankCardCache.push(bankCard, bankCardTimeout);
        return bankCardDao.insert(bankCard);
    }

    @Override
    public void update(BankCard bankCard) throws Exception {
        bankCardCache.push(bankCard, bankCardTimeout);
        bankCardDao.update(bankCard);
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public void delete(LongIdKey key) throws Exception {
        // 查询与账本相关的资金变更。
        List<LongIdKey> fundChangeKeys = fundChangeDao.lookup(
                FundChangeMaintainService.CHILD_FOR_BANK_CARD, new Object[]{key}
        ).stream().map(FundChange::getKey).collect(Collectors.toList());
        fundChangeCache.batchDelete(fundChangeKeys);
        fundChangeDao.batchDelete(fundChangeKeys);

        // 删除账本实体自身。
        bankCardDao.delete(key);
        bankCardCache.delete(key);
    }

    @Override
    public boolean allExists(List<LongIdKey> keys) throws Exception {
        return bankCardCache.allExists(keys) || bankCardDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<LongIdKey> keys) throws Exception {
        return bankCardCache.nonExists(keys) && bankCardCache.nonExists(keys);
    }

    @Override
    public List<BankCard> batchGet(List<LongIdKey> keys) throws Exception {
        if (bankCardCache.allExists(keys)) {
            return bankCardCache.batchGet(keys);
        } else {
            if (!bankCardDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<BankCard> bankCards = bankCardDao.batchGet(keys);
            bankCardCache.batchPush(bankCards, bankCardTimeout);
            return bankCards;
        }
    }

    @Override
    public List<LongIdKey> batchInsert(List<BankCard> bankCards) throws Exception {
        bankCardCache.batchPush(bankCards, bankCardTimeout);
        return bankCardDao.batchInsert(bankCards);
    }

    @Override
    public void batchUpdate(List<BankCard> bankCards) throws Exception {
        bankCardCache.batchPush(bankCards, bankCardTimeout);
        bankCardDao.batchUpdate(bankCards);
    }

    @Override
    public void batchDelete(List<LongIdKey> keys) throws Exception {
        for (LongIdKey key : keys) {
            delete(key);
        }
    }
}