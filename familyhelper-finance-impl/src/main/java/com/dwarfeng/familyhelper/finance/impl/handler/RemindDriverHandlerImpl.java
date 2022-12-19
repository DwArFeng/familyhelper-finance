package com.dwarfeng.familyhelper.finance.impl.handler;

import com.dwarfeng.familyhelper.finance.stack.exception.UnsupportedRemindDriverTypeException;
import com.dwarfeng.familyhelper.finance.stack.handler.RemindDriver;
import com.dwarfeng.familyhelper.finance.stack.handler.RemindDriverHandler;
import com.dwarfeng.familyhelper.finance.stack.handler.RemindHandler;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Component
public class RemindDriverHandlerImpl implements RemindDriverHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(RemindDriverHandlerImpl.class);

    private final RemindHandler remindHandler;

    private final List<RemindDriverProvider> remindDriverProviders;

    private final InternalRemindDriverContext remindDriverContext = new InternalRemindDriverContext();

    public RemindDriverHandlerImpl(RemindHandler remindHandler, List<RemindDriverProvider> remindDriverProviders) {
        this.remindHandler = remindHandler;
        this.remindDriverProviders = Objects.isNull(remindDriverProviders) ?
                Collections.emptyList() : remindDriverProviders;
    }

    @PostConstruct
    public void init() {
        LOGGER.info("初始化驱动器...");
        remindDriverProviders.stream().map(RemindDriverProvider::provide).forEach(
                driver -> driver.init(remindDriverContext)
        );
    }

    @Override
    public RemindDriver find(String type) throws HandlerException {
        return remindDriverProviders.stream().filter(rdp -> rdp.supportType(type)).map(RemindDriverProvider::provide)
                .findAny().orElseThrow(() -> new UnsupportedRemindDriverTypeException(type));
    }

    private class InternalRemindDriverContext implements RemindDriver.Context {

        @Override
        public void remind(LongIdKey remindDriverInfoKey) throws Exception {
            remindHandler.remind(remindDriverInfoKey);
        }
    }
}
