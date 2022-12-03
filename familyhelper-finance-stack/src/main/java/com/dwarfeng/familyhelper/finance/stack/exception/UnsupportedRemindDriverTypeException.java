package com.dwarfeng.familyhelper.finance.stack.exception;

/**
 * 不支持的提醒驱动器类型异常。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
public class UnsupportedRemindDriverTypeException extends RemindDriverException {

    private static final long serialVersionUID = -6781463933990032046L;

    private final String type;

    public UnsupportedRemindDriverTypeException(String type) {
        this.type = type;
    }

    public UnsupportedRemindDriverTypeException(Throwable cause, String type) {
        super(cause);
        this.type = type;
    }

    @Override
    public String getMessage() {
        return "不支持的提醒驱动器类型: " + type;
    }
}
