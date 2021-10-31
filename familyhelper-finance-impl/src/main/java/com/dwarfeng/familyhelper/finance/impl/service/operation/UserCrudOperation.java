package com.dwarfeng.familyhelper.finance.impl.service.operation;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.Poab;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.User;
import com.dwarfeng.familyhelper.finance.stack.bean.key.PoabKey;
import com.dwarfeng.familyhelper.finance.stack.cache.PoabCache;
import com.dwarfeng.familyhelper.finance.stack.cache.UserCache;
import com.dwarfeng.familyhelper.finance.stack.dao.PoabDao;
import com.dwarfeng.familyhelper.finance.stack.dao.UserDao;
import com.dwarfeng.familyhelper.finance.stack.service.PoabMaintainService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserCrudOperation implements BatchCrudOperation<StringIdKey, User> {

    private final UserDao userDao;
    private final PoabDao poabDao;

    private final UserCache userCache;
    private final PoabCache poabCache;

    @Value("${cache.timeout.entity.account_book}")
    private long userTimeout;

    public UserCrudOperation(UserDao userDao, PoabDao poabDao, UserCache userCache, PoabCache poabCache) {
        this.userDao = userDao;
        this.poabDao = poabDao;
        this.userCache = userCache;
        this.poabCache = poabCache;
    }

    @Override
    public boolean exists(StringIdKey key) throws Exception {
        return userCache.exists(key) || userDao.exists(key);
    }

    @Override
    public User get(StringIdKey key) throws Exception {
        if (userCache.exists(key)) {
            return userCache.get(key);
        } else {
            if (!userDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            User user = userDao.get(key);
            userCache.push(user, userTimeout);
            return user;
        }
    }

    @Override
    public StringIdKey insert(User user) throws Exception {
        userCache.push(user, userTimeout);
        return userDao.insert(user);
    }

    @Override
    public void update(User user) throws Exception {
        userCache.push(user, userTimeout);
        userDao.update(user);
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public void delete(StringIdKey key) throws Exception {
        // 删除与账本相关的账本权限。
        List<PoabKey> poabKeys = poabDao.lookup(PoabMaintainService.CHILD_FOR_USER, new Object[]{key})
                .stream().map(Poab::getKey).collect(Collectors.toList());
        poabCache.batchDelete(poabKeys);
        poabDao.batchDelete(poabKeys);

        // 删除账本实体自身。
        userDao.delete(key);
        userCache.delete(key);
    }

    @Override
    public boolean allExists(List<StringIdKey> keys) throws Exception {
        return userCache.allExists(keys) || userDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<StringIdKey> keys) throws Exception {
        return userCache.nonExists(keys) && userDao.nonExists(keys);
    }

    @Override
    public List<User> batchGet(List<StringIdKey> keys) throws Exception {
        if (userCache.allExists(keys)) {
            return userCache.batchGet(keys);
        } else {
            if (!userDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<User> users = userDao.batchGet(keys);
            userCache.batchPush(users, userTimeout);
            return users;
        }
    }

    @Override
    public List<StringIdKey> batchInsert(List<User> users) throws Exception {
        userCache.batchPush(users, userTimeout);
        return userDao.batchInsert(users);
    }

    @Override
    public void batchUpdate(List<User> users) throws Exception {
        userCache.batchPush(users, userTimeout);
        userDao.batchUpdate(users);
    }

    @Override
    public void batchDelete(List<StringIdKey> keys) throws Exception {
        for (StringIdKey key : keys) {
            delete(key);
        }
    }
}
