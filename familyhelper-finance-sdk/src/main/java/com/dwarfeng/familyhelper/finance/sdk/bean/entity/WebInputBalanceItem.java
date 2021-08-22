package com.dwarfeng.familyhelper.finance.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.BalanceItem;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.math.BigDecimal;

/**
 * WebInput 余额条目。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class WebInputBalanceItem implements Bean {

    private static final long serialVersionUID = 8465231196383325944L;

    public static BalanceItem toStackBean(WebInputBalanceItem webInputBalanceItem) {
        return new BalanceItem(
                WebInputLongIdKey.toStackBean(webInputBalanceItem.getKey()),
                WebInputLongIdKey.toStackBean(webInputBalanceItem.getBalanceKey()),
                WebInputLongIdKey.toStackBean(webInputBalanceItem.getFundRepositoryKey()),
                webInputBalanceItem.getValue(),
                webInputBalanceItem.getRemark()
        );
    }

    @JSONField(name = "key")
    private WebInputLongIdKey key;

    @JSONField(name = "balanceKey")
    private WebInputLongIdKey balanceKey;

    @JSONField(name = "fundRepositoryKey")
    private WebInputLongIdKey fundRepositoryKey;

    @JSONField(name = "value")
    private BigDecimal value;

    @JSONField(name = "remark")
    private String remark;

    public WebInputBalanceItem() {
    }

    public WebInputLongIdKey getKey() {
        return key;
    }

    public void setKey(WebInputLongIdKey key) {
        this.key = key;
    }

    public WebInputLongIdKey getBalanceKey() {
        return balanceKey;
    }

    public void setBalanceKey(WebInputLongIdKey balanceKey) {
        this.balanceKey = balanceKey;
    }

    public WebInputLongIdKey getFundRepositoryKey() {
        return fundRepositoryKey;
    }

    public void setFundRepositoryKey(WebInputLongIdKey fundRepositoryKey) {
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
        return "WebInputBalanceItem{" +
                "key=" + key +
                ", balanceKey=" + balanceKey +
                ", fundRepositoryKey=" + fundRepositoryKey +
                ", value=" + value +
                ", remark='" + remark + '\'' +
                '}';
    }
}
