package com.zking.erp.controller.cao;

import com.zking.erp.base.BaseController;
import com.zking.erp.model.cao.Depot;
import com.zking.erp.model.jhui.Log;
import com.zking.erp.service.cao.IDepotCService;
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
@RequestMapping("/cao/depot")
public class DepotCController extends BaseController{

    @Autowired
    private IDepotCService depotService;

    @RequestMapping("/create")
    @ResponseBody
    public Map<String,Object> create(HttpServletRequest request,Depot model){
        Map<String,Object> map = new HashMap<String, Object>();
        model.setId(UUID.randomUUID().toString().replace("-"," "));

        System.out.println("==================开始调用增加仓库信息方法create()===================");
        Boolean flag = false;
        try {
            depotService.insertDepot(model);
            map.put("message","true");

            //========标识位===========
            flag = true;
            //记录操作日志使用
            tipMsg = "成功";
            tipType = 0;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>>>>>>>增加仓库信息异常");
            map.put("message","false");
            flag = false;
            tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();
        } finally {
            try {
                map.put("flag",flag);
            } catch (Exception e) {
                System.out.println(">>>>>>>>>>>>增加仓库信息回写客户端结果异常");
                e.printStackTrace();
            }
        }

        logService.create(new Log(getUser(request), "增加仓库信息单位", model.getClientIp(),
                new Timestamp(System.currentTimeMillis())
                , tipType, "增加仓库信息单位名称为  " + model.getName() + " " + tipMsg + "！", "增加仓库信息单位" + tipMsg));
        System.out.println("==================结束调用增加仓库方法create()===================");
        return map;
    }

    @RequestMapping("getBasicData")
    @ResponseBody
    public Map<String,Object> getBasicData(Depot depot) {
        Map<String, Object> mapData = new HashMap<String, Object>();
        try {
            List<Depot> personList = depotService.queryDepotPager(depot,null);
            mapData.put("personList", personList);
        } catch (Exception e) {
            System.out.println(">>>>>>>>>>>>>查找系统基础数据信息异常");
            mapData.put("message","exceptoin");
            e.printStackTrace();
        }
        return mapData;
    }

    @RequestMapping("/findBy")
    @ResponseBody
    public Map<String,Object> findBy(HttpServletRequest req, Depot depot){
        PageBean pageBean = new PageBean();
        pageBean.setRequest(req);
        Map<String,Object> map=new HashMap<String, Object>();
        List<Depot> depotsList = depotService.queryDepotPager(depot, pageBean);
        List dataArray = new ArrayList();
        if (null != depotsList) {
            for (Depot depot1 : depotsList) {
                Map<String,Object> item=new HashMap<String, Object>();
                item.put("id", depot1.getId());
                //供应商名称
                item.put("name", depot1.getName());
                item.put("address", depot1.getAddress());
                item.put("warehousing", depot1.getWarehousing());
                item.put("truckage", depot1.getTruckage());
                item.put("type", depot1.getType());
                item.put("sort", depot1.getSort());
                item.put("remark", depot1.getRemark());
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
    public Map<String,Object> delete(HttpServletRequest request,Depot model){
        Map<String,Object> m = new HashMap<>();

        System.out.println("====================开始调用删除仓库信息方法delete()================");
        try {
            depotService.deleteDepotById(model.getId());
            System.out.println(model.getId());
            m.put("message","成功");
            //tipMsg = "成功";
            tipType = 0;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>删除ID为 " + model.getId() + "  的仓库信息异常");
            m.put("message","失败");
            //tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();
        }

        logService.create(new Log(getUser(request), "删除仓库信息", model.getClientIp(),
                new Timestamp(System.currentTimeMillis())
                , tipType, "删除仓库信息ID为  " + model.getId() + " " + tipMsg + "！", "删除仓库信息" + tipMsg));
        System.out.println("====================结束调用删除仓库信息方法delete()================");

        return m;
    }

    @RequestMapping("/batchDelete")
    @ResponseBody
    public Map<String,Object> batchDelete(HttpServletRequest request,Depot model){
        Map<String,Object> m = new HashMap<>();

        String[] split = model.getDepotIDs().split(",");
        model.setIds(split);


        System.out.println("====================开始调用批量删除仓库信息方法batchDelete()================");
        try {
            depotService.deleteDepotByIds(model);
            m.put("message","成功");
            //记录操作日志使用
            tipMsg = "成功";
            tipType = 0;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>批量删除仓库信息ID为：" + model.getDepotIDs() + "信息异常");
            tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();
        }

        logService.create(new Log(getUser(request), "批量删除仓库信息", model.getClientIp(),
                new Timestamp(System.currentTimeMillis())
                , tipType, "批量删除计仓库信息ID为  " + model.getIds() + " " + tipMsg + "！", "批量删除仓库信息" + tipMsg));
        System.out.println("====================结束调用批量删除仓库信息方法batchDelete()================");
        return m;
    }

    @RequestMapping("/update")
    @ResponseBody
    public Map<String,Object> update(HttpServletRequest request,Depot model){
        Map<String,Object> m = new HashMap<>();

        System.out.println("====================开始调用修改仓库信息方法update()================");
        Boolean flag = false;
        try {
            depotService.updateDepotById(model);

            flag = true;
            m.put("message","成功");
            //tipMsg = "成功";
            tipType = 0;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>修改仓库信息ID为 ： " + model.getId() + "信息失败");
            flag = false;
            m.put("message","失败");
            //tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();
        } finally {
            try {
                m.put("flag",flag);
            } catch (Exception e) {
                System.out.println(">>>>>>>>>>>>修改仓库信息回写客户端结果异常");
                e.printStackTrace();
            }
        }

        logService.create(new Log(getUser(request), "更新仓库信息", model.getClientIp(),
                new Timestamp(System.currentTimeMillis())
                , tipType, "更新仓库信息ID为  " + model.getIds() + " " + tipMsg + "！", "更新仓库信息" + tipMsg));

        System.out.println("====================结束调用修改仓库信息方法update()================");
        return m;
    }

    @RequestMapping("/checkIsNameExist")
    @ResponseBody
    public Map<String,Object> checkIsNameExist(Depot model){
        Boolean flag=false;
        Map<String,Object> m = new HashMap<>();

        System.out.println("检查输入名称是否存在");
        try {
            flag = depotService.checkIsNameExist("uname", model.getName(), "id", model.getId());
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>>>>>检查仓库名称为：" + model.getName() + " ID为： " + model.getId() + " 是否存在异常！");
        } finally {
            try {
                m.put("flag",flag);
            } catch (Exception e) {
                System.out.println(">>>>>>>>>>>>回写检查仓库名称为：" + model.getName() + " ID为： " + model.getId() + " 是否存在异常！");
                e.printStackTrace();
            }
        }
        return m;
    }

}
