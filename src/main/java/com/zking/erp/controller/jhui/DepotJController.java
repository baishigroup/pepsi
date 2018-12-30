package com.zking.erp.controller.jhui;

import com.zking.erp.model.jhui.Depot;
import com.zking.erp.service.jhui.IDepotJService;
import com.zking.erp.service.jhui.IUserBusinessJService;
import com.zking.erp.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/depot")
public class DepotJController {

    @Autowired
    private IDepotJService depotService;
    @Autowired
    private IUserBusinessJService userBusinessService;

    /**
     * 用户对应仓库显示
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/findUserDepot")
    public List findUserDepot(Depot model) {
        List lst=new ArrayList();
        try {
            List<Depot> dataList =depotService.queryDepotByAll(model);
            //开始拼接json数据
            Map<String,Object> outer = new HashMap<String, Object>();
            outer.put("id", 1);
            outer.put("text", "仓库列表");
            outer.put("state", "open");
            //存放数据json数组
            List dataArray = new ArrayList();
            if (null != dataList) {
                for (Depot depot : dataList) {
                    Map<String,Object> item = new HashMap<String, Object>();
                    item.put("id", depot.getId());
                    item.put("text", depot.getName());
                    //勾选判断1
                    Boolean flag = false;
                    try {
                        flag = userBusinessService.checkIsUserBusinessExist("Type", model.getUBType(), "KeyId", model.getUBKeyId(), "Value", "[" + depot.getId().toString() + "]");
                    } catch (DataAccessException e) {
                        System.out.println(">>>>>>>>>>>>>>>>>设置用户对应的仓库：类型" + model.getUBType() + " KeyId为： " + model.getUBKeyId() + " 存在异常！");
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
            System.out.println(">>>>>>>>>>>>>>>>>>>查找仓库异常");
            e.printStackTrace();
        }
        return lst;
    }

    @ResponseBody
    @RequestMapping("/getBasicData")
    public  Map<String, Object> getBasicData() {
        Map<String, Object> mapData = new HashMap<String, Object>();
        try {
            List<Depot> depotList = depotService.queryDepot();
            mapData.put("depotList", depotList);
        } catch (Exception e) {
            mapData.put("message","exceptoin");
            System.out.println(">>>>>>>>>>>>>查找系统基础数据信息异常");
            e.printStackTrace();
        }
        return mapData;
    }

    /**
     * 查找礼品卡-虚拟仓库
     *
     * @return
     */
    @RequestMapping("/findGiftByType")
    @ResponseBody
    public List findGiftByType(Depot model) {
        try {

            List<Depot> dataList = depotService.quertByType(model);
            //存放数据json数组
            List dataArray = new ArrayList();
            if (null != dataList) {
                for (Depot depot : dataList) {
                    Map<String, Object> item = new HashMap<String, Object>();
                    item.put("id", depot.getId());
                    //仓库名称
                    item.put("name", depot.getName());
                    dataArray.add(item);
                }
            }
           return dataArray;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>查找(礼品卡)仓库信息异常");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据用户查找对应仓库列表-仅显示有权限的
     *
     * @return
     */
    @RequestMapping("/findDepotByUserId")
    @ResponseBody
    public List  findDepotByUserId(Depot model) {
        try {
            model.setType(0);
            List<Depot> dataList = depotService.quertByType(model);
            List dataArray = new ArrayList();
            if (null != dataList) {
                for (Depot depot : dataList) {
                    Map<String, Object> item = new HashMap<String, Object>();
                    //勾选判断1
                    Boolean flag = false;
                    try {
                        flag = userBusinessService.checkIsUserBusinessExist("Type", model.getUBType(), "KeyId", model.getUBKeyId(), "Value", "[" + depot.getId().toString() + "]");
                    } catch (DataAccessException e) {
                        System.out.println(">>>>>>>>>>>>>>>>>查询用户对应的仓库：类型" + model.getUBType() + " KeyId为： " + model.getUBKeyId() + " 存在异常！");
                    }
                    if (flag == true) {
                        item.put("id", depot.getId());
                        item.put("depotName", depot.getName());
                        dataArray.add(item);
                    }
                }
            }
            return dataArray;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>查找仓库异常");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }



}
