package com.zking.erp.model.jhui;

import lombok.ToString;

import java.io.Serializable;

@ToString
public class User implements Serializable{
    private String id;

    private String username;

    private String loginame;

    private String password;

    private String position;

    private String department;

    private String email;

    private String phonenum;

    private int ismanager;

    private int isystem;

    private int status;

    private String description;

    private String remark;

    //修改密码时的原始密码
    private String orgpwd = "";

    /**
     * 用户ID
     */
    private String userID = "01";

    /**
     * 用户IDs 批量操作使用
     */
    private String userIDs = "";

    private String[] ids=null;

    /**
     * 用户IP，用户记录操作日志
     */
    private String clientIp = "";

    /**
     * 根据标识判断是校验登录名称还是用户名称 0==用户名称 1==登录名称
     */
    private int checkFlag = 0;

    public User(String id, String username, String loginame, String password, String position, String department, String email, String phonenum, Byte ismanager, Byte isystem, Byte status, String description, String remark) {
        this.id = id;
        this.username = username;
        this.loginame = loginame;
        this.password = password;
        this.position = position;
        this.department = department;
        this.email = email;
        this.phonenum = phonenum;
        this.ismanager = ismanager;
        this.isystem = isystem;
        this.status = status;
        this.description = description;
        this.remark = remark;
    }

    public User() {
        super();
    }

    public String[] getIds() {
        return ids;
    }

    public void setIds(String[] ids) {
        this.ids = ids;
    }

    public int getCheckFlag() {
        return checkFlag;
    }

    public void setCheckFlag(int checkFlag) {
        this.checkFlag = checkFlag;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserIDs() {
        return userIDs;
    }

    public void setUserIDs(String userIDs) {
        this.userIDs = userIDs;
    }

    public String getOrgpwd() {
        return orgpwd;
    }

    public void setOrgpwd(String orgpwd) {
        this.orgpwd = orgpwd;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLoginame() {
        return loginame;
    }

    public void setLoginame(String loginame) {
        this.loginame = loginame;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public int getIsmanager() {
        return ismanager;
    }

    public void setIsmanager(int ismanager) {
        this.ismanager = ismanager;
    }

    public int getIsystem() {
        return isystem;
    }

    public void setIsystem(int isystem) {
        this.isystem = isystem;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", loginame='" + loginame + '\'' +
                ", password='" + password + '\'' +
                ", position='" + position + '\'' +
                ", department='" + department + '\'' +
                ", email='" + email + '\'' +
                ", phonenum='" + phonenum + '\'' +
                ", ismanager=" + ismanager +
                ", isystem=" + isystem +
                ", status=" + status +
                ", description='" + description + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}