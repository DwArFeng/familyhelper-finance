package com.dwarfeng.familyhelper.finance.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.UrgeSetting;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 敦促设置。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
public class FastJsonUrgeSetting implements Bean {

    private static final long serialVersionUID = -8770549119823639343L;

    public static FastJsonUrgeSetting of(UrgeSetting urgeSetting) {
        if (Objects.isNull(urgeSetting)) {
            return null;
        } else {
            return new FastJsonUrgeSetting(
                    FastJsonLongIdKey.of(urgeSetting.getKey()),
                    urgeSetting.getCron(), urgeSetting.getRemark(),
                    urgeSetting.getModifiedCount(), urgeSetting.isEnabled()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonLongIdKey key;

    @JSONField(name = "cron", ordinal = 2)
    private String cron;

    @JSONField(name = "remark", ordinal = 3)
    private String remark;

    @JSONField(name = "modified_count", ordinal = 4)
    private int modifiedCount;

    @JSONField(name = "enabled", ordinal = 5)
    private boolean enabled;

    public FastJsonUrgeSetting() {
    }

    public FastJsonUrgeSetting(
            FastJsonLongIdKey key, String cron, String remark, int modifiedCount, boolean enabled
    ) {
        this.key = key;
        this.cron = cron;
        this.remark = remark;
        this.modifiedCount = modifiedCount;
        this.enabled = enabled;
    }

    public FastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(FastJsonLongIdKey key) {
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
        return "FastJsonUrgeSetting{" +
                "key=" + key +
                ", cron='" + cron + '\'' +
                ", remark='" + remark + '\'' +
                ", modifiedCount=" + modifiedCount +
                ", enabled=" + enabled +
                '}';
    }
}
