package com.dwarfeng.familyhelper.finance.stack.service;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.BankCardTypeIndicator;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;

/**
 * 银行卡类型指示器维护服务。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public interface BankCardTypeIndicatorMaintainService extends BatchCrudService<StringIdKey, BankCardTypeIndicator>,
        EntireLookupService<BankCardTypeIndicator> {
}
