package com.dwarfeng.familyhelper.finance.impl.service;

import com.dwarfeng.familyhelper.finance.stack.handler.RemindDriveHandler;
import com.dwarfeng.familyhelper.finance.stack.handler.RemindDriveLocalCacheHandler;
import com.dwarfeng.familyhelper.finance.stack.handler.RemindDriver;
import com.dwarfeng.familyhelper.finance.stack.service.RemindDriveQosService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;

@Service
public class RemindDriveQosServiceImpl implements RemindDriveQosService {

    private final RemindDriveHandler remindDriveHandler;
    private final RemindDriveLocalCacheHandler remindDriveLocalCacheHandler;

    private final ServiceExceptionMapper sem;

    public RemindDriveQosServiceImpl(
            RemindDriveHandler remindDriveHandler,
            RemindDriveLocalCacheHandler remindDriveLocalCacheHandler,
            ServiceExceptionMapper sem
    ) {
        this.remindDriveHandler = remindDriveHandler;
        this.remindDriveLocalCacheHandler = remindDriveLocalCacheHandler;
        this.sem = sem;
    }

    @PreDestroy
    public void dispose() throws Exception {
        remindDriveHandler.stop();
        remindDriveHandler.offline();
    }

    @Override
    public boolean isOnline() throws ServiceException {
        try {
            return remindDriveHandler.isOnline();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("判断提醒驱动处理器是否上线时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void online() throws ServiceException {
        try {
            remindDriveHandler.online();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("上线提醒驱动处理器时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void offline() throws ServiceException {
        try {
            remindDriveHandler.offline();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("下线提醒驱动处理器时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public boolean isLockHolding() throws ServiceException {
        try {
            return remindDriveHandler.isLockHolding();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("判断提醒驱动处理器是否正在持有锁时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public boolean isStarted() throws ServiceException {
        try {
            return remindDriveHandler.isStarted();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("判断提醒驱动处理器是否启动时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void start() throws ServiceException {
        try {
            remindDriveHandler.start();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("提醒驱动处理器启动时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void stop() throws ServiceException {
        try {
            remindDriveHandler.stop();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("提醒驱动处理器停止时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public boolean isWorking() throws ServiceException {
        try {
            return remindDriveHandler.isWorking();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("判断提醒驱动处理器是否正在工作时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public RemindDriver getDriver(LongIdKey remindDriverInfoKey) throws ServiceException {
        try {
            return remindDriveLocalCacheHandler.get(remindDriverInfoKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse(
                    "获取指定的提醒驱动器信息对应的提醒驱动器时发生异常", LogLevel.WARN, e, sem
            );
        }
    }

    @Override
    public void clearLocalCache() throws ServiceException {
        try {
            remindDriveLocalCacheHandler.clear();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("清除本地缓存时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
