package com.dwarfeng.familyhelper.finance.impl.service;

import com.dwarfeng.familyhelper.finance.stack.bean.dto.BankCardBalanceRecordInfo;
import com.dwarfeng.familyhelper.finance.stack.handler.BalanceOperateHandler;
import com.dwarfeng.familyhelper.finance.stack.service.BalanceOperateService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BalanceOperateServiceImpl implements BalanceOperateService {

    private final BalanceOperateHandler balanceOperateHandler;

    private final ServiceExceptionMapper sem;

    public BalanceOperateServiceImpl(BalanceOperateHandler balanceOperateHandler, ServiceExceptionMapper sem) {
        this.balanceOperateHandler = balanceOperateHandler;
        this.sem = sem;
    }

    @Override
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void recordBankCardBalance(StringIdKey userKey, BankCardBalanceRecordInfo bankCardBalanceRecordInfo)
            throws ServiceException {
        try {
            balanceOperateHandler.recordBankCardBalance(userKey, bankCardBalanceRecordInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("记录银行卡的余额时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void recordCommit(StringIdKey userKey, LongIdKey accountBookKey) throws ServiceException {
        try {
            balanceOperateHandler.recordCommit(userKey, accountBookKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("提交银行卡的余额时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void rollbackBankCard(StringIdKey userKey, LongIdKey bankCardKey) throws ServiceException {
        try {
            balanceOperateHandler.rollbackBankCard(userKey, bankCardKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("回滚单个银行卡的余额时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void rollbackAll(StringIdKey userKey, LongIdKey accountBookKey) throws ServiceException {
        try {
            balanceOperateHandler.rollbackAll(userKey, accountBookKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("回滚整个账本的所有余额时发生异常", LogLevel.WARN, sem, e);
        }
    }
}
