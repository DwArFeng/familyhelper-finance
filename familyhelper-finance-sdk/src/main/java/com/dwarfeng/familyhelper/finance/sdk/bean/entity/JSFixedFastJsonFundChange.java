package com.dwarfeng.familyhelper.finance.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.FundChange;
import com.dwarfeng.subgrade.sdk.bean.key.JSFixedFastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * JSFixed FastJson 资金变更。
 *
 * @author DwArFeng
 * @since 1.2.0
 */
public class JSFixedFastJsonFundChange implements Bean {

    private static final long serialVersionUID = 425011182159130846L;

    public static JSFixedFastJsonFundChange of(FundChange fundChange) {
        if (Objects.isNull(fundChange)) {
            return null;
        } else {
            return new JSFixedFastJsonFundChange(
                    JSFixedFastJsonLongIdKey.of(fundChange.getKey()),
                    JSFixedFastJsonLongIdKey.of(fundChange.getAccountBookKey()),
                    fundChange.getDelta(),
                    fundChange.getChangeType(),
                    fundChange.getHappenedDate(),
                    fundChange.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonLongIdKey key;

    @JSONField(name = "account_book_key", ordinal = 2)
    private JSFixedFastJsonLongIdKey accountBookKey;

    @JSONField(name = "delta", ordinal = 3)
    private BigDecimal delta;

    @JSONField(name = "change_type", ordinal = 4)
    private String changeType;

    @JSONField(name = "happened_date", ordinal = 5)
    private Date happenedDate;

    @JSONField(name = "remark", ordinal = 6)
    private String remark;

    public JSFixedFastJsonFundChange() {
    }

    public JSFixedFastJsonFundChange(
            JSFixedFastJsonLongIdKey key, JSFixedFastJsonLongIdKey accountBookKey, BigDecimal delta,
            String changeType, Date happenedDate, String remark
    ) {
        this.key = key;
        this.accountBookKey = accountBookKey;
        this.delta = delta;
        this.changeType = changeType;
        this.happenedDate = happenedDate;
        this.remark = remark;
    }

    public JSFixedFastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(JSFixedFastJsonLongIdKey key) {
        this.key = key;
    }

    public JSFixedFastJsonLongIdKey getAccountBookKey() {
        return accountBookKey;
    }

    public void setAccountBookKey(JSFixedFastJsonLongIdKey accountBookKey) {
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

    @Override
    public String toString() {
        return "JSFixedFastJsonFundChange{" +
                "key=" + key +
                ", accountBookKey=" + accountBookKey +
                ", delta=" + delta +
                ", changeType='" + changeType + '\'' +
                ", happenedDate=" + happenedDate +
                ", remark='" + remark + '\'' +
                '}';
    }
}
