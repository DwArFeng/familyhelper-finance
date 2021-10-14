package com.dwarfeng.familyhelper.finance.stack.handler;

import com.dwarfeng.familyhelper.finance.stack.bean.dto.FundChangeRecordInfo;
import com.dwarfeng.familyhelper.finance.stack.bean.dto.FundChangeUpdateInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 资金变更操作处理器。
 *
 * @author DwArFeng
 * @since 1.2.0
 */
public interface FundChangeOperateHandler extends Handler {

    /**
     * 记录资金变更。
     *
     * @param userKey              操作用户。
     * @param accountBookKey       资金变更所属账本的主键。
     * @param fundChangeRecordInfo 资金变更记录信息。
     * @return 生成的资金变更实体主键。
     * @throws HandlerException 处理器异常。
     */
    LongIdKey recordFundChange(
            StringIdKey userKey, LongIdKey accountBookKey, FundChangeRecordInfo fundChangeRecordInfo
    ) throws HandlerException;

    /**
     * 更新资金变更。
     *
     * @param userKey              操作用户。
     * @param fundChangeKey        资金变更实体的主键。
     * @param fundChangeUpdateInfo 资金变更更新信息。
     * @throws HandlerException 处理器异常。
     */
    void updateFundChange(StringIdKey userKey, LongIdKey fundChangeKey, FundChangeUpdateInfo fundChangeUpdateInfo)
            throws HandlerException;

    /**
     * 删除资金变更。
     *
     * @param userKey       操作用户。
     * @param fundChangeKey 资金变更实体的主键。
     * @throws HandlerException 处理器异常。
     */
    void removeFundChange(StringIdKey userKey, LongIdKey fundChangeKey) throws HandlerException;
}
