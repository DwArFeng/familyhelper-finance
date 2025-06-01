package com.dwarfeng.familyhelper.finance.sdk.handler.redriver;

import com.dwarfeng.familyhelper.finance.stack.handler.RemindDriver;

/**
 * 提醒驱动器的抽象实现。
 *
 * @author DwArFeng
 * @since 1.6.0
 */
public abstract class AbstractRemindDriver implements RemindDriver {

    protected Context context;

    @Override
    public void init(Context context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return "AbstractRemindDriver{" +
                "context=" + context +
                '}';
    }
}
