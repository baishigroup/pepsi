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

    private String accountID;

    /**
     * IDs 批量操作使用
     */
    private String accountIDs = "";

    private String[] ids=null;

    private String clientIp;

    private String Number;
    private String newType;
    private String supplier;
    private String ChangeAmount;
    private String oTime;
    private String AList;
    private String AMList;


    public String  getAccountIDs() {
        return accountIDs;
    }

    public void setAccountIDs(String accountIDs) {
        this.accountIDs = accountIDs;
    }

    public String[] getIds() {
        return ids;
    }

    public void setIds(String[] ids) {
        this.ids = ids;
    }

    public Account(String id, String name, String serialno, Double initialamount, Double currentamount, String remark, Integer isdefault) {
        this.id = id;
        this.name = name;
        this.serialno = serialno;
        this.initialamount = initialamount;
        this.currentamount = currentamount;
        this.remark = remark;
        this.isdefault = isdefault;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public Account() {
        super();
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getNewType() {
        return newType;
    }

    public void setNewType(String newType) {
        this.newType = newType;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getChangeAmount() {
        return ChangeAmount;
    }

    public void setChangeAmount(String changeAmount) {
        ChangeAmount = changeAmount;
    }

    public String getoTime() {
        return oTime;
    }

    public void setoTime(String oTime) {
        this.oTime = oTime;
    }

    public String getAList() {
        return AList;
    }

    public void setAList(String AList) {
        this.AList = AList;
    }

    public String getAMList() {
        return AMList;
    }

    public void setAMList(String AMList) {
        this.AMList = AMList;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
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