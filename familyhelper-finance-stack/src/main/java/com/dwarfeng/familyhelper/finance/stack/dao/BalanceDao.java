package com.dwarfeng.familyhelper.finance.stack.dao;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.Balance;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 余额数据访问层。
 *
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public interface BalanceDao extends BatchBaseDao<LongIdKey, Balance>, EntireLookupDao<Balance>,
        PresetLookupDao<Balance> {
}
