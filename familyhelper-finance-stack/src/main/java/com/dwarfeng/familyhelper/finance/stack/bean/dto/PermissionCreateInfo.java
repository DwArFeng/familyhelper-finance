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
public class PermissionCreateInfo implements Dto {

    private static final long serialVersionUID = -6327653953179188409L;

    private LongIdKey accountBookKey;
    private StringIdKey userKey;
    private int permissionLevel;

    public PermissionCreateInfo() {
    }

    public PermissionCreateInfo(LongIdKey AccountBookKey, StringIdKey userKey, int permissionLevel) {
        this.accountBookKey = AccountBookKey;
        this.userKey = userKey;
        this.permissionLevel = permissionLevel;
    }

    public LongIdKey getAccountBookKey() {
        return accountBookKey;
    }

    public void setAccountBookKey(LongIdKey AccountBookKey) {
        this.accountBookKey = AccountBookKey;
    }

    public StringIdKey getUserKey() {
        return userKey;
    }

    public void setUserKey(StringIdKey userKey) {
        this.userKey = userKey;
    }

    public int getPermissionLevel() {
        return permissionLevel;
    }

    public void setPermissionLevel(int permissionLevel) {
        this.permissionLevel = permissionLevel;
    }

    @Override
    public String toString() {
        return "PermissionCreateInfo{" +
                "accountBookKey=" + accountBookKey +
                ", userKey=" + userKey +
                ", permissionLevel=" + permissionLevel +
                '}';
    }
}
