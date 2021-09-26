package com.dwarfeng.familyhelper.finance.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.BankCard;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.validation.Valid;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * WebInput 银行卡。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class WebInputBankCard implements Bean {

    private static final long serialVersionUID = 5409765704082003847L;

    public static BankCard toStackBean(WebInputBankCard webInputBankCard) {
        if (Objects.isNull(webInputBankCard)) {
            return null;
        } else {
            return new BankCard(
                    WebInputLongIdKey.toStackBean(webInputBankCard.getKey()),
                    WebInputLongIdKey.toStackBean(webInputBankCard.getAccountBookKey()),
                    webInputBankCard.getName(),
                    webInputBankCard.getCardType(),
                    webInputBankCard.getLastRecordedDate(),
                    webInputBankCard.getBalanceValue(),
                    webInputBankCard.isTempFlag(),
                    webInputBankCard.getTempLastRecordedDate(),
                    webInputBankCard.getTempBalanceValue(),
                    webInputBankCard.getRemark()
            );
        }
    }

    @JSONField(name = "key")
    @Valid
    private WebInputLongIdKey key;

    @JSONField(name = "account_book_key")
    @Valid
    private WebInputLongIdKey accountBookKey;

    @JSONField(name = "name")
    @NotNull
    @NotEmpty
    private String name;

    @JSONField(name = "card_type")
    private String cardType;

    @JSONField(name = "last_recorded_date")
    @Null
    private Date lastRecordedDate;

    @JSONField(name = "balance_value")
    @Null
    private BigDecimal balanceValue;

    @JSONField(name = "temp_flag")
    @AssertFalse
    private boolean tempFlag;

    @JSONField(name = "temp_last_recorded_date")
    @Null
    private Date tempLastRecordedDate;

    @JSONField(name = "temp_balance_value")
    @Null
    private BigDecimal tempBalanceValue;

    @JSONField(name = "remark")
    private String remark;

    public WebInputBankCard() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public Date getLastRecordedDate() {
        return lastRecordedDate;
    }

    public void setLastRecordedDate(Date lastRecordedDate) {
        this.lastRecordedDate = lastRecordedDate;
    }

    public BigDecimal getBalanceValue() {
        return balanceValue;
    }

    public void setBalanceValue(BigDecimal balanceValue) {
        this.balanceValue = balanceValue;
    }

    public boolean isTempFlag() {
        return tempFlag;
    }

    public void setTempFlag(boolean tempFlag) {
        this.tempFlag = tempFlag;
    }

    public Date getTempLastRecordedDate() {
        return tempLastRecordedDate;
    }

    public void setTempLastRecordedDate(Date tempLastRecordedDate) {
        this.tempLastRecordedDate = tempLastRecordedDate;
    }

    public BigDecimal getTempBalanceValue() {
        return tempBalanceValue;
    }

    public void setTempBalanceValue(BigDecimal tempBalanceValue) {
        this.tempBalanceValue = tempBalanceValue;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "WebInputBankCard{" +
                "key=" + key +
                ", accountBookKey=" + accountBookKey +
                ", name='" + name + '\'' +
                ", cardType='" + cardType + '\'' +
                ", lastRecordedDate=" + lastRecordedDate +
                ", balanceValue=" + balanceValue +
                ", tempFlag=" + tempFlag +
                ", tempLastRecordedDate=" + tempLastRecordedDate +
                ", tempBalanceValue=" + tempBalanceValue +
                ", remark='" + remark + '\'' +
                '}';
    }
}
