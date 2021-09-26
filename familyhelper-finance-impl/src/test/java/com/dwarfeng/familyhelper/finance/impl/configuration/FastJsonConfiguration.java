package com.dwarfeng.familyhelper.finance.impl.configuration;

import com.alibaba.fastjson.parser.ParserConfig;
import com.dwarfeng.familyhelper.finance.sdk.bean.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FastJsonConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(FastJsonConfiguration.class);

    public FastJsonConfiguration() {
        LOGGER.info("正在配置 FastJson autotype 白名单");
        //实体对象。
        ParserConfig.getGlobalInstance().addAccept(FastJsonAccountBook.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonBankCard.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonBankCardTypeIndicator.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonFundChange.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonFundChangeTypeIndicator.class.getCanonicalName());
        LOGGER.debug("FastJson autotype 白名单配置完毕");
    }
}
