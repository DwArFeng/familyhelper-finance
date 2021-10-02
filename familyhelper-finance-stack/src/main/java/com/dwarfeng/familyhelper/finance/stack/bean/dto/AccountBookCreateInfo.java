package com.dwarfeng.familyhelper.finance.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

/**
 * 账本创建信息。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class AccountBookCreateInfo implements Dto {

    private static final long serialVersionUID = -1086808495667913782L;

    private String name;
    private String remark;

    public AccountBookCreateInfo() {
    }

    public AccountBookCreateInfo(String name, String remark) {
        this.name = name;
        this.remark = remark;
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

    @Override
    public String toString() {
        return "AccountBookCreateInfo{" +
                "name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
