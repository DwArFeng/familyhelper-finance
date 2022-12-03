package com.dwarfeng.familyhelper.finance.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 提醒驱动信息已禁用异常。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
public class RemindDriverInfoDisabledException extends HandlerException {

    private static final long serialVersionUID = -7392727882992130134L;

    private final LongIdKey remindDriverInfoKey;

    public RemindDriverInfoDisabledException(LongIdKey remindDriverInfoKey) {
        this.remindDriverInfoKey = remindDriverInfoKey;
    }

    public RemindDriverInfoDisabledException(Throwable cause, LongIdKey remindDriverInfoKey) {
        super(cause);
        this.remindDriverInfoKey = remindDriverInfoKey;
    }

    @Override
    public String getMessage() {
        return "提醒驱动信息 " + remindDriverInfoKey + " 已禁用";
    }
}
