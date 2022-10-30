package com.dwarfeng.familyhelper.finance.stack.dao;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.RemindDriverInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 提醒驱动器信息数据访问层。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
public interface RemindDriverInfoDao extends BatchBaseDao<LongIdKey, RemindDriverInfo>,
        EntireLookupDao<RemindDriverInfo>, PresetLookupDao<RemindDriverInfo> {
}
