package com.dwarfeng.familyhelper.finance.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.FundRepository;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 资金仓库。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class FastJsonFundRepository implements Bean {

    private static final long serialVersionUID = -6218893288704917411L;

    public static FastJsonFundRepository of(FundRepository fundRepository) {
        if (Objects.isNull(fundRepository)) {
            return null;
        } else {
            return new FastJsonFundRepository(
                    FastJsonLongIdKey.of(fundRepository.getKey()),
                    FastJsonLongIdKey.of(fundRepository.getAccountBookKey()),
                    fundRepository.getName(),
                    fundRepository.isEnabled(),
                    fundRepository.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonLongIdKey key;

    @JSONField(name = "account_book_key", ordinal = 2)
    private FastJsonLongIdKey accountBookKey;

    @JSONField(name = "name", ordinal = 3)
    private String name;

    @JSONField(name = "enabled", ordinal = 4)
    private boolean enabled;

    @JSONField(name = "remark", ordinal = 5)
    private String remark;

    public FastJsonFundRepository() {
    }

    public FastJsonFundRepository(
            FastJsonLongIdKey key, FastJsonLongIdKey accountBookKey, String name, boolean enabled, String remark
    ) {
        this.key = key;
        this.accountBookKey = accountBookKey;
        this.name = name;
        this.enabled = enabled;
        this.remark = remark;
    }

    public FastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(FastJsonLongIdKey key) {
        this.key = key;
    }

    public FastJsonLongIdKey getAccountBookKey() {
        return accountBookKey;
    }

    public void setAccountBookKey(FastJsonLongIdKey accountBookKey) {
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
        return "FastJsonFundRepository{" +
                "key=" + key +
                ", accountBookKey=" + accountBookKey +
                ", name='" + name + '\'' +
                ", enabled=" + enabled +
                ", remark='" + remark + '\'' +
                '}';
    }
}
