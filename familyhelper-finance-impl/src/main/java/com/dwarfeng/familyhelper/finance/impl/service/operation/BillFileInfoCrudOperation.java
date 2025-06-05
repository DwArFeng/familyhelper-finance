package com.dwarfeng.familyhelper.finance.impl.service.operation;

import com.dwarfeng.familyhelper.finance.impl.handler.FtpPathResolver;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.BillFileInfo;
import com.dwarfeng.familyhelper.finance.stack.cache.BillFileInfoCache;
import com.dwarfeng.familyhelper.finance.stack.dao.BillFileInfoDao;
import com.dwarfeng.ftp.handler.FtpHandler;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BillFileInfoCrudOperation implements BatchCrudOperation<LongIdKey, BillFileInfo> {

    private final BillFileInfoDao billFileInfoDao;
    private final BillFileInfoCache billFileInfoCache;

    private final FtpHandler ftpHandler;

    private final FtpPathResolver ftpPathResolver;

    @Value("${cache.timeout.entity.bill_file_info}")
    private long billFileInfoTimeout;

    public BillFileInfoCrudOperation(
            BillFileInfoDao billFileInfoDao,
            BillFileInfoCache billFileInfoCache,
            FtpHandler ftpHandler,
            FtpPathResolver ftpPathResolver
    ) {
        this.billFileInfoDao = billFileInfoDao;
        this.billFileInfoCache = billFileInfoCache;
        this.ftpHandler = ftpHandler;
        this.ftpPathResolver = ftpPathResolver;
    }

    @Override
    public boolean exists(LongIdKey key) throws Exception {
        return billFileInfoCache.exists(key) || billFileInfoDao.exists(key);
    }

    @Override
    public BillFileInfo get(LongIdKey key) throws Exception {
        if (billFileInfoCache.exists(key)) {
            return billFileInfoCache.get(key);
        } else {
            if (!billFileInfoDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            BillFileInfo billFileInfo = billFileInfoDao.get(key);
            billFileInfoCache.push(billFileInfo, billFileInfoTimeout);
            return billFileInfo;
        }
    }

    @Override
    public LongIdKey insert(BillFileInfo billFileInfo) throws Exception {
        billFileInfoCache.push(billFileInfo, billFileInfoTimeout);
        return billFileInfoDao.insert(billFileInfo);
    }

    @Override
    public void update(BillFileInfo billFileInfo) throws Exception {
        billFileInfoCache.push(billFileInfo, billFileInfoTimeout);
        billFileInfoDao.update(billFileInfo);
    }

    @Override
    public void delete(LongIdKey key) throws Exception {
        // 如果存在票据文件，则删除票据文件。
        if (ftpHandler.existsFile(
                ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_PATH_BILL_FILE), getFileName(key)
        )) {
            ftpHandler.deleteFile(
                    ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_PATH_BILL_FILE), getFileName(key)
            );
        }

        // 删除票据文件信息实体自身。
        billFileInfoCache.delete(key);
        billFileInfoDao.delete(key);
    }

    private String getFileName(LongIdKey billFileKey) {
        return Long.toString(billFileKey.getLongId());
    }

    @Override
    public boolean allExists(List<LongIdKey> keys) throws Exception {
        return billFileInfoCache.allExists(keys) || billFileInfoDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<LongIdKey> keys) throws Exception {
        return billFileInfoCache.nonExists(keys) && billFileInfoDao.nonExists(keys);
    }

    @Override
    public List<BillFileInfo> batchGet(List<LongIdKey> keys) throws Exception {
        if (billFileInfoCache.allExists(keys)) {
            return billFileInfoCache.batchGet(keys);
        } else {
            if (!billFileInfoDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<BillFileInfo> billFileInfos = billFileInfoDao.batchGet(keys);
            billFileInfoCache.batchPush(billFileInfos, billFileInfoTimeout);
            return billFileInfos;
        }
    }

    @Override
    public List<LongIdKey> batchInsert(List<BillFileInfo> billFileInfos) throws Exception {
        billFileInfoCache.batchPush(billFileInfos, billFileInfoTimeout);
        return billFileInfoDao.batchInsert(billFileInfos);
    }

    @Override
    public void batchUpdate(List<BillFileInfo> billFileInfos) throws Exception {
        billFileInfoCache.batchPush(billFileInfos, billFileInfoTimeout);
        billFileInfoDao.batchUpdate(billFileInfos);
    }

    @Override
    public void batchDelete(List<LongIdKey> keys) throws Exception {
        for (LongIdKey key : keys) {
            delete(key);
        }
    }
}
