package com.dwarfeng.familyhelper.finance.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.AccountBook;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

/**
 * WebInput 账本。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class WebInputAccountBook implements Bean {

    private static final long serialVersionUID = 3947723510060492606L;

    public static AccountBook toStackBean(WebInputAccountBook webInputAccountBook) {
        return new AccountBook(
                WebInputLongIdKey.toStackBean(webInputAccountBook.getKey()),
                webInputAccountBook.getName(),
                webInputAccountBook.getRemark()
        );
    }

    @JSONField(name = "key")
    private WebInputLongIdKey key;

    @JSONField(name = "name")
    private String name;

    @JSONField(name = "remark")
    private String remark;

    public WebInputAccountBook() {
    }

    public WebInputLongIdKey getKey() {
        return key;
    }

    public void setKey(WebInputLongIdKey key) {
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
        return "WebInputAccountBook{" +
                "key=" + key +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
