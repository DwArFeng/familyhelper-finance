package com.dwarfeng.familyhelper.finance.stack.dao;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.BankCardTypeIndicator;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;

/**
 * 银行卡类型指示器数据访问层。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public interface BankCardTypeIndicatorDao extends BatchBaseDao<StringIdKey, BankCardTypeIndicator>,
        EntireLookupDao<BankCardTypeIndicator> {
}
