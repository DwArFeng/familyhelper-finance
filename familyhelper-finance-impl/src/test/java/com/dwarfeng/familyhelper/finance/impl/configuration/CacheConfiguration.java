package com.dwarfeng.familyhelper.finance.impl.configuration;

import com.dwarfeng.familyhelper.finance.sdk.bean.entity.*;
import com.dwarfeng.familyhelper.finance.sdk.bean.key.formatter.PoabStringKeyFormatter;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.*;
import com.dwarfeng.familyhelper.finance.stack.bean.key.PoabKey;
import com.dwarfeng.subgrade.impl.bean.DozerBeanTransformer;
import com.dwarfeng.subgrade.impl.cache.RedisBatchBaseCache;
import com.dwarfeng.subgrade.sdk.redis.formatter.LongIdStringKeyFormatter;
import com.dwarfeng.subgrade.sdk.redis.formatter.StringIdStringKeyFormatter;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class CacheConfiguration {

    private final RedisTemplate<String, ?> template;
    private final Mapper mapper;

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

    public CacheConfiguration(RedisTemplate<String, ?> template, Mapper mapper) {
        this.template = template;
        this.mapper = mapper;
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, AccountBook, FastJsonAccountBook> accountBookRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonAccountBook>) template,
                new LongIdStringKeyFormatter(accountBookPrefix),
                new DozerBeanTransformer<>(AccountBook.class, FastJsonAccountBook.class, mapper)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, BankCard, FastJsonBankCard> bankCardRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonBankCard>) template,
                new LongIdStringKeyFormatter(bankCardPrefix),
                new DozerBeanTransformer<>(BankCard.class, FastJsonBankCard.class, mapper)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<StringIdKey, BankCardTypeIndicator, FastJsonBankCardTypeIndicator>
    bankCardTypeIndicatorRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonBankCardTypeIndicator>) template,
                new StringIdStringKeyFormatter(bankCardTypeIndicatorPrefix),
                new DozerBeanTransformer<>(BankCardTypeIndicator.class, FastJsonBankCardTypeIndicator.class, mapper)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, FundChange, FastJsonFundChange> fundChangeRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonFundChange>) template,
                new LongIdStringKeyFormatter(fundChangePrefix),
                new DozerBeanTransformer<>(FundChange.class, FastJsonFundChange.class, mapper)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<StringIdKey, FundChangeTypeIndicator, FastJsonFundChangeTypeIndicator>
    fundChangeTypeIndicatorRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonFundChangeTypeIndicator>) template,
                new StringIdStringKeyFormatter(fundChangeTypeIndicatorPrefix),
                new DozerBeanTransformer<>(FundChangeTypeIndicator.class, FastJsonFundChangeTypeIndicator.class, mapper)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<PoabKey, Poab, FastJsonPoab> poabRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonPoab>) template,
                new PoabStringKeyFormatter(poabPrefix),
                new DozerBeanTransformer<>(Poab.class, FastJsonPoab.class, mapper)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<StringIdKey, User, FastJsonUser> userRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonUser>) template,
                new StringIdStringKeyFormatter(userPrefix),
                new DozerBeanTransformer<>(User.class, FastJsonUser.class, mapper)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, TotalBalanceHistory, FastJsonTotalBalanceHistory>
    totalBalanceHistoryRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonTotalBalanceHistory>) template,
                new LongIdStringKeyFormatter(totalBalanceHistoryPrefix),
                new DozerBeanTransformer<>(TotalBalanceHistory.class, FastJsonTotalBalanceHistory.class, mapper)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, BankCardBalanceHistory, FastJsonBankCardBalanceHistory>
    bankCardBalanceHistoryRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonBankCardBalanceHistory>) template,
                new LongIdStringKeyFormatter(bankCardBalanceHistoryPrefix),
                new DozerBeanTransformer<>(BankCardBalanceHistory.class, FastJsonBankCardBalanceHistory.class, mapper)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, BillFileInfo, FastJsonBillFileInfo> billFileInfoRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonBillFileInfo>) template,
                new LongIdStringKeyFormatter(billFileInfoPrefix),
                new DozerBeanTransformer<>(BillFileInfo.class, FastJsonBillFileInfo.class, mapper)
        );
    }
}
