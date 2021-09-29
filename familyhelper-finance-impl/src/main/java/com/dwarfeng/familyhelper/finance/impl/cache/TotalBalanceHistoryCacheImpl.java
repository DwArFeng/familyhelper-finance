package com.dwarfeng.familyhelper.finance.impl.cache;

import com.dwarfeng.familyhelper.finance.sdk.bean.entity.FastJsonTotalBalanceHistory;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.TotalBalanceHistory;
import com.dwarfeng.familyhelper.finance.stack.cache.TotalBalanceHistoryCache;
import com.dwarfeng.subgrade.impl.cache.RedisBatchBaseCache;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.SkipRecord;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.CacheException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class TotalBalanceHistoryCacheImpl implements TotalBalanceHistoryCache {

    private final RedisBatchBaseCache<LongIdKey, TotalBalanceHistory, FastJsonTotalBalanceHistory>
            totalBalanceHistoryBatchBaseDelegate;

    public TotalBalanceHistoryCacheImpl(
            RedisBatchBaseCache<LongIdKey, TotalBalanceHistory, FastJsonTotalBalanceHistory>
                    totalBalanceHistoryBatchBaseDelegate
    ) {
        this.totalBalanceHistoryBatchBaseDelegate = totalBalanceHistoryBatchBaseDelegate;
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean exists(LongIdKey key) throws CacheException {
        return totalBalanceHistoryBatchBaseDelegate.exists(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public TotalBalanceHistory get(LongIdKey key) throws CacheException {
        return totalBalanceHistoryBatchBaseDelegate.get(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void push(TotalBalanceHistory value, long timeout) throws CacheException {
        totalBalanceHistoryBatchBaseDelegate.push(value, timeout);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void delete(LongIdKey key) throws CacheException {
        totalBalanceHistoryBatchBaseDelegate.delete(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void clear() throws CacheException {
        totalBalanceHistoryBatchBaseDelegate.clear();
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean allExists(@SkipRecord List<LongIdKey> keys) throws CacheException {
        return totalBalanceHistoryBatchBaseDelegate.allExists(keys);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean nonExists(@SkipRecord List<LongIdKey> keys) throws CacheException {
        return totalBalanceHistoryBatchBaseDelegate.nonExists(keys);
    }

    @Override
    @BehaviorAnalyse
    @SkipRecord
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public List<TotalBalanceHistory> batchGet(@SkipRecord List<LongIdKey> keys) throws CacheException {
        return totalBalanceHistoryBatchBaseDelegate.batchGet(keys);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void batchPush(@SkipRecord List<TotalBalanceHistory> entities, long timeout) throws CacheException {
        totalBalanceHistoryBatchBaseDelegate.batchPush(entities, timeout);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void batchDelete(@SkipRecord List<LongIdKey> keys) throws CacheException {
        totalBalanceHistoryBatchBaseDelegate.batchDelete(keys);
    }
}
