package com.dwarfeng.familyhelper.finance.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.FundChange;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Date;
import java.util.Objects;

/**
 * FastJson 资金变更。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class FastJsonFundChange implements Bean {

    private static final long serialVersionUID = -3424321385051387453L;

    public static FastJsonFundChange of(FundChange fundChange) {
        if (Objects.isNull(fundChange)) {
            return null;
        } else {
            return new FastJsonFundChange(
                    FastJsonLongIdKey.of(fundChange.getKey()),
                    FastJsonLongIdKey.of(fundChange.getAccountBookKey()),
                    FastJsonLongIdKey.of(fundChange.getBankCardKey()),
                    fundChange.getChangeType(),
                    fundChange.getHappenedDate(),
                    fundChange.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonLongIdKey key;

    @JSONField(name = "account_book_key", ordinal = 2)
    private FastJsonLongIdKey accountBookKey;

    @JSONField(name = "bank_card_key", ordinal = 3)
    private FastJsonLongIdKey bankCardKey;

    @JSONField(name = "change_type", ordinal = 4)
    private String changeType;

    @JSONField(name = "happened_date", ordinal = 5)
    private Date happenedDate;

    @JSONField(name = "remark", ordinal = 6)
    private String remark;

    public FastJsonFundChange() {
    }

    public FastJsonFundChange(
            FastJsonLongIdKey key, FastJsonLongIdKey accountBookKey, FastJsonLongIdKey bankCardKey, String changeType,
            Date happenedDate, String remark
    ) {
        this.key = key;
        this.accountBookKey = accountBookKey;
        this.bankCardKey = bankCardKey;
        this.changeType = changeType;
        this.happenedDate = happenedDate;
        this.remark = remark;
    }

    public FastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(FastJsonLongIdKey key) {
        this.key = key;
    }

    public FastJsonLongIdKey getAccountBookKey() {
        return accountBookKey;
    }

    public void setAccountBookKey(FastJsonLongIdKey accountBookKey) {
        this.accountBookKey = accountBookKey;
    }

    public FastJsonLongIdKey getBankCardKey() {
        return bankCardKey;
    }

    public void setBankCardKey(FastJsonLongIdKey bankCardKey) {
        this.bankCardKey = bankCardKey;
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
        return "FastJsonFundChange{" +
                "key=" + key +
                ", accountBookKey=" + accountBookKey +
                ", bankCardKey=" + bankCardKey +
                ", changeType='" + changeType + '\'' +
                ", happenedDate=" + happenedDate +
                ", remark='" + remark + '\'' +
                '}';
    }
}
