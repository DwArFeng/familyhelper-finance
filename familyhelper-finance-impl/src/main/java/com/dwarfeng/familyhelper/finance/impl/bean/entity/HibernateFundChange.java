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
@Table(name = "tbl_fund_change")
public class HibernateFundChange implements Bean {

    private static final long serialVersionUID = 5167982927296557870L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private Long longId;

    // -----------------------------------------------------------外键-----------------------------------------------------------
    @Column(name = "account_book_id")
    private Long accountBookLongId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "delta", nullable = false)
    private BigDecimal delta;

    @Column(name = "change_type", length = Constraints.LENGTH_TYPE)
    private String changeType;

    @Column(name = "happened_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date happenedDate;

    @Column(name = "remark", length = Constraints.LENGTH_REMARK)
    private String remark;

    @Column(name = "recorded_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date recordedDate;

    // -----------------------------------------------------------多对一-----------------------------------------------------------
    @ManyToOne(targetEntity = HibernateAccountBook.class)
    @JoinColumns({ //
            @JoinColumn(name = "account_book_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateAccountBook accountBook;

    // -----------------------------------------------------------一对多-----------------------------------------------------------
    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateBillFileInfo.class, mappedBy = "fundChange")
    private Set<HibernateBillFileInfo> billFileInfos = new HashSet<>();

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

    public BigDecimal getDelta() {
        return delta;
    }

    public void setDelta(BigDecimal delta) {
        this.delta = delta;
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

    public Date getRecordedDate() {
        return recordedDate;
    }

    public void setRecordedDate(Date recordedDate) {
        this.recordedDate = recordedDate;
    }

    public HibernateAccountBook getAccountBook() {
        return accountBook;
    }

    public void setAccountBook(HibernateAccountBook accountBook) {
        this.accountBook = accountBook;
    }

    public Set<HibernateBillFileInfo> getBillFileInfos() {
        return billFileInfos;
    }

    public void setBillFileInfos(Set<HibernateBillFileInfo> billFileInfos) {
        this.billFileInfos = billFileInfos;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "longId = " + longId + ", " +
                "accountBookLongId = " + accountBookLongId + ", " +
                "delta = " + delta + ", " +
                "changeType = " + changeType + ", " +
                "happenedDate = " + happenedDate + ", " +
                "remark = " + remark + ", " +
                "recordedDate = " + recordedDate + ", " +
                "accountBook = " + accountBook + ")";
    }
}
