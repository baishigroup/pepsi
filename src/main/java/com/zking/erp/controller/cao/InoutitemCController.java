package com.zking.erp.controller.cao;

import com.zking.erp.base.BaseController;
import com.zking.erp.model.cao.InoutItem;
import com.zking.erp.model.jhui.Log;
import com.zking.erp.service.cao.IInoutItemCService;
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
@RequestMapping("/cao/inOutItem")
public class InoutitemCController extends BaseController{

    @Autowired
    private IInoutItemCService inoutItemCService;

    @RequestMapping("/create")
    @ResponseBody
    public Map<String,Object> create(HttpServletRequest request,InoutItem model){
        Map<String,Object> map = new HashMap<String, Object>();
        model.setId(UUID.randomUUID().toString().replace("-"," "));

        System.out.println("==================开始调用增加收支项目create()===================");
        Boolean flag = false;
        try {
            inoutItemCService.insertInoutItem(model);
            map.put("message","true");

            //========标识位===========
            flag = true;
            //记录操作日志使用
            tipMsg = "成功";
            tipType = 0;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>>>>>>>增加收支项目异常");
            map.put("message","false");
            flag = false;
            tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();
        } finally {
            try {
                map.put("flag",flag);
            } catch (Exception e) {
                System.out.println(">>>>>>>>>>>>增加收支项目回写客户端结果异常");
                e.printStackTrace();
            }
        }

        logService.create(new Log(getUser(request), "增加收支项目", model.getClientIp(),
                new Timestamp(System.currentTimeMillis())
                , tipType, "增增加收支项目名称为  " + model.getName() + " " + tipMsg + "！", "增加收支项目" + tipMsg));
        System.out.println("==================结束调用增加收支项目create()===================");
        return map;
    }

    @RequestMapping("/findBy")
    @ResponseBody
    public Map<String,Object> findBy(HttpServletRequest req, InoutItem model){
        PageBean pageBean = new PageBean();
        pageBean.setRequest(req);
        Map<String,Object> map=new HashMap<String, Object>();
        List<InoutItem> inoutItemsList = inoutItemCService.queryInoutItemPager(model, pageBean);
        List dataArray = new ArrayList();
        if (null != inoutItemsList) {
            for (InoutItem inOutItem : inoutItemsList) {
                Map<String,Object> item=new HashMap<String, Object>();
                item.put("id", inOutItem.getId());
                //收支项目名称
                item.put("name", inOutItem.getName());
                item.put("type", inOutItem.getType());
                item.put("remark", inOutItem.getRemark());
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
    public Map<String,Object> delete(HttpServletRequest request,InoutItem model){
        Map<String,Object> m = new HashMap<>();

        System.out.println("====================开始调用删除收支项目方法delete()================");
        try {
            inoutItemCService.deleteInoutItemById(model.getId());
            m.put("message","成功");
            //tipMsg = "成功";
            tipType = 0;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>删除ID为 " + model.getId() + "  的收支项目异常");
            m.put("message","失败");
            //tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();
        }
        logService.create(new Log(getUser(request), "删除收支", model.getClientIp(),
                new Timestamp(System.currentTimeMillis())
                , tipType, "删除收支ID为  " + model.getUnitIDs() + " " + tipMsg + "！", "删除收支" + tipMsg));
        System.out.println("====================结束调用删除收支项目方法delete()================");

        return m;
    }

    @RequestMapping("/batchDelete")
    @ResponseBody
    public Map<String,Object> batchDelete(HttpServletRequest request,InoutItem model){
        Map<String,Object> m = new HashMap<>();

        String[] split = model.getUnitIDs().split(",");
        model.setIds(split);


        System.out.println("====================开始调用批量删除收支项目方法batchDelete()================");
        try {
            inoutItemCService.deleteInoutItemByIds(model);
            m.put("message","成功");
            //记录操作日志使用
            tipMsg = "成功";
            tipType = 0;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>批量删除收支项目ID为：" + model.getIds() + "信息异常");
            tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();
        }

        logService.create(new Log(getUser(request), "批量删除收支", model.getClientIp(),
                new Timestamp(System.currentTimeMillis())
                , tipType, "批量删除收支ID为  " + model.getUnitIDs() + " " + tipMsg + "！", "批量删除收支" + tipMsg));
        System.out.println("====================结束调用批量删除收支项目方法batchDelete()================");
        return m;
    }

    @RequestMapping("/update")
    @ResponseBody
    public Map<String,Object> update(HttpServletRequest request,InoutItem model){
        Map<String,Object> m = new HashMap<>();

        System.out.println("====================开始调用修改收支项目方法update()================");
        Boolean flag = false;
        try {
            inoutItemCService.updateInoutItemById(model);

            flag = true;
            m.put("message","true");
            //tipMsg = "成功";
            tipType = 0;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>修改收支项目ID为 ： " + model.getId() + "信息失败");
            flag = false;
            m.put("message","false");
            //tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();
        } finally {
            try {
                m.put("flag",flag);
            } catch (Exception e) {
                System.out.println(">>>>>>>>>>>>修改计量单位回写客户端结果异常");
                e.printStackTrace();
            }
        }

        logService.create(new Log(getUser(request), "更新收支", model.getClientIp(),
                new Timestamp(System.currentTimeMillis())
                , tipType, "更新收支ID为  " + model.getUnitIDs() + " " + tipMsg + "！", "更新收支" + tipMsg));

        System.out.println("====================结束调用修改收支项目方法update()================");
        return m;
    }

    @RequestMapping("/checkIsNameExist")
    @ResponseBody
    public Map<String,Object> checkIsNameExist(InoutItem model){
        Boolean flag=false;
        Map<String,Object> m = new HashMap<>();

        System.out.println("检查输入名称是否存在");
        try {
            flag = inoutItemCService.checkIsNameExist("name", model.getName(), "id", model.getId());
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>>>>>检查收支名称为：" + model.getName() + " ID为： " + model.getId() + " 是否存在异常！");
        } finally {
            try {
                m.put("flag",flag);
            } catch (Exception e) {
                System.out.println(">>>>>>>>>>>>回写检查收支名称为：" + model.getName() + " ID为： " + model.getId() + " 是否存在异常！");
                e.printStackTrace();
            }
        }
        return m;
    }

    /**
     * 查找收支项目信息-下拉框
     *
     * @return
     */
    @RequestMapping("/findBySelect")
    @ResponseBody
    public List<InoutItem> findBySelect(InoutItem model) {
        try {
            if (model.getType().equals("in")) {
                model.setType( "收入");
            } else if (model.getType().equals("out")) {
                model.setType( "支出");
            }
            List<InoutItem> dataList =inoutItemCService.queryInoutItemBySelect(model);
            //存放数据json数组
            List dataArray = new ArrayList();
            if (null != dataList) {
                for (InoutItem inOutItem : dataList) {
                    Map<String,Object> item = new HashMap<String, Object>();
                    item.put("Id", inOutItem.getId());
                    //收支项目名称
                    item.put("InOutItemName", inOutItem.getName());
                    dataArray.add(item);
                }
            }
            return dataArray;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>查找收支项目信息异常");
            e.printStackTrace();
            throw  new RuntimeException(e);
        } catch (Exception e) {
            System.out.println(">>>>>>>>>回写查询收支项目信息结果异常");
            e.printStackTrace();
            throw  new RuntimeException(e);
        }
    }
}
