package com.dwarfeng.familyhelper.finance.stack.handler;

import com.dwarfeng.familyhelper.finance.stack.bean.dto.BankCardCreateInfo;
import com.dwarfeng.familyhelper.finance.stack.bean.dto.BankCardUpdateInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 银行卡处理器。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public interface BankCardOperateHandler extends Handler {

    /**
     * 创建银行卡。
     *
     * @param userKey            银行卡的所有者的主键。
     * @param bankCardCreateInfo 银行卡的创建信息。
     * @return 生成的银行卡的主键。
     * @throws HandlerException 处理器异常。
     */
    LongIdKey createBankCard(StringIdKey userKey, BankCardCreateInfo bankCardCreateInfo) throws HandlerException;

    /**
     * 更新银行卡。
     *
     * @param userKey            银行卡的所有者的主键。
     * @param bankCardUpdateInfo 银行卡的更新信息。
     * @throws HandlerException 处理器异常。
     */
    void updateBankCard(StringIdKey userKey, BankCardUpdateInfo bankCardUpdateInfo) throws HandlerException;

    /**
     * 创建银行卡。
     *
     * @param userKey     银行卡的所有者的主键。
     * @param bankCardKey 银行卡主键。
     * @throws HandlerException 处理器异常。
     */
    void removeBankCard(StringIdKey userKey, LongIdKey bankCardKey) throws HandlerException;
}
