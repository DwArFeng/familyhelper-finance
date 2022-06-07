package com.dwarfeng.familyhelper.finance.stack.cache;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.UrgeSetting;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 敦促设置缓存。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
public interface UrgeSettingCache extends BatchBaseCache<LongIdKey, UrgeSetting> {
}
