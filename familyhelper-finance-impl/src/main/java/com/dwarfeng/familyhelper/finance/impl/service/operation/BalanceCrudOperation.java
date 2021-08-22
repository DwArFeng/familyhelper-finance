package com.dwarfeng.familyhelper.finance.impl.service.operation;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.Balance;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.BalanceItem;
import com.dwarfeng.familyhelper.finance.stack.cache.BalanceCache;
import com.dwarfeng.familyhelper.finance.stack.cache.BalanceItemCache;
import com.dwarfeng.familyhelper.finance.stack.dao.BalanceDao;
import com.dwarfeng.familyhelper.finance.stack.dao.BalanceItemDao;
import com.dwarfeng.familyhelper.finance.stack.service.BalanceItemMaintainService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BalanceCrudOperation implements BatchCrudOperation<LongIdKey, Balance> {

    @Autowired
    private BalanceDao balanceDao;
    @Autowired
    private BalanceItemDao balanceItemDao;

    @Autowired
    private BalanceCache balanceCache;
    @Autowired
    private BalanceItemCache balanceItemCache;

    @Value("${cache.timeout.entity.balance}")
    private long balanceTimeout;

    @Override
    public boolean exists(LongIdKey key) throws Exception {
        return balanceCache.exists(key) || balanceDao.exists(key);
    }

    @Override
    public Balance get(LongIdKey key) throws Exception {
        if (balanceCache.exists(key)) {
            return balanceCache.get(key);
        } else {
            if (!balanceDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            Balance balance = balanceDao.get(key);
            balanceCache.push(balance, balanceTimeout);
            return balance;
        }
    }

    @Override
    public LongIdKey insert(Balance balance) throws Exception {
        balanceCache.push(balance, balanceTimeout);
        return balanceDao.insert(balance);
    }

    @Override
    public void update(Balance balance) throws Exception {
        balanceCache.push(balance, balanceTimeout);
        balanceDao.update(balance);
    }

    @Override
    public void delete(LongIdKey key) throws Exception {
        //删除与余额相关的余额条目。
        List<LongIdKey> balanceItemKeys = balanceItemDao.lookup(
                        BalanceItemMaintainService.CHILD_FOR_BALANCE, new Object[]{key})
                .stream().map(BalanceItem::getKey).collect(Collectors.toList());
        balanceItemCache.batchDelete(balanceItemKeys);
        balanceItemDao.batchDelete(balanceItemKeys);

        // 删除余额实体自身。
        balanceDao.delete(key);
        balanceCache.delete(key);
    }

    @Override
    public boolean allExists(List<LongIdKey> keys) throws Exception {
        return balanceCache.allExists(keys) || balanceDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<LongIdKey> keys) throws Exception {
        return balanceCache.nonExists(keys) && balanceCache.nonExists(keys);
    }

    @Override
    public List<Balance> batchGet(List<LongIdKey> keys) throws Exception {
        if (balanceCache.allExists(keys)) {
            return balanceCache.batchGet(keys);
        } else {
            if (!balanceDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<Balance> balances = balanceDao.batchGet(keys);
            balanceCache.batchPush(balances, balanceTimeout);
            return balances;
        }
    }

    @Override
    public List<LongIdKey> batchInsert(List<Balance> balances) throws Exception {
        balanceCache.batchPush(balances, balanceTimeout);
        return balanceDao.batchInsert(balances);
    }

    @Override
    public void batchUpdate(List<Balance> balances) throws Exception {
        balanceCache.batchPush(balances, balanceTimeout);
        balanceDao.batchUpdate(balances);
    }

    @Override
    public void batchDelete(List<LongIdKey> keys) throws Exception {
        for (LongIdKey key : keys) {
            delete(key);
        }
    }
}
