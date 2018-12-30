package com.zking.erp.controller.cao;

import com.zking.erp.model.cao.DepotItem;
import com.zking.erp.model.cao.Material;
import com.zking.erp.service.cao.IDepotItemCService;
import com.zking.erp.service.cao.IMaterialCService;
import com.zking.erp.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cao/depotItem")
public class DepotItemCController {

    @Autowired
    private IDepotItemCService depotItemService;
    @Autowired
    private IMaterialCService materialService ;


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
            //存放数据json数组
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
     * 只根据商品id查询库存数量
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/findStockNumByMaterialId")
    public Map<String,Object> findStockNumByMaterialId(DepotItem model) {
        try {
            List<DepotItem> dataList = depotItemService.queryDepotItemByMId(model);
            Map<String, Object> outer = new HashMap<String, Object>();
            //存放数据json数组
            List dataArray = new ArrayList();
            if (null != dataList) {
                for (DepotItem depotItem : dataList) {
                    Map<String, Object> item = new HashMap<String, Object>();
                    Integer InSum = sumNumberByMaterialId("入库", depotItem.getMaterialid());
                    Integer OutSum = sumNumberByMaterialId("出库", depotItem.getMaterialid());
                    item.put("MaterialId", depotItem.getMaterialid() == null ? "" : depotItem.getMaterialid());
                    item.put("MaterialName", depotItem.getMaterialName());
                    item.put("MaterialModel", depotItem.getMaterialModel());
                    item.put("thisSum", InSum - OutSum);
                    dataArray.add(item);
                }
            }
            //回写查询结果
            outer.put("rows", dataArray);
            return  outer;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>>>>>>>查找信息异常");
            e.printStackTrace();
          throw new RuntimeException(e);
        }
    }


    /**
     * 仅根据商品Id进行数量合计
     *
     * @param type
     * @param MId
     * @return
     */
    @SuppressWarnings("unchecked")
    public Integer sumNumberByMaterialId(String type, String MId) {
        Integer sumNumber = 0;
        String allNumber = "";
        try {
            List<DepotItem> depotItems = depotItemService.querySumByTypeByMId(type, MId);
            if(null!=depotItems && 0!=depotItems.size()) {
                for (DepotItem depotItem : depotItems) {
                    if (null!=depotItem) {
                        if (null != depotItem.getBasicnumber())
                            allNumber = depotItem.getBasicnumber().toString();
                    }
                }
            }
            if (allNumber.equals("null")||allNumber.equals("")) {
                allNumber = "0";
            }else {
                allNumber = allNumber.substring(0, allNumber.length() - 2);
                allNumber = allNumber.replace(".0", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        sumNumber = Integer.parseInt(allNumber);
        return sumNumber;
    }

    /**
     * 只根据商品id查询单据列表
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/findDetailByTypeAndMaterialId")
    public Map<String,Object> findDetailByTypeAndMaterialId(HttpServletRequest request, DepotItem model) {
        try {
            PageBean pageBean = new PageBean();
            pageBean.setRequest(request);
            String mId = model.getMaterialid();
            List<DepotItem> dataList =depotItemService.queryDetailByMIdPager(pageBean,mId);
            Map<String,Object> outer = new HashMap<String,Object>();
            outer.put("total", pageBean.getTotal());
            //存放数据json数组
            List dataArray = new ArrayList();
            if (dataList != null) {
                for (DepotItem depotItem : dataList) {
                    Map<String,Object> item = new HashMap<String,Object>();
                    item.put("Number", depotItem.getNumber()); //商品编号
                    item.put("Type", depotItem.getType()); //进出类型
                    item.put("BasicNumber", depotItem.getBasicnumber()); //数量
                    item.put("OperTime", depotItem.getOperTime()); //时间
                    dataArray.add(item);
                }

            }
            outer.put("rows", dataArray);
            return outer;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }



}
