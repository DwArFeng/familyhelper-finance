package com.dwarfeng.familyhelper.finance.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.finance.sdk.util.Constraints;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.FundChangeTypeIndicator;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * WebInput 资金变更类型指示器。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class WebInputFundChangeTypeIndicator implements Bean {

    private static final long serialVersionUID = 7650304807319142330L;

    public static FundChangeTypeIndicator toStackBean(WebInputFundChangeTypeIndicator webInputFundChangeTypeIndicator) {
        return new FundChangeTypeIndicator(
                WebInputStringIdKey.toStackBean(webInputFundChangeTypeIndicator.getKey()),
                webInputFundChangeTypeIndicator.getLabel(),
                webInputFundChangeTypeIndicator.getRemark()
        );
    }

    @JSONField(name = "key")
    @Valid
    private WebInputStringIdKey key;

    @JSONField(name = "label")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_LABEL)
    private String label;

    @JSONField(name = "remark")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_REMARK)
    private String remark;

    public WebInputFundChangeTypeIndicator() {
    }

    public WebInputStringIdKey getKey() {
        return key;
    }

    public void setKey(WebInputStringIdKey key) {
        this.key = key;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "WebInputFundChangeTypeIndicator{" +
                "key=" + key +
                ", label='" + label + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
