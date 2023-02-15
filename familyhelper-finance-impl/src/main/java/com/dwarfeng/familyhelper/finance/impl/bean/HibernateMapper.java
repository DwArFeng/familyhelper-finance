package com.dwarfeng.familyhelper.finance.impl.bean;

import com.dwarfeng.familyhelper.finance.impl.bean.entity.*;
import com.dwarfeng.familyhelper.finance.impl.bean.key.HibernatePoabKey;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.*;
import com.dwarfeng.familyhelper.finance.stack.bean.key.PoabKey;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateStringIdKey;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Hibernate Bean 映射器。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
@Mapper
public interface HibernateMapper {

    HibernateLongIdKey longIdKeyToHibernate(LongIdKey longIdKey);

    @InheritInverseConfiguration
    LongIdKey longIdKeyFromHibernate(HibernateLongIdKey hibernateLongIdKey);

    HibernateStringIdKey stringIdKeyToHibernate(StringIdKey stringIdKey);

    @InheritInverseConfiguration
    StringIdKey stringIdKeyFromHibernate(HibernateStringIdKey hibernateStringIdKey);

    HibernatePoabKey poabKeyToHibernate(PoabKey poabKey);

    @InheritInverseConfiguration
    PoabKey poabKeyFromHibernate(HibernatePoabKey hibernatePoabKey);

    @Mapping(target = "remindDriverInfos", ignore = true)
    @Mapping(target = "poabs", ignore = true)
    @Mapping(target = "longId", ignore = true)
    @Mapping(target = "fundChanges", ignore = true)
    @Mapping(target = "cardType", ignore = true)
    @Mapping(target = "bankCards", ignore = true)
    HibernateAccountBook accountBookToHibernate(AccountBook accountBook);

    @InheritInverseConfiguration
    AccountBook accountBookFromHibernate(HibernateAccountBook hibernateAccountBook);

    @Mapping(target = "longId", ignore = true)
    @Mapping(target = "accountBookLongId", ignore = true)
    @Mapping(target = "accountBook", ignore = true)
    HibernateBankCard bankCardToHibernate(BankCard bankCard);

    @InheritInverseConfiguration
    BankCard bankCardFromHibernate(HibernateBankCard hibernateBankCard);

    @Mapping(target = "longId", ignore = true)
    @Mapping(target = "bankCardLongId", ignore = true)
    @Mapping(target = "bankCard", ignore = true)
    HibernateBankCardBalanceHistory bankCardBalanceHistoryToHibernate(BankCardBalanceHistory bankCardBalanceHistory);

    @InheritInverseConfiguration
    BankCardBalanceHistory bankCardBalanceHistoryFromHibernate(
            HibernateBankCardBalanceHistory hibernateBankCardBalanceHistory
    );

    @Mapping(target = "stringId", ignore = true)
    HibernateBankCardTypeIndicator bankCardTypeIndicatorToHibernate(BankCardTypeIndicator bankCardTypeIndicator);

    @InheritInverseConfiguration
    BankCardTypeIndicator bankCardTypeIndicatorFromHibernate(
            HibernateBankCardTypeIndicator hibernateBankCardTypeIndicator
    );

    @Mapping(target = "longId", ignore = true)
    @Mapping(target = "fundChangeLongId", ignore = true)
    @Mapping(target = "fundChange", ignore = true)
    HibernateBillFileInfo billFileInfoToHibernate(BillFileInfo billFileInfo);

    @InheritInverseConfiguration
    BillFileInfo billFileInfoFromHibernate(HibernateBillFileInfo hibernateBillFileInfo);

    @Mapping(target = "longId", ignore = true)
    @Mapping(target = "billFileInfos", ignore = true)
    @Mapping(target = "accountBookLongId", ignore = true)
    @Mapping(target = "accountBook", ignore = true)
    HibernateFundChange fundChangeToHibernate(FundChange fundChange);

    @InheritInverseConfiguration
    FundChange fundChangeFromHibernate(HibernateFundChange hibernateFundChange);

    @Mapping(target = "stringId", ignore = true)
    HibernateFundChangeTypeIndicator fundChangeTypeIndicatorToHibernate(
            FundChangeTypeIndicator fundChangeTypeIndicator
    );

    @InheritInverseConfiguration
    FundChangeTypeIndicator fundChangeTypeIndicatorFromHibernate(
            HibernateFundChangeTypeIndicator hibernateFundChangeTypeIndicator
    );

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "stringId", ignore = true)
    @Mapping(target = "longId", ignore = true)
    @Mapping(target = "accountBook", ignore = true)
    HibernatePoab poabToHibernate(Poab poab);

    @InheritInverseConfiguration
    Poab poabFromHibernate(HibernatePoab hibernatePoab);

    @Mapping(target = "longId", ignore = true)
    @Mapping(target = "accountBookLongId", ignore = true)
    @Mapping(target = "accountBook", ignore = true)
    HibernateRemindDriverInfo remindDriverInfoToHibernate(RemindDriverInfo remindDriverInfo);

    @InheritInverseConfiguration
    RemindDriverInfo remindDriverInfoFromHibernate(HibernateRemindDriverInfo hibernateRemindDriverInfo);

    @Mapping(target = "stringId", ignore = true)
    HibernateRemindDriverSupport remindDriverSupportToHibernate(RemindDriverSupport remindDriverSupport);

    @InheritInverseConfiguration
    RemindDriverSupport remindDriverSupportFromHibernate(HibernateRemindDriverSupport hibernateRemindDriverSupport);

    @Mapping(target = "longId", ignore = true)
    @Mapping(target = "accountBookLongId", ignore = true)
    @Mapping(target = "accountBook", ignore = true)
    HibernateTotalBalanceHistory totalBalanceHistoryToHibernate(TotalBalanceHistory totalBalanceHistory);

    @InheritInverseConfiguration
    TotalBalanceHistory totalBalanceHistoryFromHibernate(HibernateTotalBalanceHistory hibernateTotalBalanceHistory);

    @Mapping(target = "stringId", ignore = true)
    @Mapping(target = "poabs", ignore = true)
    HibernateUser userToHibernate(User user);

    @InheritInverseConfiguration
    User userFromHibernate(HibernateUser hibernateUser);
}
