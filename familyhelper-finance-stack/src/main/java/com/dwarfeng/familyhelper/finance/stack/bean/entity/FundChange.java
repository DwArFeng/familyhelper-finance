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

    private static final long serialVersionUID = -8814660888399626547L;

    private LongIdKey key;
    private LongIdKey accountBookKey;
    private BigDecimal delta;
    private String changeType;

    /**
     * 发生日期。
     *
     * <p>
     * 发生日期指的是资金变更发生的日期，可以称为变更日期。
     */
    private Date happenedDate;
    private String remark;

    /**
     * 记录日期。
     *
     * <p>
     * 记录日期指的是资金变更被记录的日期。<br>
     * 当资金变更被创建或被更新时，该值通常取当前的系统时间。
     *
     * @since 1.5.0
     */
    private Date recordedDate;

    public FundChange() {
    }

    public FundChange(
            LongIdKey key, LongIdKey accountBookKey, BigDecimal delta, String changeType, Date happenedDate,
            String remark, Date recordedDate
    ) {
        this.key = key;
        this.accountBookKey = accountBookKey;
        this.delta = delta;
        this.changeType = changeType;
        this.happenedDate = happenedDate;
        this.remark = remark;
        this.recordedDate = recordedDate;
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

    public Date getRecordedDate() {
        return recordedDate;
    }

    public void setRecordedDate(Date recordedDate) {
        this.recordedDate = recordedDate;
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
                ", recordedDate=" + recordedDate +
                '}';
    }
}
