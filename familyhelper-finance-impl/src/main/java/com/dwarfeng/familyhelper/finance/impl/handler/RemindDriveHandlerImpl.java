package com.dwarfeng.familyhelper.finance.impl.handler;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.RemindDriverInfo;
import com.dwarfeng.familyhelper.finance.stack.exception.RemindDriverException;
import com.dwarfeng.familyhelper.finance.stack.handler.RemindDriveHandler;
import com.dwarfeng.familyhelper.finance.stack.handler.RemindDriveLocalCacheHandler;
import com.dwarfeng.familyhelper.finance.stack.handler.RemindDriver;
import com.dwarfeng.familyhelper.finance.stack.service.RemindDriverInfoMaintainService;
import com.dwarfeng.subgrade.impl.handler.CuratorDistributedLockHandler;
import com.dwarfeng.subgrade.impl.handler.Worker;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.apache.curator.framework.CuratorFramework;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class RemindDriveHandlerImpl implements RemindDriveHandler {

    private final CuratorDistributedLockHandler handler;

    public RemindDriveHandlerImpl(
            CuratorFramework curatorFramework,
            @Value("${curator.latch_path.remind_drive.leader_latch}") String leaserLatchPath,
            RemindDriveWorker remindDriveWorker
    ) {
        handler = new CuratorDistributedLockHandler(curatorFramework, leaserLatchPath, remindDriveWorker);
    }

    @BehaviorAnalyse
    @Override
    public boolean isOnline() {
        return handler.isOnline();
    }

    @BehaviorAnalyse
    @Override
    public void online() throws HandlerException {
        handler.online();
    }

    @BehaviorAnalyse
    @Override
    public void offline() throws HandlerException {
        handler.offline();
    }

    @BehaviorAnalyse
    @Override
    public boolean isStarted() {
        return handler.isStarted();
    }

    @BehaviorAnalyse
    @Override
    public void start() throws HandlerException {
        handler.start();
    }

    @BehaviorAnalyse
    @Override
    public void stop() throws HandlerException {
        handler.stop();
    }

    @BehaviorAnalyse
    @Override
    public boolean isLockHolding() {
        return handler.isLockHolding();
    }

    @BehaviorAnalyse
    @Override
    public boolean isWorking() {
        return handler.isWorking();
    }

    @Component
    public static class RemindDriveWorker implements Worker {

        private static final Logger LOGGER = LoggerFactory.getLogger(RemindDriveWorker.class);

        private final RemindDriverInfoMaintainService remindDriverInfoMaintainService;

        private final RemindDriveLocalCacheHandler remindDriveLocalCacheHandler;

        private final Set<RemindDriver> usedRemindDrivers = new HashSet<>();

        public RemindDriveWorker(
                RemindDriverInfoMaintainService remindDriverInfoMaintainService,
                RemindDriveLocalCacheHandler remindDriveLocalCacheHandler
        ) {
            this.remindDriverInfoMaintainService = remindDriverInfoMaintainService;
            this.remindDriveLocalCacheHandler = remindDriveLocalCacheHandler;
        }

        @Override
        public void work() throws Exception {
            // 记录日志。
            LOGGER.info("提醒驱动器开始工作...");

            List<RemindDriverInfo> remindDriverInfos = remindDriverInfoMaintainService.lookupAsList(
                    RemindDriverInfoMaintainService.ENABLED, new Object[0]
            );

            // 注册所有提醒驱动成功标志。
            boolean successFlag = true;
            // 获取所有提醒驱动信息。
            for (RemindDriverInfo remindDriverInfo : remindDriverInfos) {
                RemindDriver remindDriver = remindDriveLocalCacheHandler.get(remindDriverInfo.getKey());
                if (Objects.isNull(remindDriver)) {
                    throw new RemindDriverException("无法在本地缓存中找到有效的提醒驱动上下文: " + remindDriverInfo.getKey());
                }
                if (!registerRemindDriver(remindDriver, remindDriverInfo)) {
                    successFlag = false;
                }
            }
            if (successFlag) {
                LOGGER.info("所有提醒驱动信息注册成功");
            } else {
                LOGGER.warn("至少一条提醒驱动信息注册失败，请查看警报日志以了解详细原因");
            }
        }

        private boolean registerRemindDriver(RemindDriver remindDriver, RemindDriverInfo remindDriverInfo) {
            try {
                remindDriver.register(remindDriverInfo);
                usedRemindDrivers.add(remindDriver);
                return true;
            } catch (Exception e) {
                LOGGER.warn("提醒驱动信息 {} 注册失败，将忽略此条注册信息", remindDriverInfo, e);
                return false;
            }
        }

        @Override
        public void rest() throws Exception {
            // 记录日志。
            LOGGER.info("提醒驱动器停止工作...");

            for (Iterator<RemindDriver> iterator = usedRemindDrivers.iterator(); iterator.hasNext(); ) {
                RemindDriver remindDriver = iterator.next();
                remindDriver.unregisterAll();
                iterator.remove();
            }
        }
    }
}
