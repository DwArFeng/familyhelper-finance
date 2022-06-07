package com.dwarfeng.familyhelper.finance.impl.dao.preset;

import com.dwarfeng.familyhelper.finance.stack.service.UrgeSettingMaintainService;
import com.dwarfeng.subgrade.sdk.hibernate.criteria.PresetCriteriaMaker;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class UrgeSettingPresetCriteriaMaker implements PresetCriteriaMaker {

    @Override
    public void makeCriteria(DetachedCriteria detachedCriteria, String s, Object[] objects) {
        if (UrgeSettingMaintainService.ENABLED.equals(s)) {
            enabled(detachedCriteria, objects);
        } else {
            throw new IllegalArgumentException("无法识别的预设: " + s);
        }
    }

    private void enabled(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            detachedCriteria.add(Restrictions.eq("enabled", true));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }
}
