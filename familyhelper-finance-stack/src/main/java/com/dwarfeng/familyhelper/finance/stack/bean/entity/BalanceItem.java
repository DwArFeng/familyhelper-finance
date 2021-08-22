package com.dwarfeng.familyhelper.finance.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.math.BigDecimal;

/**
 * 余额条目。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class BalanceItem implements Entity<LongIdKey> {

    private static final long serialVersionUID = -6213926882994493437L;

    private LongIdKey key;
    private LongIdKey balanceKey;
    private LongIdKey fundRepositoryKey;
    private BigDecimal value;
    private String remark;

    public BalanceItem() {
    }

    public BalanceItem(
            LongIdKey key, LongIdKey balanceKey, LongIdKey fundRepositoryKey, BigDecimal value, String remark
    ) {
        this.key = key;
        this.balanceKey = balanceKey;
        this.fundRepositoryKey = fundRepositoryKey;
        this.value = value;
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

    public LongIdKey getBalanceKey() {
        return balanceKey;
    }

    public void setBalanceKey(LongIdKey balanceKey) {
        this.balanceKey = balanceKey;
    }

    public LongIdKey getFundRepositoryKey() {
        return fundRepositoryKey;
    }

    public void setFundRepositoryKey(LongIdKey fundRepositoryKey) {
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
        return "BalanceItem{" +
                "key=" + key +
                ", balanceKey=" + balanceKey +
                ", fundRepositoryKey=" + fundRepositoryKey +
                ", value=" + value +
                ", remark='" + remark + '\'' +
                '}';
    }
}
