package com.zking.erp.controller.jhui;

import com.zking.erp.base.BaseController;
import com.zking.erp.model.jhui.Log;
import com.zking.erp.model.jhui.Role;
import com.zking.erp.service.jhui.IRoleJService;
import com.zking.erp.service.jhui.IUserBusinessJService;
import com.zking.erp.util.PageBean;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

@Controller
@RequestMapping("/role")
public class RoleJController extends BaseController {

    @Autowired
    private IRoleJService roleService;

    @Autowired
    private IUserBusinessJService userBusinessService;

    /**
     * 用户对应角色显示
     *
     * @return
     */
    @RequestMapping("/findUserRole")
    @ResponseBody
    public List findUserRole(Role model) {
        List lst=new ArrayList();
        try {
           PageBean pageBean=new PageBean();
           pageBean.setPagination(false);
            List<Role> dataList = roleService.queryRolePager(model,pageBean);
            Map<String,Object> outer = new HashMap<String, Object>();
            outer.put("id", 1);
            outer.put("text", "角色列表");
            outer.put("state", "open");
            //存放数据json数组
            List dataArray = new ArrayList();
            if (null != dataList) {
                for (Role role : dataList) {
                    Map<String,Object> item = new HashMap<String, Object>();
                    item.put("id", role.getId());
                    item.put("text", role.getName());
                    //勾选判断1
                    Boolean flag = false;
                    try {
                        flag = userBusinessService.checkIsUserBusinessExist("Type", model.getUBType(), "KeyId", model.getUBKeyId(), "Value", "[" + role.getId().toString() + "]");
                    } catch (DataAccessException e) {
                        System.out.println(">>>>>>>>>>>>>>>>>设置用户对应的角色：类型" + model.getUBType() + " KeyId为： " + model.getUBKeyId() + " 存在异常！");
                    }
                    if (flag == true) {
                        item.put("checked", true);
                    }
                    //结束
                    dataArray.add(item);
                }
            }
            outer.put("children", dataArray);
            lst.add(outer);
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>>>>>>>查找角色异常");
            e.printStackTrace();
        }
        return lst;
    }


    /**
     * 查找角色信息
     *
     * @return
     */
    @RequestMapping("/findBy")
    @ResponseBody
    public Map<String,Object> findBy(HttpServletRequest req,Role model) {
        try {
            PageBean pageBean=new PageBean();
            pageBean.setRequest(req);
            List<Role> dataList= roleService.queryRolePager(model,pageBean);
            //开始拼接json数据
//            {"total":28,"rows":[
//                {"productid":"AV-CB-01","attr1":"Adult Male","itemid":"EST-18"}
//            ]}
            Map<String,Object> outer = new HashMap<String, Object>();
            outer.put("total", pageBean.getTotal());
            //存放数据json数组
            List dataArray = new ArrayList();
            if (null != dataList) {
                for (Role role : dataList) {
                    JSONObject item = new JSONObject();
                    item.put("Id", role.getId());
                    //供应商名称
                    item.put("Name", role.getName());
                    item.put("op", 1);
                    dataArray.add(item);
                }
            }
            outer.put("rows", dataArray);
            //回写查询结果
            return outer;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>>>>>>>查找角色信息异常");
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.out.println(">>>>>>>>>>>>>>>>>>>回写查询角色信息结果异常");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 增加角色
     *
     * @return
     */
    @RequestMapping("/create")
    @ResponseBody
    public Map<String,Object> create(HttpServletRequest request,Role model) {
        System.out.println("==================开始调用增加角色信息方法create()===================");
        Map<String,Object> map=new HashMap<String, Object>();
        Boolean flag = false;
        try {
            Role role = new Role();
            role.setId(UUID.randomUUID().toString());
            role.setName(model.getName());
            roleService.insert(role);

            //========标识位===========
            flag = true;
            //记录操作日志使用
            tipMsg = "成功";
            tipType = 0;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>>>>>>>增加角色信息异常");
            flag = false;
            tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();
        }
            map.put("flag",flag);
        logService.create(new Log(getUser(request), "增加角色", model.getClientIp(),
                new Timestamp(System.currentTimeMillis())
                , tipType, "增加角色名称为  " + model.getName() + " " + tipMsg + "！", "增加角色" + tipMsg));
        return map;
    }

    /**
     * 更新角色
     *
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public Map<String,Object> update(HttpServletRequest request,Role model) {
        Map<String,Object> map=new HashMap<String, Object>();
        Boolean flag = false;
        try {
            Role role = roleService.selectById(model.getRoleID());
            role.setName(model.getName());
            roleService.updateById(role);

            flag = true;
            tipMsg = "成功";
            tipType = 0;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>修改角色ID为 ： " + model.getRoleID() + "信息失败");
            flag = false;
            tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();
        } finally {
            try {
                map.put("flag",flag);
            } catch (Exception e) {
                System.out.println(">>>>>>>>>>>>修改角色回写客户端结果异常");
                e.printStackTrace();
            }
        }
        logService.create(new Log(getUser(request), "更新角色", model.getClientIp(),
                new Timestamp(System.currentTimeMillis())
                , tipType, "更新角色ID为  " + model.getRoleID() + " " + tipMsg + "！", "更新角色" + tipMsg));
        return map;
    }


    /**
     * 删除角色
     *
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public  Map<String,Object> delete(HttpServletRequest request,Role model) {
        Map<String,Object> map=new HashMap<String, Object>();
        System.out.println("====================开始调用删除角色信息方法delete()================");
        try {
            roleService.deleteById(model.getRoleID());
            tipMsg = "成功";
            tipType = 0;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>删除ID为 " + model.getRoleID() + "  的角色异常");
            tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();
        }
        map.put("message",tipMsg);
        logService.create(new Log(getUser(request), "删除角色", model.getClientIp(),
                new Timestamp(System.currentTimeMillis())
                , tipType, "删除角色ID为  " + model.getRoleID() + " " + tipMsg + "！", "删除角色" + tipMsg));
        System.out.println("====================结束调用删除角色信息方法delete()================");
        return map;
    }

    /**
     * 批量删除指定ID角色
     *
     * @return
     */
    @RequestMapping("/batchDelete")
    @ResponseBody
    public Map<String,Object> batchDelete(HttpServletRequest request,Role model) {
        Map<String,Object> map=new HashMap<String, Object>();
        try {
            roleService.delete(model);
            map.put("message","成功");
            //记录操作日志使用
            tipMsg = "成功";
            tipType = 0;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>批量删除角色ID为：" + model.getRoleIDs() + "信息异常");
            tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();
        }

        logService.create(new Log(getUser(request), "批量删除角色", model.getClientIp(),
                new Timestamp(System.currentTimeMillis())
                , tipType, "批量删除角色ID为  " + model.getRoleIDs() + " " + tipMsg + "！", "批量删除角色" + tipMsg));
        return map;
    }

    /**
     * 检查输入名称是否存在
     */
    @RequestMapping("/checkIsNameExist")
    @ResponseBody
    public Map<String,Object> checkIsNameExist(Role model) {
        Boolean flag = false;
        Map<String,Object> map=new HashMap<String, Object>();
        try {
            flag = roleService.checkIsNameExist("name", model.getName(), "Id", model.getRoleID());
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>>>>>检查角色名称为：" + model.getName() + " ID为： " + model.getRoleID() + " 是否存在异常！");
        } finally {
            try {
                map.put("flag",flag);
            } catch (Exception e) {
                System.out.println(">>>>>>>>>>>>回写检查角色名称为：" + model.getName() + " ID为： " + model.getRoleID() + " 是否存在异常！");
                e.printStackTrace();
            }
        }
        return map;
    }

}
