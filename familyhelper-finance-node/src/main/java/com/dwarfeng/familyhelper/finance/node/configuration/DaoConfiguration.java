package com.dwarfeng.familyhelper.finance.node.configuration;

import com.dwarfeng.familyhelper.finance.impl.bean.entity.*;
import com.dwarfeng.familyhelper.finance.impl.bean.key.HibernatePoabKey;
import com.dwarfeng.familyhelper.finance.impl.dao.preset.*;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.*;
import com.dwarfeng.familyhelper.finance.stack.bean.key.PoabKey;
import com.dwarfeng.subgrade.impl.bean.MapStructBeanTransformer;
import com.dwarfeng.subgrade.impl.dao.HibernateBatchBaseDao;
import com.dwarfeng.subgrade.impl.dao.HibernateEntireLookupDao;
import com.dwarfeng.subgrade.impl.dao.HibernatePresetLookupDao;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateStringIdKey;
import com.dwarfeng.subgrade.sdk.hibernate.modification.DefaultDeletionMod;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTemplate;

@Configuration
public class DaoConfiguration {

    private final HibernateTemplate template;

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
            HibernateTemplate template, AccountBookPresetCriteriaMaker accountBookPresetCriteriaMaker,
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
                new MapStructBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(AccountBook.class, HibernateAccountBook.class, HibernateMapper.class),
                HibernateAccountBook.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<AccountBook, HibernateAccountBook> accountBookHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new MapStructBeanTransformer<>(AccountBook.class, HibernateAccountBook.class, HibernateMapper.class),
                HibernateAccountBook.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<AccountBook, HibernateAccountBook> accountBookHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new MapStructBeanTransformer<>(AccountBook.class, HibernateAccountBook.class, HibernateMapper.class),
                HibernateAccountBook.class,
                accountBookPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, BankCard, HibernateBankCard>
    bankCardHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new MapStructBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(BankCard.class, HibernateBankCard.class, HibernateMapper.class),
                HibernateBankCard.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<BankCard, HibernateBankCard> bankCardHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new MapStructBeanTransformer<>(BankCard.class, HibernateBankCard.class, HibernateMapper.class),
                HibernateBankCard.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<BankCard, HibernateBankCard> bankCardHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new MapStructBeanTransformer<>(BankCard.class, HibernateBankCard.class, HibernateMapper.class),
                HibernateBankCard.class,
                bankCardPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, BankCardTypeIndicator,
            HibernateBankCardTypeIndicator> bankCardTypeIndicatorHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new MapStructBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(
                        BankCardTypeIndicator.class, HibernateBankCardTypeIndicator.class, HibernateMapper.class
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
                new MapStructBeanTransformer<>(
                        BankCardTypeIndicator.class, HibernateBankCardTypeIndicator.class, HibernateMapper.class
                ),
                HibernateBankCardTypeIndicator.class
        );
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, FundChange, HibernateFundChange>
    fundChangeHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new MapStructBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(FundChange.class, HibernateFundChange.class, HibernateMapper.class),
                HibernateFundChange.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<FundChange, HibernateFundChange> fundChangeHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new MapStructBeanTransformer<>(FundChange.class, HibernateFundChange.class, HibernateMapper.class),
                HibernateFundChange.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<FundChange, HibernateFundChange> fundChangeHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new MapStructBeanTransformer<>(FundChange.class, HibernateFundChange.class, HibernateMapper.class),
                HibernateFundChange.class,
                fundChangePresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, FundChangeTypeIndicator,
            HibernateFundChangeTypeIndicator> fundChangeTypeIndicatorHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new MapStructBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(
                        FundChangeTypeIndicator.class, HibernateFundChangeTypeIndicator.class, HibernateMapper.class
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
                new MapStructBeanTransformer<>(
                        FundChangeTypeIndicator.class, HibernateFundChangeTypeIndicator.class, HibernateMapper.class
                ),
                HibernateFundChangeTypeIndicator.class
        );
    }

    @Bean
    public HibernateBatchBaseDao<PoabKey, HibernatePoabKey, Poab, HibernatePoab> poabHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new MapStructBeanTransformer<>(PoabKey.class, HibernatePoabKey.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(Poab.class, HibernatePoab.class, HibernateMapper.class),
                HibernatePoab.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<Poab, HibernatePoab> poabHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new MapStructBeanTransformer<>(Poab.class, HibernatePoab.class, HibernateMapper.class),
                HibernatePoab.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<Poab, HibernatePoab> poabHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new MapStructBeanTransformer<>(Poab.class, HibernatePoab.class, HibernateMapper.class),
                HibernatePoab.class,
                poabPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, User, HibernateUser> userHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new MapStructBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(User.class, HibernateUser.class, HibernateMapper.class),
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
                new MapStructBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(
                        TotalBalanceHistory.class, HibernateTotalBalanceHistory.class, HibernateMapper.class
                ),
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
                new MapStructBeanTransformer<>(
                        TotalBalanceHistory.class, HibernateTotalBalanceHistory.class, HibernateMapper.class
                ),
                HibernateTotalBalanceHistory.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<TotalBalanceHistory, HibernateTotalBalanceHistory>
    totalBalanceHistoryHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new MapStructBeanTransformer<>(
                        TotalBalanceHistory.class, HibernateTotalBalanceHistory.class, HibernateMapper.class
                ),
                HibernateTotalBalanceHistory.class,
                totalBalanceHistoryPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, BankCardBalanceHistory, HibernateBankCardBalanceHistory>
    bankCardBalanceHistoryHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new MapStructBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(
                        BankCardBalanceHistory.class, HibernateBankCardBalanceHistory.class, HibernateMapper.class
                ),
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
                new MapStructBeanTransformer<>(
                        BankCardBalanceHistory.class, HibernateBankCardBalanceHistory.class, HibernateMapper.class
                ),
                HibernateBankCardBalanceHistory.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<BankCardBalanceHistory, HibernateBankCardBalanceHistory>
    bankCardBalanceHistoryHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new MapStructBeanTransformer<>(
                        BankCardBalanceHistory.class, HibernateBankCardBalanceHistory.class, HibernateMapper.class
                ),
                HibernateBankCardBalanceHistory.class,
                bankCardBalanceHistoryPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, BillFileInfo, HibernateBillFileInfo>
    billFileInfoHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new MapStructBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(BillFileInfo.class, HibernateBillFileInfo.class, HibernateMapper.class),
                HibernateBillFileInfo.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<BillFileInfo, HibernateBillFileInfo> billFileInfoHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new MapStructBeanTransformer<>(BillFileInfo.class, HibernateBillFileInfo.class, HibernateMapper.class),
                HibernateBillFileInfo.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<BillFileInfo, HibernateBillFileInfo> billFileInfoHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new MapStructBeanTransformer<>(BillFileInfo.class, HibernateBillFileInfo.class, HibernateMapper.class),
                HibernateBillFileInfo.class,
                billFileInfoPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, RemindDriverInfo, HibernateRemindDriverInfo>
    remindDriverInfoHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new MapStructBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(
                        RemindDriverInfo.class, HibernateRemindDriverInfo.class, HibernateMapper.class
                ),
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
                new MapStructBeanTransformer<>(
                        RemindDriverInfo.class, HibernateRemindDriverInfo.class, HibernateMapper.class
                ),
                HibernateRemindDriverInfo.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<RemindDriverInfo, HibernateRemindDriverInfo>
    remindDriverInfoHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new MapStructBeanTransformer<>(
                        RemindDriverInfo.class, HibernateRemindDriverInfo.class, HibernateMapper.class
                ),
                HibernateRemindDriverInfo.class,
                remindDriverInfoPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, RemindDriverSupport, HibernateRemindDriverSupport>
    remindDriverSupportHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new MapStructBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(
                        RemindDriverSupport.class, HibernateRemindDriverSupport.class, HibernateMapper.class
                ),
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
                new MapStructBeanTransformer<>(
                        RemindDriverSupport.class, HibernateRemindDriverSupport.class, HibernateMapper.class
                ),
                HibernateRemindDriverSupport.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<RemindDriverSupport, HibernateRemindDriverSupport>
    remindDriverSupportHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new MapStructBeanTransformer<>(
                        RemindDriverSupport.class, HibernateRemindDriverSupport.class, HibernateMapper.class
                ),
                HibernateRemindDriverSupport.class,
                remindDriverSupportPresetCriteriaMaker
        );
    }
}
