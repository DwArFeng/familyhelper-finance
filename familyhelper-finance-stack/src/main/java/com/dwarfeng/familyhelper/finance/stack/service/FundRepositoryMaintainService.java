package com.dwarfeng.familyhelper.finance.stack.service;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.FundRepository;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 资金仓库维护服务。
 *
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public interface FundRepositoryMaintainService extends BatchCrudService<LongIdKey, FundRepository>,
        EntireLookupService<FundRepository>, PresetLookupService<FundRepository> {

    String CHILD_FOR_ACCOUNT_BOOK = "child_for_account_book";
}
