package com.dwarfeng.familyhelper.finance.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.dwarfeng.familyhelper.finance.stack.bean.key.PoabKey;
import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * JSFixed FastJson 账本权限主键。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class JSFixedFastJsonPoabKey implements Key {

    private static final long serialVersionUID = 2708793047916127708L;

    public static JSFixedFastJsonPoabKey of(PoabKey key) {
        if (Objects.isNull(key)) {
            return null;
        } else {
            return new JSFixedFastJsonPoabKey(key.getLongId(), key.getStringId());
        }
    }

    @JSONField(name = "long_id", ordinal = 1, serializeUsing = ToStringSerializer.class)
    private Long longId;

    @JSONField(name = "string_id", ordinal = 2)
    private String stringId;

    public JSFixedFastJsonPoabKey() {
    }

    public JSFixedFastJsonPoabKey(Long longId, String stringId) {
        this.longId = longId;
        this.stringId = stringId;
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
        return "JSFixedFastJsonPoabKey{" +
                "longId=" + longId +
                ", stringId='" + stringId + '\'' +
                '}';
    }
}
