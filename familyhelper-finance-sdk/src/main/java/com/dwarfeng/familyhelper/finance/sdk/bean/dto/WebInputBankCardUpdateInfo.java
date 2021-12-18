package com.dwarfeng.familyhelper.finance.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.finance.stack.bean.dto.BankCardUpdateInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 银行卡更新信息。
 *
 * @author DwArFeng
 * @since 1.1.1
 */
public class WebInputBankCardUpdateInfo implements Dto {

    private static final long serialVersionUID = -3858070031084898759L;

    public static BankCardUpdateInfo toStackBean(WebInputBankCardUpdateInfo webInputBankCardUpdateInfo) {
        if (Objects.isNull(webInputBankCardUpdateInfo)) {
            return null;
        } else {
            return new BankCardUpdateInfo(
                    WebInputLongIdKey.toStackBean(webInputBankCardUpdateInfo.getBankCardKey()),
                    webInputBankCardUpdateInfo.getName(),
                    webInputBankCardUpdateInfo.getCardType(),
                    webInputBankCardUpdateInfo.getRemark()
            );
        }
    }

    @JSONField(name = "bank_card_key")
    @Valid
    @NotNull
    private WebInputLongIdKey bankCardKey;

    @JSONField(name = "name")
    @NotNull
    @NotEmpty
    private String name;

    @JSONField(name = "card_type")
    private String cardType;

    @JSONField(name = "remark")
    private String remark;

    public WebInputBankCardUpdateInfo() {
    }

    public WebInputLongIdKey getBankCardKey() {
        return bankCardKey;
    }

    public void setBankCardKey(WebInputLongIdKey bankCardKey) {
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
        return "WebInputBankCardUpdateInfo{" +
                "bankCardKey=" + bankCardKey +
                ", name='" + name + '\'' +
                ", cardType='" + cardType + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
