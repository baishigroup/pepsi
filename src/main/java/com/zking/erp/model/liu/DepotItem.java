package com.zking.erp.model.liu;

import lombok.ToString;

import java.io.Serializable;

@ToString
public class DepotItem implements Serializable{
    private String id;

    private String headerid;

    private String materialid;

    private String munit;

    private Double opernumber;

    private Double basicnumber;

    private Double unitprice;

    private Double taxunitprice;

    private Double allprice;

    private String remark;

    private String img;

    private Double incidentals;

    private String depotid;

    private String anotherdepotid;

    private Double taxrate;

    private Double taxmoney;

    private Double taxlastmoney;

    private String otherfield1;

    private String otherfield2;

    private String otherfield3;

    private String otherfield4;

    private String otherfield5;

    private String mtype;

    public DepotItem(String id, String headerid, String materialid, String munit, Double opernumber, Double basicnumber, Double unitprice, Double taxunitprice, Double allprice, String remark, String img, Double incidentals, String depotid, String anotherdepotid, Double taxrate, Double taxmoney, Double taxlastmoney, String otherfield1, String otherfield2, String otherfield3, String otherfield4, String otherfield5, String mtype) {
        this.id = id;
        this.headerid = headerid;
        this.materialid = materialid;
        this.munit = munit;
        this.opernumber = opernumber;
        this.basicnumber = basicnumber;
        this.unitprice = unitprice;
        this.taxunitprice = taxunitprice;
        this.allprice = allprice;
        this.remark = remark;
        this.img = img;
        this.incidentals = incidentals;
        this.depotid = depotid;
        this.anotherdepotid = anotherdepotid;
        this.taxrate = taxrate;
        this.taxmoney = taxmoney;
        this.taxlastmoney = taxlastmoney;
        this.otherfield1 = otherfield1;
        this.otherfield2 = otherfield2;
        this.otherfield3 = otherfield3;
        this.otherfield4 = otherfield4;
        this.otherfield5 = otherfield5;
        this.mtype = mtype;
    }

    public DepotItem() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHeaderid() {
        return headerid;
    }

    public void setHeaderid(String headerid) {
        this.headerid = headerid;
    }

    public String getMaterialid() {
        return materialid;
    }

    public void setMaterialid(String materialid) {
        this.materialid = materialid;
    }

    public String getMunit() {
        return munit;
    }

    public void setMunit(String munit) {
        this.munit = munit;
    }

    public Double getOpernumber() {
        return opernumber;
    }

    public void setOpernumber(Double opernumber) {
        this.opernumber = opernumber;
    }

    public Double getBasicnumber() {
        return basicnumber;
    }

    public void setBasicnumber(Double basicnumber) {
        this.basicnumber = basicnumber;
    }

    public Double getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(Double unitprice) {
        this.unitprice = unitprice;
    }

    public Double getTaxunitprice() {
        return taxunitprice;
    }

    public void setTaxunitprice(Double taxunitprice) {
        this.taxunitprice = taxunitprice;
    }

    public Double getAllprice() {
        return allprice;
    }

    public void setAllprice(Double allprice) {
        this.allprice = allprice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Double getIncidentals() {
        return incidentals;
    }

    public void setIncidentals(Double incidentals) {
        this.incidentals = incidentals;
    }

    public String getDepotid() {
        return depotid;
    }

    public void setDepotid(String depotid) {
        this.depotid = depotid;
    }

    public String getAnotherdepotid() {
        return anotherdepotid;
    }

    public void setAnotherdepotid(String anotherdepotid) {
        this.anotherdepotid = anotherdepotid;
    }

    public Double getTaxrate() {
        return taxrate;
    }

    public void setTaxrate(Double taxrate) {
        this.taxrate = taxrate;
    }

    public Double getTaxmoney() {
        return taxmoney;
    }

    public void setTaxmoney(Double taxmoney) {
        this.taxmoney = taxmoney;
    }

    public Double getTaxlastmoney() {
        return taxlastmoney;
    }

    public void setTaxlastmoney(Double taxlastmoney) {
        this.taxlastmoney = taxlastmoney;
    }

    public String getOtherfield1() {
        return otherfield1;
    }

    public void setOtherfield1(String otherfield1) {
        this.otherfield1 = otherfield1;
    }

    public String getOtherfield2() {
        return otherfield2;
    }

    public void setOtherfield2(String otherfield2) {
        this.otherfield2 = otherfield2;
    }

    public String getOtherfield3() {
        return otherfield3;
    }

    public void setOtherfield3(String otherfield3) {
        this.otherfield3 = otherfield3;
    }

    public String getOtherfield4() {
        return otherfield4;
    }

    public void setOtherfield4(String otherfield4) {
        this.otherfield4 = otherfield4;
    }

    public String getOtherfield5() {
        return otherfield5;
    }

    public void setOtherfield5(String otherfield5) {
        this.otherfield5 = otherfield5;
    }

    public String getMtype() {
        return mtype;
    }

    public void setMtype(String mtype) {
        this.mtype = mtype;
    }

    @Override
    public String toString() {
        return "DepotItem{" +
                "id='" + id + '\'' +
                ", headerid='" + headerid + '\'' +
                ", materialid='" + materialid + '\'' +
                ", munit='" + munit + '\'' +
                ", opernumber=" + opernumber +
                ", basicnumber=" + basicnumber +
                ", unitprice=" + unitprice +
                ", taxunitprice=" + taxunitprice +
                ", allprice=" + allprice +
                ", remark='" + remark + '\'' +
                ", img='" + img + '\'' +
                ", incidentals=" + incidentals +
                ", depotid='" + depotid + '\'' +
                ", anotherdepotid='" + anotherdepotid + '\'' +
                ", taxrate=" + taxrate +
                ", taxmoney=" + taxmoney +
                ", taxlastmoney=" + taxlastmoney +
                ", otherfield1='" + otherfield1 + '\'' +
                ", otherfield2='" + otherfield2 + '\'' +
                ", otherfield3='" + otherfield3 + '\'' +
                ", otherfield4='" + otherfield4 + '\'' +
                ", otherfield5='" + otherfield5 + '\'' +
                ", mtype='" + mtype + '\'' +
                '}';
    }
}