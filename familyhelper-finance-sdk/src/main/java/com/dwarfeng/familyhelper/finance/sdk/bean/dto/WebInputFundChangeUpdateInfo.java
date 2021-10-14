package com.dwarfeng.familyhelper.finance.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.finance.stack.bean.dto.FundChangeUpdateInfo;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * WebInput 资金变更更新信息。
 *
 * @author DwArFeng
 * @since 1.2.0
 */
public class WebInputFundChangeUpdateInfo implements Dto {

    private static final long serialVersionUID = 7873346983022175175L;

    public static FundChangeUpdateInfo toStackBean(WebInputFundChangeUpdateInfo webInputFundChangeUpdateInfo) {
        if (Objects.isNull(webInputFundChangeUpdateInfo)) {
            return null;
        } else {
            return new FundChangeUpdateInfo(
                    webInputFundChangeUpdateInfo.getDelta(),
                    webInputFundChangeUpdateInfo.getChangeType(),
                    webInputFundChangeUpdateInfo.getRemark()
            );
        }
    }

    @JSONField(name = "delta")
    @NotNull
    private BigDecimal delta;

    @JSONField(name = "change_type")
    private String changeType;

    @JSONField(name = "remark")
    private String remark;

    public WebInputFundChangeUpdateInfo() {
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
        return "WebInputFundChangeUpdateInfo{" +
                "delta=" + delta +
                ", changeType='" + changeType + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
