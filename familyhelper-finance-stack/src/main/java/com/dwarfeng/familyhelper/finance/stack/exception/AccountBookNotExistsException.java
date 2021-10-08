package com.dwarfeng.familyhelper.finance.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 账本不存在异常。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class AccountBookNotExistsException extends HandlerException {

    private static final long serialVersionUID = 4394311960147137564L;

    private final LongIdKey accountBookKey;

    public AccountBookNotExistsException(LongIdKey accountBookKey) {
        this.accountBookKey = accountBookKey;
    }

    public AccountBookNotExistsException(Throwable cause, LongIdKey accountBookKey) {
        super(cause);
        this.accountBookKey = accountBookKey;
    }

    @Override
    public String getMessage() {
        return "账本 " + accountBookKey + " 不存在";
    }
}
