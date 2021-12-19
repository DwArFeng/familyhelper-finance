package com.dwarfeng.familyhelper.finance.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.finance.stack.bean.dto.BankCardBalanceRecordInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * WebInput 银行卡余额记录信息。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
public class WebInputBankCardBalanceRecordInfo implements Dto {

    private static final long serialVersionUID = 7288024589314351668L;

    public static BankCardBalanceRecordInfo toStackBean(
            WebInputBankCardBalanceRecordInfo webInputBankCardBalanceRecordInfo
    ) {
        if (Objects.isNull(webInputBankCardBalanceRecordInfo)) {
            return null;
        } else {
            return new BankCardBalanceRecordInfo(
                    WebInputLongIdKey.toStackBean(webInputBankCardBalanceRecordInfo.getBankCardKey()),
                    webInputBankCardBalanceRecordInfo.getBalance()
            );
        }
    }

    @JSONField(name = "bank_card_key")
    @Valid
    @NotNull
    private WebInputLongIdKey bankCardKey;

    @JSONField(name = "balance")
    @NotNull
    private BigDecimal balance;

    public WebInputBankCardBalanceRecordInfo() {
    }

    public WebInputLongIdKey getBankCardKey() {
        return bankCardKey;
    }

    public void setBankCardKey(WebInputLongIdKey bankCardKey) {
        this.bankCardKey = bankCardKey;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "WebInputBankCardBalanceRecordInfo{" +
                "bankCardKey=" + bankCardKey +
                ", balance=" + balance +
                '}';
    }
}
