package com.dwarfeng.familyhelper.finance.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 提醒驱动信息不存在异常。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
public class RemindDriverInfoNotExistsException extends HandlerException {

    private static final long serialVersionUID = 365887889718192967L;

    private final LongIdKey remindDriverInfoKey;

    public RemindDriverInfoNotExistsException(LongIdKey remindDriverInfoKey) {
        this.remindDriverInfoKey = remindDriverInfoKey;
    }

    public RemindDriverInfoNotExistsException(Throwable cause, LongIdKey remindDriverInfoKey) {
        super(cause);
        this.remindDriverInfoKey = remindDriverInfoKey;
    }

    @Override
    public String getMessage() {
        return "提醒驱动信息 " + remindDriverInfoKey + " 不存在";
    }
}
