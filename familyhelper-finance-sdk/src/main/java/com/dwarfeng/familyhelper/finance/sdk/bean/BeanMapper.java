package com.dwarfeng.familyhelper.finance.sdk.bean;

import com.dwarfeng.familyhelper.finance.sdk.bean.dto.*;
import com.dwarfeng.familyhelper.finance.sdk.bean.entity.*;
import com.dwarfeng.familyhelper.finance.sdk.bean.key.FastJsonPoabKey;
import com.dwarfeng.familyhelper.finance.sdk.bean.key.JSFixedFastJsonPoabKey;
import com.dwarfeng.familyhelper.finance.sdk.bean.key.WebInputPoabKey;
import com.dwarfeng.familyhelper.finance.stack.bean.dto.*;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.*;
import com.dwarfeng.familyhelper.finance.stack.bean.key.PoabKey;
import com.dwarfeng.subgrade.sdk.bean.key.*;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

/**
 * Bean 映射器。
 *
 * <p>
 * 该映射器中包含了 <code>sdk</code> 模块中所有实体与 <code>stack</code> 模块中对应实体的映射方法。
 *
 * @author DwArFeng
 * @since 1.6.0
 */
@Mapper
public interface BeanMapper {

    // -----------------------------------------------------------Subgrade Key-----------------------------------------------------------
    FastJsonLongIdKey longIdKeyToFastJson(LongIdKey longIdKey);

    @InheritInverseConfiguration
    LongIdKey longIdKeyFromFastJson(FastJsonLongIdKey fastJsonLongIdKey);

    FastJsonStringIdKey stringIdKeyToFastJson(StringIdKey stringIdKey);

    @InheritInverseConfiguration
    StringIdKey stringIdKeyFromFastJson(FastJsonStringIdKey fastJsonStringIdKey);

    JSFixedFastJsonLongIdKey longIdKeyToJSFixedFastJson(LongIdKey longIdKey);

    @InheritInverseConfiguration
    LongIdKey longIdKeyFromJSFixedFastJson(JSFixedFastJsonLongIdKey jSFixedFastJsonLongIdKey);

    WebInputLongIdKey longIdKeyToWebInput(LongIdKey longIdKey);

    @InheritInverseConfiguration
    LongIdKey longIdKeyFromWebInput(WebInputLongIdKey webInputLongIdKey);

    WebInputStringIdKey stringIdKeyToWebInput(StringIdKey stringIdKey);

    @InheritInverseConfiguration
    StringIdKey stringIdKeyFromWebInput(WebInputStringIdKey webInputStringIdKey);

    // -----------------------------------------------------------Familyhelper-finance Key-----------------------------------------------------------
    FastJsonPoabKey poabKeyToFastJson(PoabKey poabKey);

    @InheritInverseConfiguration
    PoabKey poabKeyFromFastJson(FastJsonPoabKey fastJsonPoabKey);

    JSFixedFastJsonPoabKey poabKeyToJSFixedFastJson(PoabKey poabKey);

    @InheritInverseConfiguration
    PoabKey poabKeyFromJSFixedFastJson(JSFixedFastJsonPoabKey jSFixedFastJsonPoabKey);

    WebInputPoabKey poabKeyToWebInput(PoabKey poabKey);

    @InheritInverseConfiguration
    PoabKey poabKeyFromWebInput(WebInputPoabKey webInputPoabKey);

    // -----------------------------------------------------------Familyhelper-finance Entity-----------------------------------------------------------
    FastJsonAccountBook accountBookToFastJson(AccountBook accountBook);

    @InheritInverseConfiguration
    AccountBook accountBookFromFastJson(FastJsonAccountBook fastJsonAccountBook);

    FastJsonBankCard bankCardToFastJson(BankCard bankCard);

    @InheritInverseConfiguration
    BankCard bankCardFromFastJson(FastJsonBankCard fastJsonBankCard);

    FastJsonBankCardBalanceHistory bankCardBalanceHistoryToFastJson(BankCardBalanceHistory bankCardBalanceHistory);

    @InheritInverseConfiguration
    BankCardBalanceHistory bankCardBalanceHistoryFromFastJson(FastJsonBankCardBalanceHistory fastJsonBankCardBalanceHistory);

    FastJsonBankCardTypeIndicator bankCardTypeIndicatorToFastJson(BankCardTypeIndicator bankCardTypeIndicator);

    @InheritInverseConfiguration
    BankCardTypeIndicator bankCardTypeIndicatorFromFastJson(FastJsonBankCardTypeIndicator fastJsonBankCardTypeIndicator);

    FastJsonBillFileInfo billFileInfoToFastJson(BillFileInfo billFileInfo);

    @InheritInverseConfiguration
    BillFileInfo billFileInfoFromFastJson(FastJsonBillFileInfo fastJsonBillFileInfo);

    FastJsonFundChange fundChangeToFastJson(FundChange fundChange);

    @InheritInverseConfiguration
    FundChange fundChangeFromFastJson(FastJsonFundChange fastJsonFundChange);

    FastJsonFundChangeTypeIndicator fundChangeTypeIndicatorToFastJson(FundChangeTypeIndicator fundChangeTypeIndicator);

    @InheritInverseConfiguration
    FundChangeTypeIndicator fundChangeTypeIndicatorFromFastJson(FastJsonFundChangeTypeIndicator fastJsonFundChangeTypeIndicator);

    FastJsonPoab poabToFastJson(Poab poab);

    @InheritInverseConfiguration
    Poab poabFromFastJson(FastJsonPoab fastJsonPoab);

    FastJsonRemindDriverInfo remindDriverInfoToFastJson(RemindDriverInfo remindDriverInfo);

    @InheritInverseConfiguration
    RemindDriverInfo remindDriverInfoFromFastJson(FastJsonRemindDriverInfo fastJsonRemindDriverInfo);

    FastJsonRemindDriverSupport remindDriverSupportToFastJson(RemindDriverSupport remindDriverSupport);

    @InheritInverseConfiguration
    RemindDriverSupport remindDriverSupportFromFastJson(FastJsonRemindDriverSupport fastJsonRemindDriverSupport);

    FastJsonTotalBalanceHistory totalBalanceHistoryToFastJson(TotalBalanceHistory totalBalanceHistory);

    @InheritInverseConfiguration
    TotalBalanceHistory totalBalanceHistoryFromFastJson(FastJsonTotalBalanceHistory fastJsonTotalBalanceHistory);

    FastJsonUser userToFastJson(User user);

    @InheritInverseConfiguration
    User userFromFastJson(FastJsonUser fastJsonUser);

    JSFixedFastJsonAccountBook accountBookToJSFixedFastJson(AccountBook accountBook);

    @InheritInverseConfiguration
    AccountBook accountBookFromJSFixedFastJson(JSFixedFastJsonAccountBook jSFixedFastJsonAccountBook);

    JSFixedFastJsonBankCard bankCardToJSFixedFastJson(BankCard bankCard);

    @InheritInverseConfiguration
    BankCard bankCardFromJSFixedFastJson(JSFixedFastJsonBankCard jSFixedFastJsonBankCard);

    JSFixedFastJsonBankCardBalanceHistory bankCardBalanceHistoryToJSFixedFastJson(BankCardBalanceHistory bankCardBalanceHistory);

    @InheritInverseConfiguration
    BankCardBalanceHistory bankCardBalanceHistoryFromJSFixedFastJson(JSFixedFastJsonBankCardBalanceHistory jSFixedFastJsonBankCardBalanceHistory);

    JSFixedFastJsonBillFileInfo billFileInfoToJSFixedFastJson(BillFileInfo billFileInfo);

    @InheritInverseConfiguration
    BillFileInfo billFileInfoFromJSFixedFastJson(JSFixedFastJsonBillFileInfo jSFixedFastJsonBillFileInfo);

    JSFixedFastJsonFundChange fundChangeToJSFixedFastJson(FundChange fundChange);

    @InheritInverseConfiguration
    FundChange fundChangeFromJSFixedFastJson(JSFixedFastJsonFundChange jSFixedFastJsonFundChange);

    JSFixedFastJsonPoab poabToJSFixedFastJson(Poab poab);

    @InheritInverseConfiguration
    Poab poabFromJSFixedFastJson(JSFixedFastJsonPoab jSFixedFastJsonPoab);

    JSFixedFastJsonRemindDriverInfo remindDriverInfoToJSFixedFastJson(RemindDriverInfo remindDriverInfo);

    @InheritInverseConfiguration
    RemindDriverInfo remindDriverInfoFromJSFixedFastJson(JSFixedFastJsonRemindDriverInfo jSFixedFastJsonRemindDriverInfo);

    JSFixedFastJsonTotalBalanceHistory totalBalanceHistoryToJSFixedFastJson(TotalBalanceHistory totalBalanceHistory);

    @InheritInverseConfiguration
    TotalBalanceHistory totalBalanceHistoryFromJSFixedFastJson(JSFixedFastJsonTotalBalanceHistory jSFixedFastJsonTotalBalanceHistory);

    WebInputAccountBook accountBookToWebInput(AccountBook accountBook);

    @InheritInverseConfiguration
    AccountBook accountBookFromWebInput(WebInputAccountBook webInputAccountBook);

    WebInputBankCard bankCardToWebInput(BankCard bankCard);

    @InheritInverseConfiguration
    BankCard bankCardFromWebInput(WebInputBankCard webInputBankCard);

    WebInputBankCardTypeIndicator bankCardTypeIndicatorToWebInput(BankCardTypeIndicator bankCardTypeIndicator);

    @InheritInverseConfiguration
    BankCardTypeIndicator bankCardTypeIndicatorFromWebInput(WebInputBankCardTypeIndicator webInputBankCardTypeIndicator);

    WebInputFundChange fundChangeToWebInput(FundChange fundChange);

    @InheritInverseConfiguration
    FundChange fundChangeFromWebInput(WebInputFundChange webInputFundChange);

    WebInputFundChangeTypeIndicator fundChangeTypeIndicatorToWebInput(FundChangeTypeIndicator fundChangeTypeIndicator);

    @InheritInverseConfiguration
    FundChangeTypeIndicator fundChangeTypeIndicatorFromWebInput(WebInputFundChangeTypeIndicator webInputFundChangeTypeIndicator);

    WebInputPoab poabToWebInput(Poab poab);

    @InheritInverseConfiguration
    Poab poabFromWebInput(WebInputPoab webInputPoab);

    WebInputRemindDriverInfo remindDriverInfoToWebInput(RemindDriverInfo remindDriverInfo);

    @InheritInverseConfiguration
    RemindDriverInfo remindDriverInfoFromWebInput(WebInputRemindDriverInfo webInputRemindDriverInfo);

    WebInputUser userToWebInput(User user);

    @InheritInverseConfiguration
    User userFromWebInput(WebInputUser webInputUser);

    // -----------------------------------------------------------Familyhelper-finance DTO-----------------------------------------------------------
    WebInputAccountBookCreateInfo accountBookCreateInfoToWebInput(AccountBookCreateInfo accountBookCreateInfo);

    @InheritInverseConfiguration
    AccountBookCreateInfo accountBookCreateInfoFromWebInput(WebInputAccountBookCreateInfo webInputAccountBookCreateInfo);

    WebInputAccountBookPermissionRemoveInfo accountBookPermissionRemoveInfoToWebInput(AccountBookPermissionRemoveInfo accountBookPermissionRemoveInfo);

    @InheritInverseConfiguration
    AccountBookPermissionRemoveInfo accountBookPermissionRemoveInfoFromWebInput(WebInputAccountBookPermissionRemoveInfo webInputAccountBookPermissionRemoveInfo);

    WebInputAccountBookPermissionUpsertInfo accountBookPermissionUpsertInfoToWebInput(AccountBookPermissionUpsertInfo accountBookPermissionUpsertInfo);

    @InheritInverseConfiguration
    AccountBookPermissionUpsertInfo accountBookPermissionUpsertInfoFromWebInput(WebInputAccountBookPermissionUpsertInfo webInputAccountBookPermissionUpsertInfo);

    WebInputAccountBookUpdateInfo accountBookUpdateInfoToWebInput(AccountBookUpdateInfo accountBookUpdateInfo);

    @InheritInverseConfiguration
    AccountBookUpdateInfo accountBookUpdateInfoFromWebInput(WebInputAccountBookUpdateInfo webInputAccountBookUpdateInfo);

    WebInputBankCardBalanceRecordInfo bankCardBalanceRecordInfoToWebInput(BankCardBalanceRecordInfo bankCardBalanceRecordInfo);

    @InheritInverseConfiguration
    BankCardBalanceRecordInfo bankCardBalanceRecordInfoFromWebInput(WebInputBankCardBalanceRecordInfo webInputBankCardBalanceRecordInfo);

    WebInputBankCardCreateInfo bankCardCreateInfoToWebInput(BankCardCreateInfo bankCardCreateInfo);

    @InheritInverseConfiguration
    BankCardCreateInfo bankCardCreateInfoFromWebInput(WebInputBankCardCreateInfo webInputBankCardCreateInfo);

    WebInputBankCardUpdateInfo bankCardUpdateInfoToWebInput(BankCardUpdateInfo bankCardUpdateInfo);

    @InheritInverseConfiguration
    BankCardUpdateInfo bankCardUpdateInfoFromWebInput(WebInputBankCardUpdateInfo webInputBankCardUpdateInfo);

    WebInputFundChangeRecordInfo fundChangeRecordInfoToWebInput(FundChangeRecordInfo fundChangeRecordInfo);

    @InheritInverseConfiguration
    FundChangeRecordInfo fundChangeRecordInfoFromWebInput(WebInputFundChangeRecordInfo webInputFundChangeRecordInfo);

    WebInputFundChangeUpdateInfo fundChangeUpdateInfoToWebInput(FundChangeUpdateInfo fundChangeUpdateInfo);

    @InheritInverseConfiguration
    FundChangeUpdateInfo fundChangeUpdateInfoFromWebInput(WebInputFundChangeUpdateInfo webInputFundChangeUpdateInfo);
}
