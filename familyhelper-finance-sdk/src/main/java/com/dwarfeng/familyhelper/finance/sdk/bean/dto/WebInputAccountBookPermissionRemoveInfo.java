package com.dwarfeng.familyhelper.finance.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.finance.stack.bean.dto.AccountBookPermissionRemoveInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputStringIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import javax.validation.Valid;
import java.util.Objects;

/**
 * WebInput 账本权限删除信息。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
public class WebInputAccountBookPermissionRemoveInfo implements Dto {

    private static final long serialVersionUID = -57100278594532486L;

    public static AccountBookPermissionRemoveInfo toStackBean(WebInputAccountBookPermissionRemoveInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new AccountBookPermissionRemoveInfo(
                    WebInputLongIdKey.toStackBean(webInput.getAccountBookKey()),
                    WebInputStringIdKey.toStackBean(webInput.getUserKey())
            );
        }
    }

    @JSONField(name = "account_book_key")
    @Valid
    private WebInputLongIdKey accountBookKey;

    @JSONField(name = "user_key")
    @Valid
    private WebInputStringIdKey userKey;

    public WebInputAccountBookPermissionRemoveInfo() {
    }

    public WebInputLongIdKey getAccountBookKey() {
        return accountBookKey;
    }

    public void setAccountBookKey(WebInputLongIdKey accountBookKey) {
        this.accountBookKey = accountBookKey;
    }

    public WebInputStringIdKey getUserKey() {
        return userKey;
    }

    public void setUserKey(WebInputStringIdKey userKey) {
        this.userKey = userKey;
    }

    @Override
    public String toString() {
        return "WebInputAccountBookPermissionRemoveInfo{" +
                "accountBookKey=" + accountBookKey +
                ", userKey=" + userKey +
                '}';
    }
}
