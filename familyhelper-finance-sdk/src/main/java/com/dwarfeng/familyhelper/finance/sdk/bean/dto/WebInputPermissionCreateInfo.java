package com.dwarfeng.familyhelper.finance.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.finance.stack.bean.dto.PermissionCreateInfo;
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
public class WebInputPermissionCreateInfo implements Dto {

    private static final long serialVersionUID = -5578414687611754052L;

    public static PermissionCreateInfo toStackBean(WebInputPermissionCreateInfo webInputPermissionCreateInfo) {
        if (Objects.isNull(webInputPermissionCreateInfo)) {
            return null;
        } else {
            return new PermissionCreateInfo(
                    WebInputLongIdKey.toStackBean(webInputPermissionCreateInfo.getAccountBookKey()),
                    WebInputStringIdKey.toStackBean(webInputPermissionCreateInfo.getUserKey()),
                    webInputPermissionCreateInfo.getPermissionLevel()
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

    public WebInputPermissionCreateInfo() {
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
        return "WebInputPermissionCreateInfo{" +
                "accountBookKey=" + accountBookKey +
                ", userKey=" + userKey +
                ", permissionLevel=" + permissionLevel +
                '}';
    }
}
