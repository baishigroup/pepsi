package com.zking.erp.controller.cao;

import com.zking.erp.base.BaseController;
import com.zking.erp.model.cao.Person;
import com.zking.erp.service.cao.IPersonCService;
import com.zking.erp.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/cao/person")
public class PersonCController extends BaseController{

    @Autowired
    private IPersonCService personService;

    @RequestMapping("getBasicData")
    @ResponseBody
    public Map<String,Object> getBasicData(Person person) {
        Map<String, Object> mapData = new HashMap<String, Object>();
        try {
            List<Person> personList = personService.queryPersonAllPager(person,null);
            mapData.put("personList", personList);
        } catch (Exception e) {
            System.out.println(">>>>>>>>>>>>>查找系统基础数据信息异常");
            mapData.put("message","exceptoin");
            e.printStackTrace();
        }
        return mapData;
    }

    @RequestMapping("/create")
    @ResponseBody
    public Map<String,Object> create(Person model){
        Map<String,Object> map = new HashMap<String, Object>();
        model.setId(UUID.randomUUID().toString().replace("-"," "));

        System.out.println("==================开始调用增加经手人方法create()===================");
        Boolean flag = false;
        try {
            personService.insertPerson(model);
            map.put("message","true");

            //========标识位===========
            flag = true;
            //记录操作日志使用
            tipMsg = "成功";
            tipType = 0;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>>>>>>>增加经手人异常");
            map.put("message","false");
            flag = false;
            tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();
        } finally {
            try {
                map.put("flag",flag);
            } catch (Exception e) {
                System.out.println(">>>>>>>>>>>>修改经手人回写客户端结果异常");
                e.printStackTrace();
            }
        }

//        logService.create(new Logdetails(getUser(), "增加经手人", model.getClientIp(),
//                new Timestamp(System.currentTimeMillis())
//                , tipType, "增加经手人名称为  " + model.getUName() + " " + tipMsg + "！", "增加经手人" + tipMsg));
        System.out.println("==================结束调用增加经手人方法create()===================");
        return map;
    }

    @RequestMapping("/findBy")
    @ResponseBody
    public Map<String,Object> findBy(HttpServletRequest req, Person model){
        PageBean pageBean = new PageBean();
        pageBean.setRequest(req);
        Map<String,Object> map=new HashMap<String, Object>();
        List<Person> personsList = personService.queryPersonAllPager(model,pageBean);
        List dataArray = new ArrayList();
        if (null != personsList) {
            for (Person person : personsList) {
                Map<String,Object> item=new HashMap<String, Object>();
                item.put("Id", person.getId());
                item.put("Type", person.getType());
                item.put("Name", person.getName());
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
    public Map<String,Object> delete(Person model){
        Map<String,Object> m = new HashMap<>();

        System.out.println("====================开始调用删除经手人方法delete()================");
        try {
            personService.deletePersonById(model.getId());
            System.out.println(model.getId());
            m.put("message","成功");
            //tipMsg = "成功";
            tipType = 0;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>删除ID为 " + model.getId() + "  的经手人异常");
            m.put("message","失败");
            //tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();
        }

//        logService.create(new Logdetails(getUser(), "删除经手人", model.getClientIp(),
//                new Timestamp(System.currentTimeMillis())
//                , tipType, "删除经手人ID为  " + model.getUnitID() + " " + tipMsg + "！", "删除经手人" + tipMsg));
        System.out.println("====================结束调用删除经手人方法delete()================");

        return m;
    }

    @RequestMapping("/batchDelete")
    @ResponseBody
    public Map<String,Object> batchDelete(Person model){
        Map<String,Object> m = new HashMap<>();

        String[] split = model.getPersonIDs().split(",");
        model.setIds(split);


        System.out.println("====================开始调用批量删除经手人方法batchDelete()================");
        try {
            personService.deletePersonByIds(model);
            m.put("message","成功");
            //记录操作日志使用
            tipMsg = "成功";
            tipType = 0;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>批量删除经手人ID为：" + model.getPersonIDs() + "信息异常");
            tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();
        }

//        logService.create(new Logdetails(getUser(), "批量删除经手人", model.getClientIp(),
//                new Timestamp(System.currentTimeMillis())
//                , tipType, "批量删除经手人ID为  " + model.getUnitIDs() + " " + tipMsg + "！", "批量删除经手人" + tipMsg));
        System.out.println("====================结束调用批量删除经手人方法batchDelete()================");
        return m;
    }

    @RequestMapping("/update")
    @ResponseBody
    public Map<String,Object> update(Person model){
        Map<String,Object> m = new HashMap<>();

        System.out.println("====================开始调用修改经手人方法update()================");
        Boolean flag = false;
        try {
            personService.updatePersonById(model);

            flag = true;
            m.put("message","成功");
            //tipMsg = "成功";
            tipType = 0;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>修改经手人ID为 ： " + model.getId() + "信息失败");
            flag = false;
            m.put("message","失败");
            //tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();
        } finally {
            try {
                m.put("flag",flag);
            } catch (Exception e) {
                System.out.println(">>>>>>>>>>>>修改经手人回写客户端结果异常");
                e.printStackTrace();
            }
        }

//        logService.create(new Logdetails(getUser(), "更新经手人", model.getClientIp(),
//                new Timestamp(System.currentTimeMillis())
//                , tipType, "更新经手人ID为  " + model.getUnitID() + " " + tipMsg + "！", "更新经手人" + tipMsg));

        System.out.println("====================结束调用修改经手人方法update()================");
        return m;
    }

    @RequestMapping("/checkIsNameExist")
    @ResponseBody
    public Map<String,Object> checkIsNameExist(Person model){
        Boolean flag=false;
        Map<String,Object> m = new HashMap<>();

        System.out.println("检查输入名称是否存在");
        try {
            flag = personService.checkIsNameExist("name", model.getName(), "id", model.getId());
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>>>>>检查经手人名称为：" + model.getName() + " ID为： " + model.getId() + " 是否存在异常！");
        } finally {
            try {
                m.put("flag",flag);
            } catch (Exception e) {
                System.out.println(">>>>>>>>>>>>回写检查经手人名称为：" + model.getName() + " ID为： " + model.getId() + " 是否存在异常！");
                e.printStackTrace();
            }
        }
        return m;
    }

    /**
     * 根据Id获取经手人信息
     *
     * @return
     */
    @RequestMapping("/getPersonByIds")
    @ResponseBody
    public Map<String,Object> getPersonByIds(Person model) {
        Map<String, Object> mapData = new HashMap<String, Object>();
        try {
            List<Person> dataList =personService.queryPersonByIds(model);
            StringBuffer sb = new StringBuffer();
            if (null != dataList) {
                for (Person person : dataList) {
                    sb.append(person.getName() + " ");
                }
            }
            mapData.put("sb",sb.toString());
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>查找信息异常");
            e.printStackTrace();
        }
        return  mapData;
    }


}
