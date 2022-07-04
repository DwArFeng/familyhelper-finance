package com.dwarfeng.familyhelper.finance.stack.service;

import com.dwarfeng.familyhelper.finance.stack.bean.dto.BillFile;
import com.dwarfeng.familyhelper.finance.stack.bean.dto.BillFileUploadInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

/**
 * 票据文件操作服务。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface BillFileOperateService extends Service {

    /**
     * 下载票据文件。
     *
     * @param userKey     执行用户主键。
     * @param billFileKey 票据文件主键。
     * @return 票据文件。
     * @throws ServiceException 服务异常。
     */
    BillFile downloadBillFile(StringIdKey userKey, LongIdKey billFileKey) throws ServiceException;

    /**
     * 上传票据文件。
     *
     * @param userKey            执行用户主键。
     * @param billFileUploadInfo 票据文件上传信息。
     * @throws ServiceException 服务异常。
     */
    void uploadBillFile(StringIdKey userKey, BillFileUploadInfo billFileUploadInfo) throws ServiceException;

    /**
     * 删除票据文件。
     *
     * @param userKey     执行用户主键。
     * @param billFileKey 票据文件主键。
     * @throws ServiceException 服务异常。
     */
    void removeBillFile(StringIdKey userKey, LongIdKey billFileKey) throws ServiceException;
}
