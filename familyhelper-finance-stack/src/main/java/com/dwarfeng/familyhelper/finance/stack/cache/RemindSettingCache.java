package com.dwarfeng.familyhelper.finance.stack.cache;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.RemindSetting;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 提醒设置缓存。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
public interface RemindSettingCache extends BatchBaseCache<LongIdKey, RemindSetting> {
}
