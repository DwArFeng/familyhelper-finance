package com.dwarfeng.familyhelper.finance.impl.handler.pusher;

import com.dwarfeng.familyhelper.finance.sdk.handler.pusher.AbstractPusher;
import com.dwarfeng.familyhelper.finance.stack.bean.dto.RemindInfo;
import org.springframework.stereotype.Component;

/**
 * 简单的丢弃掉所有信息的推送器。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
@Component
public class DrainPusher extends AbstractPusher {

    public static final String SUPPORT_TYPE = "drain";

    public DrainPusher() {
        super(SUPPORT_TYPE);
    }

    @Override
    public void remindHappened(RemindInfo remindInfo) {
    }

    @Override
    public void remindDriveReset() {
    }

    @Override
    public String toString() {
        return "DrainPusher{" +
                "pusherType='" + pusherType + '\'' +
                '}';
    }
}
