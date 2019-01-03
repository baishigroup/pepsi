package com.zking.erp.controller.cao;

import com.zking.erp.base.BaseController;
import com.zking.erp.model.cao.Material;
import com.zking.erp.model.jhui.Log;
import com.zking.erp.service.cao.IMaterialCService;
import com.zking.erp.util.PageBean;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.*;

@Controller
@RequestMapping("/cao/material")
public class MaterialCController extends BaseController{
    @Autowired
    private IMaterialCService materialService ;

    @RequestMapping("getBasicData")
    @ResponseBody
    public Map<String,Object> getBasicData(Material person) {
        Map<String, Object> mapData = new HashMap<String, Object>();
        try {
            List<Material> materialCategoryList = materialService.queryMaterialPager(person,null);
            mapData.put("materialCategoryList", materialCategoryList);
        } catch (Exception e) {
            System.out.println(">>>>>>>>>>>>>查找系统基础数据信息异常");
            mapData.put("message","exceptoin");
            e.printStackTrace();
        }
        return mapData;
    }

    @RequestMapping("/create")
    @ResponseBody
    public Map<String,Object> create(HttpServletRequest request,Material model){
        Map<String,Object> map = new HashMap<String, Object>();
        model.setId(UUID.randomUUID().toString().replace("-"," "));

        System.out.println("==================开始调用增加商品信息方法create()===================");
        Boolean flag = false;
        try {
            materialService.insertMaterial(model);
            //========标识位===========
            flag = true;
            //记录操作日志使用
            tipMsg = "成功";
            tipType = 0;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>>>>>>>增加商品信息异常");
            flag = false;
            tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();
        } finally {
            try {
                map.put("flag",flag);
            } catch (Exception e) {
                System.out.println(">>>>>>>>>>>>增加商品信息回写客户端结果异常");
                e.printStackTrace();
            }
        }

        logService.create(new Log(getUser(request), "增加商品信息", model.getClientIp(),
                new Timestamp(System.currentTimeMillis())
                , tipType, "增加商品信息名称为  " + model.getName() + " " + tipMsg + "！", "增加商品信息" + tipMsg));
        System.out.println("==================结束调用增加商品信息方法create()===================");
        return map;
    }

    /**
     * 查找商品信息
     *
     * @return
     */
    @RequestMapping("/findBy")
    @ResponseBody
    public Map<String,Object> findBy(HttpServletRequest request,Material model) {
        try {
            PageBean pageBean=new PageBean();
            pageBean.setRequest(request);
            String lei = model.getCategoryid();
//            if ("1".equals(lei)) //判断值还真不能用String类型的判断
//            {
//                pageUtil.setAdvSearch(getCondition_all());
//            } else if (1 != lei) {
//                pageUtil.setAdvSearch(getCondition());
//            }
            List<Material> dataList = materialService.queryMaterialPager(model,pageBean);
//            request.getSession().setAttribute("pageUtilMaterial", pageUtil);
            String mpList = model.getMpList(); //商品属性
            String[] mpArr = mpList.split(",");
            Map<String,Object> outer = new HashMap<String,Object>();
            outer.put("total", pageBean.getTotal());
            //存放数据json数组
            List dataArray = new ArrayList();
            if (null != dataList) {
                for (Material material : dataList) {
                    JSONObject item = new JSONObject();
                    item.put("Id", material.getId());
                    item.put("Name", material.getName());
                    item.put("CategoryId", material.getCategoryid()); //类型Id
                    item.put("CategoryName", material.getCategoryName()); //类型名称
                    item.put("Packing", material.getPacking() == null ? "" : material.getPacking());
                    item.put("SafetyStock", material.getSafetystock() == null ? "" : material.getSafetystock());
                    item.put("Model", material.getModel() == null ? "" : material.getModel());
                    //扩展信息
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
                    item.put("MaterialOther", materialOther);
                    item.put("Unit", material.getUnit() == null ? "" : material.getUnit());
                    item.put("RetailPrice", material.getRetailprice());
                    item.put("LowPrice", material.getLowprice());
                    item.put("PresetPriceOne", material.getPresetpriceone() == null ? "" : material.getPresetpriceone());
                    item.put("PresetPriceTwo", material.getPresetpricetwo() == null ? "" : material.getPresetpricetwo());
                    item.put("UnitId", material.getUnitId() == null ? "" : material.getUnitId()); //计量单位Id
                    item.put("UnitName", material.getUnitId() == null ? "" : material.getUnitName()); //计量单位名称
                    item.put("FirstOutUnit", material.getFirstoutunit());
                    item.put("FirstInUnit", material.getFirstinunit());
                    item.put("PriceStrategy", material.getPricestrategy());
                    item.put("Enabled", material.getEnabled());
                    item.put("Remark", material.getRemark());
                    item.put("Color", material.getColor() == null ? "" : material.getColor());
                    item.put("Standard", material.getStandard() == null ? "" : material.getStandard());
                    item.put("Mfrs", material.getMfrs() == null ? "" : material.getMfrs());
                    item.put("OtherField1", material.getOtherfield1() == null ? "" : material.getOtherfield1());
                    item.put("OtherField2", material.getOtherfield2() == null ? "" : material.getOtherfield2());
                    item.put("OtherField3", material.getOtherfield3() == null ? "" : material.getOtherfield3());
                    item.put("op", 1);
                    dataArray.add(item);
                }
            }
            outer.put("rows", dataArray);
            return  outer;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>>>>>>>查找商品信息异常");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    @RequestMapping("/delete")
    @ResponseBody
    public Map<String,Object> delete(HttpServletRequest request,Material model){
        Map<String,Object> m = new HashMap<>();

        System.out.println("====================开始调用删除商品信息方法delete()================");
        try {
            materialService.deleteMaterialById(model.getId());
            m.put("message","成功");
            tipType = 0;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>删除ID为 " + model.getId() + "  的商品信息异常");
            m.put("message","失败");
            tipType = 1;
            e.printStackTrace();
        }

        logService.create(new Log(getUser(request), "删除商品信息", model.getClientIp(),
                new Timestamp(System.currentTimeMillis())
                , tipType, "删除商品信息ID为  " + model.getIds() + " " + tipMsg + "！", "删除商品信息" + tipMsg));
        System.out.println("====================结束调用删除商品信息方法delete()================");

        return m;
    }

    @RequestMapping("/batchDelete")
    @ResponseBody
    public Map<String,Object> batchDelete(HttpServletRequest request,Material model){
        Map<String,Object> m = new HashMap<>();

        String[] split = model.getMaterialIDs().split(",");
        model.setIds(split);


        System.out.println("====================开始调用批量删除商品信息方法batchDelete()================");
        try {
            materialService.deleteMaterialByIds(model);
            //记录操作日志使用
            tipMsg = "成功";
            tipType = 0;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>批量删除商品信息ID为：" + model.getIds() + "信息异常");
            tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();
        }
        m.put("message",tipMsg);
        logService.create(new Log(getUser(request), "批量删除商品信息", model.getClientIp(),
                new Timestamp(System.currentTimeMillis())
                , tipType, "批量删除商品信息ID为  " + model.getIds() + " " + tipMsg + "！", "批量删除商品信息" + tipMsg));
        System.out.println("====================结束调用批量删除商品信息方法batchDelete()================");
        return m;
    }

    @RequestMapping("/update")
    @ResponseBody
    public Map<String,Object> update(HttpServletRequest request,Material model){
        Map<String,Object> m = new HashMap<>();

        System.out.println("====================开始调用修改商品信息方法update()================");
        Boolean flag = false;
        try {
            materialService.updateMaterial(model);

            flag = true;
            m.put("message","成功");
            tipType = 0;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>修改商品信息ID为 ： " + model.getId() + "信息失败");
            flag = false;
            m.put("message","失败");
            tipType = 1;
            e.printStackTrace();
        } finally {
            try {
                m.put("flag",flag);
            } catch (Exception e) {
                System.out.println(">>>>>>>>>>>>修改商品信息回写客户端结果异常");
                e.printStackTrace();
            }
        }

        logService.create(new Log(getUser(request), "更新商品信息", model.getClientIp(),
                new Timestamp(System.currentTimeMillis())
                , tipType, "更新商品信息ID为  " + model.getIds() + " " + tipMsg + "！", "更新商品信息" + tipMsg));

        System.out.println("====================结束调用修改商品信息方法update()================");
        return m;
    }

    @RequestMapping("/batchSetEnable")
    @ResponseBody
    public Map<String,Object> batchSetEnable(HttpServletRequest request,Material model) {
        System.out.println("====================进入调用批量启用、禁用方法batchSetEnable()================");
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            materialService.batchSetEnable(model);
            map.put("message","成功");
            //记录操作日志使用
            tipMsg = "成功";
            tipType = 0;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>批量修改状态，单位ID为：" + model.getMaterialIDs() + "信息异常");
            map.put("message","失败");
            tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();
        }

        logService.create(new Log(getUser(request), "批量修改商品信息", model.getClientIp(),
                new Timestamp(System.currentTimeMillis())
                , tipType, "批量修改状态，商品ID为  " + model.getIds() + " " + tipMsg + "！", "批量修改商品状态" + tipMsg));
        System.out.println("====================结束调用批量启用、禁用方法batchSetEnable()================");
        return map;
    }

    @RequestMapping("/checkIsExist")
    @ResponseBody
    public Map<String,Object> checkIsExist(Material model){
        Boolean flag=false;
        Map<String,Object> m = new HashMap<>();

        System.out.println("检查输入名称是否存在");
        try {
            flag = materialService.checkIsExist("uname", model.getName(), "id", model.getId());
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>>>>>检查商品信息名称为：" + model.getName() + " ID为： " + model.getId() + " 是否存在异常！");
        } finally {
            try {
                m.put("flag",flag);
            } catch (Exception e) {
                System.out.println(">>>>>>>>>>>>回写检查商品信息名称为：" + model.getName() + " ID为： " + model.getId() + " 是否存在异常！");
                e.printStackTrace();
            }
        }
        return m;
    }


    /**
     * 查找商品信息-统计排序
     *
     * @return
     */
    @RequestMapping("/findByOrder")
    @ResponseBody
    public Map<String,Object> findByOrder(HttpServletRequest request,Material model) {
        System.out.println("----------------查找商品信息-统计排序-------------------");
        try {
            PageBean pageBean = new PageBean();
            pageBean.setRequest(request);
            List<Material> dataList = materialService.queryByOrder(model);
            //存放数据json数组
            Map<String,Object> outer = new HashMap<String, Object>();
            String mId = "";
            if (null != dataList) {
                for (Material material : dataList) {
                    mId = mId + material.getId() + ",";
                }
            }
            if (mId != "") {
                mId = mId.substring(0, mId.lastIndexOf(","));
            }
            outer.put("mIds", mId);
            //回写查询结果
            return outer;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>查找供应商信息异常");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
