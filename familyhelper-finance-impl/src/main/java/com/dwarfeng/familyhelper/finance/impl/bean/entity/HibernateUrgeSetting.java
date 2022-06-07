package com.dwarfeng.familyhelper.finance.impl.bean.entity;

import com.dwarfeng.familyhelper.finance.sdk.util.Constraints;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.util.Optional;

@Entity
@IdClass(HibernateLongIdKey.class)
@Table(name = "tbl_urge_setting")
public class HibernateUrgeSetting implements Bean {

    private static final long serialVersionUID = -6265864671177757244L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private Long longId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "cron", length = Constraints.LENGTH_CRON, nullable = false)
    private String cron;

    @Column(name = "remark", length = Constraints.LENGTH_REMARK)
    private String remark;

    @Column(name = "modified_count", nullable = false)
    private int modifiedCount;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    // -----------------------------------------------------------一对一-----------------------------------------------------------
    @OneToOne(targetEntity = HibernateAccountBook.class)
    @JoinColumns({ //
            @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateAccountBook accountBook;

    public HibernateUrgeSetting() {
    }

    // -----------------------------------------------------------映射用属性区-----------------------------------------------------------
    public HibernateLongIdKey getKey() {
        return Optional.ofNullable(longId).map(HibernateLongIdKey::new).orElse(null);
    }

    public void setKey(HibernateLongIdKey idKey) {
        this.longId = Optional.ofNullable(idKey).map(HibernateLongIdKey::getLongId).orElse(null);
    }

    // -----------------------------------------------------------常规属性区-----------------------------------------------------------
    public Long getLongId() {
        return longId;
    }

    public void setLongId(Long longId) {
        this.longId = longId;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getModifiedCount() {
        return modifiedCount;
    }

    public void setModifiedCount(int modifiedCount) {
        this.modifiedCount = modifiedCount;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public HibernateAccountBook getAccountBook() {
        return accountBook;
    }

    public void setAccountBook(HibernateAccountBook accountBook) {
        this.accountBook = accountBook;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "longId = " + longId + ", " +
                "cron = " + cron + ", " +
                "remark = " + remark + ", " +
                "modifiedCount = " + modifiedCount + ", " +
                "enabled = " + enabled + ", " +
                "accountBook = " + accountBook + ")";
    }
}
