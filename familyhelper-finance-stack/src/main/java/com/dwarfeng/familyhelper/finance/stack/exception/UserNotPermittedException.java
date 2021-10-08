package com.dwarfeng.familyhelper.finance.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 用户对账本没有权限异常。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class UserNotPermittedException extends HandlerException {

    private static final long serialVersionUID = -148148909478561243L;

    private final StringIdKey userKey;
    private final LongIdKey accountBookKey;

    public UserNotPermittedException(StringIdKey userKey, LongIdKey accountBookKey) {
        this.userKey = userKey;
        this.accountBookKey = accountBookKey;
    }

    public UserNotPermittedException(Throwable cause, StringIdKey userKey, LongIdKey accountBookKey) {
        super(cause);
        this.userKey = userKey;
        this.accountBookKey = accountBookKey;
    }

    @Override
    public String getMessage() {
        return "用户 " + userKey + " 没有操作账本 " + accountBookKey + " 的权限";
    }
}
