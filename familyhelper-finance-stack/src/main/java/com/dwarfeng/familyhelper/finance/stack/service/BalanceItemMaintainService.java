package com.dwarfeng.familyhelper.finance.stack.service;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.BalanceItem;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 资金条目维护服务。
 *
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public interface BalanceItemMaintainService extends BatchCrudService<LongIdKey, BalanceItem>,
        EntireLookupService<BalanceItem>, PresetLookupService<BalanceItem> {

    String CHILD_FOR_BALANCE = "child_for_balance";
    String CHILD_FOR_FUND_REPOSITORY = "child_for_fund_repository";
    String CHILD_FOR_BALANCE_SET = "child_for_balance_set";
    String CHILD_FOR_FUND_REPOSITORY_SET = "child_for_fund_repository_set";
}
