package com.dwarfeng.familyhelper.finance.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 账本。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class AccountBook implements Entity<LongIdKey> {

    private static final long serialVersionUID = 6995084428867001374L;

    private LongIdKey key;
    private String name;
    private Date lastRecordedDate;
    private BigDecimal totalValue;
    private String remark;

    public AccountBook() {
    }

    public AccountBook(LongIdKey key, String name, Date lastRecordedDate, BigDecimal totalValue, String remark) {
        this.key = key;
        this.name = name;
        this.lastRecordedDate = lastRecordedDate;
        this.totalValue = totalValue;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getLastRecordedDate() {
        return lastRecordedDate;
    }

    public void setLastRecordedDate(Date lastRecordedDate) {
        this.lastRecordedDate = lastRecordedDate;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "AccountBook{" +
                "key=" + key +
                ", name='" + name + '\'' +
                ", lastRecordedDate=" + lastRecordedDate +
                ", totalValue=" + totalValue +
                ", remark='" + remark + '\'' +
                '}';
    }
}
