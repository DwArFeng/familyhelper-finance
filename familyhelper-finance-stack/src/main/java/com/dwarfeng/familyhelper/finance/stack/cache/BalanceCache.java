package com.dwarfeng.familyhelper.finance.stack.cache;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.Balance;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 余额缓存。
 *
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public interface BalanceCache extends BatchBaseCache<LongIdKey, Balance> {
}
