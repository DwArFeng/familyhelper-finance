package com.dwarfeng.familyhelper.finance.stack.dao;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.FundChangeTypeIndicator;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;

/**
 * 资金改变类型指示器数据访问层。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface FundChangeTypeIndicatorDao extends BatchBaseDao<StringIdKey, FundChangeTypeIndicator>,
        EntireLookupDao<FundChangeTypeIndicator> {
}
