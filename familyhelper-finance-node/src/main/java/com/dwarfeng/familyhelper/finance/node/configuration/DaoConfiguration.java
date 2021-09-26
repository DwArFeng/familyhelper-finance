package com.dwarfeng.familyhelper.finance.node.configuration;

import com.dwarfeng.familyhelper.finance.impl.bean.entity.*;
import com.dwarfeng.familyhelper.finance.impl.dao.preset.AccountBookPresetCriteriaMaker;
import com.dwarfeng.familyhelper.finance.impl.dao.preset.BankCardPresetCriteriaMaker;
import com.dwarfeng.familyhelper.finance.impl.dao.preset.FundChangePresetCriteriaMaker;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.*;
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

    @Value("${hibernate.jdbc.batch_size}")
    private int batchSize;

    public DaoConfiguration(
            HibernateTemplate template, Mapper mapper,
            AccountBookPresetCriteriaMaker accountBookPresetCriteriaMaker,
            BankCardPresetCriteriaMaker bankCardPresetCriteriaMaker,
            FundChangePresetCriteriaMaker fundChangePresetCriteriaMaker
    ) {
        this.template = template;
        this.mapper = mapper;
        this.accountBookPresetCriteriaMaker = accountBookPresetCriteriaMaker;
        this.bankCardPresetCriteriaMaker = bankCardPresetCriteriaMaker;
        this.fundChangePresetCriteriaMaker = fundChangePresetCriteriaMaker;
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
}
