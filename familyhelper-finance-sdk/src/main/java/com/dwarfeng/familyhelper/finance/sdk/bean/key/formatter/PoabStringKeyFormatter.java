package com.dwarfeng.familyhelper.finance.sdk.bean.key.formatter;

import com.dwarfeng.familyhelper.finance.stack.bean.key.PoabKey;
import com.dwarfeng.subgrade.sdk.common.Constants;
import com.dwarfeng.subgrade.sdk.redis.formatter.StringKeyFormatter;

import java.util.Objects;

/**
 * PoabKey 的文本格式化转换器。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class PoabStringKeyFormatter implements StringKeyFormatter<PoabKey> {

    private String prefix;

    public PoabStringKeyFormatter(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String format(PoabKey key) {
        Objects.requireNonNull(key);
        return prefix + key.getLongId() + "_" + key.getStringId();
    }

    @Override
    public String generalFormat() {
        return prefix + Constants.REDIS_KEY_WILDCARD_CHARACTER;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String toString() {
        return "PoabStringKeyFormatter{" +
                "prefix='" + prefix + '\'' +
                '}';
    }
}
