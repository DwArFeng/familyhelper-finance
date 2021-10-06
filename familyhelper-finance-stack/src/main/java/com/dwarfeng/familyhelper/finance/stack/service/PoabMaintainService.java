package com.dwarfeng.familyhelper.finance.stack.service;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.Poab;
import com.dwarfeng.familyhelper.finance.stack.bean.key.PoabKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 账本维护服务。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public interface PoabMaintainService extends BatchCrudService<PoabKey, Poab>,
        EntireLookupService<Poab>, PresetLookupService<Poab> {

    String CHILD_FOR_ACCOUNT_BOOK = "child_for_account_book";
    String CHILD_FOR_USER = "child_for_user";
    String CHILD_FOR_ACCOUNT_BOOK_PERMISSION_LEVEL_EQUALS = "child_for_account_book_permission_level_equals";
    String CHILD_FOR_USER_PERMISSION_LEVEL_EQUALS = "child_for_user_permission_level_equals";
}
