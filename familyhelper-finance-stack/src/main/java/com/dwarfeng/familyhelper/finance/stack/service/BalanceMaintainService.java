package com.dwarfeng.familyhelper.finance.stack.service;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.Balance;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 资金维护服务。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface BalanceMaintainService extends BatchCrudService<LongIdKey, Balance>, EntireLookupService<Balance>,
        PresetLookupService<Balance> {

    String CHILD_FOR_ACCOUNT_BOOK = "child_for_account_book";
}
