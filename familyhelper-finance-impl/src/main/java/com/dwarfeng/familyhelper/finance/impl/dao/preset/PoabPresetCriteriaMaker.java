package com.dwarfeng.familyhelper.finance.impl.dao.preset;

import com.dwarfeng.familyhelper.finance.stack.service.PoabMaintainService;
import com.dwarfeng.subgrade.sdk.hibernate.criteria.PresetCriteriaMaker;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;

@Component
public class PoabPresetCriteriaMaker implements PresetCriteriaMaker {

    @Override
    public void makeCriteria(DetachedCriteria detachedCriteria, String s, Object[] objects) {
        switch (s) {
            case PoabMaintainService.CHILD_FOR_ACCOUNT_BOOK:
                childForAccountBook(detachedCriteria, objects);
                break;
            case PoabMaintainService.CHILD_FOR_USER:
                childForUser(detachedCriteria, objects);
                break;
            case PoabMaintainService.CHILD_FOR_ACCOUNT_BOOK_PERMISSION_LEVEL_EQUALS:
                childForAccountBookPermissionLevelEquals(detachedCriteria, objects);
                break;
            case PoabMaintainService.CHILD_FOR_USER_PERMISSION_LEVEL_EQUALS:
                childForUserPermissionLevelEquals(detachedCriteria, objects);
                break;
            default:
                throw new IllegalArgumentException("无法识别的预设: " + s);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void childForAccountBook(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("longId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("longId", longIdKey.getLongId())
                );
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void childForUser(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("stringId"));
            } else {
                StringIdKey stringIdKey = (StringIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("stringId", stringIdKey.getStringId())
                );
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void childForAccountBookPermissionLevelEquals(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("longId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("longId", longIdKey.getLongId())
                );
            }
            int permissionLevel = (int) objects[1];
            detachedCriteria.add(Restrictions.eq("permissionLevel", permissionLevel));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void childForUserPermissionLevelEquals(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("stringId"));
            } else {
                StringIdKey stringIdKey = (StringIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("stringId", stringIdKey.getStringId())
                );
            }
            int permissionLevel = (int) objects[1];
            detachedCriteria.add(Restrictions.eq("permissionLevel", permissionLevel));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }
}
