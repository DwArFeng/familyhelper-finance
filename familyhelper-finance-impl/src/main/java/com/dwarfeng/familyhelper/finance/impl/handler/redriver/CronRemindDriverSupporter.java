package com.dwarfeng.familyhelper.finance.impl.handler.redriver;

import com.dwarfeng.familyhelper.finance.sdk.handler.RemindDriverSupporter;
import org.springframework.stereotype.Component;

/**
 * Cron 提醒驱动器支持器。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
@Component
public class CronRemindDriverSupporter implements RemindDriverSupporter {

    public static final String SUPPORT_TYPE = "cron_remind_driver";

    @Override
    public String provideType() {
        return SUPPORT_TYPE;
    }

    @Override
    public String provideLabel() {
        return "Cron 提醒驱动器";
    }

    @Override
    public String provideDescription() {
        return "根据指定的 Cron 表达式定时提醒驱动的提醒驱动器";
    }

    @Override
    public String provideExampleParam() {
        return "0/2 * * * * *";
    }
}
