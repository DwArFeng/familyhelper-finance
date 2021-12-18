package com.dwarfeng.familyhelper.finance.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 银行卡创更新信息。
 *
 * @author DwArFeng
 * @since 1.1.1
 */
public class BankCardUpdateInfo implements Dto {

    private static final long serialVersionUID = -5253559119807476705L;

    private LongIdKey bankCardKey;
    private String name;
    private String cardType;
    private String remark;

    public BankCardUpdateInfo() {
    }

    public BankCardUpdateInfo(LongIdKey bankCardKey, String name, String cardType, String remark) {
        this.bankCardKey = bankCardKey;
        this.name = name;
        this.cardType = cardType;
        this.remark = remark;
    }

    public LongIdKey getBankCardKey() {
        return bankCardKey;
    }

    public void setBankCardKey(LongIdKey bankCardKey) {
        this.bankCardKey = bankCardKey;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "BankCardUpdateInfo{" +
                "bankCardKey=" + bankCardKey +
                ", name='" + name + '\'' +
                ", cardType='" + cardType + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
