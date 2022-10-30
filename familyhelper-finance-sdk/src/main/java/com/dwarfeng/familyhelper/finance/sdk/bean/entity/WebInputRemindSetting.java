package com.dwarfeng.familyhelper.finance.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.finance.sdk.util.Constraints;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.RemindSetting;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 提醒设置。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
public class WebInputRemindSetting implements Bean {

    private static final long serialVersionUID = 1658270263523759480L;

    public static RemindSetting toStackBean(WebInputRemindSetting webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new RemindSetting(
                    WebInputLongIdKey.toStackBean(webInput.getKey()),
                    webInput.getCron(), webInput.getRemark(),
                    webInput.getModifiedCount(), webInput.isEnabled()
            );
        }
    }

    @JSONField(name = "key")
    private WebInputLongIdKey key;

    // TODO 在 subgrade 中新增一个关于 cron 表达式合法的验证注解，替代此处的验证注解。
    @JSONField(name = "cron")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_CRON)
    private String cron;

    @JSONField(name = "remark")
    @Length(max = Constraints.LENGTH_REMARK)
    private String remark;

    @JSONField(name = "modified_count")
    private int modifiedCount;

    @JSONField(name = "enabled")
    private boolean enabled;

    public WebInputRemindSetting() {
    }

    public WebInputLongIdKey getKey() {
        return key;
    }

    public void setKey(WebInputLongIdKey key) {
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
        return "WebInputRemindSetting{" +
                "key=" + key +
                ", cron='" + cron + '\'' +
                ", remark='" + remark + '\'' +
                ", modifiedCount=" + modifiedCount +
                ", enabled=" + enabled +
                '}';
    }
}
