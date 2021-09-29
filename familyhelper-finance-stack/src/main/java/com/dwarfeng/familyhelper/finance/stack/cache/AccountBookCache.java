package com.dwarfeng.familyhelper.finance.stack.cache;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.AccountBook;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 账本缓存。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public interface AccountBookCache extends BatchBaseCache<LongIdKey, AccountBook> {
}
