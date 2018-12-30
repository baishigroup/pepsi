package com.zking.erp.model.jhui;

import lombok.ToString;

import java.io.Serializable;

@ToString
public class Person implements Serializable{
    private String id;

    private String type;

    private String name;

    /**
     * 分类ID
     */
    private String personID = "0";

    /**
     * 分类IDs 批量操作使用
     */
    private String personIDs = "";
    private String[] ids=null;

    public Person(String id, String type, String name) {
        this.id = id;
        this.type = type;
        this.name = name;
    }

    public Person() {
        super();
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getPersonIDs() {
        return personIDs;
    }

    public void setPersonIDs(String personIDs) {
        this.personIDs = personIDs;
    }

    public String[] getIds() {
        return ids;
    }

    public void setIds(String[] ids) {
        this.ids = ids;
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

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}