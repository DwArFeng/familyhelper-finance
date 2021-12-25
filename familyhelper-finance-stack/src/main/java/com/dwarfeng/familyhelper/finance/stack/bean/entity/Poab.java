package com.dwarfeng.familyhelper.finance.stack.bean.entity;

import com.dwarfeng.familyhelper.finance.stack.bean.key.PoabKey;
import com.dwarfeng.subgrade.stack.bean.entity.Entity;

/**
 * 账本权限。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class Poab implements Entity<PoabKey> {

    private static final long serialVersionUID = 6405468025576702046L;

    private PoabKey key;
    private int permissionLevel;
    private String remark;

    public Poab() {
    }

    public Poab(PoabKey key, int permissionLevel, String remark) {
        this.key = key;
        this.permissionLevel = permissionLevel;
        this.remark = remark;
    }

    @Override
    public PoabKey getKey() {
        return key;
    }

    @Override
    public void setKey(PoabKey key) {
        this.key = key;
    }

    public int getPermissionLevel() {
        return permissionLevel;
    }

    public void setPermissionLevel(int permissionLevel) {
        this.permissionLevel = permissionLevel;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Poab{" +
                "key=" + key +
                ", permissionLevel=" + permissionLevel +
                ", remark='" + remark + '\'' +
                '}';
    }
}
