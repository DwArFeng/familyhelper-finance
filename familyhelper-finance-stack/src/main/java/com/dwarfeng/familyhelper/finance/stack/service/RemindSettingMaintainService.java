package com.dwarfeng.familyhelper.finance.stack.service;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.RemindSetting;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 提醒设置维护服务。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
public interface RemindSettingMaintainService extends BatchCrudService<LongIdKey, RemindSetting>,
        EntireLookupService<RemindSetting>, PresetLookupService<RemindSetting> {

    String ENABLED = "enabled";
}
