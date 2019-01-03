package com.zking.erp.controller.jhui;

import com.zking.erp.base.BaseController;
import com.zking.erp.model.jhui.DepotHead;
import com.zking.erp.model.jhui.DepotItem;
import com.zking.erp.model.jhui.Log;
import com.zking.erp.model.jhui.Material;
import com.zking.erp.service.jhui.IDepotItemJService;
import com.zking.erp.service.jhui.IDepotJService;
import com.zking.erp.service.jhui.IMaterialJService;
import com.zking.erp.util.PageBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

@Controller
@RequestMapping("/depotItem")
public class DepotItemJController extends BaseController{

    @Autowired
    private IDepotItemJService depotItemService;
    @Autowired
    private IMaterialJService materialService;

    /**
     * 根据商品id和仓库id查询库存数量
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/findStockNumById")
    public Map<String,Object> findStockNumById(DepotItem model) {
        try {
            List<DepotItem> dataList=depotItemService.queryDepotItemByMaterialId(model);
            Map<String, Object> outer = new HashMap<String, Object>();
            String pid = model.getProjectId();
            List dataArray = new ArrayList();
            if (null != dataList) {
                for (DepotItem depotItem : dataList) {
                    Map<String, Object> item = new HashMap<String, Object>();
                    Integer prevSum = sumNumber("入库", pid, depotItem.getMaterialid(), model.getMonthTime(), 1) - sumNumber("出库", pid, depotItem.getMaterialid(), model.getMonthTime(), 1);
                    Integer InSum = sumNumber("入库", pid, depotItem.getMaterialid(), model.getMonthTime(), 2);
                    Integer OutSum = sumNumber("出库", pid, depotItem.getMaterialid(), model.getMonthTime(), 2);
                    item.put("MaterialId", depotItem.getMaterialid() == null ? "" : depotItem.getMaterialid());
                    item.put("MaterialName", depotItem.getMaterialName());
                    item.put("MaterialModel", depotItem.getMaterialModel());
                    item.put("thisSum", prevSum + InSum - OutSum);
                    dataArray.add(item);
                }
            }
            outer.put("rows", dataArray);
            //回写查询结果
            return outer;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>>>>>>>查找信息异常");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 数量合计
     *
     * @param type
     * @param MId
     * @param MonthTime
     * @param isPrev
     * @return
     */
    @SuppressWarnings("unchecked")
    public Integer sumNumber(String type, String ProjectId, String MId, String MonthTime, int isPrev) {
        Integer sumNumber = 0;
        String allNumber = "";
        try {
            List<DepotItem> depotItems = depotItemService.queryByType(type, ProjectId, MId, MonthTime, isPrev);
            if (depotItems != null) {
                for (DepotItem depotItem : depotItems) {
                    if(depotItem!=null) {
                        Object dl = depotItem.getBasicnumber(); //获取对象
                        allNumber = allNumber + dl.toString() + ",";
                    }
                }
            }

            if (allNumber.equals("null")||allNumber.equals("")) {
                allNumber = "0";
            }else {
                allNumber = allNumber.substring(0, allNumber.length() - 1);
                allNumber = allNumber.replace(".0", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        sumNumber = Integer.parseInt(allNumber);
        return sumNumber;
    }

    /**
     * 查找所有的明细
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/findByAll")
    public Map<String,Object> findByAll(HttpServletRequest request,DepotItem model) {
        try {
            PageBean pageBean=new PageBean();
            pageBean.setRequest(request);
            List<DepotItem> dataList = depotItemService.queryByAllPager(pageBean,model);
            String mpList = model.getMpList(); //商品属性
            String[] mpArr = mpList.split(",");
            Map<String, Object> outer = new HashMap<String, Object>();
            outer.put("total", pageBean.getTotal());
            String pid = model.getProjectId();
            List dataArray = new ArrayList();
            if (null != dataList) {
                for (DepotItem depotItem : dataList) {
                    Map<String, Object> item = new HashMap<String, Object>();
                    Integer prevSum = sumNumber("入库", pid, depotItem.getMaterialid(), model.getMonthTime(), 1) - sumNumber("出库", pid, depotItem.getMaterialid(), model.getMonthTime(), 1);
                    Integer InSum = sumNumber("入库", pid, depotItem.getMaterialid(), model.getMonthTime(), 2);
                    Integer OutSum = sumNumber("出库", pid, depotItem.getMaterialid(), model.getMonthTime(), 2);
                    Double prevPrice = sumPrice("入库", pid, depotItem.getMaterialid(), model.getMonthTime(), 1) - sumPrice("出库", pid, depotItem.getMaterialid(), model.getMonthTime(), 1);
                    Double InPrice = sumPrice("入库", pid, depotItem.getMaterialid(), model.getMonthTime(), 2);
                    Double OutPrice = sumPrice("出库", pid, depotItem.getMaterialid(), model.getMonthTime(), 2);
                    item.put("Id", depotItem.getId());
                    item.put("MaterialId", depotItem.getMaterialid() == null ? "" : depotItem.getMaterialid());
                    item.put("MaterialName", depotItem.getMaterialName());
                    item.put("MaterialModel", depotItem.getMaterialModel());
                    //扩展信息
                    String materialOther = getOtherInfo(mpArr, depotItem);
                    item.put("MaterialOther", materialOther);
                    item.put("MaterialColor", depotItem.getMaterialColor());
                    item.put("MaterialUnit", depotItem.getMaterialUnit());
                    Double unitPrice = 0.0;
                    if (prevSum + InSum - OutSum != 0.0) {
                        unitPrice = (prevPrice + InPrice - OutPrice) / (prevSum + InSum - OutSum);
                    }
                    item.put("UnitPrice", unitPrice);
                    item.put("prevSum", prevSum);
                    item.put("InSum", InSum);
                    item.put("OutSum", OutSum);
                    item.put("thisSum", prevSum + InSum - OutSum);
                    item.put("thisAllPrice", prevPrice + InPrice - OutPrice);
                    dataArray.add(item);
                }
            }
            outer.put("rows", dataArray);
            return outer;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>>>>>>>查找信息异常");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 查找明细信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/findBy")
    public Map<String,Object> findBy(DepotItem model) {
        try {
            List<DepotItem> dataList = depotItemService.queryDepotJoinTable(model);
            String mpList = model.getMpList(); //商品属性
            String[] mpArr = mpList.split(",");
            Map<String, Object> outer = new HashMap<String, Object>();
            List dataArray = new ArrayList();
            if (null != dataList) {
                for (DepotItem depotItem : dataList) {
                    Map<String, Object> item = new HashMap<String, Object>();
                    item.put("Id", depotItem.getId());
                    item.put("MaterialId", depotItem.getMaterialid() == null ? "" : depotItem.getMaterialid());
                    String ratio; //比例
                    if (depotItem.getUnitId() == null || depotItem.getUnitId().equals("")) {
                        ratio = "";
                    } else {
                        ratio = depotItem.getUnitName();
                        ratio = ratio.substring(ratio.indexOf("("));
                    }
                    //品名/型号/扩展信息/包装
                    String MaterialName = depotItem.getMaterialName() + ((depotItem.getMaterialModel() == null || depotItem.getMaterialModel().equals("")) ? "" : "(" + depotItem.getMaterialModel() + ")");
                    String materialOther = getOtherInfo(mpArr, depotItem);
                    MaterialName = MaterialName + materialOther + ((depotItem.getUnitId()== null || depotItem.getMaterialUnit().equals("")) ? "" : "(" + depotItem.getMaterialUnit() + ")") + ratio;
                    item.put("MaterialName", MaterialName);
                    item.put("Unit", depotItem.getMunit());
                    item.put("OperNumber", depotItem.getOpernumber());
                    item.put("BasicNumber", depotItem.getBasicnumber());
                    item.put("UnitPrice", depotItem.getUnitprice());
                    item.put("TaxUnitPrice", depotItem.getTaxunitprice());
                    item.put("AllPrice", depotItem.getAllprice());
                    item.put("Remark", depotItem.getRemark());
                    item.put("Img", depotItem.getImg());
                    item.put("DepotId", depotItem.getDepotid() == null ? "" : depotItem.getDepotid());
                    item.put("DepotName", depotItem.getDepotid() == null ? "" : depotItem.getDepotName());
                    item.put("AnotherDepotId", depotItem.getAnotherdepotid() == null ? "" : depotItem.getAnotherdepotid());
                    item.put("AnotherDepotName", depotItem.getAnotherdepotid() == null ? "" : depotItem.getAnotherDepotName());
                    item.put("TaxRate", depotItem.getTaxrate());
                    item.put("TaxMoney", depotItem.getTaxmoney());
                    item.put("TaxLastMoney", depotItem.getTaxlastmoney());
                    item.put("OtherField1", depotItem.getOtherfield1());
                    item.put("OtherField2", depotItem.getOtherfield2());
                    item.put("OtherField3", depotItem.getOtherfield3());
                    item.put("OtherField4", depotItem.getOtherfield4());
                    item.put("OtherField5", depotItem.getOtherfield5());
                    item.put("MType", depotItem.getMtype());
                    item.put("op", 1);
                    dataArray.add(item);
                }
            }
            outer.put("rows", dataArray);
            return outer;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>>>>>>>查找仓管通信息异常");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    /**
     * 获取扩展信息
     *
     * @return
     */
    public String getOtherInfo(String[] mpArr, DepotItem depotItem) {
        Material model=new Material();
        model.setId(depotItem.getMaterialid());
        List<Material> materials = materialService.queryById(model);
        Material material=new Material();
        if(null!=materials && 0!=materials.size())
         material=materials.get(0);
        String materialOther = "";
        for (int i = 0; i < mpArr.length; i++) {
            if (mpArr[i].equals("颜色")) {
                materialOther = materialOther + ((material.getColor() == null || material.getColor().equals("")) ? "" : "(" + material.getColor() + ")");
            }
            if (mpArr[i].equals("规格")) {
                materialOther = materialOther + ((material.getStandard() == null || material.getStandard().equals("")) ? "" : "(" + material.getStandard() + ")");
            }
            if (mpArr[i].equals("制造商")) {
                materialOther = materialOther + ((material.getMfrs() == null || material.getMfrs().equals("")) ? "" : "(" + material.getMfrs() + ")");
            }
            if (mpArr[i].equals("自定义1")) {
                materialOther = materialOther + ((material.getOtherfield1() == null || material.getOtherfield1().equals("")) ? "" : "(" + material.getOtherfield1() + ")");
            }
            if (mpArr[i].equals("自定义2")) {
                materialOther = materialOther + ((material.getOtherfield2() == null || material.getOtherfield2().equals("")) ? "" : "(" + material.getOtherfield2() + ")");
            }
            if (mpArr[i].equals("自定义3")) {
                materialOther = materialOther + ((material.getOtherfield3() == null || material.getOtherfield3().equals("")) ? "" : "(" + material.getOtherfield3() + ")");
            }
        }
        return materialOther;
    }

    /**
     * 保存明细
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/saveDetials")
    public Map<String,Object> saveDetials(HttpServletRequest request, DepotItem model) {
        System.out.println("==================开始调用保存仓管通明细信息方法saveDetials()===================");
        Map<String, Object> map = new HashMap<String, Object>();
        Boolean flag = false;
        try {
            String headerId = model.getHeaderid();
            String inserted = model.getInserted();
            String deleted = model.getDeleted();
            String updated = model.getUpdated();
            //转为json
            JSONArray insertedJson = JSONArray.fromObject(inserted);
            JSONArray deletedJson = JSONArray.fromObject(deleted);
            JSONArray updatedJson = JSONArray.fromObject(updated);
            if (null != insertedJson) {
                for (int i = 0; i < insertedJson.size(); i++) {
                    DepotItem depotItem = new DepotItem();
                    JSONObject tempInsertedJson = JSONObject.fromObject(insertedJson.get(i));
                    depotItem.setHeaderid(headerId);
                    depotItem.setMaterialid(tempInsertedJson.getString("MaterialId"));
                    depotItem.setMunit(tempInsertedJson.getString("Unit"));
                    if (!StringUtils.isEmpty(tempInsertedJson.get("OperNumber").toString())) {
                        depotItem.setOpernumber(tempInsertedJson.getDouble("OperNumber"));
                        try {
                            String Unit = tempInsertedJson.get("Unit").toString();
                            Double oNumber = tempInsertedJson.getDouble("OperNumber");
                            String mId = tempInsertedJson.get("MaterialId").toString();
                            //以下进行单位换算
                            String UnitName = findUnitName(mId); //查询计量单位名称
                            if (!UnitName.equals("")) {
                                String UnitList = UnitName.substring(0, UnitName.indexOf("("));
                                String RatioList = UnitName.substring(UnitName.indexOf("("));
                                String basicUnit = UnitList.substring(0, UnitList.indexOf(",")); //基本单位
                                String otherUnit = UnitList.substring(UnitList.indexOf(",") + 1); //副单位
                                Integer ratio = Integer.parseInt(RatioList.substring(RatioList.indexOf(":") + 1).replace(")", "")); //比例
                                if (Unit.equals(basicUnit)) { //如果等于基础单位
                                    depotItem.setBasicnumber(oNumber); //数量一致
                                } else if (Unit.equals(otherUnit)) { //如果等于副单位
                                    depotItem.setBasicnumber(oNumber * ratio); //数量乘以比例
                                }
                            } else {
                                depotItem.setBasicnumber(oNumber); //其他情况
                            }
                        } catch (Exception e) {
                            System.out.println(">>>>>>>>>>>>>>>>>>>设置基础数量异常");
                            e.printStackTrace();
                        }
                    }
                    if (!StringUtils.isEmpty(tempInsertedJson.get("UnitPrice").toString())) {
                        depotItem.setUnitprice(tempInsertedJson.getDouble("UnitPrice"));
                    }
                    if (!StringUtils.isEmpty(tempInsertedJson.get("TaxUnitPrice").toString())) {
                        depotItem.setTaxunitprice(tempInsertedJson.getDouble("TaxUnitPrice"));
                    }
                    if (!StringUtils.isEmpty(tempInsertedJson.get("AllPrice").toString())) {
                        depotItem.setAllprice(tempInsertedJson.getDouble("AllPrice"));
                    }
                    depotItem.setRemark(tempInsertedJson.getString("Remark"));
                    if (tempInsertedJson.get("DepotId") != null && !StringUtils.isEmpty(tempInsertedJson.get("DepotId").toString())) {
                        depotItem.setDepotid(tempInsertedJson.getString("DepotId"));
                    }
                    if (tempInsertedJson.get("AnotherDepotId") != null && !StringUtils.isEmpty(tempInsertedJson.get("AnotherDepotId").toString())) {
                        depotItem.setAnotherdepotid(tempInsertedJson.getString("AnotherDepotId"));
                    }
                    if (!StringUtils.isEmpty(tempInsertedJson.get("TaxRate").toString())) {
                        depotItem.setTaxrate(tempInsertedJson.getDouble("TaxRate"));
                    }
                    if (!StringUtils.isEmpty(tempInsertedJson.get("TaxMoney").toString())) {
                        depotItem.setTaxmoney(tempInsertedJson.getDouble("TaxMoney"));
                    }
                    if (!StringUtils.isEmpty(tempInsertedJson.get("TaxLastMoney").toString())) {
                        depotItem.setTaxlastmoney(tempInsertedJson.getDouble("TaxLastMoney"));
                    }
                    if (tempInsertedJson.get("OtherField1") != null) {
                        depotItem.setOtherfield1(tempInsertedJson.getString("OtherField1"));
                    }
                    if (tempInsertedJson.get("OtherField2") != null) {
                        depotItem.setOtherfield2(tempInsertedJson.getString("OtherField2"));
                    }
                    if (tempInsertedJson.get("OtherField3") != null) {
                        depotItem.setOtherfield3(tempInsertedJson.getString("OtherField3"));
                    }
                    if (tempInsertedJson.get("OtherField4") != null) {
                        depotItem.setOtherfield4(tempInsertedJson.getString("OtherField4"));
                    }
                    if (tempInsertedJson.get("OtherField5") != null) {
                        depotItem.setOtherfield5(tempInsertedJson.getString("OtherField5"));
                    }
                    if (tempInsertedJson.get("MType") != null) {
                        depotItem.setMtype(tempInsertedJson.getString("MType"));
                    }
                    depotItem.setId(UUID.randomUUID().toString());
                    depotItemService.insert(depotItem);
                }
            }
            if (null != deletedJson) {
                for (int i = 0; i < deletedJson.size(); i++) {
                    JSONObject tempDeletedJson = JSONObject.fromObject(deletedJson.get(i));
                    depotItemService.deleteById(tempDeletedJson.getString("Id"));
                }
            }
            if (null != updatedJson) {
                for (int i = 0; i < updatedJson.size(); i++) {
                    JSONObject tempUpdatedJson = JSONObject.fromObject(updatedJson.get(i));
                    DepotItem depotItem = depotItemService.selectById(tempUpdatedJson.getString("Id"));
                    depotItem.setMaterialid(tempUpdatedJson.getString("MaterialId"));
                    depotItem.setMunit(tempUpdatedJson.getString("Unit"));
                    if (!StringUtils.isEmpty(tempUpdatedJson.get("OperNumber").toString())) {
                        depotItem.setOpernumber(tempUpdatedJson.getDouble("OperNumber"));
                        try {
                            String Unit = tempUpdatedJson.get("Unit").toString();
                            Double oNumber = tempUpdatedJson.getDouble("OperNumber");
                            String mId = tempUpdatedJson.get("MaterialId").toString();
                            //以下进行单位换算
                            String UnitName = findUnitName(mId); //查询计量单位名称
                            if (!UnitName.equals("")) {
                                String UnitList = UnitName.substring(0, UnitName.indexOf("("));
                                String RatioList = UnitName.substring(UnitName.indexOf("("));
                                String basicUnit = UnitList.substring(0, UnitList.indexOf(",")); //基本单位
                                String otherUnit = UnitList.substring(UnitList.indexOf(",") + 1); //副单位
                                Integer ratio = Integer.parseInt(RatioList.substring(RatioList.indexOf(":") + 1).replace(")", "")); //比例
                                if (Unit.equals(basicUnit)) { //如果等于基础单位
                                    depotItem.setBasicnumber(oNumber); //数量一致
                                } else if (Unit.equals(otherUnit)) { //如果等于副单位
                                    depotItem.setBasicnumber(oNumber * ratio); //数量乘以比例
                                }
                            } else {
                                depotItem.setBasicnumber(oNumber); //其他情况
                            }
                        } catch (Exception e) {
                            System.out.println(">>>>>>>>>>>>>>>>>>>设置基础数量异常");
                            e.printStackTrace();
                        }
                    }
                    if (!StringUtils.isEmpty(tempUpdatedJson.get("UnitPrice").toString())) {
                        depotItem.setUnitprice(tempUpdatedJson.getDouble("UnitPrice"));
                    }
                    if (!StringUtils.isEmpty(tempUpdatedJson.get("TaxUnitPrice").toString())) {
                        depotItem.setTaxunitprice(tempUpdatedJson.getDouble("TaxUnitPrice"));
                    }
                    if (!StringUtils.isEmpty(tempUpdatedJson.get("AllPrice").toString())) {
                        depotItem.setAllprice(tempUpdatedJson.getDouble("AllPrice"));
                    }
                    depotItem.setRemark(tempUpdatedJson.getString("Remark"));
                    if (tempUpdatedJson.get("DepotId") != null && !StringUtils.isEmpty(tempUpdatedJson.get("DepotId").toString())) {
                        depotItem.setDepotid(tempUpdatedJson.getString("DepotId"));
                    }
                    if (tempUpdatedJson.get("AnotherDepotId") != null && !StringUtils.isEmpty(tempUpdatedJson.get("AnotherDepotId").toString())) {
                        depotItem.setAnotherdepotid(tempUpdatedJson.getString("AnotherDepotId"));
                    }
                    if (!StringUtils.isEmpty(tempUpdatedJson.get("TaxRate").toString())) {
                        depotItem.setTaxrate(tempUpdatedJson.getDouble("TaxRate"));
                    }
                    if (!StringUtils.isEmpty(tempUpdatedJson.get("TaxMoney").toString())) {
                        depotItem.setTaxmoney(tempUpdatedJson.getDouble("TaxMoney"));
                    }
                    if (!StringUtils.isEmpty(tempUpdatedJson.get("TaxLastMoney").toString())) {
                        depotItem.setTaxlastmoney(tempUpdatedJson.getDouble("TaxLastMoney"));
                    }
                    depotItem.setOtherfield1(tempUpdatedJson.getString("OtherField1"));
                    depotItem.setOtherfield2(tempUpdatedJson.getString("OtherField2"));
                    depotItem.setOtherfield3(tempUpdatedJson.getString("OtherField3"));
                    depotItem.setOtherfield4(tempUpdatedJson.getString("OtherField4"));
                    depotItem.setOtherfield5(tempUpdatedJson.getString("OtherField5"));
                    depotItem.setMtype(tempUpdatedJson.getString("MType"));
                    depotItemService.updateById(depotItem);
                }
            }

            //========标识位===========
            flag = true;
            //记录操作日志使用
            tipMsg = "成功";
            tipType = 0;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>>>>>>>保存仓管通明细信息异常");
            flag = false;
            tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();
        }
        map.put("flag",flag);

        logService.create(new Log(getUser(request), "保存仓管通明细", model.getClientIp(),
                new Timestamp(System.currentTimeMillis())
                , tipType, "保存仓管通明细对应主表编号为  " + model.getHeaderid() + " " + tipMsg + "！", "保存仓管通明细" + tipMsg));
        System.out.println("==================结束调用保存仓管通明细方法saveDetials()===================");
        return map;
    }

    /**
     * 查询计量单位信息
     *
     * @return
     */
    public String findUnitName(String mId) {
        String unitName = "";
        try {
            unitName=materialService.queryUnitName(mId).toString();
            if (unitName != null) {
                unitName = unitName.substring(1, unitName.length() - 1);
                if (unitName.equals("null")) {
                    unitName = "";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return unitName;
    }


    /**
     * 统计总计金额
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/totalCountMoney")
    public Map<String,Object> totalCountMoney(DepotItem model) {
        try {
            List<DepotItem> dataList = depotItemService.totalCountMoney(model);
            Map<String, Object> outer = new HashMap<String, Object>();
            String pid = model.getProjectId();
            Double thisAllPrice = 0.0;
            if (null != dataList) {
                for (DepotItem depotItem : dataList) {
                    Double prevPrice = sumPrice("入库", pid, depotItem.getMaterialid(), model.getMonthTime(), 1) - sumPrice("出库", pid, depotItem.getMaterialid(), model.getMonthTime(), 1);
                    Double InPrice = sumPrice("入库", pid, depotItem.getMaterialid(), model.getMonthTime(), 2);
                    Double OutPrice = sumPrice("出库", pid, depotItem.getMaterialid(), model.getMonthTime(), 2);
                    thisAllPrice = thisAllPrice + (prevPrice + InPrice - OutPrice);
                }
            }
            outer.put("totalCount", thisAllPrice);
            return outer;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>>>>>>>查找信息异常");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    /**
     * 价格合计
     *
     * @param type
     * @param MId
     * @param MonthTime
     * @param isPrev
     * @return
     */
    public Double sumPrice(String type, String ProjectId, String  MId, String MonthTime, int isPrev) {
        Double sumPrice = 0.0;
        String allPrice = "";
        try {
            List<DepotItem> depotItems = depotItemService.queryByPrice(type, ProjectId, MId, MonthTime, isPrev);
            if (depotItems != null) {
                for (DepotItem depotItem : depotItems) {
                    if(depotItem!=null) {
                        Object dl = depotItem.getAllprice(); //获取对象
                        allPrice = allPrice + dl.toString() + ",";
                    }
                }
            }
            if (allPrice.equals("null")|| allPrice.equals("")) {
                allPrice = "0";
            }else {
                allPrice = allPrice.substring(0,allPrice.length() - 1);
                allPrice = allPrice.replace(".0", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        sumPrice =  Double.parseDouble(allPrice);
        return sumPrice;
    }






}
