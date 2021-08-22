package com.dwarfeng.familyhelper.finance.stack.cache;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.FundRepository;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 资金仓库缓存。
 *
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public interface FundRepositoryCache extends BatchBaseCache<LongIdKey, FundRepository> {
}
