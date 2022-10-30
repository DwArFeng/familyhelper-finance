package com.dwarfeng.familyhelper.finance.sdk.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 常量类。
 *
 * @author DwArFeng
 * @since 1.3.1
 */
public final class Constants {

    private static final Logger LOGGER = LoggerFactory.getLogger(Constants.class);

    public static final int PERMISSION_LEVEL_OWNER = 0;
    public static final int PERMISSION_LEVEL_GUEST = 1;

    /**
     * @since 1.4.0
     */
    @RemindScopeTypeItem
    public static final int REMIND_SCOPE_TYPE_OWNER_ONLY = 0;

    /**
     * @since 1.4.0
     */
    @RemindScopeTypeItem
    public static final int REMIND_SCOPE_TYPE_OWNER_AND_MODIFIER = 1;

    private static final Lock LOCK = new ReentrantLock();

    private static List<Integer> remindScopeTypeSpace = null;

    /**
     * 获取提醒范围类型的空间。
     *
     * @return 提醒范围类型的空间。
     * @since 1.4.0
     */
    public static List<Integer> remindScopeTypeSpace() {
        if (Objects.nonNull(remindScopeTypeSpace)) {
            return remindScopeTypeSpace;
        }
        // 基于线程安全的懒加载初始化结果列表。
        LOCK.lock();
        try {
            if (Objects.nonNull(remindScopeTypeSpace)) {
                return remindScopeTypeSpace;
            }
            initRemindScopeTypeSpace();
            return remindScopeTypeSpace;
        } finally {
            LOCK.unlock();
        }
    }

    private static void initRemindScopeTypeSpace() {
        List<Integer> result = new ArrayList<>();

        Field[] declaredFields = Constants.class.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (!declaredField.isAnnotationPresent(RemindScopeTypeItem.class)) {
                continue;
            }
            Integer value;
            try {
                value = (Integer) declaredField.get(null);
                result.add(value);
            } catch (Exception e) {
                LOGGER.error("初始化异常, 请检查代码, 信息如下: ", e);
            }
        }

        remindScopeTypeSpace = Collections.unmodifiableList(result);
    }

    private Constants() {
        throw new IllegalStateException("禁止实例化");
    }
}
