package com.dwarfeng.familyhelper.finance.impl.service;

import com.dwarfeng.familyhelper.finance.stack.bean.dto.BankCardCreateInfo;
import com.dwarfeng.familyhelper.finance.stack.handler.BankCardOperateHandler;
import com.dwarfeng.familyhelper.finance.stack.service.BankCardOperateService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.stereotype.Service;

@Service
public class BankCardOperateServiceImpl implements BankCardOperateService {

    private final BankCardOperateHandler bankCardOperateHandler;

    private final ServiceExceptionMapper sem;

    public BankCardOperateServiceImpl(BankCardOperateHandler bankCardOperateHandler, ServiceExceptionMapper sem) {
        this.bankCardOperateHandler = bankCardOperateHandler;
        this.sem = sem;
    }

    @Override
    public LongIdKey createBankCard(
            StringIdKey userKey, LongIdKey accountBookKey, BankCardCreateInfo bankCardCreateInfo
    ) throws ServiceException {
        try {
            return bankCardOperateHandler.createBankCard(userKey, accountBookKey, bankCardCreateInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("创建银行卡时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    public void removeBankCard(StringIdKey userKey, LongIdKey bankCardKey) throws ServiceException {
        try {
            bankCardOperateHandler.removeBankCard(userKey, bankCardKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("移除银行卡时发生异常", LogLevel.WARN, sem, e);
        }
    }
}
