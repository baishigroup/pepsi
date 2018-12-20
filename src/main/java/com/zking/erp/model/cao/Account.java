package com.zking.erp.model.cao;

import lombok.ToString;

import java.io.Serializable;

@ToString
public class Account implements Serializable{
    private String id;

    private String name;

    private String serialno;

    private Double initialamount;

    private Double currentamount;

    private String remark;

    private Integer isdefault;

    public Account(String id, String name, String serialno, Double initialamount, Double currentamount, String remark, Integer isdefault) {
        this.id = id;
        this.name = name;
        this.serialno = serialno;
        this.initialamount = initialamount;
        this.currentamount = currentamount;
        this.remark = remark;
        this.isdefault = isdefault;
    }

    public Account() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerialno() {
        return serialno;
    }

    public void setSerialno(String serialno) {
        this.serialno = serialno;
    }

    public Double getInitialamount() {
        return initialamount;
    }

    public void setInitialamount(Double initialamount) {
        this.initialamount = initialamount;
    }

    public Double getCurrentamount() {
        return currentamount;
    }

    public void setCurrentamount(Double currentamount) {
        this.currentamount = currentamount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getIsdefault() {
        return isdefault;
    }

    public void setIsdefault(Integer isdefault) {
        this.isdefault = isdefault;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", serialno='" + serialno + '\'' +
                ", initialamount=" + initialamount +
                ", currentamount=" + currentamount +
                ", remark='" + remark + '\'' +
                ", isdefault=" + isdefault +
                '}';
    }
}