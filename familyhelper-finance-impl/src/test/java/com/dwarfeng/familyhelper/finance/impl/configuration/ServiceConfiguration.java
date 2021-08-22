package com.dwarfeng.familyhelper.finance.impl.configuration;

import com.dwarfeng.familyhelper.finance.impl.service.operation.AccountBookCrudOperation;
import com.dwarfeng.familyhelper.finance.impl.service.operation.BalanceCrudOperation;
import com.dwarfeng.familyhelper.finance.impl.service.operation.FundRepositoryCrudOperation;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.*;
import com.dwarfeng.familyhelper.finance.stack.cache.BalanceItemCache;
import com.dwarfeng.familyhelper.finance.stack.cache.FundChangeCache;
import com.dwarfeng.familyhelper.finance.stack.cache.FundChangeTypeIndicatorCache;
import com.dwarfeng.familyhelper.finance.stack.dao.*;
import com.dwarfeng.sfds.api.integration.subgrade.SnowFlakeLongIdKeyFetcher;
import com.dwarfeng.subgrade.impl.bean.key.ExceptionKeyFetcher;
import com.dwarfeng.subgrade.impl.service.CustomBatchCrudService;
import com.dwarfeng.subgrade.impl.service.DaoOnlyEntireLookupService;
import com.dwarfeng.subgrade.impl.service.DaoOnlyPresetLookupService;
import com.dwarfeng.subgrade.impl.service.GeneralBatchCrudService;
import com.dwarfeng.subgrade.stack.bean.key.KeyFetcher;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    @Autowired
    private ServiceExceptionMapperConfiguration serviceExceptionMapperConfiguration;

    @Bean
    public KeyFetcher<LongIdKey> longIdKeyKeyFetcher() {
        return new SnowFlakeLongIdKeyFetcher();
    }

    @Autowired
    private AccountBookCrudOperation accountBookCrudOperation;
    @Autowired
    private AccountBookDao accountBookDao;
    @Autowired
    private BalanceCrudOperation balanceCrudOperation;
    @Autowired
    private BalanceDao balanceDao;
    @Autowired
    private BalanceItemDao balanceItemDao;
    @Autowired
    private BalanceItemCache balanceItemCache;
    @Autowired
    private FundChangeDao fundChangeDao;
    @Autowired
    private FundChangeCache fundChangeCache;
    @Autowired
    private FundChangeTypeIndicatorDao fundChangeTypeIndicatorDao;
    @Autowired
    private FundChangeTypeIndicatorCache fundChangeTypeIndicatorCache;
    @Autowired
    private FundRepositoryCrudOperation fundRepositoryCrudOperation;
    @Autowired
    private FundRepositoryDao fundRepositoryDao;

    @Value("${cache.timeout.entity.balance_item}")
    private long balanceItemTimeout;
    @Value("${cache.timeout.entity.fund_change}")
    private long fundChangeTimeout;
    @Value("${cache.timeout.entity.fund_change_type_indicator}")
    private long fundChangeTypeIndicatorTimeout;

    @Bean
    public CustomBatchCrudService<LongIdKey, AccountBook> accountBookBatchCustomCrudService() {
        return new CustomBatchCrudService<>(
                accountBookCrudOperation,
                longIdKeyKeyFetcher(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<AccountBook> accountBookDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                accountBookDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<AccountBook> accountBookDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                accountBookDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public CustomBatchCrudService<LongIdKey, Balance> balanceBatchCustomCrudService() {
        return new CustomBatchCrudService<>(
                balanceCrudOperation,
                longIdKeyKeyFetcher(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<Balance> balanceDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                balanceDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<Balance> balanceDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                balanceDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public GeneralBatchCrudService<LongIdKey, BalanceItem> balanceItemGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                balanceItemDao,
                balanceItemCache,
                longIdKeyKeyFetcher(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                balanceItemTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<BalanceItem> balanceItemDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                balanceItemDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<BalanceItem> balanceItemDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                balanceItemDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public GeneralBatchCrudService<LongIdKey, FundChange> fundChangeGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                fundChangeDao,
                fundChangeCache,
                longIdKeyKeyFetcher(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                fundChangeTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<FundChange> fundChangeDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                fundChangeDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<FundChange> fundChangeDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                fundChangeDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public GeneralBatchCrudService<StringIdKey, FundChangeTypeIndicator>
    fundChangeTypeIndicatorGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                fundChangeTypeIndicatorDao,
                fundChangeTypeIndicatorCache,
                new ExceptionKeyFetcher<>(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                fundChangeTypeIndicatorTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<FundChangeTypeIndicator> fundChangeTypeIndicatorDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                fundChangeTypeIndicatorDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public CustomBatchCrudService<LongIdKey, FundRepository> fundRepositoryBatchCustomCrudService() {
        return new CustomBatchCrudService<>(
                fundRepositoryCrudOperation,
                longIdKeyKeyFetcher(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<FundRepository> fundRepositoryDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                fundRepositoryDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<FundRepository> fundRepositoryDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                fundRepositoryDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }
}
