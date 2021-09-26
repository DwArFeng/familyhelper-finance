package com.dwarfeng.familyhelper.finance.stack.service;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.BankCard;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 银行卡服务。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public interface BankCardMaintainService extends BatchCrudService<LongIdKey, BankCard>,
        EntireLookupService<BankCard>, PresetLookupService<BankCard> {

    String CHILD_FOR_ACCOUNT_BOOK = "child_for_account_book";
    String NAME_LIKE = "name_like";
}
