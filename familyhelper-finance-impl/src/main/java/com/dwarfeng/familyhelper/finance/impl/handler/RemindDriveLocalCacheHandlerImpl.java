package com.dwarfeng.familyhelper.finance.impl.handler;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.RemindDriverInfo;
import com.dwarfeng.familyhelper.finance.stack.handler.RemindDriveLocalCacheHandler;
import com.dwarfeng.familyhelper.finance.stack.handler.RemindDriver;
import com.dwarfeng.familyhelper.finance.stack.handler.RemindDriverHandler;
import com.dwarfeng.familyhelper.finance.stack.service.RemindDriverInfoMaintainService;
import com.dwarfeng.subgrade.impl.handler.Fetcher;
import com.dwarfeng.subgrade.impl.handler.GeneralLocalCacheHandler;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class RemindDriveLocalCacheHandlerImpl implements RemindDriveLocalCacheHandler {

    private final GeneralLocalCacheHandler<LongIdKey, RemindDriver> handler;

    public RemindDriveLocalCacheHandlerImpl(RemindDriverFetcher remindDriverFetcher) {
        handler = new GeneralLocalCacheHandler<>(remindDriverFetcher);
    }

    @BehaviorAnalyse
    @Override
    public boolean exists(LongIdKey key) throws HandlerException {
        return handler.exists(key);
    }

    @BehaviorAnalyse
    @Override
    public RemindDriver get(LongIdKey key) throws HandlerException {
        return handler.get(key);
    }

    @BehaviorAnalyse
    @Override
    public boolean remove(LongIdKey key) {
        return handler.remove(key);
    }

    @BehaviorAnalyse
    @Override
    public void clear() {
        handler.clear();
    }

    @Component
    public static class RemindDriverFetcher implements Fetcher<LongIdKey, RemindDriver> {

        private final RemindDriverInfoMaintainService remindDriverInfoMaintainService;

        private final RemindDriverHandler remindDriverHandler;

        public RemindDriverFetcher(
                RemindDriverInfoMaintainService remindDriverInfoMaintainService,
                RemindDriverHandler remindDriverHandler
        ) {
            this.remindDriverInfoMaintainService = remindDriverInfoMaintainService;
            this.remindDriverHandler = remindDriverHandler;
        }

        @Override
        @BehaviorAnalyse
        @Transactional(
                transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class
        )
        public boolean exists(LongIdKey key) throws Exception {
            return remindDriverInfoMaintainService.exists(key);
        }

        @Override
        @BehaviorAnalyse
        @Transactional(
                transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class
        )
        public RemindDriver fetch(LongIdKey key) throws Exception {
            RemindDriverInfo remindDriverInfo = remindDriverInfoMaintainService.get(key);
            return remindDriverHandler.find(remindDriverInfo.getType());
        }
    }
}
