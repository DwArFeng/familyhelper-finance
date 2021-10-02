package com.dwarfeng.familyhelper.finance.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

/**
 * 银行卡创建信息。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class BankCardCreateInfo implements Dto {

    private static final long serialVersionUID = -1086808495667913782L;

    private String name;
    private String cardType;
    private String remark;

    public BankCardCreateInfo() {
    }

    public BankCardCreateInfo(String name, String cardType, String remark) {
        this.name = name;
        this.cardType = cardType;
        this.remark = remark;
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
        return "BankCardCreateInfo{" +
                "name='" + name + '\'' +
                ", cardType='" + cardType + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
