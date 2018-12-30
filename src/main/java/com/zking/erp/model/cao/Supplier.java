package com.zking.erp.model.cao;

import lombok.ToString;

import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@ToString
public class Supplier implements Serializable{
    private String id="";

    private String supplier="";

    private String contacts="";

    private String phonenum="";

    private String email="";

    private String description="";

    private Integer isystem=1;

    private String type="";

    private Integer enabled=0;

    private Double advancein=0d;

    private Double beginneedget=0d;

    private Double beginneedpay=0d;

    private Double allneedget=0d;

    private Double allneedpay=0d;

    private String fax="";

    private String telephone="";

    private String address="";

    private String taxnum="";

    private String bankname="";

    private String accountnumber="";

    private Double taxrate=0d;

    //根据ID批量删除
    private List<String> supplierIDs;

    public List<String> getSupplierIDs() {
        return supplierIDs;
    }

    /**
     * 用户IP，用户记录操作日志
     */
    private String clientIp = "";
    private String browserType = ""; //浏览器类型
    private String fileName = ""; //文件名称
    private InputStream excelStream;  //输入流，导出excel文件

    /**
     * 名称
     */
    public static final int EXCEL_SUPPLIER = 0;

    /**
     * 类型
     */
    public static final int EXCEL_TYPE = 1;

    /**
     * 联系人
     */
    public static final int EXCEL_CONTACTS = 2;

    /**
     * 电话
     */
    public static final int EXCEL_PHONE_NUM = 3;

    /**
     * 电子邮箱
     */
    public static final int EXCEL_EMAIL = 4;

    /**
     * 预收款
     */
    public static final int EXCEL_ADVANCE_IN = 5;

    /**
     * 期初应收
     */
    public static final int EXCEL_BEGIN_NEED_GET = 6;

    /**
     * 期初应付
     */
    public static final int EXCEL_BEGIN_NEED_PAY = 7;

    /**
     * 备注
     */
    public static final int EXCEL_DESCRIPTION = 8;

    /**
     * 传真
     */
    public static final int EXCEL_FAX = 9;

    /**
     * 手机
     */
    public static final int EXCEL_TELEPHONE = 10;

    /**
     * 地址
     */
    public static final int EXCEL_ADDRESS = 11;

    /**
     * 纳税人识别号
     */
    public static final int EXCEL_TAX_NUM = 12;

    /**
     * 开户行
     */
    public static final int EXCEL_BANK_NAME = 13;

    /**
     * 账号
     */
    public static final int EXCEL_ACCOUNT_NUMBER = 14;

    /**
     * 税率
     */
    public static final int EXCEL_TAX_RATE = 15;


    /**
     * 表头
     */
    public static final int EXCEL_TABLE_HEAD = 0;

    /**
     * action返回excel结果
     */
    public static final String EXCEL = "excel";


    //----------以下属性导入exel表格使用--------------------
    /**
     * 类型 right--正确 warn--警告  wrong--错误
     */
    private Map<Integer, String> cellInfo;

    /**
     * 行号
     */
    private Integer rowLineNum;

    private String advanceInStr;

    private String beginNeedGetStr;

    private String beginNeedPayStr;

    private String taxRateStr;

    private String enabledStr;

    public Map<Integer, String> getCellInfo() {
        return cellInfo;
    }

    public void setCellInfo(Map<Integer, String> cellInfo) {
        this.cellInfo = cellInfo;
    }

    public Integer getRowLineNum() {
        return rowLineNum;
    }

    public void setRowLineNum(Integer rowLineNum) {
        this.rowLineNum = rowLineNum;
    }

    public String getAdvanceInStr() {
        return advanceInStr;
    }

    public void setAdvanceInStr(String advanceInStr) {
        this.advanceInStr = advanceInStr;
    }

    public String getBeginNeedGetStr() {
        return beginNeedGetStr;
    }

    public void setBeginNeedGetStr(String beginNeedGetStr) {
        this.beginNeedGetStr = beginNeedGetStr;
    }

    public String getBeginNeedPayStr() {
        return beginNeedPayStr;
    }

    public void setBeginNeedPayStr(String beginNeedPayStr) {
        this.beginNeedPayStr = beginNeedPayStr;
    }

    public String getTaxRateStr() {
        return taxRateStr;
    }

    public void setTaxRateStr(String taxRateStr) {
        this.taxRateStr = taxRateStr;
    }

    public String getEnabledStr() {
        return enabledStr;
    }

    public void setEnabledStr(String enabledStr) {
        this.enabledStr = enabledStr;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getBrowserType() {
        return browserType;
    }

    public void setBrowserType(String browserType) {
        this.browserType = browserType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public InputStream getExcelStream() {
        return excelStream;
    }

    public void setExcelStream(InputStream excelStream) {
        this.excelStream = excelStream;
    }

    public void setSupplierIDs(List<String> supplierIDs) {
        this.supplierIDs = supplierIDs;
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