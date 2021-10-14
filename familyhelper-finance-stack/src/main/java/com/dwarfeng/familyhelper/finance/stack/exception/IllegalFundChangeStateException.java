package com.dwarfeng.familyhelper.finance.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 资金变更状态非法异常。
 *
 * @author DwArFeng
 * @since 1.2.0
 */
public class IllegalFundChangeStateException extends HandlerException {

    private static final long serialVersionUID = 3893534174983903068L;

    private final LongIdKey fundChangeKey;

    public IllegalFundChangeStateException(LongIdKey fundChangeKey) {
        this.fundChangeKey = fundChangeKey;
    }

    public IllegalFundChangeStateException(Throwable cause, LongIdKey fundChangeKey) {
        super(cause);
        this.fundChangeKey = fundChangeKey;
    }

    @Override
    public String getMessage() {
        return "资金变更 " + fundChangeKey + " 状态异常: 它是否没绑定账本?";
    }
}
