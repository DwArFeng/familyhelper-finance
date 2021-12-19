package com.dwarfeng.familyhelper.finance.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.finance.stack.bean.dto.FundChangeRecordInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * WebInput 资金变更记录信息。
 *
 * @author DwArFeng
 * @since 1.2.0
 */
public class WebInputFundChangeRecordInfo implements Dto {

    private static final long serialVersionUID = 2027664881221981122L;

    public static FundChangeRecordInfo toStackBean(WebInputFundChangeRecordInfo webInputFundChangeRecordInfo) {
        if (Objects.isNull(webInputFundChangeRecordInfo)) {
            return null;
        } else {
            return new FundChangeRecordInfo(
                    WebInputLongIdKey.toStackBean(webInputFundChangeRecordInfo.getAccountBookKey()),
                    webInputFundChangeRecordInfo.getDelta(),
                    webInputFundChangeRecordInfo.getChangeType(),
                    webInputFundChangeRecordInfo.getRemark()
            );
        }
    }

    @JSONField(name = "account_book_key")
    @Valid
    @NotNull
    private WebInputLongIdKey accountBookKey;

    @JSONField(name = "delta")
    @NotNull
    private BigDecimal delta;

    @JSONField(name = "change_type")
    private String changeType;

    @JSONField(name = "remark")
    private String remark;

    public WebInputFundChangeRecordInfo() {
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "WebInputFundChangeRecordInfo{" +
                "accountBookKey=" + accountBookKey +
                ", delta=" + delta +
                ", changeType='" + changeType + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
