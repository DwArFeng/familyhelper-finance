package com.dwarfeng.familyhelper.finance.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 资金变更记录信息。
 *
 * @author DwArFeng
 * @since 1.2.0
 */
public class FundChangeRecordInfo implements Dto {

    private static final long serialVersionUID = -5892794110185375481L;

    private LongIdKey accountBookKey;
    private BigDecimal delta;
    private String changeType;
    private String remark;

    /**
     * 发生日期。
     *
     * <p>
     * 发生日期指的是资金变更发生的日期，可以称为变更日期。
     *
     * <p>
     * 该值可以为 <code>null</code>，表示发生日期为当前系统日期。
     *
     * @since 1.5.0
     */
    private Date happenedDate;

    public FundChangeRecordInfo() {
    }

    public FundChangeRecordInfo(
            LongIdKey accountBookKey, BigDecimal delta, String changeType, String remark, Date happenedDate
    ) {
        this.accountBookKey = accountBookKey;
        this.delta = delta;
        this.changeType = changeType;
        this.remark = remark;
        this.happenedDate = happenedDate;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getHappenedDate() {
        return happenedDate;
    }

    public void setHappenedDate(Date happenedDate) {
        this.happenedDate = happenedDate;
    }

    @Override
    public String toString() {
        return "FundChangeRecordInfo{" +
                "accountBookKey=" + accountBookKey +
                ", delta=" + delta +
                ", changeType='" + changeType + '\'' +
                ", remark='" + remark + '\'' +
                ", happenedDate=" + happenedDate +
                '}';
    }
}
