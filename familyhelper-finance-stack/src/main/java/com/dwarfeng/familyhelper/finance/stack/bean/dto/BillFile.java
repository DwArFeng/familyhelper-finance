package com.dwarfeng.familyhelper.finance.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Arrays;

/**
 * 票据文件。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
public class BillFile implements Dto {

    private static final long serialVersionUID = 6002957744844039916L;

    private int index;
    private byte[] content;

    public BillFile() {
    }

    public BillFile(int index, byte[] content) {
        this.index = index;
        this.content = content;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "BillFile{" +
                "index=" + index +
                ", content=" + Arrays.toString(content) +
                '}';
    }
}
