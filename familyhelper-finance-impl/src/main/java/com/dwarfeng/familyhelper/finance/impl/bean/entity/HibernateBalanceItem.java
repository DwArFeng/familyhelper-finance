package com.dwarfeng.familyhelper.finance.impl.bean.entity;

import com.dwarfeng.familyhelper.finance.sdk.util.Constraints;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Optional;

@Entity
@IdClass(HibernateLongIdKey.class)
@Table(name = "tbl_balance_item")
public class HibernateBalanceItem implements Bean {

    private static final long serialVersionUID = 2233436921581208468L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private Long longId;

    // -----------------------------------------------------------外键-----------------------------------------------------------
    @Column(name = "balance_id")
    private Long balanceLongId;

    @Column(name = "fund_repository_id")
    private Long fundRepositoryLongId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "value", nullable = false)
    private BigDecimal value = BigDecimal.ZERO;

    @Column(name = "remark", length = Constraints.LENGTH_REMARK)
    private String remark;

    // -----------------------------------------------------------多对一-----------------------------------------------------------
    @ManyToOne(targetEntity = HibernateBalance.class)
    @JoinColumns({ //
            @JoinColumn(name = "balance_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateBalance balance;

    @ManyToOne(targetEntity = HibernateFundRepository.class)
    @JoinColumns({ //
            @JoinColumn(name = "fund_repository_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateFundRepository fundRepository;

    public HibernateBalanceItem() {
    }

    // -----------------------------------------------------------映射用属性区-----------------------------------------------------------
    public HibernateLongIdKey getKey() {
        return Optional.ofNullable(longId).map(HibernateLongIdKey::new).orElse(null);
    }

    public void setKey(HibernateLongIdKey idKey) {
        this.longId = Optional.ofNullable(idKey).map(HibernateLongIdKey::getLongId).orElse(null);
    }

    public HibernateLongIdKey getBalanceKey() {
        return Optional.ofNullable(balanceLongId).map(HibernateLongIdKey::new).orElse(null);
    }

    public void setBalanceKey(HibernateLongIdKey idKey) {
        this.balanceLongId = Optional.ofNullable(idKey).map(HibernateLongIdKey::getLongId).orElse(null);
    }

    public HibernateLongIdKey getFundRepositoryKey() {
        return Optional.ofNullable(fundRepositoryLongId).map(HibernateLongIdKey::new).orElse(null);
    }

    public void setFundRepositoryKey(HibernateLongIdKey idKey) {
        this.fundRepositoryLongId = Optional.ofNullable(idKey).map(HibernateLongIdKey::getLongId).orElse(null);
    }

    // -----------------------------------------------------------常规属性区-----------------------------------------------------------

    public Long getLongId() {
        return longId;
    }

    public void setLongId(Long longId) {
        this.longId = longId;
    }

    public Long getBalanceLongId() {
        return balanceLongId;
    }

    public void setBalanceLongId(Long balanceLongId) {
        this.balanceLongId = balanceLongId;
    }

    public Long getFundRepositoryLongId() {
        return fundRepositoryLongId;
    }

    public void setFundRepositoryLongId(Long fundRepositoryLongId) {
        this.fundRepositoryLongId = fundRepositoryLongId;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public HibernateBalance getBalance() {
        return balance;
    }

    public void setBalance(HibernateBalance balance) {
        this.balance = balance;
    }

    public HibernateFundRepository getFundRepository() {
        return fundRepository;
    }

    public void setFundRepository(HibernateFundRepository fundRepository) {
        this.fundRepository = fundRepository;
    }

    @Override
    public String toString() {
        return "HibernateBalanceItem{" +
                "longId=" + longId +
                ", balanceLongId=" + balanceLongId +
                ", fundRepositoryLongId=" + fundRepositoryLongId +
                ", value=" + value +
                ", remark='" + remark + '\'' +
                '}';
    }
}
