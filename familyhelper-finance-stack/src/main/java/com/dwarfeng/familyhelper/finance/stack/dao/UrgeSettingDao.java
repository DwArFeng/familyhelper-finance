package com.dwarfeng.familyhelper.finance.stack.dao;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.UrgeSetting;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 敦促设置数据访问层。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
public interface UrgeSettingDao extends BatchBaseDao<LongIdKey, UrgeSetting>, EntireLookupDao<UrgeSetting>,
        PresetLookupDao<UrgeSetting> {
}

