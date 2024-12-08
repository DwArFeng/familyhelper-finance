package com.dwarfeng.familyhelper.finance.impl.dao.preset;

import com.dwarfeng.familyhelper.finance.stack.service.BankCardBalanceHistoryMaintainService;
import com.dwarfeng.subgrade.sdk.hibernate.criteria.PresetCriteriaMaker;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class BankCardBalanceHistoryPresetCriteriaMaker implements PresetCriteriaMaker {

    @Override
    public void makeCriteria(DetachedCriteria detachedCriteria, String s, Object[] objects) {
        switch (s) {
            case BankCardBalanceHistoryMaintainService.CHILD_FOR_BANK_CARD:
                childForBankCard(detachedCriteria, objects);
                break;
            case BankCardBalanceHistoryMaintainService.CHILD_FOR_BANK_CARD_DESC:
                childForBankCardDesc(detachedCriteria, objects);
                break;
            case BankCardBalanceHistoryMaintainService.BETWEEN:
                between(detachedCriteria, objects);
                break;
            case BankCardBalanceHistoryMaintainService.BETWEEN_DESC:
                betweenDesc(detachedCriteria, objects);
                break;
            case BankCardBalanceHistoryMaintainService.CHILD_FOR_BANK_CARD_BETWEEN:
                childForBankCardBetween(detachedCriteria, objects);
                break;
            case BankCardBalanceHistoryMaintainService.CHILD_FOR_BANK_CARD_BETWEEN_DESC:
                childForBankCardBetweenDesc(detachedCriteria, objects);
                break;
            case BankCardBalanceHistoryMaintainService.CHILD_FOR_BANK_CARD_SET:
                childForBankCardSet(detachedCriteria, objects);
                break;
            default:
                throw new IllegalArgumentException("无法识别的预设: " + s);
        }
    }

    private void childForBankCard(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("bankCardLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("bankCardLongId", longIdKey.getLongId())
                );
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void childForBankCardDesc(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("bankCardLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("bankCardLongId", longIdKey.getLongId())
                );
            }
            detachedCriteria.addOrder(Order.desc("happenedDate"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void between(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            Date startDate = (Date) objects[0];
            Date endDate = (Date) objects[1];
            detachedCriteria.add(Restrictions.ge("happenedDate", startDate));
            detachedCriteria.add(Restrictions.lt("happenedDate", endDate));
            detachedCriteria.addOrder(Order.asc("happenedDate"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void betweenDesc(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            Date startDate = (Date) objects[0];
            Date endDate = (Date) objects[1];
            detachedCriteria.add(Restrictions.ge("happenedDate", startDate));
            detachedCriteria.add(Restrictions.lt("happenedDate", endDate));
            detachedCriteria.addOrder(Order.desc("happenedDate"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void childForBankCardBetween(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("bankCardLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                detachedCriteria.add(Restrictions.eqOrIsNull("bankCardLongId", longIdKey.getLongId()));
            }
            Date startDate = (Date) objects[1];
            Date endDate = (Date) objects[2];
            detachedCriteria.add(Restrictions.ge("happenedDate", startDate));
            detachedCriteria.add(Restrictions.lt("happenedDate", endDate));
            detachedCriteria.addOrder(Order.asc("happenedDate"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void childForBankCardBetweenDesc(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("bankCardLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                detachedCriteria.add(Restrictions.eqOrIsNull("bankCardLongId", longIdKey.getLongId()));
            }
            Date startDate = (Date) objects[1];
            Date endDate = (Date) objects[2];
            detachedCriteria.add(Restrictions.ge("happenedDate", startDate));
            detachedCriteria.add(Restrictions.lt("happenedDate", endDate));
            detachedCriteria.addOrder(Order.desc("happenedDate"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void childForBankCardSet(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("bankCardLongId"));
            } else {
                @SuppressWarnings("unchecked")
                Collection<LongIdKey> longIdKeys = (Collection<LongIdKey>) objects[0];
                if (longIdKeys.isEmpty()) {
                    detachedCriteria.add(Restrictions.isNull("longId"));
                } else {
                    detachedCriteria.add(Restrictions.in("bankCardLongId", longList(longIdKeys)));
                }
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private List<Long> longList(Collection<LongIdKey> list) {
        return list.stream().map(LongIdKey::getLongId).collect(Collectors.toList());
    }
}
