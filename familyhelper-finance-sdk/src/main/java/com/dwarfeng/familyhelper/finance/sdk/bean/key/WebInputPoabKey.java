package com.dwarfeng.familyhelper.finance.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.finance.stack.bean.key.PoabKey;
import com.dwarfeng.subgrade.stack.bean.key.Key;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 账本权限主键。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class WebInputPoabKey implements Key {

    private static final long serialVersionUID = 2129062555625971775L;

    public static PoabKey toStackBean(WebInputPoabKey webInputPoabKey) {
        if (Objects.isNull(webInputPoabKey)) {
            return null;
        } else {
            return new PoabKey(webInputPoabKey.getLongId(), webInputPoabKey.getStringId());
        }
    }

    @JSONField(name = "long_id")
    @NotNull
    private Long longId;

    @JSONField(name = "string_id")
    @NotNull
    @NotEmpty
    private String stringId;

    public WebInputPoabKey() {
    }

    public Long getLongId() {
        return longId;
    }

    public void setLongId(Long longId) {
        this.longId = longId;
    }

    public String getStringId() {
        return stringId;
    }

    public void setStringId(String stringId) {
        this.stringId = stringId;
    }

    @Override
    public String toString() {
        return "WebInputPoabKey{" +
                "longId=" + longId +
                ", stringId='" + stringId + '\'' +
                '}';
    }
}
