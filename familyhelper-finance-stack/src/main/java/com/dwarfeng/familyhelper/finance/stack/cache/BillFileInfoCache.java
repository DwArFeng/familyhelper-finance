package com.dwarfeng.familyhelper.finance.stack.cache;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.BillFileInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 票据文件信息缓存。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
public interface BillFileInfoCache extends BatchBaseCache<LongIdKey, BillFileInfo> {
}
