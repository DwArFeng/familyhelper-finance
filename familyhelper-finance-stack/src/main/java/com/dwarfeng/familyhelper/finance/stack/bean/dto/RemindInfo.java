package com.dwarfeng.familyhelper.finance.stack.bean.dto;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.AccountBook;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.RemindDriverInfo;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.User;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.List;

/**
 * 提醒信息。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
public class RemindInfo implements Dto {

    private static final long serialVersionUID = 7750902881714898399L;

    private AccountBook accountBook;
    private RemindDriverInfo remindDriverInfo;
    private List<User> aimedUsers;

    public RemindInfo() {
    }

    public RemindInfo(AccountBook accountBook, RemindDriverInfo remindDriverInfo, List<User> aimedUsers) {
        this.accountBook = accountBook;
        this.remindDriverInfo = remindDriverInfo;
        this.aimedUsers = aimedUsers;
    }

    public AccountBook getAccountBook() {
        return accountBook;
    }

    public void setAccountBook(AccountBook accountBook) {
        this.accountBook = accountBook;
    }

    public RemindDriverInfo getRemindDriverInfo() {
        return remindDriverInfo;
    }

    public void setRemindDriverInfo(RemindDriverInfo remindDriverInfo) {
        this.remindDriverInfo = remindDriverInfo;
    }

    public List<User> getAimedUsers() {
        return aimedUsers;
    }

    public void setAimedUsers(List<User> aimedUsers) {
        this.aimedUsers = aimedUsers;
    }

    @Override
    public String toString() {
        return "RemindInfo{" +
                "accountBook=" + accountBook +
                ", remindDriverInfo=" + remindDriverInfo +
                ", aimedUsers=" + aimedUsers +
                '}';
    }
}
