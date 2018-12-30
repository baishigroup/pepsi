package com.zking.erp.model.cao;

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

    private String MaterialName;
    private String MaterialModel;
    private String MaterialUnit;


    private String UnitId;
    private String UnitName;

    private String DepotName;
    //调拨时。对方的仓库
    private String AnotherDepotName;



    private String Inserted = "";    //json插入记录
    private String Deleted = "";    //json删除记录
    private String Updated = "";    //json修改记录

    private String HeadIds = "";    //表头集合列表
    private String MaterialIds = "";    //材料列表
    private String MonthTime = "";  //月份
    private String ProjectId = null;
    private String browserType = "";

    private String mpList = ""; //商品属性

    private String clientIp="";

    private String number;
    private String type;
    private String OperTime;

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOperTime() {
        return OperTime;
    }

    public void setOperTime(String operTime) {
        OperTime = operTime;
    }

    public String getMaterialName() {
        return MaterialName;
    }

    public void setMaterialName(String materialName) {
        MaterialName = materialName;
    }

    public String getMaterialModel() {
        return MaterialModel;
    }

    public void setMaterialModel(String materialModel) {
        MaterialModel = materialModel;
    }

    public String getMaterialUnit() {
        return MaterialUnit;
    }

    public void setMaterialUnit(String materialUnit) {
        MaterialUnit = materialUnit;
    }

    public String getUnitId() {
        return UnitId;
    }

    public void setUnitId(String unitId) {
        UnitId = unitId;
    }

    public String getUnitName() {
        return UnitName;
    }

    public void setUnitName(String unitName) {
        UnitName = unitName;
    }

    public String getDepotName() {
        return DepotName;
    }

    public void setDepotName(String depotName) {
        DepotName = depotName;
    }

    public String getAnotherDepotName() {
        return AnotherDepotName;
    }

    public void setAnotherDepotName(String anotherDepotName) {
        AnotherDepotName = anotherDepotName;
    }

    public String getInserted() {
        return Inserted;
    }

    public void setInserted(String inserted) {
        Inserted = inserted;
    }

    public String getDeleted() {
        return Deleted;
    }

    public void setDeleted(String deleted) {
        Deleted = deleted;
    }

    public String getUpdated() {
        return Updated;
    }

    public void setUpdated(String updated) {
        Updated = updated;
    }

    public String getHeadIds() {
        return HeadIds;
    }

    public void setHeadIds(String headIds) {
        HeadIds = headIds;
    }

    public String getMaterialIds() {
        return MaterialIds;
    }

    public void setMaterialIds(String materialIds) {
        MaterialIds = materialIds;
    }

    public String getMonthTime() {
        return MonthTime;
    }

    public void setMonthTime(String monthTime) {
        MonthTime = monthTime;
    }

    public String getProjectId() {
        return ProjectId;
    }

    public void setProjectId(String projectId) {
        ProjectId = projectId;
    }

    public String getBrowserType() {
        return browserType;
    }

    public void setBrowserType(String browserType) {
        this.browserType = browserType;
    }

    public String getMpList() {
        return mpList;
    }

    public void setMpList(String mpList) {
        this.mpList = mpList;
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