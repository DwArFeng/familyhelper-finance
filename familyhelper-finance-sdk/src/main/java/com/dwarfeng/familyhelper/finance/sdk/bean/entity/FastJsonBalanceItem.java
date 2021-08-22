package com.dwarfeng.familyhelper.finance.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.BalanceItem;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * FastJson 余额条目。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class FastJsonBalanceItem implements Bean {

    private static final long serialVersionUID = -627088365345545358L;

    public static FastJsonBalanceItem of(BalanceItem balanceItem) {
        if (Objects.isNull(balanceItem)) {
            return null;
        } else {
            return new FastJsonBalanceItem(
                    FastJsonLongIdKey.of(balanceItem.getKey()),
                    FastJsonLongIdKey.of(balanceItem.getBalanceKey()),
                    FastJsonLongIdKey.of(balanceItem.getFundRepositoryKey()),
                    balanceItem.getValue(),
                    balanceItem.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonLongIdKey key;

    @JSONField(name = "balanceKey", ordinal = 2)
    private FastJsonLongIdKey balanceKey;

    @JSONField(name = "fundRepositoryKey", ordinal = 3)
    private FastJsonLongIdKey fundRepositoryKey;

    @JSONField(name = "value", ordinal = 4)
    private BigDecimal value;

    @JSONField(name = "remark", ordinal = 5)
    private String remark;

    public FastJsonBalanceItem() {
    }

    public FastJsonBalanceItem(
            FastJsonLongIdKey key, FastJsonLongIdKey balanceKey, FastJsonLongIdKey fundRepositoryKey,
            BigDecimal value, String remark
    ) {
        this.key = key;
        this.balanceKey = balanceKey;
        this.fundRepositoryKey = fundRepositoryKey;
        this.value = value;
        this.remark = remark;
    }

    public FastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(FastJsonLongIdKey key) {
        this.key = key;
    }

    public FastJsonLongIdKey getBalanceKey() {
        return balanceKey;
    }

    public void setBalanceKey(FastJsonLongIdKey balanceKey) {
        this.balanceKey = balanceKey;
    }

    public FastJsonLongIdKey getFundRepositoryKey() {
        return fundRepositoryKey;
    }

    public void setFundRepositoryKey(FastJsonLongIdKey fundRepositoryKey) {
        this.fundRepositoryKey = fundRepositoryKey;
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
        return "FastJsonBalanceItem{" +
                "key=" + key +
                ", balanceKey=" + balanceKey +
                ", fundRepositoryKey=" + fundRepositoryKey +
                ", value=" + value +
                ", remark='" + remark + '\'' +
                '}';
    }
}
