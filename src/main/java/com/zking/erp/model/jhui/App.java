package com.zking.erp.model.jhui;

import lombok.ToString;

import java.io.Serializable;

@ToString
public class App implements Serializable{
    private String id;

    private String number;

    private String name;

    private String type;

    private String icon;

    private String url;

    private String width;

    private String height;

    private Boolean resize;

    private Boolean openmax;

    private Boolean flash;

    private String zl;

    private String sort;

    private String remark;

    private Boolean enabled;

    /**
     * 用户IP，用户记录操作日志
     */
    private String clientIp = "";

    /**
     * UBType，UserBusiness类型
     */
    private String UBType = "";

    /**
     * UBKeyId，UserBusiness关键id
     */
    private String UBKeyId = "";

    /**
     * 分类ID
     */
    private String appID = "01";

    /**
     * 分类IDs 批量操作使用
     */
    private String appIDs = "";

    private String[] ids=null;

    public App(String id, String number, String name, String type, String icon, String url, String width, String height, Boolean resize, Boolean openmax, Boolean flash, String zl, String sort, String remark, Boolean enabled) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.type = type;
        this.icon = icon;
        this.url = url;
        this.width = width;
        this.height = height;
        this.resize = resize;
        this.openmax = openmax;
        this.flash = flash;
        this.zl = zl;
        this.sort = sort;
        this.remark = remark;
        this.enabled = enabled;
    }

    public App() {
        super();
    }

    public String[] getIds() {
        return ids;
    }

    public void setIds(String[] ids) {
        this.ids = ids;
    }

    public String getAppID() {
        return appID;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }

    public String getAppIDs() {
        return appIDs;
    }

    public void setAppIDs(String appIDs) {
        this.appIDs = appIDs;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getUBType() {
        return UBType;
    }

    public void setUBType(String UBType) {
        this.UBType = UBType;
    }

    public String getUBKeyId() {
        return UBKeyId;
    }

    public void setUBKeyId(String UBKeyId) {
        this.UBKeyId = UBKeyId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public Boolean getResize() {
        return resize;
    }

    public void setResize(Boolean resize) {
        this.resize = resize;
    }

    public Boolean getOpenmax() {
        return openmax;
    }

    public void setOpenmax(Boolean openmax) {
        this.openmax = openmax;
    }

    public Boolean getFlash() {
        return flash;
    }

    public void setFlash(Boolean flash) {
        this.flash = flash;
    }

    public String getZl() {
        return zl;
    }

    public void setZl(String zl) {
        this.zl = zl;
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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "App{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", icon='" + icon + '\'' +
                ", url='" + url + '\'' +
                ", width='" + width + '\'' +
                ", height='" + height + '\'' +
                ", resize=" + resize +
                ", openmax=" + openmax +
                ", flash=" + flash +
                ", zl='" + zl + '\'' +
                ", sort='" + sort + '\'' +
                ", remark='" + remark + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}