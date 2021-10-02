package com.dwarfeng.familyhelper.finance.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 用户不存在异常。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class UserNotExistsException extends HandlerException {

    private static final long serialVersionUID = -6249161432514690530L;

    private final StringIdKey userKey;

    public UserNotExistsException(StringIdKey userKey) {
        this.userKey = userKey;
    }

    public UserNotExistsException(String message, StringIdKey userKey) {
        super(message);
        this.userKey = userKey;
    }

    public UserNotExistsException(String message, Throwable cause, StringIdKey userKey) {
        super(message, cause);
        this.userKey = userKey;
    }

    public UserNotExistsException(Throwable cause, StringIdKey userKey) {
        super(cause);
        this.userKey = userKey;
    }

    @Override
    public String getMessage() {
        return "用户 " + userKey + " 不存在";
    }
}
