package com.dwarfeng.familyhelper.finance.impl.bean.entity;

import com.dwarfeng.familyhelper.finance.impl.bean.key.HibernatePoabKey;
import com.dwarfeng.familyhelper.finance.sdk.util.Constraints;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.util.Objects;

@Entity
@IdClass(HibernatePoabKey.class)
@Table(name = "tbl_poab")
public class HibernatePoab implements Bean {

    private static final long serialVersionUID = -5770845452333625658L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "long_id", nullable = false)
    private Long longId;

    @Id
    @Column(name = "string_id", length = Constraints.LENGTH_USER, nullable = false)
    private String stringId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "permission_level")
    private int permissionLevel;

    @Column(name = "remark", length = Constraints.LENGTH_REMARK)
    private String remark;

    // -----------------------------------------------------------多对一-----------------------------------------------------------
    @ManyToOne(targetEntity = HibernateAccountBook.class)
    @JoinColumns({ //
            @JoinColumn(name = "long_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateAccountBook accountBook;

    @ManyToOne(targetEntity = HibernateUser.class)
    @JoinColumns({ //
            @JoinColumn(name = "string_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateUser user;

    public HibernatePoab() {
    }

    // -----------------------------------------------------------映射用属性区-----------------------------------------------------------
    public HibernatePoabKey getKey() {
        return new HibernatePoabKey(longId, stringId);
    }

    public void setKey(HibernatePoabKey key) {
        if (Objects.isNull(key)) {
            this.longId = null;
            this.stringId = null;
        } else {
            this.longId = key.getLongId();
            this.stringId = key.getStringId();
        }
    }

    // -----------------------------------------------------------常规属性区-----------------------------------------------------------
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

    public int getPermissionLevel() {
        return permissionLevel;
    }

    public void setPermissionLevel(int permissionLevel) {
        this.permissionLevel = permissionLevel;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public HibernateAccountBook getAccountBook() {
        return accountBook;
    }

    public void setAccountBook(HibernateAccountBook accountBook) {
        this.accountBook = accountBook;
    }

    public HibernateUser getUser() {
        return user;
    }

    public void setUser(HibernateUser user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "longId = " + longId + ", " +
                "stringId = " + stringId + ", " +
                "permissionLevel = " + permissionLevel + ", " +
                "remark = " + remark + ", " +
                "accountBook = " + accountBook + ", " +
                "user = " + user + ")";
    }
}
