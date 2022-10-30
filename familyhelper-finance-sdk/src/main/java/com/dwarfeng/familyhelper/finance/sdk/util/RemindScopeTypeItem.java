package com.dwarfeng.familyhelper.finance.sdk.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 提醒范围类型条目。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RemindScopeTypeItem {
}
