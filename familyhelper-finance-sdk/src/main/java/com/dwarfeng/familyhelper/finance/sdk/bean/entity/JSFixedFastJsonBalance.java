package com.dwarfeng.familyhelper.finance.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.Balance;
import com.dwarfeng.subgrade.sdk.bean.key.JSFixedFastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * JSFixed FastJson 余额。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class JSFixedFastJsonBalance implements Bean {

    private static final long serialVersionUID = 3196801127812230854L;

    public static JSFixedFastJsonBalance of(Balance balance) {
        if (Objects.isNull(balance)) {
            return null;
        } else {
            return new JSFixedFastJsonBalance(
                    JSFixedFastJsonLongIdKey.of(balance.getKey()),
                    JSFixedFastJsonLongIdKey.of(balance.getAccountBookKey()),
                    balance.getRecordedDate(),
                    balance.getValue(),
                    balance.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonLongIdKey key;

    @JSONField(name = "account_book_key", ordinal = 2)
    private JSFixedFastJsonLongIdKey accountBookKey;

    @JSONField(name = "recorded_date", ordinal = 3)
    private Date recordedDate;

    @JSONField(name = "value", ordinal = 4)
    private BigDecimal value;

    @JSONField(name = "remark", ordinal = 5)
    private String remark;

    public JSFixedFastJsonBalance() {
    }

    public JSFixedFastJsonBalance(
            JSFixedFastJsonLongIdKey key, JSFixedFastJsonLongIdKey accountBookKey, Date recordedDate, BigDecimal value,
            String remark
    ) {
        this.key = key;
        this.accountBookKey = accountBookKey;
        this.recordedDate = recordedDate;
        this.value = value;
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
        return "JSFixedFastJsonBalance{" +
                "key=" + key +
                ", accountBookKey=" + accountBookKey +
                ", recordedDate=" + recordedDate +
                ", value=" + value +
                ", remark='" + remark + '\'' +
                '}';
    }
}
