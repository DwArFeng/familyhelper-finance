package com.dwarfeng.familyhelper.finance.impl.configuration;

import com.dwarfeng.familyhelper.finance.sdk.bean.BeanMapper;
import com.dwarfeng.familyhelper.finance.sdk.bean.entity.*;
import com.dwarfeng.familyhelper.finance.sdk.bean.key.formatter.PoabStringKeyFormatter;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.*;
import com.dwarfeng.familyhelper.finance.stack.bean.key.PoabKey;
import com.dwarfeng.subgrade.impl.bean.MapStructBeanTransformer;
import com.dwarfeng.subgrade.impl.cache.RedisBatchBaseCache;
import com.dwarfeng.subgrade.sdk.redis.formatter.LongIdStringKeyFormatter;
import com.dwarfeng.subgrade.sdk.redis.formatter.StringIdStringKeyFormatter;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class CacheConfiguration {

    private final RedisTemplate<String, ?> template;

    @Value("${cache.prefix.entity.account_book}")
    private String accountBookPrefix;
    @Value("${cache.prefix.entity.bank_card}")
    private String bankCardPrefix;
    @Value("${cache.prefix.entity.bank_card_type_indicator}")
    private String bankCardTypeIndicatorPrefix;
    @Value("${cache.prefix.entity.fund_change}")
    private String fundChangePrefix;
    @Value("${cache.prefix.entity.fund_change_type_indicator}")
    private String fundChangeTypeIndicatorPrefix;
    @Value("${cache.prefix.entity.poab}")
    private String poabPrefix;
    @Value("${cache.prefix.entity.user}")
    private String userPrefix;
    @Value("${cache.prefix.entity.total_balance_history}")
    private String totalBalanceHistoryPrefix;
    @Value("${cache.prefix.entity.bank_card_balance_history}")
    private String bankCardBalanceHistoryPrefix;
    @Value("${cache.prefix.entity.bill_file_info}")
    private String billFileInfoPrefix;
    @Value("${cache.prefix.entity.remind_driver_info}")
    private String remindDriverInfoPrefix;
    @Value("${cache.prefix.entity.remind_driver_support}")
    private String remindDriverSupportPrefix;

    public CacheConfiguration(RedisTemplate<String, ?> template) {
        this.template = template;
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, AccountBook, FastJsonAccountBook> accountBookRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonAccountBook>) template,
                new LongIdStringKeyFormatter(accountBookPrefix),
                new MapStructBeanTransformer<>(AccountBook.class, FastJsonAccountBook.class, BeanMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, BankCard, FastJsonBankCard> bankCardRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonBankCard>) template,
                new LongIdStringKeyFormatter(bankCardPrefix),
                new MapStructBeanTransformer<>(BankCard.class, FastJsonBankCard.class, BeanMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<StringIdKey, BankCardTypeIndicator, FastJsonBankCardTypeIndicator>
    bankCardTypeIndicatorRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonBankCardTypeIndicator>) template,
                new StringIdStringKeyFormatter(bankCardTypeIndicatorPrefix),
                new MapStructBeanTransformer<>(
                        BankCardTypeIndicator.class, FastJsonBankCardTypeIndicator.class, BeanMapper.class
                )
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, FundChange, FastJsonFundChange> fundChangeRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonFundChange>) template,
                new LongIdStringKeyFormatter(fundChangePrefix),
                new MapStructBeanTransformer<>(FundChange.class, FastJsonFundChange.class, BeanMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<StringIdKey, FundChangeTypeIndicator, FastJsonFundChangeTypeIndicator>
    fundChangeTypeIndicatorRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonFundChangeTypeIndicator>) template,
                new StringIdStringKeyFormatter(fundChangeTypeIndicatorPrefix),
                new MapStructBeanTransformer<>(
                        FundChangeTypeIndicator.class, FastJsonFundChangeTypeIndicator.class, BeanMapper.class
                )
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<PoabKey, Poab, FastJsonPoab> poabRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonPoab>) template,
                new PoabStringKeyFormatter(poabPrefix),
                new MapStructBeanTransformer<>(Poab.class, FastJsonPoab.class, BeanMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<StringIdKey, User, FastJsonUser> userRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonUser>) template,
                new StringIdStringKeyFormatter(userPrefix),
                new MapStructBeanTransformer<>(User.class, FastJsonUser.class, BeanMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, TotalBalanceHistory, FastJsonTotalBalanceHistory>
    totalBalanceHistoryRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonTotalBalanceHistory>) template,
                new LongIdStringKeyFormatter(totalBalanceHistoryPrefix),
                new MapStructBeanTransformer<>(
                        TotalBalanceHistory.class, FastJsonTotalBalanceHistory.class, BeanMapper.class
                )
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, BankCardBalanceHistory, FastJsonBankCardBalanceHistory>
    bankCardBalanceHistoryRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonBankCardBalanceHistory>) template,
                new LongIdStringKeyFormatter(bankCardBalanceHistoryPrefix),
                new MapStructBeanTransformer<>(
                        BankCardBalanceHistory.class, FastJsonBankCardBalanceHistory.class, BeanMapper.class
                )
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, BillFileInfo, FastJsonBillFileInfo> billFileInfoRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonBillFileInfo>) template,
                new LongIdStringKeyFormatter(billFileInfoPrefix),
                new MapStructBeanTransformer<>(BillFileInfo.class, FastJsonBillFileInfo.class, BeanMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, RemindDriverInfo, FastJsonRemindDriverInfo>
    remindDriverInfoRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonRemindDriverInfo>) template,
                new LongIdStringKeyFormatter(remindDriverInfoPrefix),
                new MapStructBeanTransformer<>(
                        RemindDriverInfo.class, FastJsonRemindDriverInfo.class, BeanMapper.class
                )
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<StringIdKey, RemindDriverSupport, FastJsonRemindDriverSupport>
    remindDriverSupportRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonRemindDriverSupport>) template,
                new StringIdStringKeyFormatter(remindDriverSupportPrefix),
                new MapStructBeanTransformer<>(
                        RemindDriverSupport.class, FastJsonRemindDriverSupport.class, BeanMapper.class
                )
        );
    }
}
