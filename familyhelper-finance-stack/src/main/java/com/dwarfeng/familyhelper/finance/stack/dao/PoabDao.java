package com.dwarfeng.familyhelper.finance.stack.dao;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.Poab;
import com.dwarfeng.familyhelper.finance.stack.bean.key.PoabKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 账本权限数据访问层。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public interface PoabDao extends BatchBaseDao<PoabKey, Poab>, EntireLookupDao<Poab>,
        PresetLookupDao<Poab> {
}
