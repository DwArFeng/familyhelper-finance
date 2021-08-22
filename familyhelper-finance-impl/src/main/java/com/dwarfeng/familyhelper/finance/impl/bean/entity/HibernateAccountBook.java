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
@Table(name = "tbl_account_book")
public class HibernateAccountBook implements Bean {

    private static final long serialVersionUID = 9087609206805987437L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private Long longId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "name", length = Constraints.LENGTH_NAME, nullable = false)
    private String name;

    @Column(name = "remark", length = Constraints.LENGTH_REMARK)
    private String remark;

    // -----------------------------------------------------------一对多-----------------------------------------------------------
    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateBalance.class, mappedBy = "accountBook")
    private Set<HibernateBalance> balances = new HashSet<>();

    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateFundChange.class, mappedBy = "accountBook")
    private Set<HibernateFundChange> fundChanges = new HashSet<>();

    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateFundRepository.class, mappedBy = "accountBook")
    private Set<HibernateFundRepository> fundRepositories = new HashSet<>();

    public HibernateAccountBook() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Set<HibernateBalance> getBalances() {
        return balances;
    }

    public void setBalances(Set<HibernateBalance> balances) {
        this.balances = balances;
    }

    public Set<HibernateFundChange> getFundChanges() {
        return fundChanges;
    }

    public void setFundChanges(Set<HibernateFundChange> fundChanges) {
        this.fundChanges = fundChanges;
    }

    public Set<HibernateFundRepository> getFundRepositories() {
        return fundRepositories;
    }

    public void setFundRepositories(Set<HibernateFundRepository> fundRepositories) {
        this.fundRepositories = fundRepositories;
    }

    @Override
    public String toString() {
        return "HibernateAccountBook{" +
                "longId=" + longId +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                ", balances=" + balances +
                ", fundChanges=" + fundChanges +
                '}';
    }
}
