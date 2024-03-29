package com.dwarfeng.familyhelper.finance.sdk.util;

import com.dwarfeng.subgrade.stack.exception.ServiceException;

/**
 * 服务异常代码。
 *
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public final class ServiceExceptionCodes {

    private static int EXCEPTION_CODE_OFFSET = 11000;

    public static final ServiceException.Code ACCOUNT_BOOK_NOT_EXISTS =
            new ServiceException.Code(offset(0), "account book not exists");
    public static final ServiceException.Code BANK_CARD_NOT_EXISTS =
            new ServiceException.Code(offset(10), "bank card not exists");
    public static final ServiceException.Code ILLEGAL_BANK_CARD_STATE =
            new ServiceException.Code(offset(20), "illegal bank card state");
    public static final ServiceException.Code USER_NOT_EXISTS =
            new ServiceException.Code(offset(30), "user not exists");
    public static final ServiceException.Code USER_NOT_PERMITTED_FOR_ACCOUNT_BOOK =
            new ServiceException.Code(offset(40), "user not permitted for account book");
    public static final ServiceException.Code FUND_CHANGE_NOT_EXISTS =
            new ServiceException.Code(offset(50), "fund change not exists");
    public static final ServiceException.Code ILLEGAL_FUND_CHANGE_STATE =
            new ServiceException.Code(offset(60), "illegal fund change state");
    public static final ServiceException.Code INVALID_PERMISSION_LEVEL =
            new ServiceException.Code(offset(70), "invalid permission level");
    public static final ServiceException.Code BILL_FILE_NOT_EXISTS =
            new ServiceException.Code(offset(80), "bill file not exists");
    public static final ServiceException.Code REMIND_DRIVER_INFO_NOT_EXISTS =
            new ServiceException.Code(offset(90), "remind driver info not exists");
    public static final ServiceException.Code REMIND_DRIVER_INFO_DISABLED =
            new ServiceException.Code(offset(100), "remind driver info disabled");
    public static final ServiceException.Code REMIND_SCOPE_TYPE_MISMATCH =
            new ServiceException.Code(offset(110), "remind scope type mismatch");

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
        // 设置 EXCEPTION_CODE_OFFSET 的值。
        EXCEPTION_CODE_OFFSET = exceptionCodeOffset;

        // 以新的 EXCEPTION_CODE_OFFSET 为基准，更新异常代码的值。
        ACCOUNT_BOOK_NOT_EXISTS.setCode(offset(0));
        BANK_CARD_NOT_EXISTS.setCode(offset(10));
        ILLEGAL_BANK_CARD_STATE.setCode(offset(20));
        USER_NOT_EXISTS.setCode(offset(30));
        USER_NOT_PERMITTED_FOR_ACCOUNT_BOOK.setCode(offset(40));
        FUND_CHANGE_NOT_EXISTS.setCode(offset(50));
        ILLEGAL_FUND_CHANGE_STATE.setCode(offset(60));
        INVALID_PERMISSION_LEVEL.setCode(offset(70));
        BILL_FILE_NOT_EXISTS.setCode(offset(80));
        REMIND_DRIVER_INFO_NOT_EXISTS.setCode(offset(90));
        REMIND_DRIVER_INFO_DISABLED.setCode(offset(100));
        REMIND_SCOPE_TYPE_MISMATCH.setCode(offset(110));
    }

    private ServiceExceptionCodes() {
        throw new IllegalStateException("禁止实例化");
    }
}
