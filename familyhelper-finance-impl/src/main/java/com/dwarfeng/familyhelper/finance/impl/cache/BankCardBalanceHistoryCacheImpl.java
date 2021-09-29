package com.dwarfeng.familyhelper.finance.impl.cache;

import com.dwarfeng.familyhelper.finance.sdk.bean.entity.FastJsonBankCardBalanceHistory;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.BankCardBalanceHistory;
import com.dwarfeng.familyhelper.finance.stack.cache.BankCardBalanceHistoryCache;
import com.dwarfeng.subgrade.impl.cache.RedisBatchBaseCache;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.SkipRecord;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.CacheException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class BankCardBalanceHistoryCacheImpl implements BankCardBalanceHistoryCache {

    private final RedisBatchBaseCache<LongIdKey, BankCardBalanceHistory, FastJsonBankCardBalanceHistory>
            bankCardBalanceHistoryBatchBaseDelegate;

    public BankCardBalanceHistoryCacheImpl(
            RedisBatchBaseCache<LongIdKey, BankCardBalanceHistory, FastJsonBankCardBalanceHistory>
                    bankCardBalanceHistoryBatchBaseDelegate
    ) {
        this.bankCardBalanceHistoryBatchBaseDelegate = bankCardBalanceHistoryBatchBaseDelegate;
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean exists(LongIdKey key) throws CacheException {
        return bankCardBalanceHistoryBatchBaseDelegate.exists(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public BankCardBalanceHistory get(LongIdKey key) throws CacheException {
        return bankCardBalanceHistoryBatchBaseDelegate.get(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void push(BankCardBalanceHistory value, long timeout) throws CacheException {
        bankCardBalanceHistoryBatchBaseDelegate.push(value, timeout);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void delete(LongIdKey key) throws CacheException {
        bankCardBalanceHistoryBatchBaseDelegate.delete(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void clear() throws CacheException {
        bankCardBalanceHistoryBatchBaseDelegate.clear();
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean allExists(@SkipRecord List<LongIdKey> keys) throws CacheException {
        return bankCardBalanceHistoryBatchBaseDelegate.allExists(keys);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean nonExists(@SkipRecord List<LongIdKey> keys) throws CacheException {
        return bankCardBalanceHistoryBatchBaseDelegate.nonExists(keys);
    }

    @Override
    @BehaviorAnalyse
    @SkipRecord
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public List<BankCardBalanceHistory> batchGet(@SkipRecord List<LongIdKey> keys) throws CacheException {
        return bankCardBalanceHistoryBatchBaseDelegate.batchGet(keys);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void batchPush(@SkipRecord List<BankCardBalanceHistory> entities, long timeout) throws CacheException {
        bankCardBalanceHistoryBatchBaseDelegate.batchPush(entities, timeout);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void batchDelete(@SkipRecord List<LongIdKey> keys) throws CacheException {
        bankCardBalanceHistoryBatchBaseDelegate.batchDelete(keys);
    }
}
