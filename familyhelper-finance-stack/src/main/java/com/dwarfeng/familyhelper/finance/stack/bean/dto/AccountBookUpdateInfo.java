package com.dwarfeng.familyhelper.finance.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 账本更新信息。
 *
 * @author DwArFeng
 * @since 1.1.1
 */
public class AccountBookUpdateInfo implements Dto {

    private static final long serialVersionUID = 8145408920415218103L;

    private LongIdKey accountBookKey;
    private String name;
    private String remark;

    public AccountBookUpdateInfo() {
    }

    public AccountBookUpdateInfo(LongIdKey accountBookKey, String name, String remark) {
        this.accountBookKey = accountBookKey;
        this.name = name;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "AccountBookUpdateInfo{" +
                "accountBookKey=" + accountBookKey +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
