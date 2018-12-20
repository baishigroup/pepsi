package com.zking.erp.model.liu;

import lombok.ToString;

import java.io.Serializable;

@ToString
public class Unit implements Serializable{
    private String id;

    private String uname;

    public Unit(String id, String uname) {
        this.id = id;
        this.uname = uname;
    }

    public Unit() {
        super();
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