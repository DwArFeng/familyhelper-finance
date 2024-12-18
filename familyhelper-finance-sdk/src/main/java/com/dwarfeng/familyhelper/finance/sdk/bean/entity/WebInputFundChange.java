package com.dwarfeng.familyhelper.finance.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.finance.sdk.util.Constraints;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.FundChange;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * WebInput 资金变更。
 *
 * @author DwArFeng
 * @since 1.2.0
 */
public class WebInputFundChange implements Bean {

    private static final long serialVersionUID = 705745635724703044L;

    public static FundChange toStackBean(WebInputFundChange webInputFundChange) {
        return new FundChange(
                WebInputLongIdKey.toStackBean(webInputFundChange.getKey()),
                WebInputLongIdKey.toStackBean(webInputFundChange.getAccountBookKey()),
                webInputFundChange.getDelta(),
                webInputFundChange.getChangeType(),
                webInputFundChange.getHappenedDate(),
                webInputFundChange.getRemark(),
                webInputFundChange.getRecordedDate()
        );
    }

    @JSONField(name = "key")
    @Valid
    private WebInputLongIdKey key;

    @JSONField(name = "account_book_key")
    @Valid
    private WebInputLongIdKey accountBookKey;

    @JSONField(name = "delta")
    @NotNull
    private BigDecimal delta;

    @JSONField(name = "change_type")
    private String changeType;

    @JSONField(name = "happened_date")
    private Date happenedDate;

    @JSONField(name = "remark")
    @Length(max = Constraints.LENGTH_REMARK)
    private String remark;

    @JSONField(name = "recorded_date")
    @NotNull
    private Date recordedDate;

    public WebInputFundChange() {
    }

    public WebInputLongIdKey getKey() {
        return key;
    }

    public void setKey(WebInputLongIdKey key) {
        this.key = key;
    }

    public WebInputLongIdKey getAccountBookKey() {
        return accountBookKey;
    }

    public void setAccountBookKey(WebInputLongIdKey accountBookKey) {
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
        return "WebInputFundChange{" +
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
