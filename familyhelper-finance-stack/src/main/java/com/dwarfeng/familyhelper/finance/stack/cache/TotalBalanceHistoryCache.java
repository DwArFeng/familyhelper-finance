package com.dwarfeng.familyhelper.finance.stack.cache;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.TotalBalanceHistory;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 总余额历史缓存。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public interface TotalBalanceHistoryCache extends BatchBaseCache<LongIdKey, TotalBalanceHistory> {
}
