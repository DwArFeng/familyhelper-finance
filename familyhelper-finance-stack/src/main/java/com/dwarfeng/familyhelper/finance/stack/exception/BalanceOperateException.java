package com.dwarfeng.familyhelper.finance.stack.exception;

import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 余额操作异常。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class BalanceOperateException extends HandlerException {

    private static final long serialVersionUID = -5066454985579730219L;

    public BalanceOperateException() {
    }

    public BalanceOperateException(String message) {
        super(message);
    }

    public BalanceOperateException(String message, Throwable cause) {
        super(message, cause);
    }

    public BalanceOperateException(Throwable cause) {
        super(cause);
    }
}
