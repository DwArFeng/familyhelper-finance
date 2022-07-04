package com.dwarfeng.familyhelper.finance.impl.bean.entity;

import com.dwarfeng.familyhelper.finance.sdk.util.Constraints;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.util.Date;
import java.util.Optional;

@Entity
@IdClass(HibernateLongIdKey.class)
@Table(name = "tbl_bill_file_info", indexes = {
        @Index(name = "idx_fund_change", columnList = "fund_change_id"),
        @Index(name = "idx_fund_change_index_asc", columnList = "fund_change_id, column_index ASC"),
        @Index(name = "idx_fund_change_index_desc", columnList = "fund_change_id, column_index DESC")
})
public class HibernateBillFileInfo implements Bean {

    private static final long serialVersionUID = 3522649199522755629L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private Long longId;

    // -----------------------------------------------------------外键-----------------------------------------------------------
    @Column(name = "fund_change_id")
    private Long fundChangeLongId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "origin_name")
    private String originName;

    @Column(name = "column_index")
    private int index;

    @Column(name = "column_length")
    private long length;

    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "remark", length = Constraints.LENGTH_REMARK)
    private String remark;

    // -----------------------------------------------------------多对一-----------------------------------------------------------
    @ManyToOne(targetEntity = HibernateFundChange.class)
    @JoinColumns({ //
            @JoinColumn(name = "fund_change_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateFundChange fundChange;

    public HibernateBillFileInfo() {
    }

    // -----------------------------------------------------------映射用属性区-----------------------------------------------------------
    public HibernateLongIdKey getKey() {
        return Optional.ofNullable(longId).map(HibernateLongIdKey::new).orElse(null);
    }

    public void setKey(HibernateLongIdKey idKey) {
        this.longId = Optional.ofNullable(idKey).map(HibernateLongIdKey::getLongId).orElse(null);
    }

    public HibernateLongIdKey getFundChangeKey() {
        return Optional.ofNullable(fundChangeLongId).map(HibernateLongIdKey::new).orElse(null);
    }

    public void setFundChangeKey(HibernateLongIdKey idKey) {
        this.fundChangeLongId = Optional.ofNullable(idKey).map(HibernateLongIdKey::getLongId).orElse(null);
    }

    // -----------------------------------------------------------常规属性区-----------------------------------------------------------
    public Long getLongId() {
        return longId;
    }

    public void setLongId(Long longId) {
        this.longId = longId;
    }

    public Long getFundChangeLongId() {
        return fundChangeLongId;
    }

    public void setFundChangeLongId(Long fundChangeLongId) {
        this.fundChangeLongId = fundChangeLongId;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public HibernateFundChange getFundChange() {
        return fundChange;
    }

    public void setFundChange(HibernateFundChange fundChange) {
        this.fundChange = fundChange;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "longId = " + longId + ", " +
                "fundChangeLongId = " + fundChangeLongId + ", " +
                "originName = " + originName + ", " +
                "index = " + index + ", " +
                "length = " + length + ", " +
                "createdDate = " + createdDate + ", " +
                "remark = " + remark + ", " +
                "fundChange = " + fundChange + ")";
    }
}
