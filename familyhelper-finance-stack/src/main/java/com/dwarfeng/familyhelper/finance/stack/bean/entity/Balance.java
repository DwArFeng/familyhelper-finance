package com.dwarfeng.familyhelper.finance.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 余额。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class Balance implements Entity<LongIdKey> {

    private static final long serialVersionUID = 3963996848528103770L;

    private LongIdKey key;
    private LongIdKey accountBookKey;
    private Date recordedDate;
    private BigDecimal value;
    private String remark;

    public Balance() {
    }

    public Balance(LongIdKey key, LongIdKey accountBookKey, Date recordedDate, BigDecimal value, String remark) {
        this.key = key;
        this.accountBookKey = accountBookKey;
        this.recordedDate = recordedDate;
        this.value = value;
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

    public LongIdKey getAccountBookKey() {
        return accountBookKey;
    }

    public void setAccountBookKey(LongIdKey accountBookKey) {
        this.accountBookKey = accountBookKey;
    }

    public Date getRecordedDate() {
        return recordedDate;
    }

    public void setRecordedDate(Date recordedDate) {
        this.recordedDate = recordedDate;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Balance{" +
                "key=" + key +
                ", accountBookKey=" + accountBookKey +
                ", recordedDate=" + recordedDate +
                ", value=" + value +
                ", remark='" + remark + '\'' +
                '}';
    }
}
