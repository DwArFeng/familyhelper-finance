package com.dwarfeng.familyhelper.finance.stack.handler;

import com.dwarfeng.familyhelper.finance.stack.bean.dto.BillFile;
import com.dwarfeng.familyhelper.finance.stack.bean.dto.BillFileUploadInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 票据文件操作处理器。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface BillFileOperateHandler extends Handler {

    /**
     * 下载票据文件。
     *
     * @param userKey     执行用户主键。
     * @param billFileKey 票据文件主键。
     * @return 票据文件。
     * @throws HandlerException 处理器异常。
     */
    BillFile downloadBillFile(StringIdKey userKey, LongIdKey billFileKey) throws HandlerException;

    /**
     * 上传票据文件。
     *
     * @param userKey            执行用户主键。
     * @param billFileUploadInfo 票据文件上传信息。
     * @throws HandlerException 处理器异常。
     */
    void uploadBillFile(StringIdKey userKey, BillFileUploadInfo billFileUploadInfo) throws HandlerException;

    /**
     * 删除票据文件。
     *
     * @param userKey     执行用户主键。
     * @param billFileKey 票据文件主键。
     * @throws HandlerException 处理器异常。
     */
    void removeBillFile(StringIdKey userKey, LongIdKey billFileKey) throws HandlerException;
}
