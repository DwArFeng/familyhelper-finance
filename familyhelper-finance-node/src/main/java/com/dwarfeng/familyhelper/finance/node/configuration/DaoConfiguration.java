package com.dwarfeng.familyhelper.finance.node.configuration;

import com.dwarfeng.familyhelper.finance.impl.bean.entity.*;
import com.dwarfeng.familyhelper.finance.impl.dao.preset.*;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.orm.hibernate5.HibernateTemplate;

@Configuration
public class DaoConfiguration {

    @Autowired
    private HibernateTemplate hibernateTemplate;
    @Autowired
    private RedisTemplate<String, ?> redisTemplate;
    @Autowired
    private Mapper mapper;

    @Autowired
    private AccountBookPresetCriteriaMaker accountBookPresetCriteriaMaker;
    @Autowired
    private BalancePresetCriteriaMaker balancePresetCriteriaMaker;
    @Autowired
    private BalanceItemPresetCriteriaMaker balanceItemPresetCriteriaMaker;
    @Autowired
    private FundChangePresetCriteriaMaker fundChangePresetCriteriaMaker;
    @Autowired
    private FundRepositoryPresetCriteriaMaker fundRepositoryPresetCriteriaMaker;

    @Value("${hibernate.jdbc.batch_size}")
    private int batchSize;

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, AccountBook, HibernateAccountBook>
    accountBookHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
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
                hibernateTemplate,
                new DozerBeanTransformer<>(AccountBook.class, HibernateAccountBook.class, mapper),
                HibernateAccountBook.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<AccountBook, HibernateAccountBook> accountBookHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(AccountBook.class, HibernateAccountBook.class, mapper),
                HibernateAccountBook.class,
                accountBookPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, Balance, HibernateBalance>
    balanceHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, mapper),
                new DozerBeanTransformer<>(Balance.class, HibernateBalance.class, mapper),
                HibernateBalance.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<Balance, HibernateBalance> balanceHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(Balance.class, HibernateBalance.class, mapper),
                HibernateBalance.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<Balance, HibernateBalance> balanceHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(Balance.class, HibernateBalance.class, mapper),
                HibernateBalance.class,
                balancePresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, BalanceItem, HibernateBalanceItem>
    balanceItemHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, mapper),
                new DozerBeanTransformer<>(BalanceItem.class, HibernateBalanceItem.class, mapper),
                HibernateBalanceItem.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<BalanceItem, HibernateBalanceItem> balanceItemHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(BalanceItem.class, HibernateBalanceItem.class, mapper),
                HibernateBalanceItem.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<BalanceItem, HibernateBalanceItem> balanceItemHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(BalanceItem.class, HibernateBalanceItem.class, mapper),
                HibernateBalanceItem.class,
                balanceItemPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, FundChange, HibernateFundChange>
    fundChangeHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
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
                hibernateTemplate,
                new DozerBeanTransformer<>(FundChange.class, HibernateFundChange.class, mapper),
                HibernateFundChange.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<FundChange, HibernateFundChange> fundChangeHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(FundChange.class, HibernateFundChange.class, mapper),
                HibernateFundChange.class,
                fundChangePresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, FundChangeTypeIndicator,
            HibernateFundChangeTypeIndicator> fundChangeTypeIndicatorHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
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
                hibernateTemplate,
                new DozerBeanTransformer<>(
                        FundChangeTypeIndicator.class, HibernateFundChangeTypeIndicator.class, mapper
                ),
                HibernateFundChangeTypeIndicator.class
        );
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, FundRepository, HibernateFundRepository>
    fundRepositoryHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, mapper),
                new DozerBeanTransformer<>(FundRepository.class, HibernateFundRepository.class, mapper),
                HibernateFundRepository.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<FundRepository, HibernateFundRepository> fundRepositoryHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(FundRepository.class, HibernateFundRepository.class, mapper),
                HibernateFundRepository.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<FundRepository, HibernateFundRepository> fundRepositoryHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(FundRepository.class, HibernateFundRepository.class, mapper),
                HibernateFundRepository.class,
                fundRepositoryPresetCriteriaMaker
        );
    }
}
