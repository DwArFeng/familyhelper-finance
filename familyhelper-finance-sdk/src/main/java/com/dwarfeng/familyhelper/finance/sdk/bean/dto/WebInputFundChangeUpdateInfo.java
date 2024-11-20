package com.dwarfeng.familyhelper.finance.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.finance.sdk.util.Constraints;
import com.dwarfeng.familyhelper.finance.stack.bean.dto.FundChangeUpdateInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * WebInput 资金变更更新信息。
 *
 * @author DwArFeng
 * @since 1.2.0
 */
public class WebInputFundChangeUpdateInfo implements Dto {

    private static final long serialVersionUID = -8252293487980599266L;

    public static FundChangeUpdateInfo toStackBean(WebInputFundChangeUpdateInfo webInputFundChangeUpdateInfo) {
        if (Objects.isNull(webInputFundChangeUpdateInfo)) {
            return null;
        } else {
            return new FundChangeUpdateInfo(
                    WebInputLongIdKey.toStackBean(webInputFundChangeUpdateInfo.getFundChangeKey()),
                    webInputFundChangeUpdateInfo.getDelta(),
                    webInputFundChangeUpdateInfo.getChangeType(),
                    webInputFundChangeUpdateInfo.getRemark(),
                    webInputFundChangeUpdateInfo.getHappenedDate()
            );
        }
    }

    @JSONField(name = "fund_change_key")
    @Valid
    @NotNull
    private WebInputLongIdKey fundChangeKey;

    @JSONField(name = "delta")
    @NotNull
    private BigDecimal delta;

    @JSONField(name = "change_type")
    private String changeType;

    @JSONField(name = "remark")
    @Length(max = Constraints.LENGTH_REMARK)
    private String remark;

    @JSONField(name = "happened_date")
    private Date happenedDate;

    public WebInputFundChangeUpdateInfo() {
    }

    public WebInputLongIdKey getFundChangeKey() {
        return fundChangeKey;
    }

    public void setFundChangeKey(WebInputLongIdKey fundChangeKey) {
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
        return "WebInputFundChangeUpdateInfo{" +
                "fundChangeKey=" + fundChangeKey +
                ", delta=" + delta +
                ", changeType='" + changeType + '\'' +
                ", remark='" + remark + '\'' +
                ", happenedDate=" + happenedDate +
                '}';
    }
}
