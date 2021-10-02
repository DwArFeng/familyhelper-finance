package com.dwarfeng.familyhelper.finance.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.finance.stack.bean.dto.AccountBookCreateInfo;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 账本创建信息。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class WebInputAccountBookCreateInfo implements Dto {

    private static final long serialVersionUID = 3089843306757253182L;

    public static AccountBookCreateInfo toStackBean(WebInputAccountBookCreateInfo webInputAccountBookCreateInfo) {
        if (Objects.isNull(webInputAccountBookCreateInfo)) {
            return null;
        } else {
            return new AccountBookCreateInfo(
                    webInputAccountBookCreateInfo.getName(),
                    webInputAccountBookCreateInfo.getRemark()
            );
        }
    }

    @JSONField(name = "name")
    @NotNull
    @NotEmpty
    private String name;

    @JSONField(name = "remark")
    private String remark;

    public WebInputAccountBookCreateInfo() {
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
        return "WebInputAccountBookCreateInfo{" +
                "name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
