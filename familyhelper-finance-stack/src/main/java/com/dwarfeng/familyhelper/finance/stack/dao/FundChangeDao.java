package com.dwarfeng.familyhelper.finance.stack.dao;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.FundChange;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 资金改变访问层。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface FundChangeDao extends BatchBaseDao<LongIdKey, FundChange>, EntireLookupDao<FundChange>,
        PresetLookupDao<FundChange> {
}
