package com.dwarfeng.familyhelper.finance.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.AccountBook;
import com.dwarfeng.subgrade.sdk.bean.key.JSFixedFastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * JSFixed FastJson 账本。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class JSFixedFastJsonAccountBook implements Bean {

    private static final long serialVersionUID = 8764952131371287068L;

    public static JSFixedFastJsonAccountBook of(AccountBook accountBook) {
        if (Objects.isNull(accountBook)) {
            return null;
        } else {
            return new JSFixedFastJsonAccountBook(
                    JSFixedFastJsonLongIdKey.of(accountBook.getKey()),
                    accountBook.getName(),
                    accountBook.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonLongIdKey key;

    @JSONField(name = "name", ordinal = 2)
    private String name;

    @JSONField(name = "remark", ordinal = 3)
    private String remark;

    public JSFixedFastJsonAccountBook() {
    }

    public JSFixedFastJsonAccountBook(JSFixedFastJsonLongIdKey key, String name, String remark) {
        this.key = key;
        this.name = name;
        this.remark = remark;
    }

    public JSFixedFastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(JSFixedFastJsonLongIdKey key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "FastJsonAccountBook{" +
                "key=" + key +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
