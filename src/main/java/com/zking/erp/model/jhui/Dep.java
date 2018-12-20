package com.zking.erp.model.jhui;

import lombok.ToString;

import java.io.Serializable;

@ToString
public class Dep implements Serializable{
    private String uuid;

    private String name;

    private String tele;

    public Dep(String uuid, String name, String tele) {
        this.uuid = uuid;
        this.name = name;
        this.tele = tele;
    }

    public Dep() {
        super();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    @Override
    public String toString() {
        return "Dep{" +
                "uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", tele='" + tele + '\'' +
                '}';
    }
}