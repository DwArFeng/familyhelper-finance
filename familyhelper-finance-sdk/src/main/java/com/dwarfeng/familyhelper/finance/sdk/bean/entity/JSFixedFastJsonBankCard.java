package com.dwarfeng.familyhelper.finance.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.BankCard;
import com.dwarfeng.subgrade.sdk.bean.key.JSFixedFastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * FastJson 银行卡。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class JSFixedFastJsonBankCard implements Bean {

    private static final long serialVersionUID = 1820554646231859082L;

    public static JSFixedFastJsonBankCard of(BankCard bankCard) {
        if (Objects.isNull(bankCard)) {
            return null;
        } else {
            return new JSFixedFastJsonBankCard(
                    JSFixedFastJsonLongIdKey.of(bankCard.getKey()),
                    JSFixedFastJsonLongIdKey.of(bankCard.getAccountBookKey()),
                    bankCard.getName(),
                    bankCard.getCardType(),
                    bankCard.getLastRecordedDate(),
                    bankCard.getBalanceValue(),
                    bankCard.isTempFlag(),
                    bankCard.getTempLastRecordedDate(),
                    bankCard.getTempBalanceValue(),
                    bankCard.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonLongIdKey key;

    @JSONField(name = "account_book_key", ordinal = 2)
    private JSFixedFastJsonLongIdKey accountBookKey;

    @JSONField(name = "name", ordinal = 3)
    private String name;

    @JSONField(name = "card_type", ordinal = 4)
    private String cardType;

    @JSONField(name = "last_recorded_date", ordinal = 5)
    private Date lastRecordedDate;

    @JSONField(name = "balance_value", ordinal = 6)
    private BigDecimal balanceValue;

    @JSONField(name = "temp_flag", ordinal = 7)
    private boolean tempFlag;

    @JSONField(name = "temp_last_recorded_date", ordinal = 8)
    private Date tempLastRecordedDate;

    @JSONField(name = "temp_balance_value", ordinal = 9)
    private BigDecimal tempBalanceValue;

    @JSONField(name = "remark", ordinal = 10)
    private String remark;

    public JSFixedFastJsonBankCard() {
    }

    public JSFixedFastJsonBankCard(
            JSFixedFastJsonLongIdKey key, JSFixedFastJsonLongIdKey accountBookKey, String name, String cardType,
            Date lastRecordedDate, BigDecimal balanceValue, boolean tempFlag, Date tempLastRecordedDate,
            BigDecimal tempBalanceValue, String remark
    ) {
        this.key = key;
        this.accountBookKey = accountBookKey;
        this.name = name;
        this.cardType = cardType;
        this.lastRecordedDate = lastRecordedDate;
        this.balanceValue = balanceValue;
        this.tempFlag = tempFlag;
        this.tempLastRecordedDate = tempLastRecordedDate;
        this.tempBalanceValue = tempBalanceValue;
        this.remark = remark;
    }

    public JSFixedFastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(JSFixedFastJsonLongIdKey key) {
        this.key = key;
    }

    public JSFixedFastJsonLongIdKey getAccountBookKey() {
        return accountBookKey;
    }

    public void setAccountBookKey(JSFixedFastJsonLongIdKey accountBookKey) {
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
        return "JSFixedFastJsonBankCard{" +
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
