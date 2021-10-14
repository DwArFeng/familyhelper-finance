package com.dwarfeng.familyhelper.finance.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.math.BigDecimal;

/**
 * 资金变更更新信息。
 *
 * @author DwArFeng
 * @since 1.2.0
 */
public class FundChangeUpdateInfo implements Dto {

    private static final long serialVersionUID = 169317631032321238L;

    private BigDecimal delta;
    private String changeType;
    private String remark;

    public FundChangeUpdateInfo() {
    }

    public FundChangeUpdateInfo(BigDecimal delta, String changeType, String remark) {
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
        return "FundChangeUpdateInfo{" +
                "delta=" + delta +
                ", changeType='" + changeType + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
