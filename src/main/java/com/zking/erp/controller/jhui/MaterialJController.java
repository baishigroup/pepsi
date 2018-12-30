package com.zking.erp.controller.jhui;

import com.zking.erp.base.BaseController;
import com.zking.erp.model.jhui.Material;
import com.zking.erp.service.jhui.IMaterialJService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/material")
public class MaterialJController extends BaseController{

    @Autowired
    private IMaterialJService materialService;

    /**
     * 根据id来查询商品名称
     *
     * @return
     */
    @RequestMapping("/findById")
    @ResponseBody
    public Map<String,Object> findById(Material model) {
        try {
            model.setId(model.getMaterialID());
            List<Material> dataList =materialService.queryById(model);
            Map<String, Object> outer = new HashMap<String, Object>();
            //存放数据json数组
            List dataArray = new ArrayList();
            if (null != dataList) {
                for (Material material : dataList) {
                    Map<String, Object> item = new HashMap<String, Object>();
                    item.put("Id", material.getId());
                    item.put("Name", material.getName());
                    item.put("Mfrs", material.getMfrs() == null ? "" : material.getMfrs());
                    item.put("Packing", material.getPacking() == null ? "" : material.getPacking());
                    item.put("SafetyStock", material.getSafetystock() == null ? "" : material.getSafetystock());
                    item.put("Model", material.getModel());
                    item.put("Standard", material.getStandard());
                    item.put("Color", material.getColor() == null ? "" : material.getColor());
                    item.put("Unit", material.getUnit());
                    item.put("RetailPrice", material.getRetailprice());
                    item.put("LowPrice", material.getLowprice());
                    item.put("PresetPriceOne", material.getPresetpriceone());
                    item.put("PresetPriceTwo", material.getPresetpricetwo());
                    item.put("UnitId", material.getUnitId() == null ? "" : material.getUnitid()); //计量单位Id
                    item.put("UnitName", material.getUnitId() == null ? "" : material.getUnitName()); //计量单位名称
                    item.put("FirstOutUnit", material.getFirstoutunit());
                    item.put("FirstInUnit", material.getFirstinunit());
                    item.put("PriceStrategy", material.getPricestrategy());
                    item.put("Remark", material.getRemark());
                    item.put("op", 1);
                    dataArray.add(item);
                }
            }
            outer.put("rows", dataArray);
            return outer;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>查找商品信息异常");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 查找商品信息-下拉框(连表)
     *
     * @return
     */
    @RequestMapping("/findBySelect")
    @ResponseBody
    public List findBySelect(Material model) {
        try {
            List<Material> dataList = materialService.queryBySelect(model);
                    String mpList = model.getMpList(); //商品属性
                    String[] mpArr = mpList.split(",");
                    //存放数据json数组
                    List dataArray = new ArrayList();
                    if (null != dataList) {
                        for (Material material : dataList) {
                            Map<String, Object> item = new HashMap<String, Object>();
                    item.put("Id", material.getId());
                    String ratio; //比例
                    if (material.getUnitId() == null || material.getUnitId().equals("")) {
                        ratio = "";
                    } else {
                        ratio = material.getUnitName();
                        ratio = ratio.substring(ratio.indexOf("("));
                    }
                    //品名/型号/扩展信息/包装
                    String MaterialName = material.getName() + ((material.getModel() == null || material.getModel().equals("")) ? "" : "(" + material.getModel() + ")");
                    for (int i = 0; i < mpArr.length; i++) {
                        if (mpArr[i].equals("颜色")) {
                            MaterialName = MaterialName + ((material.getColor() == null || material.getColor().equals("")) ? "" : "(" + material.getColor() + ")");
                        }
                        if (mpArr[i].equals("规格")) {
                            MaterialName = MaterialName + ((material.getStandard() == null || material.getStandard().equals("")) ? "" : "(" + material.getStandard() + ")");
                        }
                        if (mpArr[i].equals("制造商")) {
                            MaterialName = MaterialName + ((material.getMfrs() == null || material.getMfrs().equals("")) ? "" : "(" + material.getMfrs() + ")");
                        }
                        if (mpArr[i].equals("自定义1")) {
                            MaterialName = MaterialName + ((material.getOtherfield1() == null || material.getOtherfield1().equals("")) ? "" : "(" + material.getOtherfield1() + ")");
                        }
                        if (mpArr[i].equals("自定义2")) {
                            MaterialName = MaterialName + ((material.getOtherfield2() == null || material.getOtherfield2().equals("")) ? "" : "(" + material.getOtherfield2() + ")");
                        }
                        if (mpArr[i].equals("自定义3")) {
                            MaterialName = MaterialName + ((material.getOtherfield3() == null || material.getOtherfield3().equals("")) ? "" : "(" + material.getOtherfield3() + ")");
                        }
                    }
                    MaterialName = MaterialName + ((material.getUnit() == null || material.getUnit().equals("")) ? "" : "(" + material.getUnit() + ")") + ratio;
                    item.put("MaterialName", MaterialName);
                    dataArray.add(item);
                }
            }
            return dataArray;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>查找供应商信息异常");
            e.printStackTrace();
            throw new RuntimeException(e);
        }  catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


}
