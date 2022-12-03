package com.dwarfeng.familyhelper.finance.impl.service;

import com.dwarfeng.familyhelper.finance.stack.handler.RemindHandler;
import com.dwarfeng.familyhelper.finance.stack.service.RemindService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.stereotype.Service;

@Service
public class RemindServiceImpl implements RemindService {

    private final RemindHandler remindHandler;

    private final ServiceExceptionMapper sem;

    public RemindServiceImpl(RemindHandler remindHandler, ServiceExceptionMapper sem) {
        this.remindHandler = remindHandler;
        this.sem = sem;
    }

    @Override
    public void remind(LongIdKey remindDriverInfoKey) throws ServiceException {
        try {
            remindHandler.remind(remindDriverInfoKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("指定的部件调用执行动作时发生异常", LogLevel.WARN, sem, e);
        }
    }
}
