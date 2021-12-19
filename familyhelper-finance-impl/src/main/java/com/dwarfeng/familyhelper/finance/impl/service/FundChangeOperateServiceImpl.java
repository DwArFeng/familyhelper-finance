package com.dwarfeng.familyhelper.finance.impl.service;

import com.dwarfeng.familyhelper.finance.stack.bean.dto.FundChangeRecordInfo;
import com.dwarfeng.familyhelper.finance.stack.bean.dto.FundChangeUpdateInfo;
import com.dwarfeng.familyhelper.finance.stack.handler.FundChangeOperateHandler;
import com.dwarfeng.familyhelper.finance.stack.service.FundChangeOperateService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.stereotype.Service;

@Service
public class FundChangeOperateServiceImpl implements FundChangeOperateService {

    private final FundChangeOperateHandler fundChangeOperateHandler;

    private final ServiceExceptionMapper sem;

    public FundChangeOperateServiceImpl(FundChangeOperateHandler fundChangeOperateHandler, ServiceExceptionMapper sem) {
        this.fundChangeOperateHandler = fundChangeOperateHandler;
        this.sem = sem;
    }

    @Override
    public LongIdKey recordFundChange(StringIdKey userKey, FundChangeRecordInfo fundChangeRecordInfo)
            throws ServiceException {
        try {
            return fundChangeOperateHandler.recordFundChange(userKey, fundChangeRecordInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("记录资金变更时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    public void updateFundChange(StringIdKey userKey, FundChangeUpdateInfo fundChangeUpdateInfo)
            throws ServiceException {
        try {
            fundChangeOperateHandler.updateFundChange(userKey, fundChangeUpdateInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("更新资金变更时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    public void removeFundChange(StringIdKey userKey, LongIdKey fundChangeKey) throws ServiceException {
        try {
            fundChangeOperateHandler.removeFundChange(userKey, fundChangeKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("删除资金变更时发生异常", LogLevel.WARN, sem, e);
        }
    }
}
