package com.dwarfeng.familyhelper.finance.impl.configuration;

import com.dwarfeng.familyhelper.finance.sdk.util.ServiceExceptionCodes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class ExceptionCodeOffsetConfiguration {

    @Value("${familyhelper.exception_code_offset}")
    private int exceptionCodeOffset;
    @Value("${familyhelper.exception_code_offset.subgrade}")
    private int subgradeExceptionCodeOffset;
    @Value("${familyhelper.exception_code_offset.snowflake}")
    private int snowflakeExceptionCodeOffset;
    @Value("${familyhelper.exception_code_offset.dwarfeng_ftp}")
    private int dwarfengFtpExceptionCodeOffset;
    @Value("${familyhelper.exception_code_offset.dwarfeng_datamark}")
    private int dwarfengDatamarkExceptionCodeOffset;

    @PostConstruct
    public void init() {
        ServiceExceptionCodes.setExceptionCodeOffset(exceptionCodeOffset);
        com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes.setExceptionCodeOffset(subgradeExceptionCodeOffset);
        com.dwarfeng.sfds.sdk.util.ServiceExceptionCodes.setExceptionCodeOffset(snowflakeExceptionCodeOffset);
        com.dwarfeng.ftp.util.ServiceExceptionCodes.setExceptionCodeOffset(dwarfengFtpExceptionCodeOffset);
        com.dwarfeng.datamark.util.ServiceExceptionCodes.setExceptionCodeOffset(dwarfengDatamarkExceptionCodeOffset);
    }
}
