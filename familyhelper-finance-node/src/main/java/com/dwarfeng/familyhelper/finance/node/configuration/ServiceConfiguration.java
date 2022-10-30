package com.dwarfeng.familyhelper.finance.node.configuration;

import com.dwarfeng.familyhelper.finance.impl.service.operation.*;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.*;
import com.dwarfeng.familyhelper.finance.stack.bean.key.PoabKey;
import com.dwarfeng.familyhelper.finance.stack.cache.*;
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

    private final ServiceExceptionMapperConfiguration serviceExceptionMapperConfiguration;

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
            AccountBookCrudOperation accountBookCrudOperation, AccountBookDao accountBookDao,
            BankCardCrudOperation bankCardCrudOperation, BankCardDao bankCardDao,
            BankCardTypeIndicatorDao bankCardTypeIndicatorDao, BankCardTypeIndicatorCache bankCardTypeIndicatorCache,
            FundChangeCrudOperation fundChangeCrudOperation, FundChangeDao fundChangeDao,
            FundChangeTypeIndicatorDao fundChangeTypeIndicatorDao, FundChangeTypeIndicatorCache fundChangeTypeIndicatorCache,
            PoabDao poabDao, PoabCache poabCache,
            UserCrudOperation userCrudOperation,
            TotalBalanceHistoryDao totalBalanceHistoryDao, TotalBalanceHistoryCache totalBalanceHistoryCache,
            BankCardBalanceHistoryDao bankCardBalanceHistoryDao, BankCardBalanceHistoryCache bankCardBalanceHistoryCache,
            BillFileInfoCrudOperation billFileInfoCrudOperation, BillFileInfoDao billFileInfoDao,
            RemindDriverInfoDao remindDriverInfoDao, RemindDriverInfoCache remindDriverInfoCache,
            RemindDriverSupportDao remindDriverSupportDao, RemindDriverSupportCache remindDriverSupportCache
    ) {
        this.serviceExceptionMapperConfiguration = serviceExceptionMapperConfiguration;
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
    public KeyFetcher<LongIdKey> longIdKeyKeyFetcher() {
        return new SnowFlakeLongIdKeyFetcher();
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
    public CustomBatchCrudService<LongIdKey, FundChange> fundChangeCustomBatchCrudService() {
        return new CustomBatchCrudService<>(
                fundChangeCrudOperation,
                longIdKeyKeyFetcher(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
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
    public GeneralBatchCrudService<LongIdKey, TotalBalanceHistory> totalBalanceHistoryGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                totalBalanceHistoryDao,
                totalBalanceHistoryCache,
                longIdKeyKeyFetcher(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                totalBalanceHistoryTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<TotalBalanceHistory> totalBalanceHistoryDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                totalBalanceHistoryDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<TotalBalanceHistory> totalBalanceHistoryDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                totalBalanceHistoryDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public GeneralBatchCrudService<LongIdKey, BankCardBalanceHistory> bankCardBalanceHistoryGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                bankCardBalanceHistoryDao,
                bankCardBalanceHistoryCache,
                longIdKeyKeyFetcher(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                bankCardBalanceHistoryTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<BankCardBalanceHistory> bankCardBalanceHistoryDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                bankCardBalanceHistoryDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<BankCardBalanceHistory> bankCardBalanceHistoryDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                bankCardBalanceHistoryDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public CustomBatchCrudService<LongIdKey, BillFileInfo> billFileInfoCustomBatchCrudService() {
        return new CustomBatchCrudService<>(
                billFileInfoCrudOperation,
                longIdKeyKeyFetcher(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<BillFileInfo> billFileInfoDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                billFileInfoDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<BillFileInfo> billFileInfoDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                billFileInfoDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public GeneralBatchCrudService<LongIdKey, RemindDriverInfo> remindDriverInfoGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                remindDriverInfoDao,
                remindDriverInfoCache,
                longIdKeyKeyFetcher(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                remindDriverInfoTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<RemindDriverInfo> remindDriverInfoDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                remindDriverInfoDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<RemindDriverInfo> remindDriverInfoDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                remindDriverInfoDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public GeneralBatchCrudService<StringIdKey, RemindDriverSupport> remindDriverSupportGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                remindDriverSupportDao,
                remindDriverSupportCache,
                new ExceptionKeyFetcher<>(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                remindDriverSupportTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<RemindDriverSupport> remindDriverSupportDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                remindDriverSupportDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<RemindDriverSupport> remindDriverSupportDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                remindDriverSupportDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }
}
