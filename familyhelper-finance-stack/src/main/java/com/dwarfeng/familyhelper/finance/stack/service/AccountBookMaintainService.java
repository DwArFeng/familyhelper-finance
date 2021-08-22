package com.dwarfeng.familyhelper.finance.stack.service;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.AccountBook;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 账本维护服务。
 *
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public interface AccountBookMaintainService extends BatchCrudService<LongIdKey, AccountBook>,
        EntireLookupService<AccountBook>, PresetLookupService<AccountBook> {

    String NAME_LIKE = "name_like";
}
