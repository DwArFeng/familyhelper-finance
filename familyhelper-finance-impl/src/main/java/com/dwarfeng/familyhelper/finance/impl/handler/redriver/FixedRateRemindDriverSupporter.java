package com.dwarfeng.familyhelper.finance.impl.handler.redriver;

import com.dwarfeng.familyhelper.finance.sdk.handler.RemindDriverSupporter;
import org.springframework.stereotype.Component;

/**
 * 固定间隔提醒驱动支持器。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
@Component
public class FixedRateRemindDriverSupporter implements RemindDriverSupporter {

    public static final String SUPPORT_TYPE = "fixed_rate_remind_driver";

    @Override
    public String provideType() {
        return SUPPORT_TYPE;
    }

    @Override
    public String provideLabel() {
        return "固定频率提醒驱动器";
    }

    @Override
    public String provideDescription() {
        return "根据指定的间隔定时提醒驱动，如果某一次提醒驱动晚于间隔，则后续提醒驱动的时间会提前，以保持频率不变。";
    }

    @Override
    public String provideExampleParam() {
        return "60000";
    }
}
