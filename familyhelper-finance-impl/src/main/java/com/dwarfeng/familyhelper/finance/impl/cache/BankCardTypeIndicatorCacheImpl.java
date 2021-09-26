package com.dwarfeng.familyhelper.finance.impl.cache;

import com.dwarfeng.familyhelper.finance.sdk.bean.entity.FastJsonBankCardTypeIndicator;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.BankCardTypeIndicator;
import com.dwarfeng.familyhelper.finance.stack.cache.BankCardTypeIndicatorCache;
import com.dwarfeng.subgrade.impl.cache.RedisBatchBaseCache;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.SkipRecord;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.CacheException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class BankCardTypeIndicatorCacheImpl implements BankCardTypeIndicatorCache {

    private final RedisBatchBaseCache<StringIdKey, BankCardTypeIndicator, FastJsonBankCardTypeIndicator>
            bankCardTypeIndicatorBatchBaseDelegate;

    public BankCardTypeIndicatorCacheImpl(
            RedisBatchBaseCache<StringIdKey, BankCardTypeIndicator, FastJsonBankCardTypeIndicator>
                    bankCardTypeIndicatorBatchBaseDelegate
    ) {
        this.bankCardTypeIndicatorBatchBaseDelegate = bankCardTypeIndicatorBatchBaseDelegate;
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean exists(StringIdKey key) throws CacheException {
        return bankCardTypeIndicatorBatchBaseDelegate.exists(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public BankCardTypeIndicator get(StringIdKey key) throws CacheException {
        return bankCardTypeIndicatorBatchBaseDelegate.get(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void push(BankCardTypeIndicator value, long timeout) throws CacheException {
        bankCardTypeIndicatorBatchBaseDelegate.push(value, timeout);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void delete(StringIdKey key) throws CacheException {
        bankCardTypeIndicatorBatchBaseDelegate.delete(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void clear() throws CacheException {
        bankCardTypeIndicatorBatchBaseDelegate.clear();
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean allExists(@SkipRecord List<StringIdKey> keys) throws CacheException {
        return bankCardTypeIndicatorBatchBaseDelegate.allExists(keys);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean nonExists(@SkipRecord List<StringIdKey> keys) throws CacheException {
        return bankCardTypeIndicatorBatchBaseDelegate.nonExists(keys);
    }

    @Override
    @BehaviorAnalyse
    @SkipRecord
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public List<BankCardTypeIndicator> batchGet(@SkipRecord List<StringIdKey> keys) throws CacheException {
        return bankCardTypeIndicatorBatchBaseDelegate.batchGet(keys);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void batchPush(@SkipRecord List<BankCardTypeIndicator> entities, long timeout) throws CacheException {
        bankCardTypeIndicatorBatchBaseDelegate.batchPush(entities, timeout);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void batchDelete(@SkipRecord List<StringIdKey> keys) throws CacheException {
        bankCardTypeIndicatorBatchBaseDelegate.batchDelete(keys);
    }
}
