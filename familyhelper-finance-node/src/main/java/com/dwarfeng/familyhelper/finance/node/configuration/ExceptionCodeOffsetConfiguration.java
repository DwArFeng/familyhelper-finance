package com.dwarfeng.familyhelper.finance.node.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class ExceptionCodeOffsetConfiguration {

    @Value("${familyhelper.exception_code_offset.subgrade}")
    private int subgradeExceptionCodeOffset;
    @Value("${familyhelper.exception_code_offset.snowflake}")
    private int snowflakeExceptionCodeOffset;

    @PostConstruct
    public void init() {
        com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes.setExceptionCodeOffset(subgradeExceptionCodeOffset);
        com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes.setExceptionCodeOffset(snowflakeExceptionCodeOffset);
    }
}