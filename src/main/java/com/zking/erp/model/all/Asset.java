package com.zking.erp.model.all;

import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@ToString
public class Asset implements Serializable{
    private String id;

    private String assetnameid;

    private String location;

    private String labels;

    private Integer status;

    private String userid;

    private Double price;

    private Date purchasedate;

    private Date periodofvalidity;

    private Date warrantydate;

    private String assetnum;

    private String serialnum;

    private String supplier;

    private String description;

    private String addmonth;

    private Date createtime;

    private String creator;

    private Date updatetime;

    private String updator;

    public Asset(String id, String assetnameid, String location, String labels, Integer status, String userid, Double price, Date purchasedate, Date periodofvalidity, Date warrantydate, String assetnum, String serialnum, String supplier, String description, String addmonth, Date createtime, String creator, Date updatetime, String updator) {
        this.id = id;
        this.assetnameid = assetnameid;
        this.location = location;
        this.labels = labels;
        this.status = status;
        this.userid = userid;
        this.price = price;
        this.purchasedate = purchasedate;
        this.periodofvalidity = periodofvalidity;
        this.warrantydate = warrantydate;
        this.assetnum = assetnum;
        this.serialnum = serialnum;
        this.supplier = supplier;
        this.description = description;
        this.addmonth = addmonth;
        this.createtime = createtime;
        this.creator = creator;
        this.updatetime = updatetime;
        this.updator = updator;
    }

    public Asset() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAssetnameid() {
        return assetnameid;
    }

    public void setAssetnameid(String assetnameid) {
        this.assetnameid = assetnameid;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getPurchasedate() {
        return purchasedate;
    }

    public void setPurchasedate(Date purchasedate) {
        this.purchasedate = purchasedate;
    }

    public Date getPeriodofvalidity() {
        return periodofvalidity;
    }

    public void setPeriodofvalidity(Date periodofvalidity) {
        this.periodofvalidity = periodofvalidity;
    }

    public Date getWarrantydate() {
        return warrantydate;
    }

    public void setWarrantydate(Date warrantydate) {
        this.warrantydate = warrantydate;
    }

    public String getAssetnum() {
        return assetnum;
    }

    public void setAssetnum(String assetnum) {
        this.assetnum = assetnum;
    }

    public String getSerialnum() {
        return serialnum;
    }

    public void setSerialnum(String serialnum) {
        this.serialnum = serialnum;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddmonth() {
        return addmonth;
    }

    public void setAddmonth(String addmonth) {
        this.addmonth = addmonth;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator;
    }

    @Override
    public String toString() {
        return "Asset{" +
                "id='" + id + '\'' +
                ", assetnameid='" + assetnameid + '\'' +
                ", location='" + location + '\'' +
                ", labels='" + labels + '\'' +
                ", status=" + status +
                ", userid='" + userid + '\'' +
                ", price=" + price +
                ", purchasedate=" + purchasedate +
                ", periodofvalidity=" + periodofvalidity +
                ", warrantydate=" + warrantydate +
                ", assetnum='" + assetnum + '\'' +
                ", serialnum='" + serialnum + '\'' +
                ", supplier='" + supplier + '\'' +
                ", description='" + description + '\'' +
                ", addmonth='" + addmonth + '\'' +
                ", createtime=" + createtime +
                ", creator='" + creator + '\'' +
                ", updatetime=" + updatetime +
                ", updator='" + updator + '\'' +
                '}';
    }
}