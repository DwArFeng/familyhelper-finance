package com.dwarfeng.familyhelper.finance.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.BillFileInfo;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Date;
import java.util.Objects;

/**
 * FastJson 票据文件信息。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
public class FastJsonBillFileInfo implements Bean {

    private static final long serialVersionUID = -2935267321325353201L;

    public static FastJsonBillFileInfo of(BillFileInfo billFileInfo) {
        if (Objects.isNull(billFileInfo)) {
            return null;
        } else {
            return new FastJsonBillFileInfo(
                    FastJsonLongIdKey.of(billFileInfo.getKey()),
                    FastJsonLongIdKey.of(billFileInfo.getFundChangeKey()),
                    billFileInfo.getOriginName(), billFileInfo.getIndex(), billFileInfo.getLength(),
                    billFileInfo.getCreatedDate(), billFileInfo.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonLongIdKey key;

    @JSONField(name = "fund_change_key", ordinal = 2)
    private FastJsonLongIdKey fundChangeKey;

    @JSONField(name = "origin_name", ordinal = 3)
    private String originName;

    @JSONField(name = "index", ordinal = 4)
    private int index;

    @JSONField(name = "length", ordinal = 5)
    private long length;

    @JSONField(name = "created_date", ordinal = 6)
    private Date createdDate;

    @JSONField(name = "remark", ordinal = 7)
    private String remark;

    public FastJsonBillFileInfo() {
    }

    public FastJsonBillFileInfo(
            FastJsonLongIdKey key, FastJsonLongIdKey fundChangeKey, String originName, int index, long length,
            Date createdDate, String remark
    ) {
        this.key = key;
        this.fundChangeKey = fundChangeKey;
        this.originName = originName;
        this.index = index;
        this.length = length;
        this.createdDate = createdDate;
        this.remark = remark;
    }

    public FastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(FastJsonLongIdKey key) {
        this.key = key;
    }

    public FastJsonLongIdKey getFundChangeKey() {
        return fundChangeKey;
    }

    public void setFundChangeKey(FastJsonLongIdKey fundChangeKey) {
        this.fundChangeKey = fundChangeKey;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "FastJsonBillFileInfo{" +
                "key=" + key +
                ", fundChangeKey=" + fundChangeKey +
                ", originName='" + originName + '\'' +
                ", index=" + index +
                ", length=" + length +
                ", createdDate=" + createdDate +
                ", remark='" + remark + '\'' +
                '}';
    }
}
