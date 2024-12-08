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

    /**
     * 获取展示用的资金变更实体。
     *
     * <p>
     * 返回指定账本拥有的资金变更实体，且满足查询条件。
     *
     * <p>
     * 参数列表：
     * <ol>
     *     <li>LongIdKey 账本的键。</li>
     *     <li>String 资金变更实体的变更类型，如果值为空（blank)，则不参与查询。</li>
     *     <li>String 资金变更实体的备注的模糊匹配字符串，如果值为空（blank)，则不参与查询。</li>
     * </ol>
     * 返回的数据按照 happenedDate（发生日期）降序排列。
     *
     * @since 1.5.0
     */
    String CHILD_FOR_ACCOUNT_BOOK_WITH_CONDITION_DISPLAY = "child_for_account_book_with_condition_display";
}
