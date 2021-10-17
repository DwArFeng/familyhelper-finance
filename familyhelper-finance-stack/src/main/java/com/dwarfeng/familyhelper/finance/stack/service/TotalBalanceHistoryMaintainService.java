package com.dwarfeng.familyhelper.finance.stack.service;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.TotalBalanceHistory;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 总余额历史维护服务。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public interface TotalBalanceHistoryMaintainService extends BatchCrudService<LongIdKey, TotalBalanceHistory>,
        EntireLookupService<TotalBalanceHistory>, PresetLookupService<TotalBalanceHistory> {

    String CHILD_FOR_ACCOUNT_BOOK = "child_for_account_book";
    String CHILD_FOR_ACCOUNT_BOOK_DESC = "child_for_account_book_desc";
    String BETWEEN = "between";
    String BETWEEN_DESC = "between_desc";
    String CHILD_FOR_ACCOUNT_BOOK_BETWEEN = "child_for_account_book_between";
    String CHILD_FOR_ACCOUNT_BOOK_BETWEEN_DESC = "child_for_account_book_between_desc";
}
