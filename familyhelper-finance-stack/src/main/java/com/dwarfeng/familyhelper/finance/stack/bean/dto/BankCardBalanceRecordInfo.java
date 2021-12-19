package com.dwarfeng.familyhelper.finance.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.math.BigDecimal;

/**
 * 银行卡余额记录信息。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
public class BankCardBalanceRecordInfo implements Dto {

    private static final long serialVersionUID = 4078076948282414474L;

    private LongIdKey bankCardKey;
    private BigDecimal balance;

    public BankCardBalanceRecordInfo() {
    }

    public BankCardBalanceRecordInfo(LongIdKey bankCardKey, BigDecimal balance) {
        this.bankCardKey = bankCardKey;
        this.balance = balance;
    }

    public LongIdKey getBankCardKey() {
        return bankCardKey;
    }

    public void setBankCardKey(LongIdKey bankCardKey) {
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
        return "BankCardBalanceRecordInfo{" +
                "bankCardKey=" + bankCardKey +
                ", balance=" + balance +
                '}';
    }
}
