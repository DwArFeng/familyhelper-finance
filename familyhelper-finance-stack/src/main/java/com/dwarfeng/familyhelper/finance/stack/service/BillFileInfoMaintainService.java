package com.dwarfeng.familyhelper.finance.stack.service;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.BillFileInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 票据文件信息维护服务。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
public interface BillFileInfoMaintainService extends BatchCrudService<LongIdKey, BillFileInfo>,
        EntireLookupService<BillFileInfo>, PresetLookupService<BillFileInfo> {

    String CHILD_FOR_FUND_CHANGE = "child_for_fund_change";
    String CHILD_FOR_FUND_CHANGE_INDEX_ASC = "child_for_bill_origin_name_asc";
    String CHILD_FOR_FUND_CHANGE_INDEX_DESC = "child_for_bill_origin_name_desc";
}
