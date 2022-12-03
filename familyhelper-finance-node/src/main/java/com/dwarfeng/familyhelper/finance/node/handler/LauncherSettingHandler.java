package com.dwarfeng.familyhelper.finance.node.handler;

import com.dwarfeng.subgrade.stack.handler.Handler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LauncherSettingHandler implements Handler {

    @Value("${launcher.reset_remind_driver_support}")
    private boolean resetRemindDriverSupport;

    @Value("${launcher.online_remind_drive_delay}")
    private long onlineRemindDriveDelay;
    @Value("${launcher.start_remind_drive_delay}")
    private long startRemindDriveDelay;

    public boolean isResetRemindDriverSupport() {
        return resetRemindDriverSupport;
    }

    public long getOnlineRemindDriveDelay() {
        return onlineRemindDriveDelay;
    }

    public long getStartRemindDriveDelay() {
        return startRemindDriveDelay;
    }
}
