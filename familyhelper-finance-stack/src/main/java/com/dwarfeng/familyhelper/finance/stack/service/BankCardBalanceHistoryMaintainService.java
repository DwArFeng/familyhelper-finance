package com.dwarfeng.familyhelper.finance.stack.service;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.BankCardBalanceHistory;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 银行卡余额历史维护服务。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public interface BankCardBalanceHistoryMaintainService extends BatchCrudService<LongIdKey, BankCardBalanceHistory>,
        EntireLookupService<BankCardBalanceHistory>, PresetLookupService<BankCardBalanceHistory> {

    String CHILD_FOR_BANK_CARD = "child_for_bank_card";
    String BETWEEN = "between";
    String CHILD_FOR_BANK_CARD_BETWEEN = "child_for_bank_card_between";
    String BETWEEN_DESC = "between_desc";
    String CHILD_FOR_BANK_CARD_BETWEEN_DESC = "child_for_bank_card_between_desc";
    String CHILD_FOR_BANK_CARD_SET = "child_for_bank_card_set";
}
