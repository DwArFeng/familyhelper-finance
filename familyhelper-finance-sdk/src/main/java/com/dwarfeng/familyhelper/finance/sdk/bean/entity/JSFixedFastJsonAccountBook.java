package com.dwarfeng.familyhelper.finance.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.AccountBook;
import com.dwarfeng.subgrade.sdk.bean.key.JSFixedFastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * JSFixed FastJson 账本。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class JSFixedFastJsonAccountBook implements Bean {

    private static final long serialVersionUID = 5250803371637192343L;

    public static JSFixedFastJsonAccountBook of(AccountBook accountBook) {
        if (Objects.isNull(accountBook)) {
            return null;
        } else {
            return new JSFixedFastJsonAccountBook(
                    JSFixedFastJsonLongIdKey.of(accountBook.getKey()),
                    accountBook.getName(),
                    accountBook.getLastRecordedDate(),
                    accountBook.getTotalValue(),
                    accountBook.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonLongIdKey key;

    @JSONField(name = "name", ordinal = 2)
    private String name;

    @JSONField(name = "last_recorded_date", ordinal = 3)
    private Date lastRecordedDate;

    @JSONField(name = "total_value", ordinal = 4)
    private BigDecimal totalValue;

    @JSONField(name = "remark", ordinal = 5)
    private String remark;

    public JSFixedFastJsonAccountBook() {
    }

    public JSFixedFastJsonAccountBook(
            JSFixedFastJsonLongIdKey key, String name, Date lastRecordedDate, BigDecimal totalValue, String remark
    ) {
        this.key = key;
        this.name = name;
        this.lastRecordedDate = lastRecordedDate;
        this.totalValue = totalValue;
        this.remark = remark;
    }

    public JSFixedFastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(JSFixedFastJsonLongIdKey key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getLastRecordedDate() {
        return lastRecordedDate;
    }

    public void setLastRecordedDate(Date lastRecordedDate) {
        this.lastRecordedDate = lastRecordedDate;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "JSFixedFastJsonAccountBook{" +
                "key=" + key +
                ", name='" + name + '\'' +
                ", lastRecordedDate=" + lastRecordedDate +
                ", totalValue=" + totalValue +
                ", remark='" + remark + '\'' +
                '}';
    }
}
