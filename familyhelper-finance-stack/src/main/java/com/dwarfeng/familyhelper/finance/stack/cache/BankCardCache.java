package com.dwarfeng.familyhelper.finance.stack.cache;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.BankCard;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 银行卡缓存。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public interface BankCardCache extends BatchBaseCache<LongIdKey, BankCard> {
}
