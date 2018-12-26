package com.zking.erp.model.jhui;

import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@ToString
public class AccountHead implements Serializable{
    private String id;

    private String type;

    private String organid;

    private String handspersonid;

    private Double changeamount;

    private Double totalprice;

    private String accountid;

    private String billno;

    private Date billtime;

    //前端修改
    private String billtime2;

    private String remark;

    private String OrganName;

    private String HandsPersonName;

    private String AccountName;

    private String BeginTime; //查询开始时间
    private String EndTime;  //查询结束时间
    private String MonthTime;  //查询月份

    /**
     * 用户IP，用户记录操作日志
     */
    private String clientIp = "";

    /**
     * 分类ID
     */
    private String accountHeadID = "01";

    /**
     * 分类IDs 批量操作使用
     */
    private String accountHeadIDs = "";
    private String[] ids=null;

    public AccountHead(String id, String type, String organid, String handspersonid, Double changeamount, Double totalprice, String accountid, String billno, Date billtime, String remark) {
        this.id = id;
        this.type = type;
        this.organid = organid;
        this.handspersonid = handspersonid;
        this.changeamount = changeamount;
        this.totalprice = totalprice;
        this.accountid = accountid;
        this.billno = billno;
        this.billtime = billtime;
        this.remark = remark;
    }

    public AccountHead() {
        super();
    }

    public String[] getIds() {
        return ids;
    }

    public void setIds(String[] ids) {
        this.ids = ids;
    }

    public String getBilltime2() {
        return billtime2;
    }

    public void setBilltime2(String billtime2) {
        this.billtime2 = billtime2;
    }

    public String getAccountHeadID() {
        return accountHeadID;
    }

    public void setAccountHeadID(String accountHeadID) {
        this.accountHeadID = accountHeadID;
    }

    public String getAccountHeadIDs() {
        return accountHeadIDs;
    }

    public void setAccountHeadIDs(String accountHeadIDs) {
        this.accountHeadIDs = accountHeadIDs;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getOrganName() {
        return OrganName;
    }

    public void setOrganName(String organName) {
        OrganName = organName;
    }

    public String getHandsPersonName() {
        return HandsPersonName;
    }

    public void setHandsPersonName(String handsPersonName) {
        HandsPersonName = handsPersonName;
    }

    public String getAccountName() {
        return AccountName;
    }

    public void setAccountName(String accountName) {
        AccountName = accountName;
    }

    public String getBeginTime() {
        return BeginTime;
    }

    public void setBeginTime(String beginTime) {
        BeginTime = beginTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public String getMonthTime() {
        return MonthTime;
    }

    public void setMonthTime(String monthTime) {
        MonthTime = monthTime;
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

    public Double getChangeamount() {
        return changeamount;
    }

    public void setChangeamount(Double changeamount) {
        this.changeamount = changeamount;
    }

    public Double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Double totalprice) {
        this.totalprice = totalprice;
    }

    public String getAccountid() {
        return accountid;
    }

    public void setAccountid(String accountid) {
        this.accountid = accountid;
    }

    public String getBillno() {
        return billno;
    }

    public void setBillno(String billno) {
        this.billno = billno;
    }

    public Date getBilltime() {
        return billtime;
    }

    public void setBilltime(Date billtime) {
        this.billtime = billtime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "AccountHead{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", organid='" + organid + '\'' +
                ", handspersonid='" + handspersonid + '\'' +
                ", changeamount=" + changeamount +
                ", totalprice=" + totalprice +
                ", accountid='" + accountid + '\'' +
                ", billno='" + billno + '\'' +
                ", billtime=" + billtime +
                ", remark='" + remark + '\'' +
                '}';
    }
}