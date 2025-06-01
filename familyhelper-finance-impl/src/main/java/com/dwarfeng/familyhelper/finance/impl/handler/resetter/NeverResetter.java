package com.dwarfeng.familyhelper.finance.impl.handler.resetter;

import com.dwarfeng.familyhelper.finance.sdk.handler.resetter.AbstractResetter;
import org.springframework.stereotype.Component;

/**
 * 永远不执行重置的重置器。
 *
 * @author DwArFeng
 * @since 1.4.2
 */
@Component
public class NeverResetter extends AbstractResetter {

    @Override
    public void start() {
    }

    @Override
    public void stop() {
    }

    @Override
    public String toString() {
        return "NeverResetter{}";
    }
}
