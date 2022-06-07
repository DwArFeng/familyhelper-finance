package com.dwarfeng.familyhelper.finance.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 敦促设置。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
public class UrgeSetting implements Entity<LongIdKey> {

    private static final long serialVersionUID = -906046370420062671L;

    private LongIdKey key;
    private String cron;
    private String remark;
    private int modifiedCount;
    private boolean enabled;

    public UrgeSetting() {
    }

    public UrgeSetting(LongIdKey key, String cron, String remark, int modifiedCount, boolean enabled) {
        this.key = key;
        this.cron = cron;
        this.remark = remark;
        this.modifiedCount = modifiedCount;
        this.enabled = enabled;
    }

    @Override
    public LongIdKey getKey() {
        return key;
    }

    @Override
    public void setKey(LongIdKey key) {
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
        return "UrgeSetting{" +
                "key=" + key +
                ", cron='" + cron + '\'' +
                ", remark='" + remark + '\'' +
                ", modifiedCount=" + modifiedCount +
                ", enabled=" + enabled +
                '}';
    }
}
