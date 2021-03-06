package com.dwarfeng.familyhelper.finance.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 银行卡不存在异常。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class BankCardNotExistsException extends HandlerException {

    private static final long serialVersionUID = 8825928203635627021L;

    private final LongIdKey bankCardKey;

    public BankCardNotExistsException(LongIdKey bankCardKey) {
        this.bankCardKey = bankCardKey;
    }

    public BankCardNotExistsException(Throwable cause, LongIdKey bankCardKey) {
        super(cause);
        this.bankCardKey = bankCardKey;
    }

    @Override
    public String getMessage() {
        return "银行卡 " + bankCardKey + " 不存在";
    }
}
