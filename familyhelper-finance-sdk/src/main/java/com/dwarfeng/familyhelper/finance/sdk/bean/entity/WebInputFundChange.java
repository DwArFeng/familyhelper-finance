package com.dwarfeng.familyhelper.finance.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.FundChange;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Date;

/**
 * WebInput 资金变更。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class WebInputFundChange implements Bean {

    private static final long serialVersionUID = -562586185696693603L;

    public static FundChange toStackBean(WebInputFundChange webInputFundChange) {
        return new FundChange(
                WebInputLongIdKey.toStackBean(webInputFundChange.getKey()),
                WebInputLongIdKey.toStackBean(webInputFundChange.getAccountBookKey()),
                webInputFundChange.getChangeType(),
                webInputFundChange.getHappenedDate(),
                webInputFundChange.getRemark()
        );
    }

    @JSONField(name = "key")
    private WebInputLongIdKey key;

    @JSONField(name = "account_book_key")
    private WebInputLongIdKey accountBookKey;

    @JSONField(name = "change_type")
    private String changeType;

    @JSONField(name = "happened_date")
    private Date happenedDate;

    @JSONField(name = "remark")
    private String remark;

    public WebInputFundChange() {
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

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
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
        return "WebInputFundChange{" +
                "key=" + key +
                ", accountBookKey=" + accountBookKey +
                ", changeType='" + changeType + '\'' +
                ", happenedDate=" + happenedDate +
                ", remark='" + remark + '\'' +
                '}';
    }
}
