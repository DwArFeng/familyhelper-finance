package com.dwarfeng.familyhelper.finance.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.finance.stack.bean.dto.AccountBookPermissionUpsertInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputStringIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import javax.validation.Valid;
import java.util.Objects;

/**
 * WebInput 账本权限插入或更新信息。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
public class WebInputAccountBookPermissionUpsertInfo implements Dto {

    private static final long serialVersionUID = 415556206001365208L;

    public static AccountBookPermissionUpsertInfo toStackBean(WebInputAccountBookPermissionUpsertInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new AccountBookPermissionUpsertInfo(
                    WebInputLongIdKey.toStackBean(webInput.getAccountBookKey()),
                    WebInputStringIdKey.toStackBean(webInput.getUserKey()),
                    webInput.getPermissionLevel()
            );
        }
    }

    @JSONField(name = "account_book_key")
    @Valid
    private WebInputLongIdKey accountBookKey;

    @JSONField(name = "user_key")
    @Valid
    private WebInputStringIdKey userKey;

    @JSONField(name = "permission_level")
    private int permissionLevel;

    public WebInputAccountBookPermissionUpsertInfo() {
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

    public int getPermissionLevel() {
        return permissionLevel;
    }

    public void setPermissionLevel(int permissionLevel) {
        this.permissionLevel = permissionLevel;
    }

    @Override
    public String toString() {
        return "WebInputAccountBookPermissionUpsertInfo{" +
                "accountBookKey=" + accountBookKey +
                ", userKey=" + userKey +
                ", permissionLevel=" + permissionLevel +
                '}';
    }
}
