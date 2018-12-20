package com.zking.erp.model.all;

import lombok.ToString;

import java.io.Serializable;

@ToString
public class MaterialCategory implements Serializable{
    private String id;

    private String name;

    private Integer categorylevel;

    private String parentid;

    public MaterialCategory(String id, String name, Integer categorylevel, String parentid) {
        this.id = id;
        this.name = name;
        this.categorylevel = categorylevel;
        this.parentid = parentid;
    }

    public MaterialCategory() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCategorylevel() {
        return categorylevel;
    }

    public void setCategorylevel(Integer categorylevel) {
        this.categorylevel = categorylevel;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    @Override
    public String toString() {
        return "MaterialCategory{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", categorylevel=" + categorylevel +
                ", parentid='" + parentid + '\'' +
                '}';
    }
}