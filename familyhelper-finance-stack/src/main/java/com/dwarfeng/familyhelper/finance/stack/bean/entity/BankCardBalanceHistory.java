package com.dwarfeng.familyhelper.finance.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 银行卡余额历史。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class BankCardBalanceHistory implements Entity<LongIdKey> {

    private static final long serialVersionUID = -6696620739437036946L;

    private LongIdKey key;
    private LongIdKey bankCardKey;
    private BigDecimal value;
    private Date happenedDate;
    private String remark;

    public BankCardBalanceHistory() {
    }

    public BankCardBalanceHistory(
            LongIdKey key, LongIdKey bankCardKey, BigDecimal value, Date happenedDate, String remark
    ) {
        this.key = key;
        this.bankCardKey = bankCardKey;
        this.value = value;
        this.happenedDate = happenedDate;
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

    public LongIdKey getBankCardKey() {
        return bankCardKey;
    }

    public void setBankCardKey(LongIdKey bankCardKey) {
        this.bankCardKey = bankCardKey;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Date getHappenedDate() {
        return happenedDate;
    }

    public void setHappenedDate(Date happenedDate) {
        this.happenedDate = happenedDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "BankCardBalanceHistory{" +
                "key=" + key +
                ", bankCardKey=" + bankCardKey +
                ", value=" + value +
                ", happenedDate=" + happenedDate +
                ", remark='" + remark + '\'' +
                '}';
    }
}
