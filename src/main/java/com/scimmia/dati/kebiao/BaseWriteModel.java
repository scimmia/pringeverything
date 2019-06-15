package com.scimmia.dati.kebiao;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

public class BaseWriteModel extends BaseRowModel {
    @ExcelProperty(value = {"表头1","表头1","表头31"},index = 0)
    protected String p1;

    @ExcelProperty(value = {"表头1","表头1","表头32"},index = 1)
    protected String p2;
    @ExcelProperty(value = {"表头1","表头1","表头33"},index = 2)
    protected String p3;
    @ExcelProperty(value = {"表头1","表头1","表头33"},index = 3)
    protected String p4;

    public String getP1() {
        return p1;
    }

    public void setP1(String p1) {
        this.p1 = p1;
    }

    public String getP2() {
        return p2;
    }

    public void setP2(String p2) {
        this.p2 = p2;
    }

    public String getP3() {
        return p3;
    }

    public void setP3(String p3) {
        this.p3 = p3;
    }

    public String getP4() {
        return p4;
    }

    public void setP4(String p4) {
        this.p4 = p4;
    }
}