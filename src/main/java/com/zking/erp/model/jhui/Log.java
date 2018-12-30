package com.zking.erp.model.jhui;

import lombok.ToString;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@ToString
public class Log implements Serializable{
    private String id;

    private String userid;

    private String operation;


    private Timestamp createtime;

    private int status;

    private String contentdetails;

    private String remark;

    private String username;

    /**
     * 开始时间
     */
    private String beginTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 日志ID
     */
    private String logID = "01";

    /**
     * 日志IDs 批量操作使用
     */
    private String logIDs = "";

    /**
     * 用户IP，用户记录操作日志
     */
    private String clientip = "";

    public Log(String id, String userid, String operation, String clientip, Timestamp createtime, int status, String contentdetails, String remark) {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getLogID() {
        return logID;
    }

    public void setLogID(String logID) {
        this.logID = logID;
    }

    public String getLogIDs() {
        return logIDs;
    }

    public void setLogIDs(String logIDs) {
        this.logIDs = logIDs;
    }

    public String getClientIp() {
        return clientip;
    }

    public void setClientIp(String clientIp) {
        this.clientip = clientIp;
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

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
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
                ", clientIp='" + clientip + '\'' +
                ", createtime=" + createtime +
                ", status=" + status +
                ", contentdetails='" + contentdetails + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    public Log( String userid, String operation, String clientip,Timestamp createtime, int status, String contentdetails, String remark) {
        this.userid = userid;
        this.operation = operation;
        this.createtime = createtime;
        this.status = status;
        this.contentdetails = contentdetails;
        this.remark = remark;
        this.clientip = clientip;
    }
}