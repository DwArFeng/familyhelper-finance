package com.dwarfeng.familyhelper.finance.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.Balance;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * FastJson 余额。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class FastJsonBalance implements Bean {

    private static final long serialVersionUID = -7452536239488963373L;

    public static FastJsonBalance of(Balance balance) {
        if (Objects.isNull(balance)) {
            return null;
        } else {
            return new FastJsonBalance(
                    FastJsonLongIdKey.of(balance.getKey()),
                    FastJsonLongIdKey.of(balance.getAccountBookKey()),
                    balance.getRecordedDate(),
                    balance.getValue(),
                    balance.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonLongIdKey key;

    @JSONField(name = "account_book_key", ordinal = 2)
    private FastJsonLongIdKey accountBookKey;

    @JSONField(name = "recorded_date", ordinal = 3)
    private Date recordedDate;

    @JSONField(name = "value", ordinal = 4)
    private BigDecimal value;

    @JSONField(name = "remark", ordinal = 5)
    private String remark;

    public FastJsonBalance() {
    }

    public FastJsonBalance(
            FastJsonLongIdKey key, FastJsonLongIdKey accountBookKey, Date recordedDate, BigDecimal value,
            String remark
    ) {
        this.key = key;
        this.accountBookKey = accountBookKey;
        this.recordedDate = recordedDate;
        this.value = value;
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

    public Date getRecordedDate() {
        return recordedDate;
    }

    public void setRecordedDate(Date recordedDate) {
        this.recordedDate = recordedDate;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "FastJsonBalance{" +
                "key=" + key +
                ", accountBookKey=" + accountBookKey +
                ", recordedDate=" + recordedDate +
                ", value=" + value +
                ", remark='" + remark + '\'' +
                '}';
    }
}
