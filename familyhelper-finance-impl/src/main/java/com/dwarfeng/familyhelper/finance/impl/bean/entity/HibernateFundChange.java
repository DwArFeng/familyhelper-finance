package com.dwarfeng.familyhelper.finance.impl.bean.entity;

import com.dwarfeng.familyhelper.finance.sdk.util.Constraints;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.util.Date;
import java.util.Optional;

@Entity
@IdClass(HibernateLongIdKey.class)
@Table(name = "tbl_fund_change")
public class HibernateFundChange implements Bean {

    private static final long serialVersionUID = -5335836666782885937L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private Long longId;

    // -----------------------------------------------------------外键-----------------------------------------------------------
    @Column(name = "account_book_id")
    private Long accountBookLongId;

    @Column(name = "bank_card_id")
    private Long bankCardLongId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "change_type", length = Constraints.LENGTH_TYPE)
    private String changeType;

    @Column(name = "happened_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date happenedDate;

    @Column(name = "remark", length = Constraints.LENGTH_REMARK)
    private String remark;

    // -----------------------------------------------------------多对一-----------------------------------------------------------
    @ManyToOne(targetEntity = HibernateAccountBook.class)
    @JoinColumns({ //
            @JoinColumn(name = "account_book_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateAccountBook accountBook;

    @ManyToOne(targetEntity = HibernateBankCard.class)
    @JoinColumns({ //
            @JoinColumn(name = "bank_card_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateBankCard bankCard;

    public HibernateFundChange() {
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

    public Long getAccountBookLongId() {
        return accountBookLongId;
    }

    public void setAccountBookLongId(Long accountBookLongId) {
        this.accountBookLongId = accountBookLongId;
    }

    public Long getBankCardLongId() {
        return bankCardLongId;
    }

    public void setBankCardLongId(Long bankCardLongId) {
        this.bankCardLongId = bankCardLongId;
    }

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
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

    public HibernateAccountBook getAccountBook() {
        return accountBook;
    }

    public void setAccountBook(HibernateAccountBook accountBook) {
        this.accountBook = accountBook;
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
                "accountBookLongId = " + accountBookLongId + ", " +
                "bankCardLongId = " + bankCardLongId + ", " +
                "changeType = " + changeType + ", " +
                "happenedDate = " + happenedDate + ", " +
                "remark = " + remark + ", " +
                "accountBook = " + accountBook + ", " +
                "bankCard = " + bankCard + ")";
    }
}
