package com.dwarfeng.familyhelper.finance.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.util.Date;

/**
 * 票据文件信息。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
public class BillFileInfo implements Entity<LongIdKey> {

    private static final long serialVersionUID = 7458937315126034351L;

    private LongIdKey key;
    private LongIdKey fundChangeKey;
    private String originName;
    private int index;
    private long length;
    private Date createdDate;
    private String remark;

    public BillFileInfo() {
    }

    public BillFileInfo(
            LongIdKey key, LongIdKey fundChangeKey, String originName, int index, long length, Date createdDate,
            String remark
    ) {
        this.key = key;
        this.fundChangeKey = fundChangeKey;
        this.originName = originName;
        this.index = index;
        this.length = length;
        this.createdDate = createdDate;
        this.remark = remark;
    }

    @Override
    public LongIdKey getKey() {
        return key;
    }

    @Override
    public void setKey(LongIdKey key) {
        this.key = key;
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
        return "BillFileInfo{" +
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
