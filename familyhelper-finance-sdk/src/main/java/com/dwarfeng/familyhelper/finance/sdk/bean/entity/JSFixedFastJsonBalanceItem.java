package com.dwarfeng.familyhelper.finance.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.BalanceItem;
import com.dwarfeng.subgrade.sdk.bean.key.JSFixedFastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * JSFixed FastJson 余额条目。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class JSFixedFastJsonBalanceItem implements Bean {

    private static final long serialVersionUID = -4635082310698580623L;

    public static JSFixedFastJsonBalanceItem of(BalanceItem balanceItem) {
        if (Objects.isNull(balanceItem)) {
            return null;
        } else {
            return new JSFixedFastJsonBalanceItem(
                    JSFixedFastJsonLongIdKey.of(balanceItem.getKey()),
                    JSFixedFastJsonLongIdKey.of(balanceItem.getBalanceKey()),
                    JSFixedFastJsonLongIdKey.of(balanceItem.getFundRepositoryKey()),
                    balanceItem.getValue(),
                    balanceItem.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonLongIdKey key;

    @JSONField(name = "balanceKey", ordinal = 2)
    private JSFixedFastJsonLongIdKey balanceKey;

    @JSONField(name = "fundRepositoryKey", ordinal = 3)
    private JSFixedFastJsonLongIdKey fundRepositoryKey;

    @JSONField(name = "value", ordinal = 4)
    private BigDecimal value;

    @JSONField(name = "remark", ordinal = 5)
    private String remark;

    public JSFixedFastJsonBalanceItem() {
    }

    public JSFixedFastJsonBalanceItem(
            JSFixedFastJsonLongIdKey key, JSFixedFastJsonLongIdKey balanceKey, JSFixedFastJsonLongIdKey fundRepositoryKey,
            BigDecimal value, String remark
    ) {
        this.key = key;
        this.balanceKey = balanceKey;
        this.fundRepositoryKey = fundRepositoryKey;
        this.value = value;
        this.remark = remark;
    }

    public JSFixedFastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(JSFixedFastJsonLongIdKey key) {
        this.key = key;
    }

    public JSFixedFastJsonLongIdKey getBalanceKey() {
        return balanceKey;
    }

    public void setBalanceKey(JSFixedFastJsonLongIdKey balanceKey) {
        this.balanceKey = balanceKey;
    }

    public JSFixedFastJsonLongIdKey getFundRepositoryKey() {
        return fundRepositoryKey;
    }

    public void setFundRepositoryKey(JSFixedFastJsonLongIdKey fundRepositoryKey) {
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
