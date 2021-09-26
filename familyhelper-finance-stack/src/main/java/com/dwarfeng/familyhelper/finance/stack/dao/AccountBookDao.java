package com.dwarfeng.familyhelper.finance.stack.dao;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.AccountBook;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 账本数据访问层。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface AccountBookDao extends BatchBaseDao<LongIdKey, AccountBook>, EntireLookupDao<AccountBook>,
        PresetLookupDao<AccountBook> {
}
