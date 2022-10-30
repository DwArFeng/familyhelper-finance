package com.dwarfeng.familyhelper.finance.node.configuration;

import com.dwarfeng.familyhelper.finance.impl.bean.entity.*;
import com.dwarfeng.familyhelper.finance.impl.bean.key.HibernatePoabKey;
import com.dwarfeng.familyhelper.finance.impl.dao.preset.*;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.*;
import com.dwarfeng.familyhelper.finance.stack.bean.key.PoabKey;
import com.dwarfeng.subgrade.impl.bean.DozerBeanTransformer;
import com.dwarfeng.subgrade.impl.dao.HibernateBatchBaseDao;
import com.dwarfeng.subgrade.impl.dao.HibernateEntireLookupDao;
import com.dwarfeng.subgrade.impl.dao.HibernatePresetLookupDao;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateStringIdKey;
import com.dwarfeng.subgrade.sdk.hibernate.modification.DefaultDeletionMod;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTemplate;

@Configuration
public class DaoConfiguration {

    private final HibernateTemplate template;
    private final Mapper mapper;

    private final AccountBookPresetCriteriaMaker accountBookPresetCriteriaMaker;
    private final BankCardPresetCriteriaMaker bankCardPresetCriteriaMaker;
    private final FundChangePresetCriteriaMaker fundChangePresetCriteriaMaker;
    private final PoabPresetCriteriaMaker poabPresetCriteriaMaker;
    private final TotalBalanceHistoryPresetCriteriaMaker totalBalanceHistoryPresetCriteriaMaker;
    private final BankCardBalanceHistoryPresetCriteriaMaker bankCardBalanceHistoryPresetCriteriaMaker;
    private final BillFileInfoPresetCriteriaMaker billFileInfoPresetCriteriaMaker;
    private final RemindDriverInfoPresetCriteriaMaker remindDriverInfoPresetCriteriaMaker;
    private final RemindDriverSupportPresetCriteriaMaker remindDriverSupportPresetCriteriaMaker;

    @Value("${hibernate.jdbc.batch_size}")
    private int batchSize;

    public DaoConfiguration(
            HibernateTemplate template, Mapper mapper, AccountBookPresetCriteriaMaker accountBookPresetCriteriaMaker,
            BankCardPresetCriteriaMaker bankCardPresetCriteriaMaker,
            FundChangePresetCriteriaMaker fundChangePresetCriteriaMaker,
            PoabPresetCriteriaMaker poabPresetCriteriaMaker,
            TotalBalanceHistoryPresetCriteriaMaker totalBalanceHistoryPresetCriteriaMaker,
            BankCardBalanceHistoryPresetCriteriaMaker bankCardBalanceHistoryPresetCriteriaMaker,
            BillFileInfoPresetCriteriaMaker billFileInfoPresetCriteriaMaker,
            RemindDriverInfoPresetCriteriaMaker remindDriverInfoPresetCriteriaMaker,
            RemindDriverSupportPresetCriteriaMaker remindDriverSupportPresetCriteriaMaker
    ) {
        this.template = template;
        this.mapper = mapper;
        this.accountBookPresetCriteriaMaker = accountBookPresetCriteriaMaker;
        this.bankCardPresetCriteriaMaker = bankCardPresetCriteriaMaker;
        this.fundChangePresetCriteriaMaker = fundChangePresetCriteriaMaker;
        this.poabPresetCriteriaMaker = poabPresetCriteriaMaker;
        this.totalBalanceHistoryPresetCriteriaMaker = totalBalanceHistoryPresetCriteriaMaker;
        this.bankCardBalanceHistoryPresetCriteriaMaker = bankCardBalanceHistoryPresetCriteriaMaker;
        this.billFileInfoPresetCriteriaMaker = billFileInfoPresetCriteriaMaker;
        this.remindDriverInfoPresetCriteriaMaker = remindDriverInfoPresetCriteriaMaker;
        this.remindDriverSupportPresetCriteriaMaker = remindDriverSupportPresetCriteriaMaker;
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, AccountBook, HibernateAccountBook>
    accountBookHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new DozerBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, mapper),
                new DozerBeanTransformer<>(AccountBook.class, HibernateAccountBook.class, mapper),
                HibernateAccountBook.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<AccountBook, HibernateAccountBook> accountBookHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new DozerBeanTransformer<>(AccountBook.class, HibernateAccountBook.class, mapper),
                HibernateAccountBook.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<AccountBook, HibernateAccountBook> accountBookHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new DozerBeanTransformer<>(AccountBook.class, HibernateAccountBook.class, mapper),
                HibernateAccountBook.class,
                accountBookPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, BankCard, HibernateBankCard>
    bankCardHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new DozerBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, mapper),
                new DozerBeanTransformer<>(BankCard.class, HibernateBankCard.class, mapper),
                HibernateBankCard.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<BankCard, HibernateBankCard> bankCardHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new DozerBeanTransformer<>(BankCard.class, HibernateBankCard.class, mapper),
                HibernateBankCard.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<BankCard, HibernateBankCard> bankCardHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new DozerBeanTransformer<>(BankCard.class, HibernateBankCard.class, mapper),
                HibernateBankCard.class,
                bankCardPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, BankCardTypeIndicator,
            HibernateBankCardTypeIndicator> bankCardTypeIndicatorHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new DozerBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, mapper),
                new DozerBeanTransformer<>(
                        BankCardTypeIndicator.class, HibernateBankCardTypeIndicator.class, mapper
                ),
                HibernateBankCardTypeIndicator.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<BankCardTypeIndicator, HibernateBankCardTypeIndicator>
    bankCardTypeIndicatorHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new DozerBeanTransformer<>(
                        BankCardTypeIndicator.class, HibernateBankCardTypeIndicator.class, mapper
                ),
                HibernateBankCardTypeIndicator.class
        );
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, FundChange, HibernateFundChange>
    fundChangeHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new DozerBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, mapper),
                new DozerBeanTransformer<>(FundChange.class, HibernateFundChange.class, mapper),
                HibernateFundChange.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<FundChange, HibernateFundChange> fundChangeHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new DozerBeanTransformer<>(FundChange.class, HibernateFundChange.class, mapper),
                HibernateFundChange.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<FundChange, HibernateFundChange> fundChangeHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new DozerBeanTransformer<>(FundChange.class, HibernateFundChange.class, mapper),
                HibernateFundChange.class,
                fundChangePresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, FundChangeTypeIndicator,
            HibernateFundChangeTypeIndicator> fundChangeTypeIndicatorHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new DozerBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, mapper),
                new DozerBeanTransformer<>(
                        FundChangeTypeIndicator.class, HibernateFundChangeTypeIndicator.class, mapper
                ),
                HibernateFundChangeTypeIndicator.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<FundChangeTypeIndicator, HibernateFundChangeTypeIndicator>
    fundChangeTypeIndicatorHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new DozerBeanTransformer<>(
                        FundChangeTypeIndicator.class, HibernateFundChangeTypeIndicator.class, mapper
                ),
                HibernateFundChangeTypeIndicator.class
        );
    }

    @Bean
    public HibernateBatchBaseDao<PoabKey, HibernatePoabKey, Poab, HibernatePoab> poabHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new DozerBeanTransformer<>(PoabKey.class, HibernatePoabKey.class, mapper),
                new DozerBeanTransformer<>(Poab.class, HibernatePoab.class, mapper),
                HibernatePoab.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<Poab, HibernatePoab> poabHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new DozerBeanTransformer<>(Poab.class, HibernatePoab.class, mapper),
                HibernatePoab.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<Poab, HibernatePoab> poabHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new DozerBeanTransformer<>(Poab.class, HibernatePoab.class, mapper),
                HibernatePoab.class,
                poabPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, User, HibernateUser> userHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new DozerBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, mapper),
                new DozerBeanTransformer<>(User.class, HibernateUser.class, mapper),
                HibernateUser.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, TotalBalanceHistory, HibernateTotalBalanceHistory>
    totalBalanceHistoryHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new DozerBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, mapper),
                new DozerBeanTransformer<>(TotalBalanceHistory.class, HibernateTotalBalanceHistory.class, mapper),
                HibernateTotalBalanceHistory.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<TotalBalanceHistory, HibernateTotalBalanceHistory>
    totalBalanceHistoryHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new DozerBeanTransformer<>(TotalBalanceHistory.class, HibernateTotalBalanceHistory.class, mapper),
                HibernateTotalBalanceHistory.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<TotalBalanceHistory, HibernateTotalBalanceHistory>
    totalBalanceHistoryHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new DozerBeanTransformer<>(TotalBalanceHistory.class, HibernateTotalBalanceHistory.class, mapper),
                HibernateTotalBalanceHistory.class,
                totalBalanceHistoryPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, BankCardBalanceHistory, HibernateBankCardBalanceHistory>
    bankCardBalanceHistoryHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new DozerBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, mapper),
                new DozerBeanTransformer<>(BankCardBalanceHistory.class, HibernateBankCardBalanceHistory.class, mapper),
                HibernateBankCardBalanceHistory.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<BankCardBalanceHistory, HibernateBankCardBalanceHistory>
    bankCardBalanceHistoryHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new DozerBeanTransformer<>(BankCardBalanceHistory.class, HibernateBankCardBalanceHistory.class, mapper),
                HibernateBankCardBalanceHistory.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<BankCardBalanceHistory, HibernateBankCardBalanceHistory>
    bankCardBalanceHistoryHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new DozerBeanTransformer<>(BankCardBalanceHistory.class, HibernateBankCardBalanceHistory.class, mapper),
                HibernateBankCardBalanceHistory.class,
                bankCardBalanceHistoryPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, BillFileInfo, HibernateBillFileInfo>
    billFileInfoHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new DozerBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, mapper),
                new DozerBeanTransformer<>(BillFileInfo.class, HibernateBillFileInfo.class, mapper),
                HibernateBillFileInfo.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<BillFileInfo, HibernateBillFileInfo> billFileInfoHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new DozerBeanTransformer<>(BillFileInfo.class, HibernateBillFileInfo.class, mapper),
                HibernateBillFileInfo.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<BillFileInfo, HibernateBillFileInfo> billFileInfoHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new DozerBeanTransformer<>(BillFileInfo.class, HibernateBillFileInfo.class, mapper),
                HibernateBillFileInfo.class,
                billFileInfoPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, RemindDriverInfo, HibernateRemindDriverInfo>
    remindDriverInfoHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new DozerBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, mapper),
                new DozerBeanTransformer<>(RemindDriverInfo.class, HibernateRemindDriverInfo.class, mapper),
                HibernateRemindDriverInfo.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<RemindDriverInfo, HibernateRemindDriverInfo>
    remindDriverInfoHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new DozerBeanTransformer<>(RemindDriverInfo.class, HibernateRemindDriverInfo.class, mapper),
                HibernateRemindDriverInfo.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<RemindDriverInfo, HibernateRemindDriverInfo>
    remindDriverInfoHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new DozerBeanTransformer<>(RemindDriverInfo.class, HibernateRemindDriverInfo.class, mapper),
                HibernateRemindDriverInfo.class,
                remindDriverInfoPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, RemindDriverSupport, HibernateRemindDriverSupport>
    remindDriverSupportHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new DozerBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, mapper),
                new DozerBeanTransformer<>(RemindDriverSupport.class, HibernateRemindDriverSupport.class, mapper),
                HibernateRemindDriverSupport.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<RemindDriverSupport, HibernateRemindDriverSupport>
    remindDriverSupportHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new DozerBeanTransformer<>(RemindDriverSupport.class, HibernateRemindDriverSupport.class, mapper),
                HibernateRemindDriverSupport.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<RemindDriverSupport, HibernateRemindDriverSupport>
    remindDriverSupportHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new DozerBeanTransformer<>(RemindDriverSupport.class, HibernateRemindDriverSupport.class, mapper),
                HibernateRemindDriverSupport.class,
                remindDriverSupportPresetCriteriaMaker
        );
    }
}
