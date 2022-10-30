package com.dwarfeng.familyhelper.finance.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.finance.sdk.util.Constraints;
import com.dwarfeng.familyhelper.finance.sdk.util.ValidRemindScopeType;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.RemindDriverInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 提醒驱动信息。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
public class WebInputRemindDriverInfo implements Bean {

    private static final long serialVersionUID = 1139200998709942728L;

    public static RemindDriverInfo toStackBean(WebInputRemindDriverInfo webInputRemindDriverInfo) {
        if (Objects.isNull(webInputRemindDriverInfo)) {
            return null;
        } else {
            return new RemindDriverInfo(
                    WebInputLongIdKey.toStackBean(webInputRemindDriverInfo.getKey()),
                    WebInputLongIdKey.toStackBean(webInputRemindDriverInfo.getAccountBookKey()),
                    webInputRemindDriverInfo.isEnabled(), webInputRemindDriverInfo.getType(),
                    webInputRemindDriverInfo.getParam(), webInputRemindDriverInfo.getRemindScopeType(),
                    webInputRemindDriverInfo.getRemark()
            );
        }
    }

    @JSONField(name = "key")
    @Valid
    private WebInputLongIdKey key;

    @JSONField(name = "account_book_key")
    @Valid
    private WebInputLongIdKey accountBookKey;

    @JSONField(name = "enabled")
    private boolean enabled;

    @JSONField(name = "type")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_TYPE)
    private String type;

    @JSONField(name = "param")
    private String param;

    @JSONField(name = "remind_scope_type")
    @ValidRemindScopeType
    private int remindScopeType;

    @JSONField(name = "remark")
    @Length(max = Constraints.LENGTH_REMARK)
    private String remark;

    public WebInputRemindDriverInfo() {
    }

    public WebInputLongIdKey getKey() {
        return key;
    }

    public void setKey(WebInputLongIdKey key) {
        this.key = key;
    }

    public WebInputLongIdKey getAccountBookKey() {
        return accountBookKey;
    }

    public void setAccountBookKey(WebInputLongIdKey accountBookKey) {
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
        return "WebInputRemindDriverInfo{" +
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
