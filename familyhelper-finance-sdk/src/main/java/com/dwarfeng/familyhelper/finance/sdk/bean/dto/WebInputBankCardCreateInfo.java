package com.dwarfeng.familyhelper.finance.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.finance.stack.bean.dto.BankCardCreateInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import javax.validation.Valid;
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

    private static final long serialVersionUID = -8378109328163794082L;

    public static BankCardCreateInfo toStackBean(WebInputBankCardCreateInfo webInputBankCardCreateInfo) {
        if (Objects.isNull(webInputBankCardCreateInfo)) {
            return null;
        } else {
            return new BankCardCreateInfo(
                    WebInputLongIdKey.toStackBean(webInputBankCardCreateInfo.getAccountBookKey()),
                    webInputBankCardCreateInfo.getName(),
                    webInputBankCardCreateInfo.getCardType(),
                    webInputBankCardCreateInfo.getRemark()
            );
        }
    }

    @JSONField(name = "account_book_key")
    @Valid
    @NotNull
    private WebInputLongIdKey accountBookKey;

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "WebInputBankCardCreateInfo{" +
                "accountBookKey=" + accountBookKey +
                ", name='" + name + '\'' +
                ", cardType='" + cardType + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
