package com.zking.erp.model.jhui;

import lombok.ToString;

import java.io.Serializable;
import java.util.Arrays;

@ToString
public class MaterialProperty implements Serializable{
    private String id;

    private String nativename;

    private Integer enabled;

    private String sort;

    private String anothername;

    /**
     * 用户IP，用户记录操作日志
     */
    private String clientIp = "";


    public MaterialProperty(String id, String nativename, Integer enabled, String sort, String anothername) {
        this.id = id;
        this.nativename = nativename;
        this.enabled = enabled;
        this.sort = sort;
        this.anothername = anothername;
    }

    public MaterialProperty() {
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

    public String getNativename() {
        return nativename;
    }

    public void setNativename(String nativename) {
        this.nativename = nativename;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getAnothername() {
        return anothername;
    }

    public void setAnothername(String anothername) {
        this.anothername = anothername;
    }

    @Override
    public String toString() {
        return "MaterialProperty{" +
                "id=" + id +
                ", nativename='" + nativename + '\'' +
                ", enabled=" + enabled +
                ", sort='" + sort + '\'' +
                ", anothername='" + anothername + '\'' +
                '}';
    }
}