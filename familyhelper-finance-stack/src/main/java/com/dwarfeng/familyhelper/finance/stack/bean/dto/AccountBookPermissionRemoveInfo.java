package com.dwarfeng.familyhelper.finance.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;

/**
 * 账本权限删除信息。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
public class AccountBookPermissionRemoveInfo implements Dto {

    private static final long serialVersionUID = -3317933138827581080L;

    private LongIdKey accountBookKey;
    private StringIdKey userKey;

    public AccountBookPermissionRemoveInfo() {
    }

    public AccountBookPermissionRemoveInfo(LongIdKey accountBookKey, StringIdKey userKey) {
        this.accountBookKey = accountBookKey;
        this.userKey = userKey;
    }

    public LongIdKey getAccountBookKey() {
        return accountBookKey;
    }

    public void setAccountBookKey(LongIdKey accountBookKey) {
        this.accountBookKey = accountBookKey;
    }

    public StringIdKey getUserKey() {
        return userKey;
    }

    public void setUserKey(StringIdKey userKey) {
        this.userKey = userKey;
    }

    @Override
    public String toString() {
        return "AccountBookPermissionRemoveInfo{" +
                "accountBookKey=" + accountBookKey +
                ", userKey=" + userKey +
                '}';
    }
}
