package com.dwarfeng.familyhelper.finance.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.FundRepository;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

/**
 * WebInput 资金仓库。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class WebInputFundRepository implements Bean {

    private static final long serialVersionUID = -540311855441756907L;

    public static FundRepository toStackBean(WebInputFundRepository webInputFundRepository) {
        return new FundRepository(
                WebInputLongIdKey.toStackBean(webInputFundRepository.getKey()),
                WebInputLongIdKey.toStackBean(webInputFundRepository.getAccountBookKey()),
                webInputFundRepository.getName(),
                webInputFundRepository.isEnabled(),
                webInputFundRepository.getRemark()
        );
    }

    @JSONField(name = "key")
    private WebInputLongIdKey key;

    @JSONField(name = "account_book_key")
    private WebInputLongIdKey accountBookKey;

    @JSONField(name = "name")
    private String name;

    @JSONField(name = "enabled")
    private boolean enabled;

    @JSONField(name = "remark")
    private String remark;

    public WebInputFundRepository() {
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
        return "WebInputFundRepository{" +
                "key=" + key +
                ", accountBookKey=" + accountBookKey +
                ", name='" + name + '\'' +
                ", enabled=" + enabled +
                ", remark='" + remark + '\'' +
                '}';
    }
}
