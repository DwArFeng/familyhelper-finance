package com.dwarfeng.familyhelper.finance.stack.service;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.RemindDriverSupport;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 提醒驱动器支持维护服务。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
public interface RemindDriverSupportMaintainService extends BatchCrudService<StringIdKey, RemindDriverSupport>,
        EntireLookupService<RemindDriverSupport>, PresetLookupService<RemindDriverSupport> {

    String ID_LIKE = "id_like";
    String LABEL_LIKE = "label_like";

    /**
     * 重置调度器支持。
     *
     * @throws ServiceException 服务异常。
     */
    void reset() throws ServiceException;
}
