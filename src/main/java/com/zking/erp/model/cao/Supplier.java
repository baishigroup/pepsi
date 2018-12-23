package com.zking.erp.model.cao;

import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@ToString
public class Supplier implements Serializable{
    private String id;

    private String supplier;

    private String contacts;

    private String phonenum;

    private String email;

    private String description;

    private Integer isystem;

    private String type;

    private Integer enabled;

    private Double advancein;

    private Double beginneedget;

    private Double beginneedpay;

    private Double allneedget;

    private Double allneedpay;

    private String fax;

    private String telephone;

    private String address;

    private String taxnum;

    private String bankname;

    private String accountnumber;

    private Double taxrate;

    //根据ID批量删除
    private List<String> ids;

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public Supplier(String id, String supplier, String contacts, String phonenum, String email, String description, Integer isystem, String type, Integer enabled, Double advancein, Double beginneedget, Double beginneedpay, Double allneedget, Double allneedpay, String fax, String telephone, String address, String taxnum, String bankname, String accountnumber, Double taxrate) {
        this.id = id;
        this.supplier = supplier;
        this.contacts = contacts;
        this.phonenum = phonenum;
        this.email = email;
        this.description = description;
        this.isystem = isystem;
        this.type = type;
        this.enabled = enabled;
        this.advancein = advancein;
        this.beginneedget = beginneedget;
        this.beginneedpay = beginneedpay;
        this.allneedget = allneedget;
        this.allneedpay = allneedpay;
        this.fax = fax;
        this.telephone = telephone;
        this.address = address;
        this.taxnum = taxnum;
        this.bankname = bankname;
        this.accountnumber = accountnumber;
        this.taxrate = taxrate;
    }

    public Supplier() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIsystem() {
        return isystem;
    }

    public void setIsystem(Integer isystem) {
        this.isystem = isystem;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public Double getAdvancein() {
        return advancein;
    }

    public void setAdvancein(Double advancein) {
        this.advancein = advancein;
    }

    public Double getBeginneedget() {
        return beginneedget;
    }

    public void setBeginneedget(Double beginneedget) {
        this.beginneedget = beginneedget;
    }

    public Double getBeginneedpay() {
        return beginneedpay;
    }

    public void setBeginneedpay(Double beginneedpay) {
        this.beginneedpay = beginneedpay;
    }

    public Double getAllneedget() {
        return allneedget;
    }

    public void setAllneedget(Double allneedget) {
        this.allneedget = allneedget;
    }

    public Double getAllneedpay() {
        return allneedpay;
    }

    public void setAllneedpay(Double allneedpay) {
        this.allneedpay = allneedpay;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTaxnum() {
        return taxnum;
    }

    public void setTaxnum(String taxnum) {
        this.taxnum = taxnum;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(String accountnumber) {
        this.accountnumber = accountnumber;
    }

    public Double getTaxrate() {
        return taxrate;
    }

    public void setTaxrate(Double taxrate) {
        this.taxrate = taxrate;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "id='" + id + '\'' +
                ", supplier='" + supplier + '\'' +
                ", contacts='" + contacts + '\'' +
                ", phonenum='" + phonenum + '\'' +
                ", email='" + email + '\'' +
                ", description='" + description + '\'' +
                ", isystem=" + isystem +
                ", type='" + type + '\'' +
                ", enabled=" + enabled +
                ", advancein=" + advancein +
                ", beginneedget=" + beginneedget +
                ", beginneedpay=" + beginneedpay +
                ", allneedget=" + allneedget +
                ", allneedpay=" + allneedpay +
                ", fax='" + fax + '\'' +
                ", telephone='" + telephone + '\'' +
                ", address='" + address + '\'' +
                ", taxnum='" + taxnum + '\'' +
                ", bankname='" + bankname + '\'' +
                ", accountnumber='" + accountnumber + '\'' +
                ", taxrate=" + taxrate +
                '}';
    }
}