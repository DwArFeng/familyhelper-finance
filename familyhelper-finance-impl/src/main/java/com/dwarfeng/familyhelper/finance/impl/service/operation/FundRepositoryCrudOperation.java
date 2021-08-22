package com.dwarfeng.familyhelper.finance.impl.service.operation;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.BalanceItem;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.FundRepository;
import com.dwarfeng.familyhelper.finance.stack.cache.BalanceItemCache;
import com.dwarfeng.familyhelper.finance.stack.cache.FundRepositoryCache;
import com.dwarfeng.familyhelper.finance.stack.dao.BalanceItemDao;
import com.dwarfeng.familyhelper.finance.stack.dao.FundRepositoryDao;
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
public class FundRepositoryCrudOperation implements BatchCrudOperation<LongIdKey, FundRepository> {

    @Autowired
    private FundRepositoryDao fundRepositoryDao;
    @Autowired
    private BalanceItemDao balanceItemDao;

    @Autowired
    private FundRepositoryCache fundRepositoryCache;
    @Autowired
    private BalanceItemCache balanceItemCache;

    @Value("${cache.timeout.entity.fund_repository}")
    private long fundRepositoryTimeout;

    @Override
    public boolean exists(LongIdKey key) throws Exception {
        return fundRepositoryCache.exists(key) || fundRepositoryDao.exists(key);
    }

    @Override
    public FundRepository get(LongIdKey key) throws Exception {
        if (fundRepositoryCache.exists(key)) {
            return fundRepositoryCache.get(key);
        } else {
            if (!fundRepositoryDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            FundRepository fundRepository = fundRepositoryDao.get(key);
            fundRepositoryCache.push(fundRepository, fundRepositoryTimeout);
            return fundRepository;
        }
    }

    @Override
    public LongIdKey insert(FundRepository fundRepository) throws Exception {
        fundRepositoryCache.push(fundRepository, fundRepositoryTimeout);
        return fundRepositoryDao.insert(fundRepository);
    }

    @Override
    public void update(FundRepository fundRepository) throws Exception {
        fundRepositoryCache.push(fundRepository, fundRepositoryTimeout);
        fundRepositoryDao.update(fundRepository);
    }

    @Override
    public void delete(LongIdKey key) throws Exception {
        //取消与账本相关的余额条目的关联。
        List<BalanceItem> balanceItems = balanceItemDao.lookup(
                BalanceItemMaintainService.CHILD_FOR_FUND_REPOSITORY, new Object[]{key});
        balanceItems.forEach(b -> b.setFundRepositoryKey(null));
        balanceItemCache.batchDelete(
                balanceItems.stream().map(BalanceItem::getKey).collect(Collectors.toList())
        );
        balanceItemDao.batchUpdate(balanceItems);

        // 删除资金仓库实体自身。
        fundRepositoryDao.delete(key);
        fundRepositoryCache.delete(key);
    }

    @Override
    public boolean allExists(List<LongIdKey> keys) throws Exception {
        return fundRepositoryCache.allExists(keys) || fundRepositoryDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<LongIdKey> keys) throws Exception {
        return fundRepositoryCache.nonExists(keys) && fundRepositoryCache.nonExists(keys);
    }

    @Override
    public List<FundRepository> batchGet(List<LongIdKey> keys) throws Exception {
        if (fundRepositoryCache.allExists(keys)) {
            return fundRepositoryCache.batchGet(keys);
        } else {
            if (!fundRepositoryDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<FundRepository> fundRepositorys = fundRepositoryDao.batchGet(keys);
            fundRepositoryCache.batchPush(fundRepositorys, fundRepositoryTimeout);
            return fundRepositorys;
        }
    }

    @Override
    public List<LongIdKey> batchInsert(List<FundRepository> fundRepositorys) throws Exception {
        fundRepositoryCache.batchPush(fundRepositorys, fundRepositoryTimeout);
        return fundRepositoryDao.batchInsert(fundRepositorys);
    }

    @Override
    public void batchUpdate(List<FundRepository> fundRepositorys) throws Exception {
        fundRepositoryCache.batchPush(fundRepositorys, fundRepositoryTimeout);
        fundRepositoryDao.batchUpdate(fundRepositorys);
    }

    @Override
    public void batchDelete(List<LongIdKey> keys) throws Exception {
        for (LongIdKey key : keys) {
            delete(key);
        }
    }
}
