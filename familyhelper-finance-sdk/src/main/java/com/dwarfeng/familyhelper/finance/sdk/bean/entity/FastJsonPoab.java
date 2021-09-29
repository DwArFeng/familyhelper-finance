package com.dwarfeng.familyhelper.finance.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.finance.sdk.bean.key.FastJsonPoabKey;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.Poab;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 账本权限。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class FastJsonPoab implements Bean {

    private static final long serialVersionUID = -3408738912153363522L;

    public static FastJsonPoab of(Poab poab) {
        if (Objects.isNull(poab)) {
            return null;
        } else {
            return new FastJsonPoab(FastJsonPoabKey.of(poab.getKey()), poab.getPermissionLevel(), poab.getRemark());
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonPoabKey key;

    @JSONField(name = "permission_level", ordinal = 2)
    private int permissionLevel;

    @JSONField(name = "remark", ordinal = 3)
    private String remark;

    public FastJsonPoab() {
    }

    public FastJsonPoab(FastJsonPoabKey key, int permissionLevel, String remark) {
        this.key = key;
        this.permissionLevel = permissionLevel;
        this.remark = remark;
    }

    public FastJsonPoabKey getKey() {
        return key;
    }

    public void setKey(FastJsonPoabKey key) {
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
        return "FastJsonPoab{" +
                "key=" + key +
                ", permissionLevel=" + permissionLevel +
                ", remark='" + remark + '\'' +
                '}';
    }
}
