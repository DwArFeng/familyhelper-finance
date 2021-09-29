package com.dwarfeng.familyhelper.finance.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.finance.sdk.bean.key.WebInputPoabKey;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.Poab;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.validation.Valid;
import java.util.Objects;

/**
 * WebInput 账本权限。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class WebInputPoab implements Bean {

    private static final long serialVersionUID = -6169785052876883872L;

    public static Poab toStackBean(WebInputPoab webInputPoab) {
        if (Objects.isNull(webInputPoab)) {
            return null;
        } else {
            return new Poab(
                    WebInputPoabKey.toStackBean(webInputPoab.getKey()),
                    webInputPoab.getPermissionLevel(),
                    webInputPoab.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    @Valid
    private WebInputPoabKey key;

    @JSONField(name = "permission_level", ordinal = 2)
    private int permissionLevel;

    @JSONField(name = "remark", ordinal = 3)
    private String remark;

    public WebInputPoab() {
    }

    public WebInputPoabKey getKey() {
        return key;
    }

    public void setKey(WebInputPoabKey key) {
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
        return "WebInputPoab{" +
                "key=" + key +
                ", permissionLevel=" + permissionLevel +
                ", remark='" + remark + '\'' +
                '}';
    }
}
