package com.dwarfeng.familyhelper.finance.stack.dao;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.BankCardBalanceHistory;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 银行卡余额历史数据访问层。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public interface BankCardBalanceHistoryDao extends BatchBaseDao<LongIdKey, BankCardBalanceHistory>,
        EntireLookupDao<BankCardBalanceHistory>, PresetLookupDao<BankCardBalanceHistory> {
}
