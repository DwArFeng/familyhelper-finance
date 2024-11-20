package com.dwarfeng.familyhelper.finance.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 资金变更更新信息。
 *
 * @author DwArFeng
 * @since 1.2.0
 */
public class FundChangeUpdateInfo implements Dto {

    private static final long serialVersionUID = -6106639703776245079L;

    private LongIdKey fundChangeKey;
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

    public FundChangeUpdateInfo() {
    }

    public FundChangeUpdateInfo(
            LongIdKey fundChangeKey, BigDecimal delta, String changeType, String remark, Date happenedDate
    ) {
        this.fundChangeKey = fundChangeKey;
        this.delta = delta;
        this.changeType = changeType;
        this.remark = remark;
        this.happenedDate = happenedDate;
    }

    public LongIdKey getFundChangeKey() {
        return fundChangeKey;
    }

    public void setFundChangeKey(LongIdKey fundChangeKey) {
        this.fundChangeKey = fundChangeKey;
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
        return "FundChangeUpdateInfo{" +
                "fundChangeKey=" + fundChangeKey +
                ", delta=" + delta +
                ", changeType='" + changeType + '\'' +
                ", remark='" + remark + '\'' +
                ", happenedDate=" + happenedDate +
                '}';
    }
}
