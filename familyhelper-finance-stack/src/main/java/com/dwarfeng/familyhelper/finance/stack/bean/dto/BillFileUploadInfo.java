package com.dwarfeng.familyhelper.finance.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.util.Arrays;

/**
 * 票据文件上传信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class BillFileUploadInfo implements Dto {

    private static final long serialVersionUID = -1700467209585773672L;

    private LongIdKey fundChangeKey;
    private byte[] content;

    public BillFileUploadInfo() {
    }

    public BillFileUploadInfo(LongIdKey fundChangeKey, byte[] content) {
        this.fundChangeKey = fundChangeKey;
        this.content = content;
    }

    public LongIdKey getFundChangeKey() {
        return fundChangeKey;
    }

    public void setFundChangeKey(LongIdKey fundChangeKey) {
        this.fundChangeKey = fundChangeKey;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "BillFileUploadInfo{" +
                "fundChangeKey=" + fundChangeKey +
                ", content=" + Arrays.toString(content) +
                '}';
    }
}
