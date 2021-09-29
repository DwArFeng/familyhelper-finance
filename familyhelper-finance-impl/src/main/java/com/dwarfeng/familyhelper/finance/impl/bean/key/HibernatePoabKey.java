package com.dwarfeng.familyhelper.finance.impl.bean.key;

import com.dwarfeng.subgrade.stack.bean.Bean;

import java.io.Serializable;
import java.util.Objects;

/**
 * Hibernate 账本权限主键。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class HibernatePoabKey implements Bean, Serializable {

    private static final long serialVersionUID = -2729878794254679486L;

    private Long longId;
    private String stringId;

    public HibernatePoabKey() {
    }

    public HibernatePoabKey(Long longId, String stringId) {
        this.longId = longId;
        this.stringId = stringId;
    }

    public Long getLongId() {
        return longId;
    }

    public void setLongId(Long longId) {
        this.longId = longId;
    }

    public String getStringId() {
        return stringId;
    }

    public void setStringId(String stringId) {
        this.stringId = stringId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HibernatePoabKey that = (HibernatePoabKey) o;

        if (!Objects.equals(longId, that.longId)) return false;
        return Objects.equals(stringId, that.stringId);
    }

    @Override
    public int hashCode() {
        int result = longId != null ? longId.hashCode() : 0;
        result = 31 * result + (stringId != null ? stringId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "HibernatePoabKey{" +
                "longId=" + longId +
                ", stringId='" + stringId + '\'' +
                '}';
    }
}
