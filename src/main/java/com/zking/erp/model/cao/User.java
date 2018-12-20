package com.zking.erp.model.cao;

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

    private Byte ismanager;

    private Byte isystem;

    private Byte status;

    private String description;

    private String remark;

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

    public Byte getIsmanager() {
        return ismanager;
    }

    public void setIsmanager(Byte ismanager) {
        this.ismanager = ismanager;
    }

    public Byte getIsystem() {
        return isystem;
    }

    public void setIsystem(Byte isystem) {
        this.isystem = isystem;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
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