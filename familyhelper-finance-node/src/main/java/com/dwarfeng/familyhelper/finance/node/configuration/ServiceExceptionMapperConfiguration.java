package com.dwarfeng.familyhelper.finance.node.configuration;

import com.dwarfeng.familyhelper.finance.sdk.util.ServiceExceptionCodes;
import com.dwarfeng.familyhelper.finance.stack.exception.*;
import com.dwarfeng.subgrade.impl.exception.MapServiceExceptionMapper;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class ServiceExceptionMapperConfiguration {

    @Bean
    public MapServiceExceptionMapper mapServiceExceptionMapper() {
        Map<Class<? extends Exception>, ServiceException.Code> destination = ServiceExceptionHelper.putDefaultDestination(null);
        destination = com.dwarfeng.ftp.util.ServiceExceptionHelper.putDefaultDestination(destination);
        destination.put(AccountBookNotExistsException.class, ServiceExceptionCodes.ACCOUNT_BOOK_NOT_EXISTS);
        destination.put(BankCardNotExistsException.class, ServiceExceptionCodes.BANK_CARD_NOT_EXISTS);
        destination.put(IllegalBankCardStateException.class, ServiceExceptionCodes.ILLEGAL_BANK_CARD_STATE);
        destination.put(UserNotExistsException.class, ServiceExceptionCodes.USER_NOT_EXISTS);
        destination.put(UserNotPermittedForAccountBookException.class, ServiceExceptionCodes.USER_NOT_PERMITTED_FOR_ACCOUNT_BOOK);
        destination.put(FundChangeNotExistsException.class, ServiceExceptionCodes.FUND_CHANGE_NOT_EXISTS);
        destination.put(IllegalFundChangeStateException.class, ServiceExceptionCodes.ILLEGAL_FUND_CHANGE_STATE);
        destination.put(InvalidPermissionLevelException.class, ServiceExceptionCodes.INVALID_PERMISSION_LEVEL);
        destination.put(BillFileNotExistsException.class, ServiceExceptionCodes.BILL_FILE_NOT_EXISTS);
        destination.put(RemindDriverInfoNotExistsException.class, ServiceExceptionCodes.REMIND_DRIVER_INFO_NOT_EXISTS);
        destination.put(RemindDriverInfoDisabledException.class, ServiceExceptionCodes.REMIND_DRIVER_INFO_DISABLED);
        destination.put(RemindScopeTypeMismatchException.class, ServiceExceptionCodes.REMIND_SCOPE_TYPE_MISMATCH);
        return new MapServiceExceptionMapper(destination, com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes.UNDEFINED);
    }
}
