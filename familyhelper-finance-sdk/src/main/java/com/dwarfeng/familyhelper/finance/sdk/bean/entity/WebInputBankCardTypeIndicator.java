package com.dwarfeng.familyhelper.finance.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.BankCardTypeIndicator;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

/**
 * WebInput 银行卡类型指示器。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class WebInputBankCardTypeIndicator implements Bean {

    private static final long serialVersionUID = -6384249388542365083L;

    public static BankCardTypeIndicator toStackBean(WebInputBankCardTypeIndicator webInputBankCardTypeIndicator) {
        return new BankCardTypeIndicator(
                WebInputStringIdKey.toStackBean(webInputBankCardTypeIndicator.getKey()),
                webInputBankCardTypeIndicator.getLabel(),
                webInputBankCardTypeIndicator.getRemark()
        );
    }

    @JSONField(name = "key")
    private WebInputStringIdKey key;

    @JSONField(name = "label")
    private String label;

    @JSONField(name = "remark")
    private String remark;

    public WebInputBankCardTypeIndicator() {
    }

    public WebInputStringIdKey getKey() {
        return key;
    }

    public void setKey(WebInputStringIdKey key) {
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
        return "WebInputBankCardTypeIndicator{" +
                "key=" + key +
                ", label='" + label + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
