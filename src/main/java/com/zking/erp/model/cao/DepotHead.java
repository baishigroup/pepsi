package com.zking.erp.model.cao;

import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@ToString
public class DepotHead implements Serializable{
    private String id;

    private String type;

    private String subtype;

    private String projectid;

    private String defaultnumber;

    private String checker;

    private String number;

    private String operpersonname;

    private Date checktime;

    private Date createtime;

    private Date opertime;

    private String organid;

    private String handspersonid;

    private String accountid;

    private Double changeamount;

    private String allocationprojectid;

    private Double totalprice;

    private String paytype;

    private String remark;

    private String salesman;

    private String accountidlist;

    private String accountmoneylist;

    private Double discount;

    private Double discountmoney;

    private Double discountlastmoney;

    private Double othermoney;

    private String othermoneylist;

    private String othermoneyitem;

    private Integer accountday;

    private Integer status;

    public DepotHead(String id, String type, String subtype, String projectid, String defaultnumber, String checker, String number, String operpersonname, Date checktime, Date createtime, Date opertime, String organid, String handspersonid, String accountid, Double changeamount, String allocationprojectid, Double totalprice, String paytype, String remark, String salesman, String accountidlist, String accountmoneylist, Double discount, Double discountmoney, Double discountlastmoney, Double othermoney, String othermoneylist, String othermoneyitem, Integer accountday, Integer status) {
        this.id = id;
        this.type = type;
        this.subtype = subtype;
        this.projectid = projectid;
        this.defaultnumber = defaultnumber;
        this.checker = checker;
        this.number = number;
        this.operpersonname = operpersonname;
        this.checktime = checktime;
        this.createtime = createtime;
        this.opertime = opertime;
        this.organid = organid;
        this.handspersonid = handspersonid;
        this.accountid = accountid;
        this.changeamount = changeamount;
        this.allocationprojectid = allocationprojectid;
        this.totalprice = totalprice;
        this.paytype = paytype;
        this.remark = remark;
        this.salesman = salesman;
        this.accountidlist = accountidlist;
        this.accountmoneylist = accountmoneylist;
        this.discount = discount;
        this.discountmoney = discountmoney;
        this.discountlastmoney = discountlastmoney;
        this.othermoney = othermoney;
        this.othermoneylist = othermoneylist;
        this.othermoneyitem = othermoneyitem;
        this.accountday = accountday;
        this.status = status;
    }

    public DepotHead() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    public String getDefaultnumber() {
        return defaultnumber;
    }

    public void setDefaultnumber(String defaultnumber) {
        this.defaultnumber = defaultnumber;
    }

    public String getChecker() {
        return checker;
    }

    public void setChecker(String checker) {
        this.checker = checker;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getOperpersonname() {
        return operpersonname;
    }

    public void setOperpersonname(String operpersonname) {
        this.operpersonname = operpersonname;
    }

    public Date getChecktime() {
        return checktime;
    }

    public void setChecktime(Date checktime) {
        this.checktime = checktime;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getOpertime() {
        return opertime;
    }

    public void setOpertime(Date opertime) {
        this.opertime = opertime;
    }

    public String getOrganid() {
        return organid;
    }

    public void setOrganid(String organid) {
        this.organid = organid;
    }

    public String getHandspersonid() {
        return handspersonid;
    }

    public void setHandspersonid(String handspersonid) {
        this.handspersonid = handspersonid;
    }

    public String getAccountid() {
        return accountid;
    }

    public void setAccountid(String accountid) {
        this.accountid = accountid;
    }

    public Double getChangeamount() {
        return changeamount;
    }

    public void setChangeamount(Double changeamount) {
        this.changeamount = changeamount;
    }

    public String getAllocationprojectid() {
        return allocationprojectid;
    }

    public void setAllocationprojectid(String allocationprojectid) {
        this.allocationprojectid = allocationprojectid;
    }

    public Double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Double totalprice) {
        this.totalprice = totalprice;
    }

    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSalesman() {
        return salesman;
    }

    public void setSalesman(String salesman) {
        this.salesman = salesman;
    }

    public String getAccountidlist() {
        return accountidlist;
    }

    public void setAccountidlist(String accountidlist) {
        this.accountidlist = accountidlist;
    }

    public String getAccountmoneylist() {
        return accountmoneylist;
    }

    public void setAccountmoneylist(String accountmoneylist) {
        this.accountmoneylist = accountmoneylist;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getDiscountmoney() {
        return discountmoney;
    }

    public void setDiscountmoney(Double discountmoney) {
        this.discountmoney = discountmoney;
    }

    public Double getDiscountlastmoney() {
        return discountlastmoney;
    }

    public void setDiscountlastmoney(Double discountlastmoney) {
        this.discountlastmoney = discountlastmoney;
    }

    public Double getOthermoney() {
        return othermoney;
    }

    public void setOthermoney(Double othermoney) {
        this.othermoney = othermoney;
    }

    public String getOthermoneylist() {
        return othermoneylist;
    }

    public void setOthermoneylist(String othermoneylist) {
        this.othermoneylist = othermoneylist;
    }

    public String getOthermoneyitem() {
        return othermoneyitem;
    }

    public void setOthermoneyitem(String othermoneyitem) {
        this.othermoneyitem = othermoneyitem;
    }

    public Integer getAccountday() {
        return accountday;
    }

    public void setAccountday(Integer accountday) {
        this.accountday = accountday;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "DepotHead{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", subtype='" + subtype + '\'' +
                ", projectid='" + projectid + '\'' +
                ", defaultnumber='" + defaultnumber + '\'' +
                ", checker='" + checker + '\'' +
                ", number='" + number + '\'' +
                ", operpersonname='" + operpersonname + '\'' +
                ", checktime=" + checktime +
                ", createtime=" + createtime +
                ", opertime=" + opertime +
                ", organid='" + organid + '\'' +
                ", handspersonid='" + handspersonid + '\'' +
                ", accountid='" + accountid + '\'' +
                ", changeamount=" + changeamount +
                ", allocationprojectid='" + allocationprojectid + '\'' +
                ", totalprice=" + totalprice +
                ", paytype='" + paytype + '\'' +
                ", remark='" + remark + '\'' +
                ", salesman='" + salesman + '\'' +
                ", accountidlist='" + accountidlist + '\'' +
                ", accountmoneylist='" + accountmoneylist + '\'' +
                ", discount=" + discount +
                ", discountmoney=" + discountmoney +
                ", discountlastmoney=" + discountlastmoney +
                ", othermoney=" + othermoney +
                ", othermoneylist='" + othermoneylist + '\'' +
                ", othermoneyitem='" + othermoneyitem + '\'' +
                ", accountday=" + accountday +
                ", status=" + status +
                '}';
    }
}