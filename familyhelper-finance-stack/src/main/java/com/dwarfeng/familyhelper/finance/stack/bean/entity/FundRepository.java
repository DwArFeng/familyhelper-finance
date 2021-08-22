package com.dwarfeng.familyhelper.finance.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 资金仓库。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class FundRepository implements Entity<LongIdKey> {

    private static final long serialVersionUID = -6790184787001122805L;

    private LongIdKey key;
    private LongIdKey accountBookKey;
    private String name;
    private boolean enabled;
    private String remark;

    public FundRepository() {
    }

    public FundRepository(LongIdKey key, LongIdKey accountBookKey, String name, boolean enabled, String remark) {
        this.key = key;
        this.accountBookKey = accountBookKey;
        this.name = name;
        this.enabled = enabled;
        this.remark = remark;
    }

    @Override
    public LongIdKey getKey() {
        return key;
    }

    @Override
    public void setKey(LongIdKey key) {
        this.key = key;
    }

    public LongIdKey getAccountBookKey() {
        return accountBookKey;
    }

    public void setAccountBookKey(LongIdKey accountBookKey) {
        this.accountBookKey = accountBookKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "FundRepository{" +
                "key=" + key +
                ", accountBookKey=" + accountBookKey +
                ", name='" + name + '\'' +
                ", enabled=" + enabled +
                ", remark='" + remark + '\'' +
                '}';
    }
}
