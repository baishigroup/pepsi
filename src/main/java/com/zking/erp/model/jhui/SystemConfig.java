package com.zking.erp.model.jhui;

import lombok.ToString;

import java.io.Serializable;

@ToString
public class SystemConfig implements Serializable{
    private String id;

    private String type;

    private String name;

    private String value;

    private String description;
    private String ClientIp="0:0:0:0:0:0:0:1";


    public SystemConfig(String id, String type, String name, String value, String description) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.value = value;
        this.description = description;
    }

    public SystemConfig() {
        super();
    }

    public String getClientIp() {
        return ClientIp;
    }

    public void setClientIp(String clientIp) {
        ClientIp = clientIp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "SystemConfig{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}