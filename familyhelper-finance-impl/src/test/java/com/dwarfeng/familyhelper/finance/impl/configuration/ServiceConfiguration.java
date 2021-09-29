package com.dwarfeng.familyhelper.finance.impl.configuration;

import com.dwarfeng.familyhelper.finance.impl.service.operation.AccountBookCrudOperation;
import com.dwarfeng.familyhelper.finance.impl.service.operation.BankCardCrudOperation;
import com.dwarfeng.familyhelper.finance.impl.service.operation.UserCrudOperation;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.*;
import com.dwarfeng.familyhelper.finance.stack.bean.key.PoabKey;
import com.dwarfeng.familyhelper.finance.stack.cache.BankCardTypeIndicatorCache;
import com.dwarfeng.familyhelper.finance.stack.cache.FundChangeCache;
import com.dwarfeng.familyhelper.finance.stack.cache.FundChangeTypeIndicatorCache;
import com.dwarfeng.familyhelper.finance.stack.cache.PoabCache;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    @Bean
    public KeyFetcher<LongIdKey> longIdKeyKeyFetcher() {
        return new SnowFlakeLongIdKeyFetcher();
    }

    private final ServiceExceptionMapperConfiguration serviceExceptionMapperConfiguration;

    private final AccountBookCrudOperation accountBookCrudOperation;
    private final AccountBookDao accountBookDao;
    private final BankCardCrudOperation bankCardCrudOperation;
    private final BankCardDao bankCardDao;
    private final BankCardTypeIndicatorDao bankCardTypeIndicatorDao;
    private final BankCardTypeIndicatorCache bankCardTypeIndicatorCache;
    private final FundChangeDao fundChangeDao;
    private final FundChangeCache fundChangeCache;
    private final FundChangeTypeIndicatorDao fundChangeTypeIndicatorDao;
    private final FundChangeTypeIndicatorCache fundChangeTypeIndicatorCache;
    private final PoabDao poabDao;
    private final PoabCache poabCache;
    private final UserCrudOperation userCrudOperation;
    private final UserDao userDao;

    @Value("${cache.timeout.entity.bank_card_type_indicator}")
    private long bankCardTypeIndicatorTimeout;
    @Value("${cache.timeout.entity.fund_change}")
    private long fundChangeTimeout;
    @Value("${cache.timeout.entity.fund_change_type_indicator}")
    private long fundChangeTypeIndicatorTimeout;
    @Value("${cache.timeout.entity.poab}")
    private long poabTimeout;

    public ServiceConfiguration(
            ServiceExceptionMapperConfiguration serviceExceptionMapperConfiguration,
            AccountBookCrudOperation accountBookCrudOperation, AccountBookDao accountBookDao,
            BankCardCrudOperation bankCardCrudOperation, BankCardDao bankCardDao,
            BankCardTypeIndicatorDao bankCardTypeIndicatorDao, BankCardTypeIndicatorCache bankCardTypeIndicatorCache,
            FundChangeDao fundChangeDao, FundChangeCache fundChangeCache,
            FundChangeTypeIndicatorDao fundChangeTypeIndicatorDao,
            FundChangeTypeIndicatorCache fundChangeTypeIndicatorCache, PoabDao poabDao, PoabCache poabCache,
            UserCrudOperation userCrudOperation, UserDao userDao
    ) {
        this.serviceExceptionMapperConfiguration = serviceExceptionMapperConfiguration;
        this.accountBookCrudOperation = accountBookCrudOperation;
        this.accountBookDao = accountBookDao;
        this.bankCardCrudOperation = bankCardCrudOperation;
        this.bankCardDao = bankCardDao;
        this.bankCardTypeIndicatorDao = bankCardTypeIndicatorDao;
        this.bankCardTypeIndicatorCache = bankCardTypeIndicatorCache;
        this.fundChangeDao = fundChangeDao;
        this.fundChangeCache = fundChangeCache;
        this.fundChangeTypeIndicatorDao = fundChangeTypeIndicatorDao;
        this.fundChangeTypeIndicatorCache = fundChangeTypeIndicatorCache;
        this.poabDao = poabDao;
        this.poabCache = poabCache;
        this.userCrudOperation = userCrudOperation;
        this.userDao = userDao;
    }

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
    public CustomBatchCrudService<LongIdKey, BankCard> bankCardBatchCustomCrudService() {
        return new CustomBatchCrudService<>(
                bankCardCrudOperation,
                longIdKeyKeyFetcher(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<BankCard> bankCardDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                bankCardDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<BankCard> bankCardDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                bankCardDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public GeneralBatchCrudService<StringIdKey, BankCardTypeIndicator>
    bankCardTypeIndicatorGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                bankCardTypeIndicatorDao,
                bankCardTypeIndicatorCache,
                new ExceptionKeyFetcher<>(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                bankCardTypeIndicatorTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<BankCardTypeIndicator> bankCardTypeIndicatorDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                bankCardTypeIndicatorDao,
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
    public GeneralBatchCrudService<PoabKey, Poab> poabGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                poabDao,
                poabCache,
                new ExceptionKeyFetcher<>(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                poabTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<Poab> poabDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                poabDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<Poab> poabDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                poabDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }


    @Bean
    public CustomBatchCrudService<StringIdKey, User> userBatchCustomCrudService() {
        return new CustomBatchCrudService<>(
                userCrudOperation,
                new ExceptionKeyFetcher<>(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<User> userDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                userDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }
}
