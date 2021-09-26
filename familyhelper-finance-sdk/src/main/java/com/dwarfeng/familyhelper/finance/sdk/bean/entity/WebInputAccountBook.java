package com.dwarfeng.familyhelper.finance.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.AccountBook;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.validation.Valid;
import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.util.Date;

/**
 * WebInput 账本。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class WebInputAccountBook implements Bean {

    private static final long serialVersionUID = 1881596395658959364L;

    public static AccountBook toStackBean(WebInputAccountBook webInputAccountBook) {
        return new AccountBook(
                WebInputLongIdKey.toStackBean(webInputAccountBook.getKey()),
                webInputAccountBook.getName(),
                webInputAccountBook.getLastRecordedDate(),
                webInputAccountBook.getTotalValue(),
                webInputAccountBook.getRemark()
        );
    }

    @JSONField(name = "key")
    @Valid
    private WebInputLongIdKey key;

    @JSONField(name = "name")
    private String name;

    @JSONField(name = "last_recorded_date")
    @Null
    private Date lastRecordedDate;

    @JSONField(name = "total_value")
    @Null
    private BigDecimal totalValue;

    @JSONField(name = "remark")
    private String remark;

    public WebInputAccountBook() {
    }

    public WebInputLongIdKey getKey() {
        return key;
    }

    public void setKey(WebInputLongIdKey key) {
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
        return "WebInputAccountBook{" +
                "key=" + key +
                ", name='" + name + '\'' +
                ", lastRecordedDate=" + lastRecordedDate +
                ", totalValue=" + totalValue +
                ", remark='" + remark + '\'' +
                '}';
    }
}
