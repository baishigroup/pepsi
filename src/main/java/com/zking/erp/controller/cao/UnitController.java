package com.zking.erp.controller.cao;

import com.zking.erp.base.BaseController;
import com.zking.erp.model.cao.Unit;
import com.zking.erp.service.cao.IUnitService;
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
@RequestMapping("/cao/unit")
public class UnitController extends BaseController{
    @Autowired
    private IUnitService unitService;

    @RequestMapping("/create")
    public Map<String,Object> create(Unit unit){
        Map<String,Object> map = new HashMap<String, Object>();
        unit.setId(UUID.randomUUID().toString().replace("-"," "));

        System.out.println("==================开始调用增加计量单位方法create()===================");
        Boolean flag = false;
        try {
            unitService.insertUnit(unit);
            map.put("message","true");

            //========标识位===========
            flag = true;
            //记录操作日志使用
            tipMsg = "成功";
            tipType = 0;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>>>>>>>增加计量单位异常");
            map.put("message","false");
            flag = false;
            tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();
        }
//        finally {
//            try {
//                toClient(flag.toString());
//            } catch (IOException e) {
//                System.out.println(">>>>>>>>>>>>增加计量单位回写客户端结果异常");
//                e.printStackTrace();
//            }
//        }

//        logService.create(new Logdetails(getUser(), "增加计量单位", model.getClientIp(),
//                new Timestamp(System.currentTimeMillis())
//                , tipType, "增加计量单位名称为  " + model.getUName() + " " + tipMsg + "！", "增加计量单位" + tipMsg));
        System.out.println("==================结束调用增加计量单位方法create()===================");
        return map;
    }

    @RequestMapping("/findBy")
    @ResponseBody
    public Map<String,Object> findBy(HttpServletRequest req, Unit unit){
        PageBean pageBean = new PageBean();
        pageBean.setRequest(req);
        Map<String,Object> map=new HashMap<String, Object>();
        List<Unit> unitsList = unitService.queryUnitPager(unit, pageBean);
        List dataArray = new ArrayList();
        if (null != unitsList) {
            for (Unit unit1 : unitsList) {
                Map<String,Object> item=new HashMap<String, Object>();
                item.put("id", unit1.getId());
                //名称
                item.put("UName", unit1.getUname());
                item.put("op", 1);
                dataArray.add(item);
            }
        }

        map.put("total",pageBean.getTotal());
        map.put("rows",dataArray);
        return map;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Map<String,Object> delete(Unit unit){
        Map<String,Object> m = new HashMap<>();

        System.out.println("====================开始调用删除计量单位方法delete()================");
        try {
            unitService.deleteUnitById(unit.getId());
            System.out.println(unit.getId());
            m.put("message","成功");
            //tipMsg = "成功";
            tipType = 0;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>删除ID为 " + unit.getId() + "  的计量单位异常");
            m.put("message","失败");
            //tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();
        }
//        model.getShowModel().setMsgTip(tipMsg);
//        logService.create(new Logdetails(getUser(), "删除计量单位", model.getClientIp(),
//                new Timestamp(System.currentTimeMillis())
//                , tipType, "删除计量单位ID为  " + model.getUnitID() + " " + tipMsg + "！", "删除计量单位" + tipMsg));
        System.out.println("====================结束调用删除计量单位方法delete()================");

        return m;
    }

    @RequestMapping("/batchDelete")
    @ResponseBody
    public Map<String,Object> batchDelete(Unit unit){
        Map<String,Object> m = new HashMap<>();

        System.out.println("====================开始调用批量删除计量单位方法batchDelete()================");
        try {
            unitService.deleteUnitByIds(unit);
            m.put("message","成功");
            //记录操作日志使用
            tipMsg = "成功";
            tipType = 0;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>批量删除计量单位ID为：" + unit.getIds() + "信息异常");
            tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();
        }

//        logService.create(new Logdetails(getUser(), "批量删除计量单位", model.getClientIp(),
//                new Timestamp(System.currentTimeMillis())
//                , tipType, "批量删除计量单位ID为  " + model.getUnitIDs() + " " + tipMsg + "！", "批量删除计量单位" + tipMsg));
        System.out.println("====================结束调用批量删除计量单位方法batchDelete()================");
        return m;
    }

    @RequestMapping("/update")
    @ResponseBody
    public Map<String,Object> update(Unit unit){
        Map<String,Object> m = new HashMap<>();

        System.out.println("====================开始调用修改计量单位方法update()================");
        Boolean flag = false;
        try {
            unitService.updateUnitById(unit);

            flag = true;
            m.put("message","成功");
            //tipMsg = "成功";
            tipType = 0;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>修改计量单位ID为 ： " + unit.getId() + "信息失败");
            flag = false;
            m.put("message","失败");
            //tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();
        }
//        finally {
//            try {
//                toClient(flag.toString());
//            } catch (IOException e) {
//                Log.errorFileSync(">>>>>>>>>>>>修改计量单位回写客户端结果异常", e);
//            }
//        }

//        logService.create(new Logdetails(getUser(), "更新计量单位", model.getClientIp(),
//                new Timestamp(System.currentTimeMillis())
//                , tipType, "更新计量单位ID为  " + model.getUnitID() + " " + tipMsg + "！", "更新计量单位" + tipMsg));

        System.out.println("====================结束调用修改计量单位方法update()================");
        return m;
    }

    @RequestMapping("/checkIsNameExist")
    @ResponseBody
    public Map<String,Object> checkIsNameExist(Unit unit){
        Map<String,Object> m = new HashMap<>();

        System.out.println("====================开始调用判断是否重复的方法checkIsNameExist()================");

        List<Unit> units = unitService.queryUnitPager(unit, null);
        for (Unit unit1 : units) {
            if(unit.equals(unit1.getUname())){
                m.put("message","false");
            }else{
                m.put("message","true");
            }
        }
        System.out.println("====================结束调用判断是否重复的方法checkIsNameExist()================");

        return m;
    }


}
