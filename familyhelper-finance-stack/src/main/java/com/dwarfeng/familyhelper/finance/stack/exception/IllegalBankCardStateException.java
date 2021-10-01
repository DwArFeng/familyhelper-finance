package com.dwarfeng.familyhelper.finance.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 银行卡状态非法异常。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class IllegalBankCardStateException extends BalanceOperateException {

    private static final long serialVersionUID = -5066454985579730219L;

    private final LongIdKey bankCardKey;

    public IllegalBankCardStateException(LongIdKey bankCardKey) {
        this.bankCardKey = bankCardKey;
    }

    public IllegalBankCardStateException(String message, LongIdKey bankCardKey) {
        super(message);
        this.bankCardKey = bankCardKey;
    }

    public IllegalBankCardStateException(String message, Throwable cause, LongIdKey bankCardKey) {
        super(message, cause);
        this.bankCardKey = bankCardKey;
    }

    public IllegalBankCardStateException(Throwable cause, LongIdKey bankCardKey) {
        super(cause);
        this.bankCardKey = bankCardKey;
    }

    @Override
    public String getMessage() {
        return "银行卡 " + bankCardKey + " 状态异常: 它是否没绑定账本?";
    }
}
