package com.zking.erp.model.cao;

import lombok.ToString;

import java.io.Serializable;

@ToString
public class Material implements Serializable{
    private String id;

    private String categoryid;

    private String name;

    private String mfrs;

    private Double packing;

    private Double safetystock;

    private String model;

    private String standard;

    private String color;

    private String unit;

    private String remark;

    private Double retailprice;

    private Double lowprice;

    private Double presetpriceone;

    private Double presetpricetwo;

    private String firstoutunit;

    private String firstinunit;

    private String pricestrategy;

    private Integer enabled;

    private String otherfield1;

    private String otherfield2;

    private String otherfield3;

    private String mpList = ""; //商品属性

    /**
     * 分类ID
     */
    private String materialID = "01";

    /**
     * 分类IDs 批量操作使用
     */
    private String materialIDs = "";

    private String[] ids=null;

    private String UnitId;
    private String UnitName;

    private String CategoryName;

    private String clientIp;

    /**
     * CategoryIds 用于in子查询
     */
    private String CategoryIds = "1";

    public Material(String id, String categoryid, String name, String mfrs, Double packing, Double safetystock, String model, String standard, String color, String unit, String remark, Double retailprice, Double lowprice, Double presetpriceone, Double presetpricetwo, String unitId, String firstoutunit, String firstinunit, String pricestrategy, Integer enabled, String otherfield1, String otherfield2, String otherfield3) {
        this.id = id;
        this.categoryid = categoryid;
        this.name = name;
        this.mfrs = mfrs;
        this.packing = packing;
        this.safetystock = safetystock;
        this.model = model;
        this.standard = standard;
        this.color = color;
        this.unit = unit;
        this.remark = remark;
        this.retailprice = retailprice;
        this.lowprice = lowprice;
        this.presetpriceone = presetpriceone;
        this.presetpricetwo = presetpricetwo;
        this.UnitId = unitId;
        this.firstoutunit = firstoutunit;
        this.firstinunit = firstinunit;
        this.pricestrategy = pricestrategy;
        this.enabled = enabled;
        this.otherfield1 = otherfield1;
        this.otherfield2 = otherfield2;
        this.otherfield3 = otherfield3;
    }

    public Material() {
        super();
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getCategoryIds() {
        return CategoryIds;
    }

    public void setCategoryIds(String categoryIds) {
        CategoryIds = categoryIds;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public String getMpList() {
        return mpList;
    }

    public void setMpList(String mpList) {
        this.mpList = mpList;
    }

    public String getMaterialID() {
        return materialID;
    }

    public void setMaterialID(String materialID) {
        this.materialID = materialID;
    }

    public String getMaterialIDs() {
        return materialIDs;
    }

    public void setMaterialIDs(String materialIDs) {
        this.materialIDs = materialIDs;
    }

    public String[] getIds() {
        return ids;
    }

    public void setIds(String[] ids) {
        this.ids = ids;
    }

    public String getUnitId() {
        return UnitId;
    }

    public void setUnitId(String unitId){
        UnitId=this.UnitId;
    }

    public String getUnitName() {
        return UnitName;
    }

    public void setUnitName(String unitName) {
        UnitName = unitName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(String categoryid) {
        this.categoryid = categoryid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMfrs() {
        return mfrs;
    }

    public void setMfrs(String mfrs) {
        this.mfrs = mfrs;
    }

    public Double getPacking() {
        return packing;
    }

    public void setPacking(Double packing) {
        this.packing = packing;
    }

    public Double getSafetystock() {
        return safetystock;
    }

    public void setSafetystock(Double safetystock) {
        this.safetystock = safetystock;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Double getRetailprice() {
        return retailprice;
    }

    public void setRetailprice(Double retailprice) {
        this.retailprice = retailprice;
    }

    public Double getLowprice() {
        return lowprice;
    }

    public void setLowprice(Double lowprice) {
        this.lowprice = lowprice;
    }

    public Double getPresetpriceone() {
        return presetpriceone;
    }

    public void setPresetpriceone(Double presetpriceone) {
        this.presetpriceone = presetpriceone;
    }

    public Double getPresetpricetwo() {
        return presetpricetwo;
    }

    public void setPresetpricetwo(Double presetpricetwo) {
        this.presetpricetwo = presetpricetwo;
    }

    public String getFirstoutunit() {
        return firstoutunit;
    }

    public void setFirstoutunit(String firstoutunit) {
        this.firstoutunit = firstoutunit;
    }

    public String getFirstinunit() {
        return firstinunit;
    }

    public void setFirstinunit(String firstinunit) {
        this.firstinunit = firstinunit;
    }

    public String getPricestrategy() {
        return pricestrategy;
    }

    public void setPricestrategy(String pricestrategy) {
        this.pricestrategy = pricestrategy;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
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

    @Override
    public String toString() {
        return "Material{" +
                "id='" + id + '\'' +
                ", categoryid='" + categoryid + '\'' +
                ", name='" + name + '\'' +
                ", mfrs='" + mfrs + '\'' +
                ", packing=" + packing +
                ", safetystock=" + safetystock +
                ", model='" + model + '\'' +
                ", standard='" + standard + '\'' +
                ", color='" + color + '\'' +
                ", unit='" + unit + '\'' +
                ", remark='" + remark + '\'' +
                ", retailprice=" + retailprice +
                ", lowprice=" + lowprice +
                ", presetpriceone=" + presetpriceone +
                ", presetpricetwo=" + presetpricetwo +
                ", unitid='" + UnitId + '\'' +
                ", firstoutunit='" + firstoutunit + '\'' +
                ", firstinunit='" + firstinunit + '\'' +
                ", pricestrategy='" + pricestrategy + '\'' +
                ", enabled=" + enabled +
                ", otherfield1='" + otherfield1 + '\'' +
                ", otherfield2='" + otherfield2 + '\'' +
                ", otherfield3='" + otherfield3 + '\'' +
                '}';
    }
}