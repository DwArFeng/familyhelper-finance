package com.dwarfeng.familyhelper.finance.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 银行卡
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class BankCard implements Entity<LongIdKey> {

    private static final long serialVersionUID = -5823320713879134920L;

    private LongIdKey key;
    private LongIdKey accountBookKey;
    private String name;
    private String cardType;
    private Date lastRecordedDate;
    private BigDecimal balanceValue;
    private boolean tempFlag;
    private Date tempLastRecordedDate;
    private BigDecimal tempBalanceValue;
    private String remark;

    public BankCard() {
    }

    public BankCard(
            LongIdKey key, LongIdKey accountBookKey, String name, String cardType, Date lastRecordedDate,
            BigDecimal balanceValue, boolean tempFlag, Date tempLastRecordedDate, BigDecimal tempBalanceValue, String remark
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

    @Override
    public LongIdKey getKey() {
        return key;
    }

    @Override
    public void setKey(LongIdKey key) {
        this.key = key;
    }

    public LongIdKey getAccountBookKey() {
        return accountBookKey;
    }

    public void setAccountBookKey(LongIdKey accountBookKey) {
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
        return "BankCard{" +
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
