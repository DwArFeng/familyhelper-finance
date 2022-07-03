package com.dwarfeng.familyhelper.finance.stack.dao;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.BillFileInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 票据文件信息数据访问层。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
public interface BillFileInfoDao extends BatchBaseDao<LongIdKey, BillFileInfo>, EntireLookupDao<BillFileInfo>,
        PresetLookupDao<BillFileInfo> {
}
