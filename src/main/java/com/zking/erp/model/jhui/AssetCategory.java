package com.zking.erp.model.jhui;

import lombok.ToString;

import java.io.Serializable;

@ToString
public class AssetCategory implements Serializable{
    private String id;

    private String assetname;

    private Integer isystem;

    private String description;

    public AssetCategory(String id, String assetname, Integer isystem, String description) {
        this.id = id;
        this.assetname = assetname;
        this.isystem = isystem;
        this.description = description;
    }

    public AssetCategory() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAssetname() {
        return assetname;
    }

    public void setAssetname(String assetname) {
        this.assetname = assetname;
    }

    public Integer getIsystem() {
        return isystem;
    }

    public void setIsystem(Integer isystem) {
        this.isystem = isystem;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "AssetCategory{" +
                "id='" + id + '\'' +
                ", assetname='" + assetname + '\'' +
                ", isystem=" + isystem +
                ", description='" + description + '\'' +
                '}';
    }
}