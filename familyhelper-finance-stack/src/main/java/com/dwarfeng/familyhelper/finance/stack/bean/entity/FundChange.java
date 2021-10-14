package com.dwarfeng.familyhelper.finance.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 资金变更。
 *
 * @author DwArFeng
 * @since 1.2.0
 */
public class FundChange implements Entity<LongIdKey> {

    private static final long serialVersionUID = -306278124499756731L;

    private LongIdKey key;
    private LongIdKey accountBookKey;
    private BigDecimal delta;
    private String changeType;
    private Date happenedDate;
    private String remark;

    public FundChange() {
    }

    public FundChange(
            LongIdKey key, LongIdKey accountBookKey, BigDecimal delta, String changeType, Date happenedDate,
            String remark
    ) {
        this.key = key;
        this.accountBookKey = accountBookKey;
        this.delta = delta;
        this.changeType = changeType;
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

    public LongIdKey getAccountBookKey() {
        return accountBookKey;
    }

    public void setAccountBookKey(LongIdKey accountBookKey) {
        this.accountBookKey = accountBookKey;
    }

    public BigDecimal getDelta() {
        return delta;
    }

    public void setDelta(BigDecimal delta) {
        this.delta = delta;
    }

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
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
        return "FundChange{" +
                "key=" + key +
                ", accountBookKey=" + accountBookKey +
                ", delta=" + delta +
                ", changeType='" + changeType + '\'' +
                ", happenedDate=" + happenedDate +
                ", remark='" + remark + '\'' +
                '}';
    }
}
