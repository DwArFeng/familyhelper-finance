package com.dwarfeng.familyhelper.finance.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.finance.stack.bean.dto.PermissionRemoveInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputStringIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import javax.validation.Valid;
import java.util.Objects;

/**
 * WebInput 资产目录权限信息。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
public class WebInputPermissionRemoveInfo implements Dto {

    private static final long serialVersionUID = 2246792128203361311L;

    public static PermissionRemoveInfo toStackBean(WebInputPermissionRemoveInfo webInputPermissionRemoveInfo) {
        if (Objects.isNull(webInputPermissionRemoveInfo)) {
            return null;
        } else {
            return new PermissionRemoveInfo(
                    WebInputLongIdKey.toStackBean(webInputPermissionRemoveInfo.getAccountBookKey()),
                    WebInputStringIdKey.toStackBean(webInputPermissionRemoveInfo.getUserKey())
            );
        }
    }

    @JSONField(name = "account_book_key")
    @Valid
    private WebInputLongIdKey accountBookKey;

    @JSONField(name = "user_key")
    @Valid
    private WebInputStringIdKey userKey;

    public WebInputPermissionRemoveInfo() {
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
        return "WebInputPermissionRemoveInfo{" +
                "accountBookKey=" + accountBookKey +
                ", userKey=" + userKey +
                '}';
    }
}
