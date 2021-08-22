package com.dwarfeng.familyhelper.finance.stack.service;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.FundChange;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 资金改变维护服务。
 *
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public interface FundChangeMaintainService extends BatchCrudService<LongIdKey, FundChange>,
        EntireLookupService<FundChange>, PresetLookupService<FundChange> {

    String CHILD_FOR_ACCOUNT_BOOK = "child_for_account_book";
}
