package com.dwarfeng.familyhelper.finance.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;

/**
 * 用户对账本没有权限异常。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class UserNotPermittedException extends BalanceOperateException {

    private static final long serialVersionUID = -5066454985579730219L;

    private final StringIdKey userKey;
    private final LongIdKey accountBookKey;

    public UserNotPermittedException(StringIdKey userKey, LongIdKey accountBookKey) {
        this.userKey = userKey;
        this.accountBookKey = accountBookKey;
    }

    public UserNotPermittedException(String message, StringIdKey userKey, LongIdKey accountBookKey) {
        super(message);
        this.userKey = userKey;
        this.accountBookKey = accountBookKey;
    }

    public UserNotPermittedException(String message, Throwable cause, StringIdKey userKey, LongIdKey accountBookKey) {
        super(message, cause);
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
