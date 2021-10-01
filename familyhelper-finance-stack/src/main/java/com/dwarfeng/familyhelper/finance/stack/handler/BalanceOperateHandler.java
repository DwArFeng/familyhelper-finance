package com.dwarfeng.familyhelper.finance.stack.handler;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

import java.math.BigDecimal;

/**
 * 余额操作处理器。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public interface BalanceOperateHandler extends Handler {

    /**
     * 记录银行卡的余额。
     * <p>
     * 将银行卡的余额记录在临时区，并不是真正的记录。调用余额提交接口后，才正式记录。
     * <br>
     * 余额提交接口: {@link #recordCommit(StringIdKey, LongIdKey)}
     *
     * @param userKey     操作用户。
     * @param bankCardKey 指定的银行卡主键。
     * @param balance     余额值。
     * @throws HandlerException 处理器异常。
     */
    void recordBankCardBalance(StringIdKey userKey, LongIdKey bankCardKey, BigDecimal balance) throws HandlerException;

    /**
     * 提交银行卡的余额。
     *
     * @param userKey        操作用户。
     * @param accountBookKey 指定的账本主键
     * @throws HandlerException 处理器异常。
     */
    void recordCommit(StringIdKey userKey, LongIdKey accountBookKey) throws HandlerException;

    /**
     * 回滚单个银行卡的余额。
     *
     * @param userKey     操作用户。
     * @param bankCardKey 指定的银行卡主键。
     * @throws HandlerException 处理器异常。
     */
    void rollbackBankCard(StringIdKey userKey, LongIdKey bankCardKey) throws HandlerException;

    /**
     * 回滚整个账本的所有余额。
     *
     * @param userKey        操作用户。
     * @param accountBookKey 指定的账本主键
     * @throws HandlerException 处理器异常。
     */
    void rollbackAll(StringIdKey userKey, LongIdKey accountBookKey) throws HandlerException;
}
