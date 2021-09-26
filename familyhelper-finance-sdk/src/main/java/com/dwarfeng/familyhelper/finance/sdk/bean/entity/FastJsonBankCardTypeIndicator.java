package com.dwarfeng.familyhelper.finance.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.BankCardTypeIndicator;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 银行卡类型指示器。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class FastJsonBankCardTypeIndicator implements Bean {

    private static final long serialVersionUID = -7833725118536382638L;

    public static FastJsonBankCardTypeIndicator of(BankCardTypeIndicator bankCardTypeIndicator) {
        if (Objects.isNull(bankCardTypeIndicator)) {
            return null;
        } else {
            return new FastJsonBankCardTypeIndicator(
                    FastJsonStringIdKey.of(bankCardTypeIndicator.getKey()),
                    bankCardTypeIndicator.getLabel(),
                    bankCardTypeIndicator.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonStringIdKey key;

    @JSONField(name = "label", ordinal = 2)
    private String label;

    @JSONField(name = "remark", ordinal = 3)
    private String remark;

    public FastJsonBankCardTypeIndicator() {
    }

    public FastJsonBankCardTypeIndicator(FastJsonStringIdKey key, String label, String remark) {
        this.key = key;
        this.label = label;
        this.remark = remark;
    }

    public FastJsonStringIdKey getKey() {
        return key;
    }

    public void setKey(FastJsonStringIdKey key) {
        this.key = key;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "FastJsonBankCardTypeIndicator{" +
                "key=" + key +
                ", label='" + label + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
