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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class CacheConfiguration {

    @Autowired
    private RedisTemplate<String, ?> template;
    @Autowired
    private Mapper mapper;

    @Value("${cache.prefix.entity.account_book}")
    private String accountBookPrefix;
    @Value("${cache.prefix.entity.balance}")
    private String balancePrefix;
    @Value("${cache.prefix.entity.balance_item}")
    private String balanceItemPrefix;
    @Value("${cache.prefix.entity.fund_change}")
    private String fundChangePrefix;
    @Value("${cache.prefix.entity.fund_change_type_indicator}")
    private String fundChangeTypeIndicatorPrefix;
    @Value("${cache.prefix.entity.fund_repository}")
    private String fundRepositoryPrefix;

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
    public RedisBatchBaseCache<LongIdKey, Balance, FastJsonBalance> balanceRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonBalance>) template,
                new LongIdStringKeyFormatter(balancePrefix),
                new DozerBeanTransformer<>(Balance.class, FastJsonBalance.class, mapper)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, BalanceItem, FastJsonBalanceItem> balanceItemRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonBalanceItem>) template,
                new LongIdStringKeyFormatter(balanceItemPrefix),
                new DozerBeanTransformer<>(BalanceItem.class, FastJsonBalanceItem.class, mapper)
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
    public RedisBatchBaseCache<LongIdKey, FundRepository, FastJsonFundRepository> fundRepositoryRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonFundRepository>) template,
                new LongIdStringKeyFormatter(fundRepositoryPrefix),
                new DozerBeanTransformer<>(FundRepository.class, FastJsonFundRepository.class, mapper)
        );
    }
}
