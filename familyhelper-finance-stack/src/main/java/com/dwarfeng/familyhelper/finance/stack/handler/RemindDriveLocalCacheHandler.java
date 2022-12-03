package com.dwarfeng.familyhelper.finance.stack.handler;

import com.dwarfeng.familyhelper.finance.stack.bean.dto.RemindDriveInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 提醒驱动用本地缓存处理器。
 *
 * <p>处理器在本地保存数据，缓存中的数据可以保证与数据源保持同步。
 * <p>数据存放在本地，必要时才与数据访问层通信，这有助于程序效率的提升。
 * <p>该处理器线程安全。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
public interface RemindDriveLocalCacheHandler extends Handler {

    /**
     * 获取指定账本的提醒驱动信息。
     *
     * <p>
     * 如果指定的账本不存在，则返回 <code>null</code>。
     *
     * @param accountBookKey 指定的账本。
     * @return 指定账本对应的的提醒驱动信息，或者是 <code>null</code>。
     * @throws HandlerException 处理器异常。
     */
    RemindDriveInfo getRemindDriveInfo(LongIdKey accountBookKey) throws HandlerException;

    /**
     * 清除本地缓存。
     *
     * @throws HandlerException 处理器异常。
     */
    void clear() throws HandlerException;
}
