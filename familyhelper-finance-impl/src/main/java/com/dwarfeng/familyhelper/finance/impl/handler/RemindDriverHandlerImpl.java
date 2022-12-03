package com.dwarfeng.familyhelper.finance.impl.handler;

import com.dwarfeng.familyhelper.finance.stack.exception.UnsupportedRemindDriverTypeException;
import com.dwarfeng.familyhelper.finance.stack.handler.RemindDriver;
import com.dwarfeng.familyhelper.finance.stack.handler.RemindDriverHandler;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Component
public class RemindDriverHandlerImpl implements RemindDriverHandler {

    private final List<RemindDriverProvider> remindDriverProviders;

    public RemindDriverHandlerImpl(List<RemindDriverProvider> remindDriverProviders) {
        this.remindDriverProviders = Objects.isNull(remindDriverProviders) ?
                Collections.emptyList() : remindDriverProviders;
    }

    @Override
    public RemindDriver find(String type) throws HandlerException {
        return remindDriverProviders.stream().filter(rdp -> rdp.supportType(type)).map(RemindDriverProvider::provide)
                .findAny().orElseThrow(() -> new UnsupportedRemindDriverTypeException(type));
    }
}
