package com.dwarfeng.familyhelper.finance.stack.service;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.FundChangeTypeIndicator;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;

/**
 * 资金改变类型指示器维护服务。
 *
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public interface FundChangeTypeIndicatorMaintainService extends BatchCrudService<StringIdKey, FundChangeTypeIndicator>,
        EntireLookupService<FundChangeTypeIndicator> {
}
