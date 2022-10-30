package com.dwarfeng.familyhelper.finance.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.RemindDriverInfo;
import com.dwarfeng.subgrade.sdk.bean.key.JSFixedFastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * JSFixed FastJson 提醒驱动信息。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
public class JSFixedFastJsonRemindDriverInfo implements Bean {

    private static final long serialVersionUID = 4015473162112507476L;

    public static JSFixedFastJsonRemindDriverInfo of(RemindDriverInfo remindDriverInfo) {
        if (Objects.isNull(remindDriverInfo)) {
            return null;
        } else {
            return new JSFixedFastJsonRemindDriverInfo(
                    JSFixedFastJsonLongIdKey.of(remindDriverInfo.getKey()),
                    JSFixedFastJsonLongIdKey.of(remindDriverInfo.getAccountBookKey()),
                    remindDriverInfo.isEnabled(), remindDriverInfo.getType(), remindDriverInfo.getParam(),
                    remindDriverInfo.getRemindScopeType(), remindDriverInfo.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonLongIdKey key;

    @JSONField(name = "account_book_key", ordinal = 2)
    private JSFixedFastJsonLongIdKey accountBookKey;

    @JSONField(name = "enabled", ordinal = 3)
    private boolean enabled;

    @JSONField(name = "type", ordinal = 4)
    private String type;

    @JSONField(name = "param", ordinal = 5)
    private String param;

    @JSONField(name = "remind_scope_type", ordinal = 6)
    private int remindScopeType;

    @JSONField(name = "remark", ordinal = 7)
    private String remark;

    public JSFixedFastJsonRemindDriverInfo() {
    }

    public JSFixedFastJsonRemindDriverInfo(
            JSFixedFastJsonLongIdKey key, JSFixedFastJsonLongIdKey accountBookKey, boolean enabled, String type, String param,
            int remindScopeType, String remark
    ) {
        this.key = key;
        this.accountBookKey = accountBookKey;
        this.enabled = enabled;
        this.type = type;
        this.param = param;
        this.remindScopeType = remindScopeType;
        this.remark = remark;
    }

    public JSFixedFastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(JSFixedFastJsonLongIdKey key) {
        this.key = key;
    }

    public JSFixedFastJsonLongIdKey getAccountBookKey() {
        return accountBookKey;
    }

    public void setAccountBookKey(JSFixedFastJsonLongIdKey accountBookKey) {
        this.accountBookKey = accountBookKey;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public int getRemindScopeType() {
        return remindScopeType;
    }

    public void setRemindScopeType(int remindScopeType) {
        this.remindScopeType = remindScopeType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "FastJsonRemindDriverInfo{" +
                "key=" + key +
                ", accountBookKey=" + accountBookKey +
                ", enabled=" + enabled +
                ", type='" + type + '\'' +
                ", param='" + param + '\'' +
                ", remindScopeType=" + remindScopeType +
                ", remark='" + remark + '\'' +
                '}';
    }
}
