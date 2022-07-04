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

    private static final long serialVersionUID = -4465535669215747933L;

    private LongIdKey fundChangeKey;
    private String originName;
    private byte[] content;

    public BillFileUploadInfo() {
    }

    public BillFileUploadInfo(LongIdKey fundChangeKey, String originName, byte[] content) {
        this.fundChangeKey = fundChangeKey;
        this.originName = originName;
        this.content = content;
    }

    public LongIdKey getFundChangeKey() {
        return fundChangeKey;
    }

    public void setFundChangeKey(LongIdKey fundChangeKey) {
        this.fundChangeKey = fundChangeKey;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
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
                ", originName='" + originName + '\'' +
                ", content=" + Arrays.toString(content) +
                '}';
    }
}
