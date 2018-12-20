package com.zking.erp.model.cao;

import lombok.ToString;

import java.io.Serializable;

@ToString
public class AccountItem implements Serializable{
    private String id;

    private String headerid;

    private String accountid;

    private String inoutitemid;

    private Double eachamount;

    private String remark;

    public AccountItem(String id, String headerid, String accountid, String inoutitemid, Double eachamount, String remark) {
        this.id = id;
        this.headerid = headerid;
        this.accountid = accountid;
        this.inoutitemid = inoutitemid;
        this.eachamount = eachamount;
        this.remark = remark;
    }

    public AccountItem() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHeaderid() {
        return headerid;
    }

    public void setHeaderid(String headerid) {
        this.headerid = headerid;
    }

    public String getAccountid() {
        return accountid;
    }

    public void setAccountid(String accountid) {
        this.accountid = accountid;
    }

    public String getInoutitemid() {
        return inoutitemid;
    }

    public void setInoutitemid(String inoutitemid) {
        this.inoutitemid = inoutitemid;
    }

    public Double getEachamount() {
        return eachamount;
    }

    public void setEachamount(Double eachamount) {
        this.eachamount = eachamount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "AccountItem{" +
                "id='" + id + '\'' +
                ", headerid='" + headerid + '\'' +
                ", accountid='" + accountid + '\'' +
                ", inoutitemid='" + inoutitemid + '\'' +
                ", eachamount=" + eachamount +
                ", remark='" + remark + '\'' +
                '}';
    }
}