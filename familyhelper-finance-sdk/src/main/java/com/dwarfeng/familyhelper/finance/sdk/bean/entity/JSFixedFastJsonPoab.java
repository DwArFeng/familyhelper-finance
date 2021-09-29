package com.dwarfeng.familyhelper.finance.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.finance.sdk.bean.key.JSFixedFastJsonPoabKey;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.Poab;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * JSFixed FastJson 账本权限。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class JSFixedFastJsonPoab implements Bean {

    private static final long serialVersionUID = -7761698465500085933L;

    public static JSFixedFastJsonPoab of(Poab poab) {
        if (Objects.isNull(poab)) {
            return null;
        } else {
            return new JSFixedFastJsonPoab(
                    JSFixedFastJsonPoabKey.of(poab.getKey()), poab.getPermissionLevel(), poab.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonPoabKey key;

    @JSONField(name = "permission_level", ordinal = 2)
    private int permissionLevel;

    @JSONField(name = "remark", ordinal = 3)
    private String remark;

    public JSFixedFastJsonPoab() {
    }

    public JSFixedFastJsonPoab(JSFixedFastJsonPoabKey key, int permissionLevel, String remark) {
        this.key = key;
        this.permissionLevel = permissionLevel;
        this.remark = remark;
    }

    public JSFixedFastJsonPoabKey getKey() {
        return key;
    }

    public void setKey(JSFixedFastJsonPoabKey key) {
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
        return "JSFixedFastJsonPoab{" +
                "key=" + key +
                ", permissionLevel=" + permissionLevel +
                ", remark='" + remark + '\'' +
                '}';
    }
}
