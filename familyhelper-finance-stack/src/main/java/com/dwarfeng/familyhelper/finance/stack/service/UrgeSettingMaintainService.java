package com.dwarfeng.familyhelper.finance.stack.service;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.UrgeSetting;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 敦促设置维护服务。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
public interface UrgeSettingMaintainService extends BatchCrudService<LongIdKey, UrgeSetting>,
        EntireLookupService<UrgeSetting>, PresetLookupService<UrgeSetting> {

    String ENABLED = "enabled";
}
