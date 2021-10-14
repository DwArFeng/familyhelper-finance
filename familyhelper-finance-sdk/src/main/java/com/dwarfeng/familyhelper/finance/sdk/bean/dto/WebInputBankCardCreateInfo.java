package com.dwarfeng.familyhelper.finance.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.finance.stack.bean.dto.BankCardCreateInfo;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 银行卡创建信息。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class WebInputBankCardCreateInfo implements Dto {

    private static final long serialVersionUID = -9199994504833105204L;

    public static BankCardCreateInfo toStackBean(WebInputBankCardCreateInfo webInputBankCardCreateInfo) {
        if (Objects.isNull(webInputBankCardCreateInfo)) {
            return null;
        } else {
            return new BankCardCreateInfo(
                    webInputBankCardCreateInfo.getName(),
                    webInputBankCardCreateInfo.getCardType(),
                    webInputBankCardCreateInfo.getRemark()
            );
        }
    }

    @JSONField(name = "name")
    @NotNull
    @NotEmpty
    private String name;

    @JSONField(name = "card_type")
    private String cardType;

    @JSONField(name = "remark")
    private String remark;

    public WebInputBankCardCreateInfo() {
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
        return "WebInputBankCardCreateInfo{" +
                "name='" + name + '\'' +
                ", cardType='" + cardType + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}