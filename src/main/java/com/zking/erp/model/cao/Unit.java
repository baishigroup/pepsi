package com.zking.erp.model.cao;

import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@ToString
public class Unit implements Serializable{
    private String id;

    private String uname;

    //根据ID批量删除
    private List<String> ids;

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
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