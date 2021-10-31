package com.dwarfeng.familyhelper.finance.impl.service.operation;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.*;
import com.dwarfeng.familyhelper.finance.stack.bean.key.PoabKey;
import com.dwarfeng.familyhelper.finance.stack.cache.*;
import com.dwarfeng.familyhelper.finance.stack.dao.*;
import com.dwarfeng.familyhelper.finance.stack.service.*;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountBookCrudOperation implements BatchCrudOperation<LongIdKey, AccountBook> {

    private final AccountBookDao accountBookDao;
    private final BankCardDao bankCardDao;
    private final FundChangeDao fundChangeDao;
    private final PoabDao poabDao;
    private final TotalBalanceHistoryDao totalBalanceHistoryDao;
    private final BankCardBalanceHistoryDao bankCardBalanceHistoryDao;

    private final AccountBookCache accountBookCache;
    private final BankCardCache bankCardCache;
    private final FundChangeCache fundChangeCache;
    private final PoabCache poabCache;
    private final TotalBalanceHistoryCache totalBalanceHistoryCache;
    private final BankCardBalanceHistoryCache bankCardBalanceHistoryCache;

    @Value("${cache.timeout.entity.account_book}")
    private long accountBookTimeout;

    public AccountBookCrudOperation(
            AccountBookDao accountBookDao, BankCardDao bankCardDao, FundChangeDao fundChangeDao, PoabDao poabDao,
            TotalBalanceHistoryDao totalBalanceHistoryDao, BankCardBalanceHistoryDao bankCardBalanceHistoryDao,
            AccountBookCache accountBookCache, BankCardCache bankCardCache, FundChangeCache fundChangeCache,
            PoabCache poabCache, TotalBalanceHistoryCache totalBalanceHistoryCache,
            BankCardBalanceHistoryCache bankCardBalanceHistoryCache
    ) {
        this.accountBookDao = accountBookDao;
        this.bankCardDao = bankCardDao;
        this.fundChangeDao = fundChangeDao;
        this.poabDao = poabDao;
        this.totalBalanceHistoryDao = totalBalanceHistoryDao;
        this.bankCardBalanceHistoryDao = bankCardBalanceHistoryDao;
        this.accountBookCache = accountBookCache;
        this.bankCardCache = bankCardCache;
        this.fundChangeCache = fundChangeCache;
        this.poabCache = poabCache;
        this.totalBalanceHistoryCache = totalBalanceHistoryCache;
        this.bankCardBalanceHistoryCache = bankCardBalanceHistoryCache;
    }

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

    @SuppressWarnings("DuplicatedCode")
    @Override
    public void delete(LongIdKey key) throws Exception {
        // 找到与账本相关的银行卡。
        List<LongIdKey> bankCardKeys = bankCardDao.lookup(
                BankCardMaintainService.CHILD_FOR_ACCOUNT_BOOK, new Object[]{key}
        ).stream().map(BankCard::getKey).collect(Collectors.toList());
        // 删除与账本相关的银行卡余额历史。
        List<LongIdKey> bankCardBalanceHistoryKeys = bankCardBalanceHistoryDao.lookup(
                BankCardBalanceHistoryMaintainService.CHILD_FOR_BANK_CARD_SET, new Object[]{bankCardKeys}
        ).stream().map(BankCardBalanceHistory::getKey).collect(Collectors.toList());
        bankCardBalanceHistoryCache.batchDelete(bankCardBalanceHistoryKeys);
        bankCardBalanceHistoryDao.batchDelete(bankCardBalanceHistoryKeys);
        // 删除与账本相关的银行卡。
        bankCardCache.batchDelete(bankCardKeys);
        bankCardDao.batchDelete(bankCardKeys);

        // 删除与账本相关的资金变更。
        List<LongIdKey> fundChangeKeys = fundChangeDao.lookup(
                FundChangeMaintainService.CHILD_FOR_ACCOUNT_BOOK, new Object[]{key}
        ).stream().map(FundChange::getKey).collect(Collectors.toList());
        fundChangeCache.batchDelete(fundChangeKeys);
        fundChangeDao.batchDelete(fundChangeKeys);

        // 删除与账本相关的账本权限。
        List<PoabKey> poabKeys = poabDao.lookup(PoabMaintainService.CHILD_FOR_ACCOUNT_BOOK, new Object[]{key})
                .stream().map(Poab::getKey).collect(Collectors.toList());
        poabCache.batchDelete(poabKeys);
        poabDao.batchDelete(poabKeys);

        // 删除与账本相关的总余额历史。
        List<LongIdKey> totalBalanceHistoryKeys = totalBalanceHistoryDao.lookup(
                TotalBalanceHistoryMaintainService.CHILD_FOR_ACCOUNT_BOOK, new Object[]{key}
        ).stream().map(TotalBalanceHistory::getKey).collect(Collectors.toList());
        totalBalanceHistoryCache.batchDelete(totalBalanceHistoryKeys);
        totalBalanceHistoryDao.batchDelete(totalBalanceHistoryKeys);

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
        return accountBookCache.nonExists(keys) && accountBookDao.nonExists(keys);
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
