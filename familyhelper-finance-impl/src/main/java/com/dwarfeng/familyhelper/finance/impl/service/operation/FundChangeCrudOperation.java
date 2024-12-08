package com.dwarfeng.familyhelper.finance.impl.service.operation;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.BillFileInfo;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.FundChange;
import com.dwarfeng.familyhelper.finance.stack.cache.FundChangeCache;
import com.dwarfeng.familyhelper.finance.stack.dao.BillFileInfoDao;
import com.dwarfeng.familyhelper.finance.stack.dao.FundChangeDao;
import com.dwarfeng.familyhelper.finance.stack.service.BillFileInfoMaintainService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FundChangeCrudOperation implements BatchCrudOperation<LongIdKey, FundChange> {

    private final FundChangeDao fundChangeDao;
    private final FundChangeCache fundChangeCache;

    private final BillFileInfoCrudOperation billFileInfoCrudOperation;
    private final BillFileInfoDao billFileInfoDao;

    @Value("${cache.timeout.entity.fund_change}")
    private long fundChangeTimeout;

    public FundChangeCrudOperation(
            FundChangeDao fundChangeDao, FundChangeCache fundChangeCache,
            BillFileInfoCrudOperation billFileInfoCrudOperation, BillFileInfoDao billFileInfoDao
    ) {
        this.fundChangeDao = fundChangeDao;
        this.fundChangeCache = fundChangeCache;
        this.billFileInfoCrudOperation = billFileInfoCrudOperation;
        this.billFileInfoDao = billFileInfoDao;
    }

    @Override
    public boolean exists(LongIdKey key) throws Exception {
        return fundChangeCache.exists(key) || fundChangeDao.exists(key);
    }

    @Override
    public FundChange get(LongIdKey key) throws Exception {
        if (fundChangeCache.exists(key)) {
            return fundChangeCache.get(key);
        } else {
            if (!fundChangeDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            FundChange fundChange = fundChangeDao.get(key);
            fundChangeCache.push(fundChange, fundChangeTimeout);
            return fundChange;
        }
    }

    @Override
    public LongIdKey insert(FundChange fundChange) throws Exception {
        fundChangeCache.push(fundChange, fundChangeTimeout);
        return fundChangeDao.insert(fundChange);
    }

    @Override
    public void update(FundChange fundChange) throws Exception {
        fundChangeCache.push(fundChange, fundChangeTimeout);
        fundChangeDao.update(fundChange);
    }

    @Override
    public void delete(LongIdKey key) throws Exception {
        // 查询与账本相关的银行卡余额历史。
        List<LongIdKey> billFileInfoKeys = billFileInfoDao.lookup(
                BillFileInfoMaintainService.CHILD_FOR_FUND_CHANGE, new Object[]{key}
        ).stream().map(BillFileInfo::getKey).collect(Collectors.toList());
        billFileInfoCrudOperation.batchDelete(billFileInfoKeys);

        // 删除账本实体自身。
        fundChangeCache.delete(key);
        fundChangeDao.delete(key);
    }

    @Override
    public boolean allExists(List<LongIdKey> keys) throws Exception {
        return fundChangeCache.allExists(keys) || fundChangeDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<LongIdKey> keys) throws Exception {
        return fundChangeCache.nonExists(keys) && fundChangeDao.nonExists(keys);
    }

    @Override
    public List<FundChange> batchGet(List<LongIdKey> keys) throws Exception {
        if (fundChangeCache.allExists(keys)) {
            return fundChangeCache.batchGet(keys);
        } else {
            if (!fundChangeDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<FundChange> fundChanges = fundChangeDao.batchGet(keys);
            fundChangeCache.batchPush(fundChanges, fundChangeTimeout);
            return fundChanges;
        }
    }

    @Override
    public List<LongIdKey> batchInsert(List<FundChange> fundChanges) throws Exception {
        fundChangeCache.batchPush(fundChanges, fundChangeTimeout);
        return fundChangeDao.batchInsert(fundChanges);
    }

    @Override
    public void batchUpdate(List<FundChange> fundChanges) throws Exception {
        fundChangeCache.batchPush(fundChanges, fundChangeTimeout);
        fundChangeDao.batchUpdate(fundChanges);
    }

    @Override
    public void batchDelete(List<LongIdKey> keys) throws Exception {
        for (LongIdKey key : keys) {
            delete(key);
        }
    }
}
