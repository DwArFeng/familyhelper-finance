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
public class UserNotPermittedForAccountBookException extends HandlerException {

    private static final long serialVersionUID = -5568749637690477417L;

    private final StringIdKey userKey;
    private final LongIdKey accountBookKey;

    public UserNotPermittedForAccountBookException(StringIdKey userKey, LongIdKey accountBookKey) {
        this.userKey = userKey;
        this.accountBookKey = accountBookKey;
    }

    public UserNotPermittedForAccountBookException(Throwable cause, StringIdKey userKey, LongIdKey accountBookKey) {
        super(cause);
        this.userKey = userKey;
        this.accountBookKey = accountBookKey;
    }

    @Override
    public String getMessage() {
        return "用户 " + userKey + " 没有操作账本 " + accountBookKey + " 的权限";
    }
}
