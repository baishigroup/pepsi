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

    private String AccountName;

    private String InOutItemName;

    private String Inserted = "";   //json插入记录
    private String Deleted = "";    //json删除记录
    private String Updated = "";    //json修改记录

    private String ListType ;   //单据类型

    private String ClientIp;


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

    public String getAccountName() {
        return AccountName;
    }

    public void setAccountName(String accountName) {
        AccountName = accountName;
    }

    public String getInOutItemName() {
        return InOutItemName;
    }

    public void setInOutItemName(String inOutItemName) {
        InOutItemName = inOutItemName;
    }

    public String getInserted() {
        return Inserted;
    }

    public void setInserted(String inserted) {
        Inserted = inserted;
    }

    public String getDeleted() {
        return Deleted;
    }

    public void setDeleted(String deleted) {
        Deleted = deleted;
    }

    public String getUpdated() {
        return Updated;
    }

    public void setUpdated(String updated) {
        Updated = updated;
    }

    public String getListType() {
        return ListType;
    }

    public void setListType(String listType) {
        ListType = listType;
    }

    public String getClientIp() {
        return ClientIp;
    }

    public void setClientIp(String clientIp) {
        ClientIp = clientIp;
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