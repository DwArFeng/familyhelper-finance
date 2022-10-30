package com.dwarfeng.familyhelper.finance.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 提醒驱动信息。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
public class RemindDriverInfo implements Entity<LongIdKey> {

    private static final long serialVersionUID = 6580987173337246156L;

    private LongIdKey key;
    private LongIdKey accountBookKey;
    private boolean enabled;
    private String type;
    private String param;
    private int remindScopeType;
    private String remark;

    public RemindDriverInfo() {
    }

    public RemindDriverInfo(
            LongIdKey key, LongIdKey accountBookKey, boolean enabled, String type, String param,
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
        return "RemindDriverInfo{" +
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
