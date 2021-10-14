package com.dwarfeng.familyhelper.finance.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 资金变更不存在异常。
 *
 * @author DwArFeng
 * @since 1.2.0
 */
public class FundChangeNotExistsException extends HandlerException {

    private static final long serialVersionUID = 8825928203635627021L;

    private final LongIdKey fundChangeKey;

    public FundChangeNotExistsException(LongIdKey fundChangeKey) {
        this.fundChangeKey = fundChangeKey;
    }

    public FundChangeNotExistsException(Throwable cause, LongIdKey fundChangeKey) {
        super(cause);
        this.fundChangeKey = fundChangeKey;
    }

    @Override
    public String getMessage() {
        return "资金变更 " + fundChangeKey + " 不存在";
    }
}
