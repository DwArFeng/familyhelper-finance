package com.dwarfeng.familyhelper.finance.impl.handler;

import com.dwarfeng.familyhelper.finance.impl.util.FtpConstants;
import com.dwarfeng.familyhelper.finance.stack.bean.dto.BillFile;
import com.dwarfeng.familyhelper.finance.stack.bean.dto.BillFileUploadInfo;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.BillFileInfo;
import com.dwarfeng.familyhelper.finance.stack.handler.BillFileOperateHandler;
import com.dwarfeng.familyhelper.finance.stack.service.BillFileInfoMaintainService;
import com.dwarfeng.ftp.handler.FtpHandler;
import com.dwarfeng.subgrade.stack.bean.key.KeyFetcher;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

@Component
public class BillFileOperateHandlerImpl implements BillFileOperateHandler {

    private final BillFileInfoMaintainService billFileInfoMaintainService;
    private final FtpHandler ftpHandler;

    private final KeyFetcher<LongIdKey> keyFetcher;

    private final HandlerValidator handlerValidator;

    public BillFileOperateHandlerImpl(
            BillFileInfoMaintainService billFileInfoMaintainService,
            FtpHandler ftpHandler,
            KeyFetcher<LongIdKey> keyFetcher,
            HandlerValidator handlerValidator
    ) {
        this.billFileInfoMaintainService = billFileInfoMaintainService;
        this.ftpHandler = ftpHandler;
        this.keyFetcher = keyFetcher;
        this.handlerValidator = handlerValidator;
    }

    @Override
    public BillFile downloadBillFile(StringIdKey userKey, LongIdKey billFileKey) throws HandlerException {
        try {
            // 1. 确认用户存在。
            handlerValidator.makeSureUserExists(userKey);

            // 2. 确认票据文件存在。
            handlerValidator.makeSureBillFileExists(billFileKey);

            // 3. 获取票据文件对应的票据，并确认用户有权限操作票据。
            BillFileInfo billFileInfo = billFileInfoMaintainService.get(billFileKey);
            handlerValidator.makeSureUserInspectPermittedForFundChange(userKey, billFileInfo.getFundChangeKey());

            // 4. 下载票据文件。
            byte[] content = ftpHandler.getFileContent(
                    new String[]{FtpConstants.PATH_BILL_FILE}, getFileName(billFileKey)
            );

            // 6. 拼接 BillFile 并返回。
            return new BillFile(billFileInfo.getOriginName(), content);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public void uploadBillFile(StringIdKey userKey, BillFileUploadInfo billFileUploadInfo) throws HandlerException {
        try {
            // 1. 确认用户存在。
            handlerValidator.makeSureUserExists(userKey);

            // 2. 确认票据文件所属的票据存在。
            LongIdKey fundChangeKey = billFileUploadInfo.getFundChangeKey();
            handlerValidator.makeSureFundChangeExists(fundChangeKey);

            // 3. 确认用户有权限操作票据。
            handlerValidator.makeSureUserModifyPermittedForFundChange(userKey, fundChangeKey);

            // 4. 分配主键。
            LongIdKey billFileKey = keyFetcher.fetchKey();

            // 5. 票据文件内容并存储（覆盖）。
            byte[] content = billFileUploadInfo.getContent();
            ftpHandler.storeFile(new String[]{FtpConstants.PATH_BILL_FILE}, getFileName(billFileKey), content);

            // 6. 查询当前资金变更的票据的最高 maxIndex，令新的 index = maxIndex + 1。
            BillFileInfo lastBillFileInfo = billFileInfoMaintainService.lookupFirst(
                    BillFileInfoMaintainService.CHILD_FOR_FUND_CHANGE_INDEX_DESC, new Object[]{fundChangeKey}
            );
            int index = Objects.isNull(lastBillFileInfo) ? 0 : lastBillFileInfo.getIndex() + 1;

            // 6. 根据 billFileUploadInfo 构造 BillFileInfo，插入或更新。
            Date currentDate = new Date();
            // 映射属性。
            BillFileInfo billFileInfo = new BillFileInfo();
            billFileInfo.setKey(billFileKey);
            billFileInfo.setFundChangeKey(fundChangeKey);
            billFileInfo.setOriginName(billFileUploadInfo.getOriginName());
            billFileInfo.setIndex(index);
            billFileInfo.setLength(billFileUploadInfo.getContent().length);
            billFileInfo.setCreatedDate(currentDate);
            billFileInfo.setRemark("通过 familyhelper-finance 服务上传/更新票据文件");
            billFileInfoMaintainService.insertOrUpdate(billFileInfo);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public void removeBillFile(StringIdKey userKey, LongIdKey billFileKey) throws HandlerException {
        try {
            // 1. 确认用户存在。
            handlerValidator.makeSureUserExists(userKey);

            // 2. 确认票据文件存在。
            handlerValidator.makeSureBillFileExists(billFileKey);

            // 3. 获取票据文件对应的票据，并确认用户有权限操作票据。
            BillFileInfo billFileInfo = billFileInfoMaintainService.get(billFileKey);
            handlerValidator.makeSureUserModifyPermittedForFundChange(userKey, billFileInfo.getFundChangeKey());

            // 4. 如果存在 BillFile 文件，则删除。
            if (ftpHandler.existsFile(new String[]{FtpConstants.PATH_BILL_FILE}, getFileName(billFileKey))) {
                ftpHandler.deleteFile(new String[]{FtpConstants.PATH_BILL_FILE}, getFileName(billFileKey));
            }

            // 5. 如果存在 BillFileInfo 实体，则删除。
            billFileInfoMaintainService.deleteIfExists(billFileKey);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    private String getFileName(LongIdKey billFileKey) {
        return Long.toString(billFileKey.getLongId());
    }
}
