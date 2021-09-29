package com.dwarfeng.familyhelper.finance.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.TotalBalanceHistory;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * FastJson 总余额历史。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class FastJsonTotalBalanceHistory implements Bean {

    private static final long serialVersionUID = 602430199876949359L;

    public static FastJsonTotalBalanceHistory of(TotalBalanceHistory totalBalanceHistory) {
        if (Objects.isNull(totalBalanceHistory)) {
            return null;
        } else {
            return new FastJsonTotalBalanceHistory(
                    FastJsonLongIdKey.of(totalBalanceHistory.getKey()),
                    FastJsonLongIdKey.of(totalBalanceHistory.getAccountBookKey()),
                    totalBalanceHistory.getTotalValue(),
                    totalBalanceHistory.getHappenedDate(),
                    totalBalanceHistory.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonLongIdKey key;

    @JSONField(name = "account_book_key", ordinal = 2)
    private FastJsonLongIdKey accountBookKey;

    @JSONField(name = "total_value", ordinal = 3)
    private BigDecimal totalValue;

    @JSONField(name = "happened_date", ordinal = 4)
    private Date happenedDate;

    @JSONField(name = "remark", ordinal = 5)
    private String remark;

    public FastJsonTotalBalanceHistory() {
    }

    public FastJsonTotalBalanceHistory(
            FastJsonLongIdKey key, FastJsonLongIdKey accountBookKey, BigDecimal totalValue, Date happenedDate,
            String remark
    ) {
        this.key = key;
        this.accountBookKey = accountBookKey;
        this.totalValue = totalValue;
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
        return "FastJsonTotalBalanceHistory{" +
                "key=" + key +
                ", accountBookKey=" + accountBookKey +
                ", totalValue=" + totalValue +
                ", happenedDate=" + happenedDate +
                ", remark='" + remark + '\'' +
                '}';
    }
}
