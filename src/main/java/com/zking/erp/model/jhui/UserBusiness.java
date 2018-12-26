package com.zking.erp.model.jhui;

import lombok.ToString;

import java.io.Serializable;

@ToString
public class UserBusiness implements Serializable{

    private String id;

    private String type;

    private String keyid;

    private String value;

    private String btnstr;

    /**
     * 角色名称
     */
    private String Type = "";


    /**
     * 用户IP，用户记录操作日志
     */
    private String clientIp = "";

    /**
     * 分类ID
     */
    private String userBusinessID = "01";

    public UserBusiness(String id, String type, String keyid, String value, String btnstr) {
        this.id = id;
        this.type = type;
        this.keyid = keyid;
        this.value = value;
        this.btnstr = btnstr;
    }

    public UserBusiness() {
        super();
    }

    public String getUserBusinessID() {
        return userBusinessID;
    }

    public void setUserBusinessID(String userBusinessID) {
        this.userBusinessID = userBusinessID;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKeyid() {
        return keyid;
    }

    public void setKeyid(String keyid) {
        this.keyid = keyid;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getBtnstr() {
        return btnstr;
    }

    public void setBtnstr(String btnstr) {
        this.btnstr = btnstr;
    }

    @Override
    public String toString() {
        return "UserBusiness{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", keyid='" + keyid + '\'' +
                ", value='" + value + '\'' +
                ", btnstr='" + btnstr + '\'' +
                '}';
    }
}