package com.dwarfeng.familyhelper.finance.impl.configuration;

import com.dwarfeng.familyhelper.finance.impl.service.operation.*;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.*;
import com.dwarfeng.familyhelper.finance.stack.bean.key.PoabKey;
import com.dwarfeng.familyhelper.finance.stack.cache.*;
import com.dwarfeng.familyhelper.finance.stack.dao.*;
import com.dwarfeng.subgrade.impl.generation.ExceptionKeyGenerator;
import com.dwarfeng.subgrade.impl.service.CustomBatchCrudService;
import com.dwarfeng.subgrade.impl.service.DaoOnlyEntireLookupService;
import com.dwarfeng.subgrade.impl.service.DaoOnlyPresetLookupService;
import com.dwarfeng.subgrade.impl.service.GeneralBatchCrudService;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    private final ServiceExceptionMapperConfiguration serviceExceptionMapperConfiguration;
    private final GenerateConfiguration generateConfiguration;

    private final AccountBookCrudOperation accountBookCrudOperation;
    private final AccountBookDao accountBookDao;
    private final BankCardCrudOperation bankCardCrudOperation;
    private final BankCardDao bankCardDao;
    private final BankCardTypeIndicatorDao bankCardTypeIndicatorDao;
    private final BankCardTypeIndicatorCache bankCardTypeIndicatorCache;
    private final FundChangeCrudOperation fundChangeCrudOperation;
    private final FundChangeDao fundChangeDao;
    private final FundChangeTypeIndicatorDao fundChangeTypeIndicatorDao;
    private final FundChangeTypeIndicatorCache fundChangeTypeIndicatorCache;
    private final PoabDao poabDao;
    private final PoabCache poabCache;
    private final UserCrudOperation userCrudOperation;
    private final TotalBalanceHistoryDao totalBalanceHistoryDao;
    private final TotalBalanceHistoryCache totalBalanceHistoryCache;
    private final BankCardBalanceHistoryDao bankCardBalanceHistoryDao;
    private final BankCardBalanceHistoryCache bankCardBalanceHistoryCache;
    private final BillFileInfoCrudOperation billFileInfoCrudOperation;
    private final BillFileInfoDao billFileInfoDao;
    private final RemindDriverInfoDao remindDriverInfoDao;
    private final RemindDriverInfoCache remindDriverInfoCache;
    private final RemindDriverSupportDao remindDriverSupportDao;
    private final RemindDriverSupportCache remindDriverSupportCache;

    @Value("${cache.timeout.entity.bank_card_type_indicator}")
    private long bankCardTypeIndicatorTimeout;
    @Value("${cache.timeout.entity.fund_change_type_indicator}")
    private long fundChangeTypeIndicatorTimeout;
    @Value("${cache.timeout.entity.poab}")
    private long poabTimeout;
    @Value("${cache.timeout.entity.total_balance_history}")
    private long totalBalanceHistoryTimeout;
    @Value("${cache.timeout.entity.bank_card_balance_history}")
    private long bankCardBalanceHistoryTimeout;
    @Value("${cache.timeout.entity.remind_driver_info}")
    private long remindDriverInfoTimeout;
    @Value("${cache.timeout.entity.remind_driver_support}")
    private long remindDriverSupportTimeout;

    public ServiceConfiguration(
            ServiceExceptionMapperConfiguration serviceExceptionMapperConfiguration,
            GenerateConfiguration generateConfiguration,
            AccountBookCrudOperation accountBookCrudOperation,
            AccountBookDao accountBookDao,
            BankCardCrudOperation bankCardCrudOperation,
            BankCardDao bankCardDao,
            BankCardTypeIndicatorDao bankCardTypeIndicatorDao,
            BankCardTypeIndicatorCache bankCardTypeIndicatorCache,
            FundChangeCrudOperation fundChangeCrudOperation,
            FundChangeDao fundChangeDao,
            FundChangeTypeIndicatorDao fundChangeTypeIndicatorDao,
            FundChangeTypeIndicatorCache fundChangeTypeIndicatorCache,
            PoabDao poabDao,
            PoabCache poabCache,
            UserCrudOperation userCrudOperation,
            TotalBalanceHistoryDao totalBalanceHistoryDao,
            TotalBalanceHistoryCache totalBalanceHistoryCache,
            BankCardBalanceHistoryDao bankCardBalanceHistoryDao,
            BankCardBalanceHistoryCache bankCardBalanceHistoryCache,
            BillFileInfoCrudOperation billFileInfoCrudOperation,
            BillFileInfoDao billFileInfoDao,
            RemindDriverInfoDao remindDriverInfoDao,
            RemindDriverInfoCache remindDriverInfoCache,
            RemindDriverSupportDao remindDriverSupportDao,
            RemindDriverSupportCache remindDriverSupportCache
    ) {
        this.serviceExceptionMapperConfiguration = serviceExceptionMapperConfiguration;
        this.generateConfiguration = generateConfiguration;
        this.accountBookCrudOperation = accountBookCrudOperation;
        this.accountBookDao = accountBookDao;
        this.bankCardCrudOperation = bankCardCrudOperation;
        this.bankCardDao = bankCardDao;
        this.bankCardTypeIndicatorDao = bankCardTypeIndicatorDao;
        this.bankCardTypeIndicatorCache = bankCardTypeIndicatorCache;
        this.fundChangeCrudOperation = fundChangeCrudOperation;
        this.fundChangeDao = fundChangeDao;
        this.fundChangeTypeIndicatorDao = fundChangeTypeIndicatorDao;
        this.fundChangeTypeIndicatorCache = fundChangeTypeIndicatorCache;
        this.poabDao = poabDao;
        this.poabCache = poabCache;
        this.userCrudOperation = userCrudOperation;
        this.totalBalanceHistoryDao = totalBalanceHistoryDao;
        this.totalBalanceHistoryCache = totalBalanceHistoryCache;
        this.bankCardBalanceHistoryDao = bankCardBalanceHistoryDao;
        this.bankCardBalanceHistoryCache = bankCardBalanceHistoryCache;
        this.billFileInfoCrudOperation = billFileInfoCrudOperation;
        this.billFileInfoDao = billFileInfoDao;
        this.remindDriverInfoDao = remindDriverInfoDao;
        this.remindDriverInfoCache = remindDriverInfoCache;
        this.remindDriverSupportDao = remindDriverSupportDao;
        this.remindDriverSupportCache = remindDriverSupportCache;
    }

    @Bean
    public CustomBatchCrudService<LongIdKey, AccountBook> accountBookBatchCustomCrudService() {
        return new CustomBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                accountBookCrudOperation,
                generateConfiguration.snowflakeLongIdKeyGenerator()
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<AccountBook> accountBookDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                accountBookDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<AccountBook> accountBookDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                accountBookDao
        );
    }

    @Bean
    public CustomBatchCrudService<LongIdKey, BankCard> bankCardBatchCustomCrudService() {
        return new CustomBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                bankCardCrudOperation,
                generateConfiguration.snowflakeLongIdKeyGenerator()
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<BankCard> bankCardDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                bankCardDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<BankCard> bankCardDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                bankCardDao
        );
    }

    @Bean
    public GeneralBatchCrudService<StringIdKey, BankCardTypeIndicator>
    bankCardTypeIndicatorGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                bankCardTypeIndicatorDao,
                bankCardTypeIndicatorCache,
                new ExceptionKeyGenerator<>(),
                bankCardTypeIndicatorTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<BankCardTypeIndicator> bankCardTypeIndicatorDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                bankCardTypeIndicatorDao
        );
    }

    @Bean
    public CustomBatchCrudService<LongIdKey, FundChange> fundChangeCustomBatchCrudService() {
        return new CustomBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                fundChangeCrudOperation,
                generateConfiguration.snowflakeLongIdKeyGenerator()
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<FundChange> fundChangeDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                fundChangeDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<FundChange> fundChangeDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                fundChangeDao
        );
    }

    @Bean
    public GeneralBatchCrudService<StringIdKey, FundChangeTypeIndicator>
    fundChangeTypeIndicatorGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                fundChangeTypeIndicatorDao,
                fundChangeTypeIndicatorCache,
                new ExceptionKeyGenerator<>(),
                fundChangeTypeIndicatorTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<FundChangeTypeIndicator> fundChangeTypeIndicatorDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                fundChangeTypeIndicatorDao
        );
    }

    @Bean
    public GeneralBatchCrudService<PoabKey, Poab> poabGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                poabDao,
                poabCache,
                new ExceptionKeyGenerator<>(),
                poabTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<Poab> poabDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                poabDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<Poab> poabDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                poabDao
        );
    }

    @Bean
    public CustomBatchCrudService<StringIdKey, User> userBatchCustomCrudService() {
        return new CustomBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                userCrudOperation,
                new ExceptionKeyGenerator<>()
        );
    }

    @Bean
    public GeneralBatchCrudService<LongIdKey, TotalBalanceHistory> totalBalanceHistoryGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                totalBalanceHistoryDao,
                totalBalanceHistoryCache,
                generateConfiguration.snowflakeLongIdKeyGenerator(),
                totalBalanceHistoryTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<TotalBalanceHistory> totalBalanceHistoryDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                totalBalanceHistoryDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<TotalBalanceHistory> totalBalanceHistoryDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                totalBalanceHistoryDao
        );
    }

    @Bean
    public GeneralBatchCrudService<LongIdKey, BankCardBalanceHistory> bankCardBalanceHistoryGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                bankCardBalanceHistoryDao,
                bankCardBalanceHistoryCache,
                generateConfiguration.snowflakeLongIdKeyGenerator(),
                bankCardBalanceHistoryTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<BankCardBalanceHistory> bankCardBalanceHistoryDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                bankCardBalanceHistoryDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<BankCardBalanceHistory> bankCardBalanceHistoryDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                bankCardBalanceHistoryDao
        );
    }

    @Bean
    public CustomBatchCrudService<LongIdKey, BillFileInfo> billFileInfoCustomBatchCrudService() {
        return new CustomBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                billFileInfoCrudOperation,
                generateConfiguration.snowflakeLongIdKeyGenerator()
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<BillFileInfo> billFileInfoDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                billFileInfoDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<BillFileInfo> billFileInfoDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                billFileInfoDao
        );
    }

    @Bean
    public GeneralBatchCrudService<LongIdKey, RemindDriverInfo> remindDriverInfoGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                remindDriverInfoDao,
                remindDriverInfoCache,
                generateConfiguration.snowflakeLongIdKeyGenerator(),
                remindDriverInfoTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<RemindDriverInfo> remindDriverInfoDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                remindDriverInfoDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<RemindDriverInfo> remindDriverInfoDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                remindDriverInfoDao
        );
    }

    @Bean
    public GeneralBatchCrudService<StringIdKey, RemindDriverSupport> remindDriverSupportGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                remindDriverSupportDao,
                remindDriverSupportCache,
                new ExceptionKeyGenerator<>(),
                remindDriverSupportTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<RemindDriverSupport> remindDriverSupportDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                remindDriverSupportDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<RemindDriverSupport> remindDriverSupportDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                remindDriverSupportDao
        );
    }
}
