package com.dwarfeng.familyhelper.finance.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;

/**
 * 资产目录权限信息。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
public class PermissionRemoveInfo implements Dto {

    private static final long serialVersionUID = -2288921537659735982L;

    private LongIdKey accountBookKey;
    private StringIdKey userKey;

    public PermissionRemoveInfo() {
    }

    public PermissionRemoveInfo(LongIdKey accountBookKey, StringIdKey userKey) {
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
        return "PermissionRemoveInfo{" +
                "accountBookKey=" + accountBookKey +
                ", userKey=" + userKey +
                '}';
    }
}
