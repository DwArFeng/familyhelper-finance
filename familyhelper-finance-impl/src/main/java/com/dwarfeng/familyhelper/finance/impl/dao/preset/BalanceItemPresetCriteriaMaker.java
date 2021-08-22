package com.dwarfeng.familyhelper.finance.impl.dao.preset;

import com.dwarfeng.familyhelper.finance.stack.service.BalanceItemMaintainService;
import com.dwarfeng.subgrade.sdk.hibernate.criteria.PresetCriteriaMaker;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class BalanceItemPresetCriteriaMaker implements PresetCriteriaMaker {

    @Override
    public void makeCriteria(DetachedCriteria detachedCriteria, String s, Object[] objects) {
        switch (s) {
            case BalanceItemMaintainService.CHILD_FOR_BALANCE:
                childForBalance(detachedCriteria, objects);
                break;
            case BalanceItemMaintainService.CHILD_FOR_FUND_REPOSITORY:
                childForFundRepository(detachedCriteria, objects);
                break;
            case BalanceItemMaintainService.CHILD_FOR_BALANCE_SET:
                childForBalanceSet(detachedCriteria, objects);
                break;
            case BalanceItemMaintainService.CHILD_FOR_FUND_REPOSITORY_SET:
                childForFundRepositorySet(detachedCriteria, objects);
                break;
            default:
                throw new IllegalArgumentException("无法识别的预设: " + s);
        }
    }

    private void childForBalance(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("balanceLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                detachedCriteria.add(Restrictions.eqOrIsNull("balanceLongId", longIdKey.getLongId()));
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void childForFundRepository(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("fundRepositoryLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("fundRepositoryLongId", longIdKey.getLongId())
                );
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void childForBalanceSet(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("balanceLongId"));
            } else {
                @SuppressWarnings("unchecked")
                Collection<LongIdKey> longIdKeys = (Collection<LongIdKey>) objects[0];
                if (longIdKeys.isEmpty()) {
                    detachedCriteria.add(Restrictions.isNull("longId"));
                } else {
                    Collection<Long> longIds = toLongCollection(longIdKeys);
                    detachedCriteria.add(Restrictions.in("balanceLongId", longIds));
                }
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void childForFundRepositorySet(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("fundRepositoryLongId"));
            } else {
                @SuppressWarnings("unchecked")
                Collection<LongIdKey> longIdKeys = (Collection<LongIdKey>) objects[0];
                if (longIdKeys.isEmpty()) {
                    detachedCriteria.add(Restrictions.isNull("longId"));
                } else {
                    Collection<Long> longIds = toLongCollection(longIdKeys);
                    detachedCriteria.add(Restrictions.in("fundRepositoryLongId", longIds));
                }
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private Collection<Long> toLongCollection(Collection<LongIdKey> longIdKeys) {
        return longIdKeys.stream().map(LongIdKey::getLongId).collect(Collectors.toSet());
    }
}
