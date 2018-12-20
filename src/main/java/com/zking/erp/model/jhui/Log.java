package com.zking.erp.model.jhui;

import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@ToString
public class Log implements Serializable{
    private String id;

    private String userid;

    private String operation;

    private String clientip;

    private Date createtime;

    private Byte status;

    private String contentdetails;

    private String remark;

    public Log(String id, String userid, String operation, String clientip, Date createtime, Byte status, String contentdetails, String remark) {
        this.id = id;
        this.userid = userid;
        this.operation = operation;
        this.clientip = clientip;
        this.createtime = createtime;
        this.status = status;
        this.contentdetails = contentdetails;
        this.remark = remark;
    }

    public Log() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getClientip() {
        return clientip;
    }

    public void setClientip(String clientip) {
        this.clientip = clientip;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getContentdetails() {
        return contentdetails;
    }

    public void setContentdetails(String contentdetails) {
        this.contentdetails = contentdetails;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id='" + id + '\'' +
                ", userid='" + userid + '\'' +
                ", operation='" + operation + '\'' +
                ", clientip='" + clientip + '\'' +
                ", createtime=" + createtime +
                ", status=" + status +
                ", contentdetails='" + contentdetails + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}