package com.zking.erp.controller.jhui;

import com.zking.erp.base.BaseController;
import com.zking.erp.model.jhui.Functions;
import com.zking.erp.model.jhui.Log;
import com.zking.erp.model.jhui.vo.FunctionsVo;
import com.zking.erp.service.jhui.IFunctionsJService;
import com.zking.erp.service.jhui.IUserBusinessJService;
import com.zking.erp.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

@Controller
@RequestMapping("/functions")
public class FunctionsJController extends BaseController{

    @Autowired
    private IFunctionsJService functionsService;
    @Autowired
    private IUserBusinessJService userBusinessService;

    /**
     * 页面显示菜单
     *
     * @return
     */
    @RequestMapping("/findMenu")
    @ResponseBody
    public List findMenu(FunctionsVo functionsVo) {
        try {
            String fc = functionsVo.getHasFunctions(); //当前用户所拥有的功能列表，格式如：[1][2][5]
            List<Functions> dataList = functionsService.queryMenuByPNumber(functionsVo);
            //存放数据json数组
            List dataArray = new ArrayList();
            if (null != dataList) {
                for (Functions functions : dataList) {
                    Map<String,Object> item = new HashMap<String,Object>();
                    item.put("id", functions.getId());
                    List<Functions> dataList1 = functionsService.queryMenuByPNumber(new FunctionsVo(functions.getNumber()));
                    List dataArray1 = new ArrayList();
                    if (dataList1.size() != 0) {
                        item.put("text", functions.getName()); //是目录就没链接
                        for (Functions functions1 : dataList1) {
                            item.put("state", "open");   //如果不为空，节点展开
                            Map<String,Object> item1 = new HashMap<String,Object>();
                            List<Functions> dataList2 = functionsService.queryMenuByPNumber(new FunctionsVo(functions1.getNumber()));

                            if (fc.indexOf("[" + functions1.getId().toString() + "]") != -1 || dataList2.size() != 0) {
                                item1.put("id", functions1.getId());
                                List dataArray2 = new ArrayList();
                                if (dataList2.size() != 0) {
                                    item1.put("text", functions1.getName());//是目录就没链接
                                    for (Functions functions2 : dataList2) {
                                        item1.put("state", "closed");   //如果不为空，节点不展开
                                        Map<String,Object> item2 = new HashMap<String,Object>();

                                        List<Functions> dataList3 = functionsService.queryMenuByPNumber(new FunctionsVo(functions2.getNumber()));


                                        if (fc.indexOf("[" + functions2.getId().toString() + "]") != -1 || dataList3.size() != 0) {
                                            item2.put("id", functions2.getId());
                                            List dataArray3 = new ArrayList();
                                            if (dataList3.size() != 0) {
                                                item2.put("text", functions2.getName());//是目录就没链接
                                                for (Functions functions3 : dataList3) {
                                                    item2.put("state", "closed");   //如果不为空，节点不展开
                                                    Map<String,Object> item3 = new HashMap<String,Object>();
                                                    item3.put("id", functions3.getId());
                                                    item3.put("text", functions3.getName());
                                                    //
                                                    dataArray3.add(item3);
                                                    item2.put("children", dataArray3);
                                                }
                                            } else {
                                                //不是目录，有链接
                                                item2.put("text", "<a onclick=\"NewTab('" + functions2.getName() + "','" + functions2.getUrl() + "','" + functions2.getId() + "')\">" + functions2.getName() + "</a>");
                                            }
                                        } else {
                                            //不是目录，有链接
                                            item2.put("text", "<a onclick=\"NewTab('" + functions2.getName() + "','" + functions2.getUrl() + "','" + functions2.getId() + "')\">" + functions2.getName() + "</a>");
                                        }
                                        dataArray2.add(item2);
                                        item1.put("children", dataArray2);
                                    }
                                } else {
                                    //不是目录，有链接
                                    item1.put("text", "<a onclick=\"NewTab('" + functions1.getName() + "','" + functions1.getUrl() + "','" + functions1.getId() + "')\">" + functions1.getName() + "</a>");
                                }
                            } else {
                                //不是目录，有链接
                                item1.put("text", "<a onclick=\"NewTab('" + functions1.getName() + "','" + functions1.getUrl() + "','" + functions1.getId() + "')\">" + functions1.getName() + "</a>");
                            }
                            dataArray1.add(item1);
                            item.put("children", dataArray1);
                        }
                    } else {
                        //不是目录，有链接
                        item.put("text", "<a onclick=\"NewTab('" + functions.getName() + "','" + functions.getUrl() + "','" + functions.getId() + "')\">" + functions.getName() + "</a>");
                    }
                    dataArray.add(item);
                }
            }
            return dataArray;
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 角色对应功能显示
     *
     * @return
     */
    @RequestMapping("/findRoleFunctions")
    @ResponseBody
    public List findRoleFunctions(Functions model) {
        List lst=new ArrayList();
        try {
            model.setPnumber("0");
            List<Functions> dataList = functionsService.queryFunctionsByRole(model);

            //开始拼接json数据
            Map<String,Object> outer = new HashMap<String, Object>();
            outer.put("id", 1);
            outer.put("text", "功能列表");
            outer.put("state", "open");
            //存放数据json数组
            List dataArray = new ArrayList();
            if (null != dataList) {
                for (Functions functions : dataList) {
                    Map<String,Object> item = new HashMap<String, Object>();
                    item.put("id", functions.getId());
                    item.put("text", functions.getName());

                    //勾选判断1
                    Boolean flag = false;
                    try {
                        flag = userBusinessService.checkIsUserBusinessExist("Type", model.getUBType(), "KeyId", model.getUBKeyId(), "Value", "[" + functions.getId().toString() + "]");
                    } catch (DataAccessException e) {
                        System.out.println(">>>>>>>>>>>>>>>>>设置角色对应的功能：类型" + model.getUBType() + " KeyId为： " + model.getUBKeyId() + " 存在异常！");
                    }
                    if (flag == true) {
                        item.put("checked", true);
                    }
                    //结束
                    model.setPnumber(functions.getNumber());
                    List<Functions> dataList1 =functionsService.queryFunctionsByRole(model);
                    List dataArray1 = new ArrayList();
                    if (null != dataList1) {

                        for (Functions functions1 : dataList1) {
                            item.put("state", "open");   //如果不为空，节点不展开
                            Map<String,Object> item1 = new HashMap<String, Object>();
                            item1.put("id", functions1.getId());
                            item1.put("text", functions1.getName());

                            //勾选判断2
                            //Boolean flag = false;
                            try {
                                flag = userBusinessService.checkIsUserBusinessExist("Type", model.getUBType(), "KeyId", model.getUBKeyId(), "Value", "[" + functions1.getId().toString() + "]");
                            } catch (DataAccessException e) {
                                System.out.println(">>>>>>>>>>>>>>>>>设置角色对应的功能：类型" + model.getUBType() + " KeyId为： " + model.getUBKeyId() + " 存在异常！");
                            }
                            if (flag == true) {
                                item1.put("checked", true);
                            }
                            //结束
                            model.setPnumber(functions1.getNumber());
                            List<Functions> dataList2 =functionsService.queryFunctionsByRole(model);
                            List dataArray2 = new ArrayList();
                            if (null != dataList2) {

                                for (Functions functions2 : dataList2) {
                                    item1.put("state", "closed");   //如果不为空，节点不展开
                                    Map<String,Object> item2 = new HashMap<String, Object>();
                                    item2.put("id", functions2.getId());
                                    item2.put("text", functions2.getName());

                                    //勾选判断3
                                    //Boolean flag = false;
                                    try {
                                        flag = userBusinessService.checkIsUserBusinessExist("Type", model.getUBType(), "KeyId", model.getUBKeyId(), "Value", "[" + functions2.getId().toString() + "]");
                                    } catch (DataAccessException e) {
                                        System.out.println(">>>>>>>>>>>>>>>>>设置角色对应的功能：类型" + model.getUBType() + " KeyId为： " + model.getUBKeyId() + " 存在异常！");
                                    }
                                    if (flag == true) {
                                        item2.put("checked", true);
                                    }
                                    //结束
                                    model.setPnumber(functions2.getNumber());
                                    List<Functions> dataList3  =functionsService.queryFunctionsByRole(model);
                                    List dataArray3 = new ArrayList();
                                    if (null != dataList3) {

                                        for (Functions functions3 : dataList3) {
                                            item2.put("state", "closed");   //如果不为空，节点不展开
                                            Map<String,Object> item3 = new HashMap<String, Object>();
                                            item3.put("id", functions3.getId());
                                            item3.put("text", functions3.getName());

                                            //勾选判断4
                                            //Boolean flag = false;
                                            try {
                                                flag = userBusinessService.checkIsUserBusinessExist("Type", model.getUBType(), "KeyId", model.getUBKeyId(), "Value", "[" + functions3.getId().toString() + "]");
                                            } catch (DataAccessException e) {
                                                System.out.println(">>>>>>>>>>>>>>>>>设置角色对应的功能：类型" + model.getUBType() + " KeyId为： " + model.getUBKeyId() + " 存在异常！");
                                            }
                                            if (flag == true) {
                                                item3.put("checked", true);
                                            }
                                            //结束

                                            dataArray3.add(item3);
                                            item2.put("children", dataArray3);
                                        }
                                    }

                                    dataArray2.add(item2);
                                    item1.put("children", dataArray2);
                                }
                            }

                            dataArray1.add(item1);
                            item.put("children", dataArray1);
                        }

                    }

                    dataArray.add(item);
                }
                outer.put("children", dataArray);
            }
            //回写查询结果
            lst.add(outer);
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>>>>>>>查找应用异常");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(">>>>>>>>>>>>>>>>>>>回写查询应用结果异常");
            e.printStackTrace();
        }
        return lst;
    }

    /**
     * 根据id列表查找功能信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/findByIds")
    public Map<String,Object> findByIds(Functions model) {
        Map<String,Object> outer = new HashMap<String, Object>();
        try {
            List<Functions> dataList =functionsService.queryFunctionByIds(model);
            //存放数据json数组
            List dataArray = new ArrayList();
            if (null != dataList) {
                for (Functions functions : dataList) {
                    Map<String,Object> item = new HashMap<String, Object>();
                    item.put("Id", functions.getId());
                    item.put("Name", functions.getName());
                    item.put("PushBtn", functions.getPushbtn());
                    item.put("op", 1);
                    dataArray.add(item);
                }
            }
            outer.put("rows", dataArray);
            System.out.println(dataArray);
            //回写查询结果
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>>>>>>>查找功能信息异常");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(">>>>>>>>>>>>>>>>>>>回写查询功能信息结果异常");
            e.printStackTrace();
        }
        return outer;

    }

    /**
     * 查找功能信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/findBy")
    public Map<String,Object> findBy(HttpServletRequest request,Functions model) {
        try {
            PageBean pageBean=new PageBean();
            pageBean.setRequest(request);
            List<Functions> dataList = functionsService.queryFunctionByLikePager(pageBean,model);
//            {"total":28,"rows":[
//                {"productid":"AV-CB-01","attr1":"Adult Male","itemid":"EST-18"}
//            ]}
            Map<String,Object> outer = new HashMap<String, Object>();
            outer.put("total", pageBean.getTotal());
            //存放数据json数组
            List dataArray = new ArrayList();
            if (null != dataList) {
                for (Functions functions : dataList) {
                    Map<String,Object> item = new HashMap<String, Object>();
                    item.put("Id", functions.getId());
                    item.put("Number", functions.getNumber());
                    item.put("Name", functions.getName());
                    item.put("PNumber", functions.getPnumber());
                    item.put("URL", functions.getUrl());
                    item.put("State", functions.getState());
                    item.put("Sort", functions.getSort());
                    item.put("Enabled", functions.getEnabled());
                    item.put("Type", functions.getType());
                    item.put("PushBtn", functions.getPushbtn());
                    item.put("op", 1);
                    dataArray.add(item);
                }
            }
            outer.put("rows", dataArray);
            //回写查询结果
            return outer;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>>>>>>>查找功能信息异常");
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.out.println(">>>>>>>>>>>>>>>>>>>回写查询功能信息结果异常");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 检查输入名称是否存在
     */
    @ResponseBody
    @RequestMapping("/checkIsNameExist")
    public Map<String,Object> checkIsNameExist(Functions model) {
        Map<String,Object> map = new HashMap<String, Object>();
        Boolean flag = false;
        try {
            flag = functionsService.checkIsNameExist("Name", model.getName(), "Id", model.getFunctionsID());
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>>>>>检查功能名称为：" + model.getName() + " ID为： " + model.getFunctionsID() + " 是否存在异常！");
        }
        map.put("flag",flag);
        return map;
    }

    /**
     * 增加功能
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/create")
    public Map<String,Object> create(HttpServletRequest request,Functions model) {
        System.out.println("==================开始调用增加功能信息方法create()===================");
        Boolean flag = false;
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            model.setId(UUID.randomUUID().toString());
            functionsService.insert(model);

            //========标识位===========
            flag = true;
            //记录操作日志使用
            tipMsg = "成功";
            tipType = 0;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>>>>>>>增加功能信息异常");
            flag = false;
            tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();
        }
            map.put("flag",flag);

        logService.create(new Log(getUser(request), "增加功能", model.getClientIp(),
                new Timestamp(System.currentTimeMillis())
                , tipType, "增加功能名称为  " + model.getName() + " " + tipMsg + "！", "增加功能" + tipMsg));
            System.out.println("==================结束调用增加功能方法create()===================");
            return map;
    }

    /**
     * 删除功能
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/delete")
    public Map<String,Object> delete(HttpServletRequest request,Functions model) {
            System.out.println("====================开始调用删除功能信息方法delete()================");
            Map<String,Object> map = new HashMap<String, Object>();
            try {
            functionsService.deleteById(model.getFunctionsID());
            tipMsg = "成功";
            tipType = 0;
        } catch (DataAccessException e) {
                System.out.println(">>>>>>>>>>>删除ID为 " + model.getFunctionsID() + "  的功能异常");
            tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();
        }
        map.put("message",tipMsg);
        logService.create(new Log(getUser(request), "删除功能", model.getClientIp(),
                new Timestamp(System.currentTimeMillis())
                , tipType, "删除功能ID为  " + model.getFunctionsID() + " " + tipMsg + "！", "删除功能" + tipMsg));
            System.out.println("====================结束调用删除功能信息方法delete()================");
        return map;
    }



    /**
     * 更新功能
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/update")
    public Map<String,Object> update(HttpServletRequest request,Functions model) {
        Map<String,Object> map = new HashMap<String, Object>();
        Boolean flag = false;
        try {
            Functions functions = functionsService.selectById(model.getFunctionsID());
            functions.setNumber(model.getNumber());
            functions.setName(model.getName());
            functions.setPnumber(model.getPnumber());
            functions.setUrl(model.getUrl());
            functions.setState(model.getState());
            functions.setSort(model.getSort());
            functions.setEnabled(model.getEnabled());
            functions.setType(model.getType());
            functions.setPushbtn(model.getPushbtn());
            functionsService.updateById(functions);

            flag = true;
            tipMsg = "成功";
            tipType = 0;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>修改功能ID为 ： " + model.getFunctionsID() + "信息失败");
            flag = false;
            tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();
        }
        map.put("flag",flag);
        logService.create(new Log(getUser(request), "更新功能", model.getClientIp(),
                new Timestamp(System.currentTimeMillis())
                , tipType, "更新功能ID为  " + model.getFunctionsID() + " " + tipMsg + "！", "更新功能" + tipMsg));
        return map;
    }

    /**
     * 批量删除指定ID功能
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/batchDelete")
    public Map<String,Object>  batchDelete(HttpServletRequest request,Functions model) {
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            functionsService.delete(model);
            map.put("message","成功");
            //记录操作日志使用
            tipMsg = "成功";
            tipType = 0;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>批量删除功能ID为：" + model.getFunctionsIDs() + "信息异常");
            tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();
        }

        logService.create(new Log(getUser(request), "批量删除功能", model.getClientIp(),
                new Timestamp(System.currentTimeMillis())
                , tipType, "批量删除功能ID为  " + model.getFunctionsIDs() + " " + tipMsg + "！", "批量删除功能" + tipMsg));
        return map;
    }

    @ResponseBody
    @RequestMapping("/getPushBtn")
    public List getPushBtn(){
        List lst=new ArrayList();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("id",1);
        map.put("text","导入导出");
        Map<String,Object> map1 = new HashMap<String, Object>();
        map1.put("id",2);
        map1.put("text","启用禁用");
        Map<String,Object> map2 = new HashMap<String, Object>();
        map2.put("id",3);
        map2.put("text","审核反审核");
        Map<String,Object> map3 = new HashMap<String, Object>();
        map3.put("id",4);
        map3.put("text","打印");
        Map<String,Object> map4 = new HashMap<String, Object>();
        map4.put("id",5);
        map4.put("text","作废");
        lst.add(map);
        lst.add(map1);
        lst.add(map2);
        lst.add(map3);
        lst.add(map4);

        return lst;
    }





}
