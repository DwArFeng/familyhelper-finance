package com.dwarfeng.familyhelper.finance.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.finance.stack.bean.dto.AccountBookUpdateInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import javax.validation.Valid;
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

    private static final long serialVersionUID = 7908441560987806241L;

    public static AccountBookUpdateInfo toStackBean(WebInputAccountBookUpdateInfo webInputAccountBookUpdateInfo) {
        if (Objects.isNull(webInputAccountBookUpdateInfo)) {
            return null;
        } else {
            return new AccountBookUpdateInfo(
                    WebInputLongIdKey.toStackBean(webInputAccountBookUpdateInfo.getAccountBookKey()),
                    webInputAccountBookUpdateInfo.getName(),
                    webInputAccountBookUpdateInfo.getRemark()
            );
        }
    }

    @JSONField(name = "account_book_key")
    @Valid
    @NotNull
    private WebInputLongIdKey accountBookKey;

    @JSONField(name = "name")
    @NotNull
    @NotEmpty
    private String name;

    @JSONField(name = "remark")
    private String remark;

    public WebInputAccountBookUpdateInfo() {
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "WebInputAccountBookUpdateInfo{" +
                "accountBookKey=" + accountBookKey +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
