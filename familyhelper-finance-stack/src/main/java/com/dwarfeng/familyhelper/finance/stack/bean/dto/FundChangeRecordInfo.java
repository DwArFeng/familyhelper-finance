package com.dwarfeng.familyhelper.finance.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.math.BigDecimal;

/**
 * 资金变更记录信息。
 *
 * @author DwArFeng
 * @since 1.2.0
 */
public class FundChangeRecordInfo implements Dto {

    private static final long serialVersionUID = -5298196621624192898L;

    private BigDecimal delta;
    private String changeType;
    private String remark;

    public FundChangeRecordInfo() {
    }

    public FundChangeRecordInfo(BigDecimal delta, String changeType, String remark) {
        this.delta = delta;
        this.changeType = changeType;
        this.remark = remark;
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

    @Override
    public String toString() {
        return "FundChangeRecordInfo{" +
                "delta=" + delta +
                ", changeType='" + changeType + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
