package com.dwarfeng.familyhelper.finance.sdk.handler.resetter;

import com.dwarfeng.familyhelper.finance.stack.handler.Resetter;

/**
 * 重置器的抽象实现。
 *
 * @author DwArFeng
 * @since 1.6.0
 */
public abstract class AbstractResetter implements Resetter {

    protected Context context;

    @Override
    public void init(Context context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return "AbstractResetter{" +
                "context=" + context +
                '}';
    }
}
