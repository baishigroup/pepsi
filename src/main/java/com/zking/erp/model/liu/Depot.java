package com.zking.erp.model.liu;

import lombok.ToString;

import java.io.Serializable;

@ToString
public class Depot implements Serializable {
    private String id;

    private String name;

    private String address;

    private Double warehousing;

    private Double truckage;

    private Integer type;

    private String sort;

    private String remark;

    public Depot(String id, String name, String address, Double warehousing, Double truckage, Integer type, String sort, String remark) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.warehousing = warehousing;
        this.truckage = truckage;
        this.type = type;
        this.sort = sort;
        this.remark = remark;
    }

    public Depot() {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getWarehousing() {
        return warehousing;
    }

    public void setWarehousing(Double warehousing) {
        this.warehousing = warehousing;
    }

    public Double getTruckage() {
        return truckage;
    }

    public void setTruckage(Double truckage) {
        this.truckage = truckage;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Depot{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", warehousing=" + warehousing +
                ", truckage=" + truckage +
                ", type=" + type +
                ", sort='" + sort + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}