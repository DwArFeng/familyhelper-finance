package com.dwarfeng.familyhelper.finance.stack.service;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.RemindDriverInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 提醒驱动器信息维护服务。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
public interface RemindDriverInfoMaintainService extends BatchCrudService<LongIdKey, RemindDriverInfo>,
        EntireLookupService<RemindDriverInfo>, PresetLookupService<RemindDriverInfo> {

    String CHILD_FOR_ACCOUNT_BOOK = "child_for_account_book";
    String ENABLED = "enabled";
}
