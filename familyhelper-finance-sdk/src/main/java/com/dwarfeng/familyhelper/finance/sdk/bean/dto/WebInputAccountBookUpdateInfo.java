package com.dwarfeng.familyhelper.finance.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.finance.stack.bean.dto.AccountBookUpdateInfo;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 账本更新信息。
 *
 * @author DwArFeng
 * @since 1.1.1
 */
public class WebInputAccountBookUpdateInfo implements Dto {

    private static final long serialVersionUID = 3075340741632950467L;

    public static AccountBookUpdateInfo toStackBean(WebInputAccountBookUpdateInfo webInputAccountBookUpdateInfo) {
        if (Objects.isNull(webInputAccountBookUpdateInfo)) {
            return null;
        } else {
            return new AccountBookUpdateInfo(
                    webInputAccountBookUpdateInfo.getName(),
                    webInputAccountBookUpdateInfo.getRemark()
            );
        }
    }

    @JSONField(name = "name")
    @NotNull
    @NotEmpty
    private String name;

    @JSONField(name = "remark")
    private String remark;

    public WebInputAccountBookUpdateInfo() {
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
        return "WebInputAccountBookUpdateInfo{" +
                "name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
