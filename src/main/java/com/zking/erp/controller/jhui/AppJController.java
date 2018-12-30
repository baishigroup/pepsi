package com.zking.erp.controller.jhui;

import com.zking.erp.base.BaseController;
import com.zking.erp.model.jhui.App;
import com.zking.erp.model.jhui.Log;
import com.zking.erp.service.jhui.IAppJService;
import com.zking.erp.service.jhui.IUserBusinessJService;
import com.zking.erp.util.PageBean;
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
@RequestMapping("/app")
public class AppJController extends BaseController {
    @Autowired
    private IAppJService appService;
    @Autowired
    private IUserBusinessJService userBusinessService;

    /**
     * 桌面应用显示
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/findDesk")
    public Map<String,Object> findDesk(HttpServletRequest req ,App App) {
        Map<String, Object> outer = new HashMap<String, Object>();
        try {
            //下面是dock
            App app1 = new App();
            app1.setZl("dock");
            List<App> dataList1 = appService.queryAppByfindDesk(app1);
            System.out.println(dataList1);
            List dataArray1 = new ArrayList();
            if (null != dataList1) {
                for (App app : dataList1) {
                    Map<String, Object> item = new HashMap<String, Object>();
                    item.put("id", app.getId());
                    item.put("title", app.getName());
                    item.put("type", app.getType());
                    item.put("icon", "../../upload/images/deskIcon/" + app.getIcon());
                    item.put("url", app.getUrl());
                    item.put("width", app.getWidth());
                    item.put("height", app.getHeight());
                    item.put("isresize", app.getResize());
                    item.put("isopenmax", app.getOpenmax());
                    item.put("isflash", app.getFlash());
                    dataArray1.add(item);


                }
            }
            outer.put("dock", dataArray1);

            //下面是desk
            App app2 = new App();
            app2.setZl("desk");
            List<App> dataList2 = appService.queryAppByfindDesk(app2);
            List dataArray2 = new ArrayList();
            if (null != dataList2) {
                for (App app : dataList2) {
                    Map<String, Object> item = new HashMap<String, Object>();
                    item.put("id", app.getId());
                    item.put("title", app.getName());
                    item.put("type", app.getType());
                    item.put("icon", "../../upload/images/deskIcon/" + app.getIcon());
                    item.put("url", "../../pages/common/menu.jsp?appID=" + app.getNumber() + "&id=" + app.getId());
                    item.put("width", app.getWidth());
                    item.put("height", app.getHeight());
                    item.put("isresize", app.getResize());
                    item.put("isopenmax", app.getOpenmax());
                    item.put("isflash", app.getFlash());
                    dataArray2.add(item);
                }
            }
            outer.put("desk", dataArray2);
            return outer;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    /**
     * 角色对应应用显示
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/findRoleAPP")
    public List findRoleAPP(App model) {
        try {
            List lst=new ArrayList();
            List<App> dataList = appService.queryAppByRole(model);
            Map<String,Object> outer = new HashMap<String, Object>();
            outer.put("id", 1);
            outer.put("text", "应用列表");
            outer.put("state", "open");
            List dataArray = new ArrayList();
            if (null != dataList) {
                for (App app : dataList) {
                    Map<String,Object> item = new HashMap<String, Object>();
                    item.put("id", app.getId());
                    item.put("text", app.getName());
                    //勾选判断1
                    Boolean flag = false;
                    try {
                        flag = userBusinessService.checkIsUserBusinessExist("Type", model.getUBType(), "KeyId", model.getUBKeyId(), "Value", "[" + app.getId().toString() + "]");
                    } catch (DataAccessException e) {
                        System.out.println(">>>>>>>>>>>>>>>>>设置角色对应的应用：类型" + model.getUBType() + " KeyId为： " + model.getUBKeyId() + " 存在异常！");
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
            System.out.println(lst);
            return lst;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>>>>>>>查找应用异常");
            e.printStackTrace();
            throw  new RuntimeException(e);
        } catch (Exception e) {
            System.out.println(">>>>>>>>>>>>>>>>>>>回写查询应用结果异常");
            e.printStackTrace();
            throw  new RuntimeException(e);
        }
    }

    /**
     * 查找应用信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/findBy")
    public Map<String,Object> findBy(HttpServletRequest req,App model) {
        try {
            PageBean pageBean=new PageBean();
            pageBean.setRequest(req);
            List<App> dataList=appService.queryAppByLikePager(model,pageBean);
//            {"total":28,"rows":[
//                {"productid":"AV-CB-01","attr1":"Adult Male","itemid":"EST-18"}
//            ]}
            Map<String,Object> outer = new HashMap<String, Object>();
            outer.put("total", pageBean.getTotal());
            List dataArray = new ArrayList();
            if (null != dataList) {
                for (App app : dataList) {
                    Map<String,Object> item = new HashMap<String, Object>();
                    item.put("Id", app.getId());
                    //应用名称
                    item.put("Number", app.getNumber());
                    item.put("Name", app.getName());
                    item.put("Type", app.getType());
                    item.put("Icon", app.getIcon());
                    item.put("URL", app.getUrl());
                    item.put("Width", app.getWidth());
                    item.put("Height", app.getHeight());
                    item.put("ReSize", app.getResize());
                    item.put("OpenMax", app.getOpenmax());
                    item.put("Flash", app.getFlash());
                    item.put("ZL", app.getZl());
                    item.put("Sort", app.getSort());
                    item.put("Remark", app.getRemark());
                    item.put("Enabled", app.getEnabled());
                    item.put("op", 1);
                    dataArray.add(item);
                }
            }
            outer.put("rows", dataArray);
            //回写查询结果
            return outer;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>>>>>>>查找应用异常");
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.out.println(">>>>>>>>>>>>>>>>>>>回写查询应用结果异常");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 检查输入名称是否存在
     */
    @ResponseBody
    @RequestMapping("/checkIsNameExist")
    public  Map<String,Object> checkIsNameExist(App model) {
        Boolean flag = false;
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            flag = appService.checkIsNameExist("name", model.getName(), "Id", model.getAppID());
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>>>>>检查应用名称为：" + model.getName() + " ID为： " + model.getAppID() + " 是否存在异常！");
        }
        map.put("flag",flag);
        return map;
    }

    /**
     * 增加应用
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/create")
    public Map<String,Object> create(HttpServletRequest request,App model) {
        System.out.println("==================开始调用增加应用方法create()===================");
        Boolean flag = false;
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            model.setId(UUID.randomUUID().toString());
            appService.insert(model);

            //========标识位===========
            flag = true;
            //记录操作日志使用
            tipMsg = "成功";
            tipType = 0;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>>>>>>>增加应用异常");
            flag = false;
            tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();
        }
        logService.create(new Log(getUser(request), "增加应用", model.getClientIp(),
                new Timestamp(System.currentTimeMillis())
                , tipType, "增加应用名称为  " + model.getName() + " " + tipMsg + "！", "增加应用" + tipMsg));
        System.out.println("==================结束调用增加应用方法create()===================");
        map.put("flag",flag);
        return map;
    }

    /**
     * 修改应用
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/update")
    public  Map<String,Object> update(HttpServletRequest request,App model) {
        Boolean flag = false;
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            App app = appService.selectById(model.getAppID());
            app.setNumber(model.getNumber());
            app.setName(model.getName());
            app.setType(model.getType());
            //app.setIcon(model.getIcon());
            app.setUrl(model.getUrl());
            app.setWidth(model.getWidth());
            app.setHeight(model.getHeight());
            app.setResize(model.getResize());
            app.setOpenmax(model.getOpenmax());
            app.setFlash(model.getFlash());
            app.setZl(model.getZl());
            app.setSort(model.getSort());
            app.setRemark(model.getRemark());
            app.setEnabled(model.getEnabled());
            appService.updateById(app);

            flag = true;
            tipMsg = "成功";
            tipType = 0;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>修改应用ID为 ： " + model.getAppID() + "信息失败");
            flag = false;
            tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();
        }
        map.put("flag",flag);
        logService.create(new Log(getUser(request), "更新应用", model.getClientIp(),
                new Timestamp(System.currentTimeMillis())
                , tipType, "更新应用ID为  " + model.getAppID() + " " + tipMsg + "！", "更新应用" + tipMsg));
        return map;
    }

    /**
     * 批量删除指定ID应用
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/batchDelete")
    public Map<String,Object>  batchDelete(HttpServletRequest request,App model) {
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            appService.delete(model);
            map.put("message","成功");
            //记录操作日志使用
            tipMsg = "成功";
            tipType = 0;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>批量删除应用ID为：" + model.getAppIDs() + "信息异常");
            tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();
        }

        logService.create(new Log(getUser(request), "批量删除应用", model.getClientIp(),
                new Timestamp(System.currentTimeMillis())
                , tipType, "批量删除应用ID为  " + model.getAppIDs() + " " + tipMsg + "！", "批量删除应用" + tipMsg));
        return map;
    }

    /**
     * 删除应用
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/delete")
    public Map<String,Object>  delete(HttpServletRequest request,App model) {
        Map<String,Object> map = new HashMap<String, Object>();
        System.out.println("====================开始调用删除应用方法delete()================");
        try {
            appService.deleteById(model.getAppID());
            tipMsg = "成功";
            tipType = 0;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>删除ID为 " + model.getAppID() + "  的应用异常");
            tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();
        }
        map.put("message",tipMsg);
        logService.create(new Log(getUser(request), "删除应用", model.getClientIp(),
                new Timestamp(System.currentTimeMillis())
                , tipType, "删除应用ID为  " + model.getAppID() + " " + tipMsg + "！", "删除应用" + tipMsg));
        System.out.println("====================结束调用删除应用方法delete()================");
        return map;
    }







}
