package com.zking.erp.model.all;

import lombok.ToString;

import java.io.Serializable;

@ToString
public class AssetName implements Serializable{
    private String id;

    private String assetname;

    private String assetcategoryid;

    private Integer isystem;

    private String description;

    private Integer isconsumables;

    public AssetName(String id, String assetname, String assetcategoryid, Integer isystem, String description, Integer isconsumables) {
        this.id = id;
        this.assetname = assetname;
        this.assetcategoryid = assetcategoryid;
        this.isystem = isystem;
        this.description = description;
        this.isconsumables = isconsumables;
    }

    public AssetName() {
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

    public String getAssetcategoryid() {
        return assetcategoryid;
    }

    public void setAssetcategoryid(String assetcategoryid) {
        this.assetcategoryid = assetcategoryid;
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

    public Integer getIsconsumables() {
        return isconsumables;
    }

    public void setIsconsumables(Integer isconsumables) {
        this.isconsumables = isconsumables;
    }

    @Override
    public String toString() {
        return "AssetName{" +
                "id='" + id + '\'' +
                ", assetname='" + assetname + '\'' +
                ", assetcategoryid='" + assetcategoryid + '\'' +
                ", isystem=" + isystem +
                ", description='" + description + '\'' +
                ", isconsumables=" + isconsumables +
                '}';
    }
}