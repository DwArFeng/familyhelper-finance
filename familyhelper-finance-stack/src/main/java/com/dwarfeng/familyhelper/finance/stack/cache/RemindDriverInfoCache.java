package com.dwarfeng.familyhelper.finance.stack.cache;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.RemindDriverInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 提醒驱动器信息缓存。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
public interface RemindDriverInfoCache extends BatchBaseCache<LongIdKey, RemindDriverInfo> {
}
