package com.dwarfeng.familyhelper.finance.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.Balance;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * WebInput 余额。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class WebInputBalance implements Bean {

    private static final long serialVersionUID = -4393176837935825408L;

    public static Balance toStackBean(WebInputBalance webInputBalance) {
        return new Balance(
                WebInputLongIdKey.toStackBean(webInputBalance.getKey()),
                WebInputLongIdKey.toStackBean(webInputBalance.getAccountBookKey()),
                webInputBalance.getRecordedDate(),
                webInputBalance.getValue(),
                webInputBalance.getRemark()
        );
    }

    @JSONField(name = "key")
    private WebInputLongIdKey key;

    @JSONField(name = "account_book_key")
    private WebInputLongIdKey accountBookKey;

    @JSONField(name = "recorded_date")
    private Date recordedDate;

    @JSONField(name = "value")
    private BigDecimal value;

    @JSONField(name = "remark")
    private String remark;

    public WebInputBalance() {
    }

    public WebInputLongIdKey getKey() {
        return key;
    }

    public void setKey(WebInputLongIdKey key) {
        this.key = key;
    }

    public WebInputLongIdKey getAccountBookKey() {
        return accountBookKey;
    }

    public void setAccountBookKey(WebInputLongIdKey accountBookKey) {
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
        return "WebInputBalance{" +
                "key=" + key +
                ", accountBookKey=" + accountBookKey +
                ", recordedDate=" + recordedDate +
                ", value=" + value +
                ", remark='" + remark + '\'' +
                '}';
    }
}
