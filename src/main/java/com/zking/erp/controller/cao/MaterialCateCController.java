package com.zking.erp.controller.cao;

import com.zking.erp.base.BaseController;
import com.zking.erp.model.cao.MaterialCategory;
import com.zking.erp.model.jhui.Log;
import com.zking.erp.service.cao.IMaterialCategoryCService;
import com.zking.erp.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.*;

@Controller
@RequestMapping("/cao/materialCategory")
public class MaterialCateCController extends BaseController{

    @Autowired
    private IMaterialCategoryCService materialCategoryCService;

    @RequestMapping("getBasicData")
    @ResponseBody
    public Map<String,Object> getBasicData(MaterialCategory model) {
        Map<String, Object> mapData = new HashMap<String, Object>();
        try {
            List<MaterialCategory> materialCateList = materialCategoryCService.queryMaterialCatePager(model,null);
            mapData.put("materialCateList", materialCateList);
        } catch (Exception e) {
            System.out.println(">>>>>>>>>>>>>查找商品类别信息异常");
            mapData.put("message","exceptoin");
            e.printStackTrace();
        }
        return mapData;
    }

    @RequestMapping("/create")
    @ResponseBody
    public Map<String,Object> create(HttpServletRequest request,MaterialCategory model){
        Map<String,Object> map = new HashMap<String, Object>();
        model.setId(UUID.randomUUID().toString().replace("-"," "));

        System.out.println("==================开始调用增加商品类别方法create()===================");
        Boolean flag = false;
        try {
            materialCategoryCService.insertMaterialCate(model);
            map.put("message","true");

            //========标识位===========
            flag = true;
            //记录操作日志使用
            tipMsg = "成功";
            tipType = 0;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>>>>>>>增加商品类别异常");
            map.put("message","false");
            flag = false;
            tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();
        } finally {
            try {
                map.put("flag",flag);
            } catch (Exception e) {
                System.out.println(">>>>>>>>>>>>修改商品类别回写客户端结果异常");
                e.printStackTrace();
            }
        }

        logService.create(new Log(getUser(request), "增加商品类别", model.getClientIp(),
                new Timestamp(System.currentTimeMillis())
                , tipType, "增加商品类别名称为  " + model.getName() + " " + tipMsg + "！", "增加商品类别" + tipMsg));
        System.out.println("==================结束调用增加商品类别方法create()===================");
        return map;
    }

    @RequestMapping("/findBy")
    @ResponseBody
    public Map<String,Object> findBy(HttpServletRequest req, MaterialCategory model){
        PageBean pageBean = new PageBean();
        pageBean.setRequest(req);
        Map<String,Object> map=new HashMap<String, Object>();
        List<MaterialCategory> materialCatesList = materialCategoryCService.queryMaterialCatePager(model,pageBean);
        List dataArray = new ArrayList();
        if (null != materialCatesList) {
            for (MaterialCategory materialCategory : materialCatesList) {
                Map<String,Object> item=new HashMap<String, Object>();
                item.put("Id", materialCategory.getId());
                item.put("ParentId", materialCategory.getParentid());
                item.put("ParentName", materialCategory.getParentName());
                item.put("CategoryLevel", materialCategory.getCategorylevel());
                item.put("Name", materialCategory.getName());
                item.put("op", 1);
                dataArray.add(item);
            }
        }

        map.put("total",pageBean.getTotal());
        System.out.println(pageBean.getTotal());
        map.put("rows",dataArray);
        return map;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Map<String,Object> delete(HttpServletRequest request,MaterialCategory model){
        Map<String,Object> m = new HashMap<>();

        System.out.println("====================开始调用删除商品类别方法delete()================");
        try {
            materialCategoryCService.deleteMaterialCateById(model.getId());
            System.out.println(model.getId());
            m.put("message","成功");
            //tipMsg = "成功";
            tipType = 0;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>删除ID为 " + model.getId() + "  的商品类别异常");
            m.put("message","失败");
            //tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();
        }

        logService.create(new Log(getUser(request), "删除商品类别", model.getClientIp(),
                new Timestamp(System.currentTimeMillis())
                , tipType, "删除商品类别ID为  " + model.getIds() + " " + tipMsg + "！", "删除商品类别" + tipMsg));
        System.out.println("====================结束调用删除商品类别方法delete()================");

        return m;
    }

    @RequestMapping("/batchDelete")
    @ResponseBody
    public Map<String,Object> batchDelete(HttpServletRequest request,MaterialCategory model){
        Map<String,Object> m = new HashMap<>();

        String[] split = model.getMaterialCategoryIDs().split(",");
        model.setIds(split);


        System.out.println("====================开始调用批量删除商品类别方法batchDelete()================");
        try {
            materialCategoryCService.deleteMaterialCateByIds(model);
            m.put("message","成功");
            //记录操作日志使用
            tipMsg = "成功";
            tipType = 0;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>批量删除商品类别ID为：" + model.getIds() + "信息异常");
            tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();
        }

        logService.create(new Log(getUser(request), "批量删除商品类别", model.getClientIp(),
                new Timestamp(System.currentTimeMillis())
                , tipType, "批量删除商品类别ID为  " + model.getIds() + " " + tipMsg + "！", "批量删除商品类别" + tipMsg));
        System.out.println("====================结束调用批量删除商品类别方法batchDelete()================");
        return m;
    }

    @RequestMapping("/update")
    @ResponseBody
    public Map<String,Object> update(HttpServletRequest request,MaterialCategory model){
        Map<String,Object> m = new HashMap<>();

        System.out.println("====================开始调用修改商品类别方法update()================");
        Boolean flag = false;
        try {
            materialCategoryCService.updateMaterialCateById(model);

            flag = true;
            m.put("message","成功");
            //tipMsg = "成功";
            tipType = 0;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>修改商品类别ID为 ： " + model.getId() + "信息失败");
            flag = false;
            m.put("message","失败");
            //tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();
        } finally {
            try {
                m.put("flag",flag);
            } catch (Exception e) {
                System.out.println(">>>>>>>>>>>>修改商品类别回写客户端结果异常");
                e.printStackTrace();
            }
        }

        logService.create(new Log(getUser(request), "更新商品类别", model.getClientIp(),
                new Timestamp(System.currentTimeMillis())
                , tipType, "更新商品类别ID为  " + model.getIds() + " " + tipMsg + "！", "更新商品类别" + tipMsg));

        System.out.println("====================结束调用修改商品类别方法update()================");
        return m;
    }

    @RequestMapping("/checkIsNameExist")
    @ResponseBody
    public Map<String,Object> checkIsNameExist(MaterialCategory model){
        Map<String,Object> m = new HashMap<>();
        Boolean flag=false;
        System.out.println("检查输入名称是否存在");
        try {
            flag = materialCategoryCService.checkIsNameExist("name", model.getName(), "id", model.getId());
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>>>>>检查商品类别名称为：" + model.getName() + " ID为： " + model.getId() + " 是否存在异常！");
        } finally {
            try {
                m.put("flag",flag);
            } catch (Exception e) {
                System.out.println(">>>>>>>>>>>>回写检查商品类别名称为：" + model.getName() + " ID为： " + model.getId() + " 是否存在异常！");
                e.printStackTrace();
            }
        }
        return m;
    }

}
