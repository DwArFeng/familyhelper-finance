package com.dwarfeng.familyhelper.finance.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 银行卡创建信息。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class BankCardCreateInfo implements Dto {

    private static final long serialVersionUID = -6874075089331691767L;

    private LongIdKey accountBookKey;
    private String name;
    private String cardType;
    private String remark;

    public BankCardCreateInfo() {
    }

    public BankCardCreateInfo(LongIdKey accountBookKey, String name, String cardType, String remark) {
        this.accountBookKey = accountBookKey;
        this.name = name;
        this.cardType = cardType;
        this.remark = remark;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "BankCardCreateInfo{" +
                "accountBookKey=" + accountBookKey +
                ", name='" + name + '\'' +
                ", cardType='" + cardType + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
