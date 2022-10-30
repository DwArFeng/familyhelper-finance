package com.dwarfeng.familyhelper.finance.stack.dao;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.RemindSetting;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 提醒设置数据访问层。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
public interface RemindSettingDao extends BatchBaseDao<LongIdKey, RemindSetting>, EntireLookupDao<RemindSetting>,
        PresetLookupDao<RemindSetting> {
}

