package com.dwarfeng.familyhelper.finance.sdk.bean;

import com.dwarfeng.familyhelper.finance.sdk.bean.entity.*;
import com.dwarfeng.familyhelper.finance.sdk.bean.key.FastJsonPoabKey;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.*;
import com.dwarfeng.familyhelper.finance.stack.bean.key.PoabKey;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

/**
 * FastJson Bean 映射器。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
@Mapper
public interface FastJsonMapper {

    FastJsonLongIdKey longIdKeyToFastJson(LongIdKey longIdKey);

    @InheritInverseConfiguration
    LongIdKey longIdKeyFromFastJson(FastJsonLongIdKey fastJsonLongIdKey);

    FastJsonStringIdKey stringIdKeyToFastJson(StringIdKey stringIdKey);

    @InheritInverseConfiguration
    StringIdKey stringIdKeyFromFastJson(FastJsonStringIdKey fastJsonStringIdKey);

    FastJsonPoabKey poabKeyToFastJson(PoabKey poabKey);

    @InheritInverseConfiguration
    PoabKey poabKeyFromFastJson(FastJsonPoabKey fastJsonPoabKey);

    FastJsonAccountBook accountBookToFastJson(AccountBook accountBook);

    @InheritInverseConfiguration
    AccountBook accountBookFromFastJson(FastJsonAccountBook fastJsonAccountBook);

    FastJsonBankCard bankCardToFastJson(BankCard bankCard);

    @InheritInverseConfiguration
    BankCard bankCardFromFastJson(FastJsonBankCard fastJsonBankCard);

    FastJsonBankCardBalanceHistory bankCardBalanceHistoryToFastJson(BankCardBalanceHistory bankCardBalanceHistory);

    @InheritInverseConfiguration
    BankCardBalanceHistory bankCardBalanceHistoryFromFastJson(
            FastJsonBankCardBalanceHistory fastJsonBankCardBalanceHistory
    );

    FastJsonBankCardTypeIndicator bankCardTypeIndicatorToFastJson(BankCardTypeIndicator bankCardTypeIndicator);

    @InheritInverseConfiguration
    BankCardTypeIndicator bankCardTypeIndicatorFromFastJson(
            FastJsonBankCardTypeIndicator fastJsonBankCardTypeIndicator
    );

    FastJsonBillFileInfo billFileInfoToFastJson(BillFileInfo billFileInfo);

    @InheritInverseConfiguration
    BillFileInfo billFileInfoFromFastJson(FastJsonBillFileInfo fastJsonBillFileInfo);

    FastJsonFundChange fundChangeToFastJson(FundChange fundChange);

    @InheritInverseConfiguration
    FundChange fundChangeFromFastJson(FastJsonFundChange fastJsonFundChange);

    FastJsonFundChangeTypeIndicator fundChangeTypeIndicatorToFastJson(
            FundChangeTypeIndicator fundChangeTypeIndicator
    );

    @InheritInverseConfiguration
    FundChangeTypeIndicator fundChangeTypeIndicatorFromFastJson(
            FastJsonFundChangeTypeIndicator fastJsonFundChangeTypeIndicator
    );

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
}
