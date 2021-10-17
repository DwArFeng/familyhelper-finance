package com.dwarfeng.familyhelper.finance.impl.dao.preset;

import com.dwarfeng.familyhelper.finance.stack.service.FundChangeMaintainService;
import com.dwarfeng.subgrade.sdk.hibernate.criteria.PresetCriteriaMaker;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

@Component
public class FundChangePresetCriteriaMaker implements PresetCriteriaMaker {

    @Override
    public void makeCriteria(DetachedCriteria detachedCriteria, String s, Object[] objects) {
        switch (s) {
            case FundChangeMaintainService.CHILD_FOR_ACCOUNT_BOOK:
                childForAccountBook(detachedCriteria, objects);
                break;
            case FundChangeMaintainService.CHILD_FOR_ACCOUNT_BOOK_DESC:
                childForAccountBookDesc(detachedCriteria, objects);
                break;
            case FundChangeMaintainService.CHILD_FOR_ACCOUNT_BOOK_BETWEEN:
                childForAccountBookBetween(detachedCriteria, objects);
                break;
            case FundChangeMaintainService.CHILD_FOR_ACCOUNT_BOOK_BETWEEN_DESC:
                childForAccountBookBetweenDesc(detachedCriteria, objects);
                break;
            case FundChangeMaintainService.CHILD_FOR_ACCOUNT_BOOK_TYPE_EQUALS:
                childForAccountBookTypeEquals(detachedCriteria, objects);
                break;
            case FundChangeMaintainService.CHILD_FOR_ACCOUNT_BOOK_TYPE_EQUALS_DESC:
                childForAccountBookTypeEqualsDesc(detachedCriteria, objects);
                break;
            default:
                throw new IllegalArgumentException("无法识别的预设: " + s);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void childForAccountBook(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("accountBookLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("accountBookLongId", longIdKey.getLongId())
                );
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void childForAccountBookDesc(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("accountBookLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("accountBookLongId", longIdKey.getLongId())
                );
            }
            detachedCriteria.addOrder(Order.desc("happenedDate"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void childForAccountBookBetween(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("accountBookLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("accountBookLongId", longIdKey.getLongId())
                );
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
    private void childForAccountBookBetweenDesc(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("accountBookLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("accountBookLongId", longIdKey.getLongId())
                );
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

    @SuppressWarnings("DuplicatedCode")
    private void childForAccountBookTypeEquals(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("accountBookLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("accountBookLongId", longIdKey.getLongId())
                );
            }
            String changeType = (String) objects[1];
            detachedCriteria.add(Restrictions.eq("changeType", changeType));
            detachedCriteria.addOrder(Order.asc("happenedDate"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void childForAccountBookTypeEqualsDesc(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("accountBookLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("accountBookLongId", longIdKey.getLongId())
                );
            }
            String changeType = (String) objects[1];
            detachedCriteria.add(Restrictions.eq("changeType", changeType));
            detachedCriteria.addOrder(Order.desc("happenedDate"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }
}
