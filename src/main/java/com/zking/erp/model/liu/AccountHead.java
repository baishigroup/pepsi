package com.zking.erp.model.liu;

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

    private String remark;

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