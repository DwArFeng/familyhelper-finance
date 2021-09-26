package com.dwarfeng.familyhelper.finance.impl.configuration;

import com.dwarfeng.familyhelper.finance.sdk.bean.entity.*;
import com.dwarfeng.familyhelper.finance.stack.bean.entity.*;
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
}
