package com.zking.erp.model.cao;

import lombok.ToString;

import java.io.Serializable;

@ToString
public class Unit implements Serializable{
    private String id;

    private String uname;

    /**
     * IDs 批量操作使用
     */
    private String unitIDs = "";

    private String[] ids=null;

    private String clientIp;

    public String getUnitIDs() {
        return unitIDs;
    }

    public void setUnitIDs(String unitIDs) {
        this.unitIDs = unitIDs;
    }

    public String[] getIds() {
        return ids;
    }

    public void setIds(String[] ids) {
        this.ids = ids;
    }

    public Unit(String id, String uname) {
        this.id = id;
        this.uname = uname;
    }

    public Unit(String uname) {
        this.uname = uname;
    }

    public Unit() {
        super();
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

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    @Override
    public String toString() {
        return "Unit{" +
                "id='" + id + '\'' +
                ", uname='" + uname + '\'' +
                '}';
    }
}