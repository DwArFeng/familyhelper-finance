package com.dwarfeng.familyhelper.finance.impl.service.operation;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.*;
import com.dwarfeng.familyhelper.finance.stack.cache.*;
import com.dwarfeng.familyhelper.finance.stack.dao.*;
import com.dwarfeng.familyhelper.finance.stack.service.BalanceItemMaintainService;
import com.dwarfeng.familyhelper.finance.stack.service.BalanceMaintainService;
import com.dwarfeng.familyhelper.finance.stack.service.FundChangeMaintainService;
import com.dwarfeng.familyhelper.finance.stack.service.FundRepositoryMaintainService;
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
public class AccountBookCrudOperation implements BatchCrudOperation<LongIdKey, AccountBook> {

    @Autowired
    private AccountBookDao accountBookDao;
    @Autowired
    private BalanceItemDao balanceItemDao;
    @Autowired
    private BalanceDao balanceDao;
    @Autowired
    private FundChangeDao fundChangeDao;
    @Autowired
    private FundRepositoryDao fundRepositoryDao;

    @Autowired
    private AccountBookCache accountBookCache;
    @Autowired
    private BalanceItemCache balanceItemCache;
    @Autowired
    private BalanceCache balanceCache;
    @Autowired
    private FundChangeCache fundChangeCache;
    @Autowired
    private FundRepositoryCache fundRepositoryCache;

    @Value("${cache.timeout.entity.account_book}")
    private long accountBookTimeout;

    @Override
    public boolean exists(LongIdKey key) throws Exception {
        return accountBookCache.exists(key) || accountBookDao.exists(key);
    }

    @Override
    public AccountBook get(LongIdKey key) throws Exception {
        if (accountBookCache.exists(key)) {
            return accountBookCache.get(key);
        } else {
            if (!accountBookDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            AccountBook accountBook = accountBookDao.get(key);
            accountBookCache.push(accountBook, accountBookTimeout);
            return accountBook;
        }
    }

    @Override
    public LongIdKey insert(AccountBook accountBook) throws Exception {
        accountBookCache.push(accountBook, accountBookTimeout);
        return accountBookDao.insert(accountBook);
    }

    @Override
    public void update(AccountBook accountBook) throws Exception {
        accountBookCache.push(accountBook, accountBookTimeout);
        accountBookDao.update(accountBook);
    }

    @Override
    public void delete(LongIdKey key) throws Exception {
        // 查询与账本相关的余额。
        List<LongIdKey> balanceKeys = balanceDao.lookup(
                        BalanceMaintainService.CHILD_FOR_ACCOUNT_BOOK, new Object[]{key})
                .stream().map(Balance::getKey).collect(Collectors.toList());
        // 查询与账本相关的资金仓库。
        List<LongIdKey> fundRepositoryKeys = fundRepositoryDao.lookup(
                        FundRepositoryMaintainService.CHILD_FOR_ACCOUNT_BOOK, new Object[]{key})
                .stream().map(FundRepository::getKey).collect(Collectors.toList());

        // 根据相关的余额和资金变更查询相关的余额条目，并删除。
        List<LongIdKey> balanceItemKeys1 = balanceItemDao.lookup(
                BalanceItemMaintainService.CHILD_FOR_BALANCE_SET, new Object[]{balanceKeys}
        ).stream().map(BalanceItem::getKey).collect(Collectors.toList());
        balanceItemCache.batchDelete(balanceItemKeys1);
        balanceItemDao.batchDelete(balanceItemKeys1);
        List<LongIdKey> balanceItemKeys2 = balanceItemDao.lookup(
                BalanceItemMaintainService.CHILD_FOR_FUND_REPOSITORY_SET, new Object[]{fundRepositoryKeys}
        ).stream().map(BalanceItem::getKey).collect(Collectors.toList());
        balanceItemCache.batchDelete(balanceItemKeys2);
        balanceItemDao.batchDelete(balanceItemKeys2);

        // 删除与账本相关的余额。
        balanceCache.batchDelete(balanceKeys);
        balanceDao.batchDelete(balanceKeys);

        // 删除与账本相关的资金仓库。
        fundRepositoryCache.batchDelete(fundRepositoryKeys);
        fundRepositoryDao.batchDelete(fundRepositoryKeys);

        // 查询与账本相关的资金变更。
        List<LongIdKey> fundChangeKeys = fundChangeDao.lookup(
                        FundChangeMaintainService.CHILD_FOR_ACCOUNT_BOOK, new Object[]{key})
                .stream().map(FundChange::getKey).collect(Collectors.toList());
        fundChangeCache.batchDelete(fundChangeKeys);
        fundChangeDao.batchDelete(fundChangeKeys);

        // 删除账本实体自身。
        accountBookDao.delete(key);
        accountBookCache.delete(key);
    }

    @Override
    public boolean allExists(List<LongIdKey> keys) throws Exception {
        return accountBookCache.allExists(keys) || accountBookDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<LongIdKey> keys) throws Exception {
        return accountBookCache.nonExists(keys) && accountBookCache.nonExists(keys);
    }

    @Override
    public List<AccountBook> batchGet(List<LongIdKey> keys) throws Exception {
        if (accountBookCache.allExists(keys)) {
            return accountBookCache.batchGet(keys);
        } else {
            if (!accountBookDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<AccountBook> accountBooks = accountBookDao.batchGet(keys);
            accountBookCache.batchPush(accountBooks, accountBookTimeout);
            return accountBooks;
        }
    }

    @Override
    public List<LongIdKey> batchInsert(List<AccountBook> accountBooks) throws Exception {
        accountBookCache.batchPush(accountBooks, accountBookTimeout);
        return accountBookDao.batchInsert(accountBooks);
    }

    @Override
    public void batchUpdate(List<AccountBook> accountBooks) throws Exception {
        accountBookCache.batchPush(accountBooks, accountBookTimeout);
        accountBookDao.batchUpdate(accountBooks);
    }

    @Override
    public void batchDelete(List<LongIdKey> keys) throws Exception {
        for (LongIdKey key : keys) {
            delete(key);
        }
    }
}
