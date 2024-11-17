package com.dwarfeng.familyhelper.finance.impl.service;

import com.dwarfeng.familyhelper.finance.stack.bean.dto.BillFile;
import com.dwarfeng.familyhelper.finance.stack.bean.dto.BillFileUploadInfo;
import com.dwarfeng.familyhelper.finance.stack.handler.BillFileOperateHandler;
import com.dwarfeng.familyhelper.finance.stack.service.BillFileOperateService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.stereotype.Service;

@Service
public class BillFileOperateServiceImpl implements BillFileOperateService {

    private final BillFileOperateHandler billFileOperateHandler;

    private final ServiceExceptionMapper sem;

    public BillFileOperateServiceImpl(BillFileOperateHandler billFileOperateHandler, ServiceExceptionMapper sem) {
        this.billFileOperateHandler = billFileOperateHandler;
        this.sem = sem;
    }

    @Override
    public BillFile downloadBillFile(StringIdKey userKey, LongIdKey billFileKey) throws ServiceException {
        try {
            return billFileOperateHandler.downloadBillFile(userKey, billFileKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("下载票据文件时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void uploadBillFile(StringIdKey userKey, BillFileUploadInfo billFileUploadInfo) throws ServiceException {
        try {
            billFileOperateHandler.uploadBillFile(userKey, billFileUploadInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("上传票据文件时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void removeBillFile(StringIdKey userKey, LongIdKey billFileKey) throws ServiceException {
        try {
            billFileOperateHandler.removeBillFile(userKey, billFileKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("删除票据文件时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
