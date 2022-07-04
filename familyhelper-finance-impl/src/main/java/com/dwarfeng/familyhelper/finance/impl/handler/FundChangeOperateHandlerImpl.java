package com.dwarfeng.familyhelper.finance.impl.handler;

import com.dwarfeng.familyhelper.finance.stack.bean.dto.FundChangeRecordInfo;
import com.dwarfeng.familyhelper.finance.stack.bean.dto.FundChangeUpdateInfo;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.FundChange;
import com.dwarfeng.familyhelper.finance.stack.handler.FundChangeOperateHandler;
import com.dwarfeng.familyhelper.finance.stack.service.FundChangeMaintainService;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class FundChangeOperateHandlerImpl implements FundChangeOperateHandler {

    private final FundChangeMaintainService fundChangeMaintainService;

    private final OperateHandlerValidator operateHandlerValidator;

    public FundChangeOperateHandlerImpl(
            FundChangeMaintainService fundChangeMaintainService,
            OperateHandlerValidator operateHandlerValidator
    ) {
        this.fundChangeMaintainService = fundChangeMaintainService;
        this.operateHandlerValidator = operateHandlerValidator;
    }

    @Override
    public LongIdKey recordFundChange(StringIdKey userKey, FundChangeRecordInfo fundChangeRecordInfo)
            throws HandlerException {
        try {
            LongIdKey accountBookKey = fundChangeRecordInfo.getAccountBookKey();

            // 1. 确认用户存在。
            operateHandlerValidator.makeSureUserExists(userKey);

            // 2. 确认账本存在。
            operateHandlerValidator.makeSureAccountBookExists(accountBookKey);

            // 3. 确认用户有权限操作指定的账本。
            operateHandlerValidator.makeSureUserModifyPermittedForAccountBook(userKey, accountBookKey);

            // 4. 根据 fundChangeRecordInfo 以及创建的规则组合 资金变更 实体。
            FundChange fundChange = new FundChange(
                    null, accountBookKey, fundChangeRecordInfo.getDelta(), fundChangeRecordInfo.getChangeType(),
                    new Date(), fundChangeRecordInfo.getRemark()
            );

            // 4. 插入资金变更实体，并返回生成的主键。
            return fundChangeMaintainService.insert(fundChange);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public void updateFundChange(StringIdKey userKey, FundChangeUpdateInfo fundChangeUpdateInfo)
            throws HandlerException {
        try {
            LongIdKey fundChangeKey = fundChangeUpdateInfo.getFundChangeKey();

            // 1. 确认用户存在。
            operateHandlerValidator.makeSureUserExists(userKey);

            // 2. 确认资金变更存在。
            operateHandlerValidator.makeSureFundChangeExists(fundChangeKey);

            // 3. 确认用户有权限操作指定的资金变更记录。
            operateHandlerValidator.makeSureUserModifyPermittedForFundChange(userKey, fundChangeKey);

            // 4. 根据 fundChangeUpdateInfo 以及更新的规则设置 资金变更 实体。
            FundChange fundChange = fundChangeMaintainService.get(fundChangeKey);
            fundChange.setDelta(fundChangeUpdateInfo.getDelta());
            fundChange.setChangeType(fundChangeUpdateInfo.getChangeType());
            fundChange.setRemark(fundChangeUpdateInfo.getRemark());

            // 5. 更新资金变更实体。
            fundChangeMaintainService.update(fundChange);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public void removeFundChange(StringIdKey userKey, LongIdKey fundChangeKey) throws HandlerException {
        try {
            // 1. 确认用户存在。
            operateHandlerValidator.makeSureUserExists(userKey);

            // 2. 确认资金变更存在。
            operateHandlerValidator.makeSureFundChangeExists(fundChangeKey);

            // 3. 确认用户有权限操作指定的资金变更。
            operateHandlerValidator.makeSureUserModifyPermittedForFundChange(userKey, fundChangeKey);

            // 4. 存在删除指定的资金变更。
            fundChangeMaintainService.deleteIfExists(fundChangeKey);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }
}
