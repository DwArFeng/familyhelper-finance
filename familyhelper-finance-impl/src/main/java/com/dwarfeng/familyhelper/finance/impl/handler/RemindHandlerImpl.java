package com.dwarfeng.familyhelper.finance.impl.handler;

import com.dwarfeng.familyhelper.finance.sdk.util.Constants;
import com.dwarfeng.familyhelper.finance.stack.bean.dto.RemindInfo;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.AccountBook;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.Poab;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.RemindDriverInfo;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.User;
import com.dwarfeng.familyhelper.finance.stack.handler.PushHandler;
import com.dwarfeng.familyhelper.finance.stack.handler.RemindHandler;
import com.dwarfeng.familyhelper.finance.stack.service.AccountBookMaintainService;
import com.dwarfeng.familyhelper.finance.stack.service.PoabMaintainService;
import com.dwarfeng.familyhelper.finance.stack.service.RemindDriverInfoMaintainService;
import com.dwarfeng.familyhelper.finance.stack.service.UserMaintainService;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class RemindHandlerImpl implements RemindHandler {

    private final RemindDriverInfoMaintainService remindDriverInfoMaintainService;
    private final AccountBookMaintainService accountBookMaintainService;
    private final PoabMaintainService poabMaintainService;
    private final UserMaintainService userMaintainService;

    private final PushHandler pushHandler;

    private final HandlerValidator handlerValidator;

    public RemindHandlerImpl(
            RemindDriverInfoMaintainService remindDriverInfoMaintainService,
            AccountBookMaintainService accountBookMaintainService,
            PoabMaintainService poabMaintainService,
            UserMaintainService userMaintainService,
            PushHandler pushHandler,
            HandlerValidator handlerValidator
    ) {
        this.remindDriverInfoMaintainService = remindDriverInfoMaintainService;
        this.accountBookMaintainService = accountBookMaintainService;
        this.poabMaintainService = poabMaintainService;
        this.userMaintainService = userMaintainService;
        this.pushHandler = pushHandler;
        this.handlerValidator = handlerValidator;
    }

    @Override
    public void remind(LongIdKey remindDriverInfoKey) throws HandlerException {
        try {
            // 确认 RemindDriverInfo 合法。
            handlerValidator.makeSureRemindDriverInfoExists(remindDriverInfoKey);
            handlerValidator.makeSureRemindDriverInfoValid(remindDriverInfoKey);
            RemindDriverInfo remindDriverInfo = remindDriverInfoMaintainService.get(remindDriverInfoKey);

            // 确认 AccountBook 存在。
            LongIdKey accountBookKey = remindDriverInfo.getAccountBookKey();
            handlerValidator.makeSureAccountBookExists(accountBookKey);
            AccountBook accountBook = accountBookMaintainService.get(accountBookKey);

            //根据 RemindDriverInfo.remindScopeType 确定提醒的目标用户。
            List<User> aimedUsers = analyseAimedUsers(accountBook, remindDriverInfo.getRemindScopeType());

            // 构造 RemindInfo。
            RemindInfo remindInfo = new RemindInfo(accountBook, remindDriverInfo, aimedUsers);

            // 调用推送处理器推送事件。
            pushHandler.remindHappened(remindInfo);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    private List<User> analyseAimedUsers(AccountBook accountBook, int remindScopeType) throws Exception {
        // 获取与指定账本相关的所有权限信息。
        List<Poab> poabs = poabMaintainService.lookupAsList(
                PoabMaintainService.CHILD_FOR_ACCOUNT_BOOK, new Object[]{accountBook.getKey()}
        );

        // 根据 remindScopeType 确定不同的筛选器。
        Predicate<Poab> predicate;
        switch (remindScopeType) {
            case Constants.REMIND_SCOPE_TYPE_OWNER_ONLY:
                predicate = poab -> poab.getPermissionLevel() == Constants.PERMISSION_LEVEL_OWNER;
                break;
            case Constants.REMIND_SCOPE_TYPE_OWNER_AND_MODIFIER:
            case Constants.REMIND_SCOPE_TYPE_ALL_PERMITTED:
                predicate = poab -> true;
                break;
            default:
                throw new IllegalStateException("不应该执行到此处，请联系开发人员");
        }

        // 筛选出目标用户的主键，并查询出所有用户。
        List<StringIdKey> aimedUserKeys = poabs.stream().filter(predicate)
                .map(poab -> new StringIdKey(poab.getKey().getStringId())).collect(Collectors.toList());
        return userMaintainService.batchGet(aimedUserKeys);
    }
}
