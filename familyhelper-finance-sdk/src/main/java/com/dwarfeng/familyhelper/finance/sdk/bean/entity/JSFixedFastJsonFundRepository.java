package com.dwarfeng.familyhelper.finance.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.FundRepository;
import com.dwarfeng.subgrade.sdk.bean.key.JSFixedFastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * JSFixed FastJson 资金仓库。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class JSFixedFastJsonFundRepository implements Bean {

    private static final long serialVersionUID = 8296541456679016118L;

    public static JSFixedFastJsonFundRepository of(FundRepository fundRepository) {
        if (Objects.isNull(fundRepository)) {
            return null;
        } else {
            return new JSFixedFastJsonFundRepository(
                    JSFixedFastJsonLongIdKey.of(fundRepository.getKey()),
                    JSFixedFastJsonLongIdKey.of(fundRepository.getAccountBookKey()),
                    fundRepository.getName(),
                    fundRepository.isEnabled(),
                    fundRepository.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonLongIdKey key;

    @JSONField(name = "account_book_key", ordinal = 2)
    private JSFixedFastJsonLongIdKey accountBookKey;

    @JSONField(name = "name", ordinal = 3)
    private String name;

    @JSONField(name = "enabled", ordinal = 4)
    private boolean enabled;

    @JSONField(name = "remark", ordinal = 5)
    private String remark;

    public JSFixedFastJsonFundRepository() {
    }

    public JSFixedFastJsonFundRepository(
            JSFixedFastJsonLongIdKey key, JSFixedFastJsonLongIdKey accountBookKey, String name, boolean enabled,
            String remark
    ) {
        this.key = key;
        this.accountBookKey = accountBookKey;
        this.name = name;
        this.enabled = enabled;
        this.remark = remark;
    }

    public JSFixedFastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(JSFixedFastJsonLongIdKey key) {
        this.key = key;
    }

    public JSFixedFastJsonLongIdKey getAccountBookKey() {
        return accountBookKey;
    }

    public void setAccountBookKey(JSFixedFastJsonLongIdKey accountBookKey) {
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
        return "JSFixedFastJsonFundRepository{" +
                "key=" + key +
                ", accountBookKey=" + accountBookKey +
                ", name='" + name + '\'' +
                ", enabled=" + enabled +
                ", remark='" + remark + '\'' +
                '}';
    }
}
