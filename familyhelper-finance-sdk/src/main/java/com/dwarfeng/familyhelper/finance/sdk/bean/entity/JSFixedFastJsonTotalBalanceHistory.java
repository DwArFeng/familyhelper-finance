package com.dwarfeng.familyhelper.finance.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.TotalBalanceHistory;
import com.dwarfeng.subgrade.sdk.bean.key.JSFixedFastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * JSFixed FastJson 总余额历史。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class JSFixedFastJsonTotalBalanceHistory implements Bean {

    private static final long serialVersionUID = 7793869334680564180L;

    public static JSFixedFastJsonTotalBalanceHistory of(TotalBalanceHistory totalBalanceHistory) {
        if (Objects.isNull(totalBalanceHistory)) {
            return null;
        } else {
            return new JSFixedFastJsonTotalBalanceHistory(
                    JSFixedFastJsonLongIdKey.of(totalBalanceHistory.getKey()),
                    JSFixedFastJsonLongIdKey.of(totalBalanceHistory.getAccountBookKey()),
                    totalBalanceHistory.getTotalValue(),
                    totalBalanceHistory.getHappenedDate(),
                    totalBalanceHistory.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonLongIdKey key;

    @JSONField(name = "account_book_key", ordinal = 2)
    private JSFixedFastJsonLongIdKey accountBookKey;

    @JSONField(name = "total_value", ordinal = 3)
    private BigDecimal totalValue;

    @JSONField(name = "happened_date", ordinal = 4)
    private Date happenedDate;

    @JSONField(name = "remark", ordinal = 5)
    private String remark;

    public JSFixedFastJsonTotalBalanceHistory() {
    }

    public JSFixedFastJsonTotalBalanceHistory(
            JSFixedFastJsonLongIdKey key, JSFixedFastJsonLongIdKey accountBookKey, BigDecimal totalValue, Date happenedDate,
            String remark
    ) {
        this.key = key;
        this.accountBookKey = accountBookKey;
        this.totalValue = totalValue;
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

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
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
        return "JSFixedFastJsonTotalBalanceHistory{" +
                "key=" + key +
                ", accountBookKey=" + accountBookKey +
                ", totalValue=" + totalValue +
                ", happenedDate=" + happenedDate +
                ", remark='" + remark + '\'' +
                '}';
    }
}
