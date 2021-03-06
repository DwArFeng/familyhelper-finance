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
 * @since 1.0.0
 */
public interface FundChangeMaintainService extends BatchCrudService<LongIdKey, FundChange>,
        EntireLookupService<FundChange>, PresetLookupService<FundChange> {

    String CHILD_FOR_ACCOUNT_BOOK = "child_for_account_book";
    String CHILD_FOR_ACCOUNT_BOOK_DESC = "child_for_account_book_desc";
    String CHILD_FOR_ACCOUNT_BOOK_BETWEEN = "child_for_account_book_between";
    String CHILD_FOR_ACCOUNT_BOOK_BETWEEN_DESC = "child_for_account_book_between_desc";
    String CHILD_FOR_ACCOUNT_BOOK_TYPE_EQUALS = "child_for_account_book_type_equals";
    String CHILD_FOR_ACCOUNT_BOOK_TYPE_EQUALS_DESC = "child_for_account_book_type_equals_desc";
}
