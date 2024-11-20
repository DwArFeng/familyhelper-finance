package com.dwarfeng.familyhelper.finance.impl.handler;

import com.dwarfeng.familyhelper.finance.stack.bean.dto.FundChangeRecordInfo;
import com.dwarfeng.familyhelper.finance.stack.bean.dto.FundChangeUpdateInfo;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.FundChange;
import com.dwarfeng.familyhelper.finance.stack.handler.FundChangeOperateHandler;
import com.dwarfeng.familyhelper.finance.stack.service.FundChangeMaintainService;
import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Component
public class FundChangeOperateHandlerImpl implements FundChangeOperateHandler {

    private final FundChangeMaintainService fundChangeMaintainService;

    private final HandlerValidator handlerValidator;

    public FundChangeOperateHandlerImpl(
            FundChangeMaintainService fundChangeMaintainService,
            HandlerValidator handlerValidator
    ) {
        this.fundChangeMaintainService = fundChangeMaintainService;
        this.handlerValidator = handlerValidator;
    }

    @Override
    public LongIdKey recordFundChange(StringIdKey userKey, FundChangeRecordInfo fundChangeRecordInfo)
            throws HandlerException {
        try {
            // 展开参数。
            LongIdKey accountBookKey = fundChangeRecordInfo.getAccountBookKey();
            BigDecimal delta = fundChangeRecordInfo.getDelta();
            String changeType = fundChangeRecordInfo.getChangeType();
            String remark = fundChangeRecordInfo.getRemark();
            Date happenedDate = fundChangeRecordInfo.getHappenedDate();

            // 确认用户存在。
            handlerValidator.makeSureUserExists(userKey);

            // 确认账本存在。
            handlerValidator.makeSureAccountBookExists(accountBookKey);

            // 确认用户有权限操作指定的账本。
            handlerValidator.makeSureUserModifyPermittedForAccountBook(userKey, accountBookKey);

            // 设置当前日期。
            Date currentDate = new Date();

            // 如果 happenedDate 为 null，则设置为当前系统时间。
            if (Objects.isNull(happenedDate)) {
                happenedDate = currentDate;
            }

            // 根据 fundChangeRecordInfo 以及创建的规则组合 资金变更 实体。
            FundChange fundChange = new FundChange(
                    null, accountBookKey, delta, changeType, happenedDate, remark, currentDate
            );

            // 插入资金变更实体，并返回生成的主键。
            return fundChangeMaintainService.insert(fundChange);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public void updateFundChange(StringIdKey userKey, FundChangeUpdateInfo fundChangeUpdateInfo)
            throws HandlerException {
        try {
            // 展开参数。
            LongIdKey fundChangeKey = fundChangeUpdateInfo.getFundChangeKey();
            BigDecimal delta = fundChangeUpdateInfo.getDelta();
            String changeType = fundChangeUpdateInfo.getChangeType();
            String remark = fundChangeUpdateInfo.getRemark();
            Date happenedDate = fundChangeUpdateInfo.getHappenedDate();

            // 确认用户存在。
            handlerValidator.makeSureUserExists(userKey);

            // 确认资金变更存在。
            handlerValidator.makeSureFundChangeExists(fundChangeKey);

            // 确认用户有权限操作指定的资金变更记录。
            handlerValidator.makeSureUserModifyPermittedForFundChange(userKey, fundChangeKey);

            // 设置当前日期。
            Date currentDate = new Date();

            // 如果 happenedDate 为 null，则设置为当前系统时间。
            if (Objects.isNull(happenedDate)) {
                happenedDate = currentDate;
            }

            // 根据 fundChangeUpdateInfo 以及更新的规则设置 资金变更 实体。
            FundChange fundChange = fundChangeMaintainService.get(fundChangeKey);
            fundChange.setDelta(delta);
            fundChange.setChangeType(changeType);
            fundChange.setRemark(remark);
            fundChange.setHappenedDate(happenedDate);
            fundChange.setRecordedDate(currentDate);

            // 更新资金变更实体。
            fundChangeMaintainService.update(fundChange);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public void removeFundChange(StringIdKey userKey, LongIdKey fundChangeKey) throws HandlerException {
        try {
            // 确认用户存在。
            handlerValidator.makeSureUserExists(userKey);

            // 确认资金变更存在。
            handlerValidator.makeSureFundChangeExists(fundChangeKey);

            // 确认用户有权限操作指定的资金变更。
            handlerValidator.makeSureUserModifyPermittedForFundChange(userKey, fundChangeKey);

            // 存在删除指定的资金变更。
            fundChangeMaintainService.deleteIfExists(fundChangeKey);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }
}
