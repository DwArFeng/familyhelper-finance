package com.dwarfeng.familyhelper.finance.impl.service.operation;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.BankCard;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.BankCardBalanceHistory;
import com.dwarfeng.familyhelper.finance.stack.cache.BankCardBalanceHistoryCache;
import com.dwarfeng.familyhelper.finance.stack.cache.BankCardCache;
import com.dwarfeng.familyhelper.finance.stack.dao.BankCardBalanceHistoryDao;
import com.dwarfeng.familyhelper.finance.stack.dao.BankCardDao;
import com.dwarfeng.familyhelper.finance.stack.service.BankCardBalanceHistoryMaintainService;
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
    private final BankCardBalanceHistoryDao bankCardBalanceHistoryDao;

    private final BankCardCache bankCardCache;
    private final BankCardBalanceHistoryCache bankCardBalanceHistoryCache;

    @Value("${cache.timeout.entity.bank_card}")
    private long bankCardTimeout;

    public BankCardCrudOperation(
            BankCardDao bankCardDao, BankCardBalanceHistoryDao bankCardBalanceHistoryDao, BankCardCache bankCardCache,
            BankCardBalanceHistoryCache bankCardBalanceHistoryCache
    ) {
        this.bankCardDao = bankCardDao;
        this.bankCardBalanceHistoryDao = bankCardBalanceHistoryDao;
        this.bankCardCache = bankCardCache;
        this.bankCardBalanceHistoryCache = bankCardBalanceHistoryCache;
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
        // 查询与账本相关的银行卡余额历史。
        List<LongIdKey> bankCardBalanceHistoryKeys = bankCardBalanceHistoryDao.lookup(
                BankCardBalanceHistoryMaintainService.CHILD_FOR_BANK_CARD, new Object[]{key}
        ).stream().map(BankCardBalanceHistory::getKey).collect(Collectors.toList());
        bankCardBalanceHistoryCache.batchDelete(bankCardBalanceHistoryKeys);
        bankCardBalanceHistoryDao.batchDelete(bankCardBalanceHistoryKeys);

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
        return bankCardCache.nonExists(keys) && bankCardDao.nonExists(keys);
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
