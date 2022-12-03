package com.dwarfeng.familyhelper.finance.stack.exception;

import com.dwarfeng.subgrade.stack.exception.HandlerException;

import java.util.List;

/**
 * 提醒人员的范围不匹配异常。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
public class RemindScopeTypeMismatchException extends HandlerException {

    private static final long serialVersionUID = 5869657097330476554L;

    private final int value;
    private final List<Integer> space;

    public RemindScopeTypeMismatchException(int value, List<Integer> space) {
        this.value = value;
        this.space = space;
    }

    public RemindScopeTypeMismatchException(Throwable cause, int value, List<Integer> space) {
        super(cause);
        this.value = value;
        this.space = space;
    }

    @Override
    public String getMessage() {
        return "不匹配的提醒人员的范围: " + value + ", 正确值的空间为: " + space;
    }
}
