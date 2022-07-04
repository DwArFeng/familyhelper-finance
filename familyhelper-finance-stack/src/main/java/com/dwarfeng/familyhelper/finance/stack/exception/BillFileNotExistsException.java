package com.dwarfeng.familyhelper.finance.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 票据文件不存在异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class BillFileNotExistsException extends HandlerException {

    private static final long serialVersionUID = 4507068278148353641L;

    private final LongIdKey billFileKey;

    public BillFileNotExistsException(LongIdKey billFileKey) {
        this.billFileKey = billFileKey;
    }

    public BillFileNotExistsException(Throwable cause, LongIdKey billFileKey) {
        super(cause);
        this.billFileKey = billFileKey;
    }

    @Override
    public String getMessage() {
        return "票据文件 " + billFileKey + " 不存在";
    }
}
