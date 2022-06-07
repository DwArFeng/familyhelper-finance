package com.dwarfeng.familyhelper.finance.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.UrgeSetting;
import com.dwarfeng.subgrade.sdk.bean.key.JSFixedFastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * JSFixed FastJson 敦促设置。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
public class JSFixedFastJsonUrgeSetting implements Bean {

    private static final long serialVersionUID = 8602432403956100860L;

    public static JSFixedFastJsonUrgeSetting of(UrgeSetting urgeSetting) {
        if (Objects.isNull(urgeSetting)) {
            return null;
        } else {
            return new JSFixedFastJsonUrgeSetting(
                    JSFixedFastJsonLongIdKey.of(urgeSetting.getKey()),
                    urgeSetting.getCron(), urgeSetting.getRemark(),
                    urgeSetting.getModifiedCount(), urgeSetting.isEnabled()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonLongIdKey key;

    @JSONField(name = "cron", ordinal = 2)
    private String cron;

    @JSONField(name = "remark", ordinal = 3)
    private String remark;

    @JSONField(name = "modified_count", ordinal = 4)
    private int modifiedCount;

    @JSONField(name = "enabled", ordinal = 5)
    private boolean enabled;

    public JSFixedFastJsonUrgeSetting() {
    }

    public JSFixedFastJsonUrgeSetting(
            JSFixedFastJsonLongIdKey key, String cron, String remark, int modifiedCount, boolean enabled
    ) {
        this.key = key;
        this.cron = cron;
        this.remark = remark;
        this.modifiedCount = modifiedCount;
        this.enabled = enabled;
    }

    public JSFixedFastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(JSFixedFastJsonLongIdKey key) {
        this.key = key;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getModifiedCount() {
        return modifiedCount;
    }

    public void setModifiedCount(int modifiedCount) {
        this.modifiedCount = modifiedCount;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "JSFixedFastJsonUrgeSetting{" +
                "key=" + key +
                ", cron='" + cron + '\'' +
                ", remark='" + remark + '\'' +
                ", modifiedCount=" + modifiedCount +
                ", enabled=" + enabled +
                '}';
    }
}
