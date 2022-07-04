package com.dwarfeng.familyhelper.finance.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.BillFileInfo;
import com.dwarfeng.subgrade.sdk.bean.key.JSFixedFastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Date;
import java.util.Objects;

/**
 * JSFixed FastJson 票据文件信息。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
public class JSFixedFastJsonBillFileInfo implements Bean {

    private static final long serialVersionUID = 7605107427016196071L;

    public static JSFixedFastJsonBillFileInfo of(BillFileInfo billFileInfo) {
        if (Objects.isNull(billFileInfo)) {
            return null;
        } else {
            return new JSFixedFastJsonBillFileInfo(
                    JSFixedFastJsonLongIdKey.of(billFileInfo.getKey()),
                    JSFixedFastJsonLongIdKey.of(billFileInfo.getFundChangeKey()),
                    billFileInfo.getIndex(), billFileInfo.getLength(),
                    billFileInfo.getCreatedDate(), billFileInfo.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonLongIdKey key;

    @JSONField(name = "fund_change_key", ordinal = 2)
    private JSFixedFastJsonLongIdKey fundChangeKey;

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

    public JSFixedFastJsonBillFileInfo() {
    }

    public JSFixedFastJsonBillFileInfo(
            JSFixedFastJsonLongIdKey key, JSFixedFastJsonLongIdKey fundChangeKey, int index, long length,
            Date createdDate, String remark
    ) {
        this.key = key;
        this.fundChangeKey = fundChangeKey;
        this.index = index;
        this.length = length;
        this.createdDate = createdDate;
        this.remark = remark;
    }

    public JSFixedFastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(JSFixedFastJsonLongIdKey key) {
        this.key = key;
    }

    public JSFixedFastJsonLongIdKey getFundChangeKey() {
        return fundChangeKey;
    }

    public void setFundChangeKey(JSFixedFastJsonLongIdKey fundChangeKey) {
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
        return "JSFixedFastJsonBillFileInfo{" +
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
