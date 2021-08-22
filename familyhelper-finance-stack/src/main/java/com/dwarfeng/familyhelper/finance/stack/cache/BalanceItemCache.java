package com.dwarfeng.familyhelper.finance.stack.cache;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.BalanceItem;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 余额条目缓存。
 *
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public interface BalanceItemCache extends BatchBaseCache<LongIdKey, BalanceItem> {
}
