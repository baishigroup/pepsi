package com.zking.erp.model.liu;

import lombok.ToString;

import java.io.Serializable;

@ToString
public class InoutItem implements Serializable{
    private String id;

    private String name;

    private String type;

    private String remark;

    public InoutItem(String id, String name, String type, String remark) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.remark = remark;
    }

    public InoutItem() {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "InoutItem{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}