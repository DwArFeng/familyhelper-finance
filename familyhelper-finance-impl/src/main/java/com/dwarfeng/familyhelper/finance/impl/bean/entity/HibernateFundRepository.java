package com.dwarfeng.familyhelper.finance.impl.bean.entity;

import com.dwarfeng.familyhelper.finance.sdk.util.Constraints;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@IdClass(HibernateLongIdKey.class)
@Table(name = "tbl_fund_repository")
public class HibernateFundRepository implements Bean {

    private static final long serialVersionUID = -2044186356592030230L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private Long longId;

    // -----------------------------------------------------------外键-----------------------------------------------------------
    @Column(name = "account_book_id")
    private Long accountBookLongId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "name", length = Constraints.LENGTH_NAME, nullable = false)
    private String name;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @Column(name = "remark", length = Constraints.LENGTH_REMARK)
    private String remark;

    // -----------------------------------------------------------多对一-----------------------------------------------------------
    @ManyToOne(targetEntity = HibernateAccountBook.class)
    @JoinColumns({ //
            @JoinColumn(name = "account_book_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateAccountBook accountBook;

    // -----------------------------------------------------------一对多-----------------------------------------------------------
    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateBalanceItem.class, mappedBy = "fundRepository")
    private Set<HibernateBalanceItem> balanceItems = new HashSet<>();

    public HibernateFundRepository() {
    }

    // -----------------------------------------------------------映射用属性区-----------------------------------------------------------
    public HibernateLongIdKey getKey() {
        return Optional.ofNullable(longId).map(HibernateLongIdKey::new).orElse(null);
    }

    public void setKey(HibernateLongIdKey idKey) {
        this.longId = Optional.ofNullable(idKey).map(HibernateLongIdKey::getLongId).orElse(null);
    }

    public HibernateLongIdKey getAccountBookKey() {
        return Optional.ofNullable(accountBookLongId).map(HibernateLongIdKey::new).orElse(null);
    }

    public void setAccountBookKey(HibernateLongIdKey idKey) {
        this.accountBookLongId = Optional.ofNullable(idKey).map(HibernateLongIdKey::getLongId).orElse(null);
    }

    // -----------------------------------------------------------常规属性区-----------------------------------------------------------

    public Long getLongId() {
        return longId;
    }

    public void setLongId(Long longId) {
        this.longId = longId;
    }

    public Long getAccountBookLongId() {
        return accountBookLongId;
    }

    public void setAccountBookLongId(Long accountBookLongId) {
        this.accountBookLongId = accountBookLongId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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

    public Set<HibernateBalanceItem> getBalanceItems() {
        return balanceItems;
    }

    public void setBalanceItems(Set<HibernateBalanceItem> balanceItems) {
        this.balanceItems = balanceItems;
    }

    @Override
    public String toString() {
        return "HibernateFundRepository{" +
                "longId=" + longId +
                ", accountBookLongId=" + accountBookLongId +
                ", name='" + name + '\'' +
                ", enabled=" + enabled +
                ", remark='" + remark + '\'' +
                '}';
    }
}
