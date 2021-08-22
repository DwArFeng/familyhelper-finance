package com.dwarfeng.familyhelper.finance.sdk.util;

import com.dwarfeng.subgrade.stack.exception.ServiceException;

/**
 * 服务异常代码。
 *
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public final class ServiceExceptionCodes {

    private static int EXCEPTION_CODE_OFFSET = 5000;

    public static final ServiceException.Code FILTER_FAILED =
            new ServiceException.Code(offset(0), "filter failed");
    public static final ServiceException.Code FILTER_MAKE_FAILED =
            new ServiceException.Code(offset(1), "filter make failed");
    public static final ServiceException.Code FILTER_TYPE_UNSUPPORTED =
            new ServiceException.Code(offset(2), "filter type unsupported");
    public static final ServiceException.Code TRIGGER_FAILED =
            new ServiceException.Code(offset(10), "trigger failed");
    public static final ServiceException.Code TRIGGER_MAKE_FAILED =
            new ServiceException.Code(offset(11), "trigger make failed");
    public static final ServiceException.Code TRIGGER_TYPE_UNSUPPORTED =
            new ServiceException.Code(offset(12), "trigger type unsupported");
    public static final ServiceException.Code POINT_NOT_EXISTS =
            new ServiceException.Code(offset(20), "point not exists");
    public static final ServiceException.Code RECORD_HANDLER_STOPPED =
            new ServiceException.Code(offset(30), "record handler stopped");
    public static final ServiceException.Code CONSUME_HANDLER_STOPPED =
            new ServiceException.Code(offset(31), "consume handler stopped");
    public static final ServiceException.Code MAPPER_FAILED =
            new ServiceException.Code(offset(40), "mapper failed");
    public static final ServiceException.Code MAPPER_MAKE_FAILED =
            new ServiceException.Code(offset(41), "mapper make failed");
    public static final ServiceException.Code MAPPER_TYPE_UNSUPPORTED =
            new ServiceException.Code(offset(42), "mapper type unsupported");
    public static final ServiceException.Code PERSISTENCE_DISABLED =
            new ServiceException.Code(offset(50), "persistence disabled");
    public static final ServiceException.Code REALTIME_DISABLED =
            new ServiceException.Code(offset(51), "realtime disabled");

    private static int offset(int i) {
        return EXCEPTION_CODE_OFFSET + i;
    }

    /**
     * 获取异常代号的偏移量。
     *
     * @return 异常代号的偏移量。
     */
    public static int getExceptionCodeOffset() {
        return EXCEPTION_CODE_OFFSET;
    }

    /**
     * 设置异常代号的偏移量。
     *
     * @param exceptionCodeOffset 指定的异常代号的偏移量。
     */
    public static void setExceptionCodeOffset(int exceptionCodeOffset) {
        EXCEPTION_CODE_OFFSET = exceptionCodeOffset;
    }

    private ServiceExceptionCodes() {
        throw new IllegalStateException("禁止实例化");
    }
}
