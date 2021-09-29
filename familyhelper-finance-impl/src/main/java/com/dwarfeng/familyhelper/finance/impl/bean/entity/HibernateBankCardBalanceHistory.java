package com.dwarfeng.familyhelper.finance.impl.bean.entity;

import com.dwarfeng.familyhelper.finance.sdk.util.Constraints;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

@Entity
@IdClass(HibernateLongIdKey.class)
@Table(name = "tbl_bank_card_balance_history")
public class HibernateBankCardBalanceHistory implements Bean {

    private static final long serialVersionUID = 3107670979466993797L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private Long longId;

    // -----------------------------------------------------------外键-----------------------------------------------------------
    @Column(name = "bank_card_id")
    private Long bankCardLongId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "column_value")
    private BigDecimal value;

    @Column(name = "happened_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date happenedDate;

    @Column(name = "remark", length = Constraints.LENGTH_REMARK)
    private String remark;

    // -----------------------------------------------------------多对一-----------------------------------------------------------
    @ManyToOne(targetEntity = HibernateBankCard.class)
    @JoinColumns({ //
            @JoinColumn(name = "bank_card_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateBankCard bankCard;

    // -----------------------------------------------------------映射用属性区-----------------------------------------------------------
    public HibernateLongIdKey getKey() {
        return Optional.ofNullable(longId).map(HibernateLongIdKey::new).orElse(null);
    }

    public void setKey(HibernateLongIdKey idKey) {
        this.longId = Optional.ofNullable(idKey).map(HibernateLongIdKey::getLongId).orElse(null);
    }

    public HibernateLongIdKey getBankCardKey() {
        return Optional.ofNullable(bankCardLongId).map(HibernateLongIdKey::new).orElse(null);
    }

    public void setBankCardKey(HibernateLongIdKey idKey) {
        this.bankCardLongId = Optional.ofNullable(idKey).map(HibernateLongIdKey::getLongId).orElse(null);
    }

    // -----------------------------------------------------------常规属性区-----------------------------------------------------------
    public Long getLongId() {
        return longId;
    }

    public void setLongId(Long longId) {
        this.longId = longId;
    }

    public Long getBankCardLongId() {
        return bankCardLongId;
    }

    public void setBankCardLongId(Long bankCardLongId) {
        this.bankCardLongId = bankCardLongId;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Date getHappenedDate() {
        return happenedDate;
    }

    public void setHappenedDate(Date happenedDate) {
        this.happenedDate = happenedDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public HibernateBankCard getBankCard() {
        return bankCard;
    }

    public void setBankCard(HibernateBankCard bankCard) {
        this.bankCard = bankCard;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "longId = " + longId + ", " +
                "bankCardLongId = " + bankCardLongId + ", " +
                "value = " + value + ", " +
                "happenedDate = " + happenedDate + ", " +
                "remark = " + remark + ", " +
                "bankCard = " + bankCard + ")";
    }
}
