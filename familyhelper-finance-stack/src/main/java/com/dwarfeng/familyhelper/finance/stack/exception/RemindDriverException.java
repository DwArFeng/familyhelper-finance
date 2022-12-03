package com.dwarfeng.familyhelper.finance.stack.exception;

import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 提醒驱动异常。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
public class RemindDriverException extends HandlerException {

    private static final long serialVersionUID = -2050082305974574969L;

    public RemindDriverException() {
    }

    public RemindDriverException(String message, Throwable cause) {
        super(message, cause);
    }

    public RemindDriverException(String message) {
        super(message);
    }

    public RemindDriverException(Throwable cause) {
        super(cause);
    }
}
