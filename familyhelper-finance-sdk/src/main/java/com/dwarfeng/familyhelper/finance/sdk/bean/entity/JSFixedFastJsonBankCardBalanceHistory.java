package com.dwarfeng.familyhelper.finance.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.BankCardBalanceHistory;
import com.dwarfeng.subgrade.sdk.bean.key.JSFixedFastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * JSFixed FastJson 银行卡余额历史。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class JSFixedFastJsonBankCardBalanceHistory implements Bean {

    private static final long serialVersionUID = -2565267214920927930L;

    public static JSFixedFastJsonBankCardBalanceHistory of(BankCardBalanceHistory bankCardBalanceHistory) {
        if (Objects.isNull(bankCardBalanceHistory)) {
            return null;
        } else {
            return new JSFixedFastJsonBankCardBalanceHistory(
                    JSFixedFastJsonLongIdKey.of(bankCardBalanceHistory.getKey()),
                    JSFixedFastJsonLongIdKey.of(bankCardBalanceHistory.getBankCardKey()),
                    bankCardBalanceHistory.getValue(),
                    bankCardBalanceHistory.getHappenedDate(),
                    bankCardBalanceHistory.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonLongIdKey key;

    @JSONField(name = "bank_card_key", ordinal = 2)
    private JSFixedFastJsonLongIdKey bankCardKey;

    @JSONField(name = "value", ordinal = 3)
    private BigDecimal value;

    @JSONField(name = "happened_date", ordinal = 4)
    private Date happenedDate;

    @JSONField(name = "remark", ordinal = 5)
    private String remark;

    public JSFixedFastJsonBankCardBalanceHistory() {
    }

    public JSFixedFastJsonBankCardBalanceHistory(
            JSFixedFastJsonLongIdKey key, JSFixedFastJsonLongIdKey bankCardKey, BigDecimal value, Date happenedDate, String remark
    ) {
        this.key = key;
        this.bankCardKey = bankCardKey;
        this.value = value;
        this.happenedDate = happenedDate;
        this.remark = remark;
    }

    public JSFixedFastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(JSFixedFastJsonLongIdKey key) {
        this.key = key;
    }

    public JSFixedFastJsonLongIdKey getBankCardKey() {
        return bankCardKey;
    }

    public void setBankCardKey(JSFixedFastJsonLongIdKey bankCardKey) {
        this.bankCardKey = bankCardKey;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
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
        return "JSFixedFastJsonBankCardBalanceHistory{" +
                "key=" + key +
                ", bankCardKey=" + bankCardKey +
                ", value=" + value +
                ", happenedDate=" + happenedDate +
                ", remark='" + remark + '\'' +
                '}';
    }
}
