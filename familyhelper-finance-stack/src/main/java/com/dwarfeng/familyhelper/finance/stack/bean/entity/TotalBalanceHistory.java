package com.dwarfeng.familyhelper.finance.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 总余额历史。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class TotalBalanceHistory implements Entity<LongIdKey> {

    private static final long serialVersionUID = 1120585572357770628L;

    private LongIdKey key;
    private LongIdKey accountBookKey;
    private BigDecimal totalValue;
    private Date happenedDate;
    private String remark;

    public TotalBalanceHistory() {
    }

    public TotalBalanceHistory(
            LongIdKey key, LongIdKey accountBookKey, BigDecimal totalValue, Date happenedDate, String remark
    ) {
        this.key = key;
        this.accountBookKey = accountBookKey;
        this.totalValue = totalValue;
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

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
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
        return "TotalBalanceHistory{" +
                "key=" + key +
                ", accountBookKey=" + accountBookKey +
                ", totalValue=" + totalValue +
                ", happenedDate=" + happenedDate +
                ", remark='" + remark + '\'' +
                '}';
    }
}
