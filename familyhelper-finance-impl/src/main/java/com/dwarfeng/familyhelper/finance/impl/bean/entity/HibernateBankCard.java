package com.dwarfeng.familyhelper.finance.impl.bean.entity;

import com.dwarfeng.familyhelper.finance.sdk.util.Constraints;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@IdClass(HibernateLongIdKey.class)
@Table(name = "tbl_bank_card")
public class HibernateBankCard implements Bean {

    private static final long serialVersionUID = 3107670979466993797L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private Long longId;

    // -----------------------------------------------------------外键-----------------------------------------------------------
    @Column(name = "account_book_id")
    private Long accountBookLongId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "card_type")
    private String cardType;

    @Column(name = "name", length = Constraints.LENGTH_NAME, nullable = false)
    private String name;

    @Column(name = "recorded_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastRecordedDate;

    @Column(name = "balance_value")
    private BigDecimal balanceValue;

    @Column(name = "temp_flag")
    private boolean tempFlag = false;

    @Column(name = "temp_last_recorded_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tempLastRecordedDate;

    @Column(name = "temp_balance_value")
    private BigDecimal tempBalanceValue;

    @Column(name = "remark", length = Constraints.LENGTH_REMARK)
    private String remark;

    // -----------------------------------------------------------多对一-----------------------------------------------------------
    @ManyToOne(targetEntity = HibernateAccountBook.class)
    @JoinColumns({ //
            @JoinColumn(name = "account_book_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateAccountBook accountBook;

    // -----------------------------------------------------------一对多-----------------------------------------------------------
    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateFundChange.class, mappedBy = "bankCard")
    private Set<HibernateFundChange> fundChanges = new HashSet<>();

    public HibernateBankCard() {
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

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public Date getLastRecordedDate() {
        return lastRecordedDate;
    }

    public void setLastRecordedDate(Date lastRecordedDate) {
        this.lastRecordedDate = lastRecordedDate;
    }

    public BigDecimal getBalanceValue() {
        return balanceValue;
    }

    public void setBalanceValue(BigDecimal balanceValue) {
        this.balanceValue = balanceValue;
    }

    public boolean isTempFlag() {
        return tempFlag;
    }

    public void setTempFlag(boolean tempFlag) {
        this.tempFlag = tempFlag;
    }

    public Date getTempLastRecordedDate() {
        return tempLastRecordedDate;
    }

    public void setTempLastRecordedDate(Date tempLastRecordedDate) {
        this.tempLastRecordedDate = tempLastRecordedDate;
    }

    public BigDecimal getTempBalanceValue() {
        return tempBalanceValue;
    }

    public void setTempBalanceValue(BigDecimal tempBalanceValue) {
        this.tempBalanceValue = tempBalanceValue;
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

    public Set<HibernateFundChange> getFundChanges() {
        return fundChanges;
    }

    public void setFundChanges(Set<HibernateFundChange> fundChanges) {
        this.fundChanges = fundChanges;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "longId = " + longId + ", " +
                "accountBookLongId = " + accountBookLongId + ", " +
                "cardType = " + cardType + ", " +
                "name = " + name + ", " +
                "lastRecordedDate = " + lastRecordedDate + ", " +
                "balanceValue = " + balanceValue + ", " +
                "tempFlag = " + tempFlag + ", " +
                "tempLastRecordedDate = " + tempLastRecordedDate + ", " +
                "tempBalanceValue = " + tempBalanceValue + ", " +
                "remark = " + remark + ", " +
                "accountBook = " + accountBook + ")";
    }
}
