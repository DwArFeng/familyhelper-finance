package com.dwarfeng.familyhelper.finance.stack.cache;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.Poab;
import com.dwarfeng.familyhelper.finance.stack.bean.key.PoabKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 账本权限缓存。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public interface PoabCache extends BatchBaseCache<PoabKey, Poab> {
}
