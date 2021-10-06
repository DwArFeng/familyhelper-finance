package com.dwarfeng.familyhelper.finance.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

/**
 * 账本更新信息。
 *
 * @author DwArFeng
 * @since 1.1.1
 */
public class AccountBookUpdateInfo implements Dto {

    private static final long serialVersionUID = -5967903529464444511L;

    private String name;
    private String remark;

    public AccountBookUpdateInfo() {
    }

    public AccountBookUpdateInfo(String name, String remark) {
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
        return "AccountBookUpdateInfo{" +
                "name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
