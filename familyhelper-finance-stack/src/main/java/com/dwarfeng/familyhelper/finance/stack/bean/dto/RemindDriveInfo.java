package com.dwarfeng.familyhelper.finance.stack.bean.dto;

import com.dwarfeng.familyhelper.finance.stack.bean.entity.AccountBook;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.RemindDriverInfo;
import com.dwarfeng.familyhelper.finance.stack.handler.RemindDriver;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Map;

/**
 * 提醒驱动信息。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
public class RemindDriveInfo implements Dto {

    private static final long serialVersionUID = 773163451373192876L;

    private AccountBook accountBook;
    private Map<RemindDriverInfo, RemindDriver> remindDriverMap;

    public RemindDriveInfo() {
    }

    public RemindDriveInfo(AccountBook accountBook, Map<RemindDriverInfo, RemindDriver> remindDriverMap) {
        this.accountBook = accountBook;
        this.remindDriverMap = remindDriverMap;
    }

    public AccountBook getAccountBook() {
        return accountBook;
    }

    public void setAccountBook(AccountBook accountBook) {
        this.accountBook = accountBook;
    }

    public Map<RemindDriverInfo, RemindDriver> getRemindDriverMap() {
        return remindDriverMap;
    }

    public void setRemindDriverMap(Map<RemindDriverInfo, RemindDriver> remindDriverMap) {
        this.remindDriverMap = remindDriverMap;
    }

    @Override
    public String toString() {
        return "RemindDriveInfo{" +
                "accountBook=" + accountBook +
                ", remindDriverMap=" + remindDriverMap +
                '}';
    }
}
