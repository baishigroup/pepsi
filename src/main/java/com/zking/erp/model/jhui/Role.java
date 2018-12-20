package com.zking.erp.model.jhui;

import lombok.ToString;

import java.io.Serializable;

@ToString
public class Role implements Serializable{
    private String id;

    private String name;

    private String type;

    private String value;

    private String description;

    public Role(String id, String name, String type, String value, String description) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.value = value;
        this.description = description;
    }

    public Role() {
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
        return "Role{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", value='" + value + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}