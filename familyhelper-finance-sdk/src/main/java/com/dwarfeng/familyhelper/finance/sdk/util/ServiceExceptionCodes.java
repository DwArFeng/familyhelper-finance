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

    public static final ServiceException.Code ACCOUNT_BOOK_NOT_EXISTS =
            new ServiceException.Code(offset(0), "account book not exists");
    public static final ServiceException.Code BANK_CARD_NOT_EXISTS =
            new ServiceException.Code(offset(10), "bank card not exists");
    public static final ServiceException.Code ILLEGAL_BANK_CARD_STATE =
            new ServiceException.Code(offset(20), "illegal bank card state");
    public static final ServiceException.Code USER_NOT_EXISTS =
            new ServiceException.Code(offset(30), "user not exists");
    public static final ServiceException.Code USER_NOT_PERMITTED =
            new ServiceException.Code(offset(40), "user not permitted");
    public static final ServiceException.Code FUND_CHANGE_NOT_EXISTS =
            new ServiceException.Code(offset(50), "fund change not exists");
    public static final ServiceException.Code ILLEGAL_FUND_CHANGE_STATE =
            new ServiceException.Code(offset(60), "illegal fund change state");

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
