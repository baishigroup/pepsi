package com.zking.erp.controller.jhui;

import com.zking.erp.base.BaseController;
import com.zking.erp.model.jhui.Log;
import com.zking.erp.model.jhui.SystemConfig;
import com.zking.erp.service.jhui.ISystemConfigJService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/systemConfig")
public class SystemConfigJController extends BaseController{

    @Autowired
    private ISystemConfigJService systemConfigService;

    /**
     * 更新系统配置
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/update")
    public Map<String,Object> update(HttpServletRequest request, SystemConfig model) {
        Map<String,Object> map=new HashMap<String, Object>();
        Boolean flag = false;
        try {
            SystemConfig sysConfig = systemConfigService.selectById(model.getId());
            sysConfig.setType(sysConfig.getType());
            sysConfig.setName(sysConfig.getName());
            sysConfig.setValue(model.getValue());
            sysConfig.setDescription(sysConfig.getDescription());
            systemConfigService.updateById(sysConfig);

            flag = true;
            tipMsg = "成功";
            tipType = 0;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>修改系统配置ID为 ： " + model.getId() + "信息失败");
            flag = false;
            tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();
        }
        map.put("flag",flag);
        logService.create(new Log(getUser(request), "更新系统配置", model.getClientIp(),
                new Timestamp(System.currentTimeMillis())
                , tipType, "更新系统配置ID为  " + model.getId() + " " + tipMsg + "！", "更新系统配置" + tipMsg));
        return map;
    }

    /**
     * 查找系统配置信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/findBy")
    public Map<String,Object> findBy(SystemConfig model) {
        try {
            List<SystemConfig> dataList = systemConfigService.querySystemConfig(model);
            Map<String,Object> outer=new HashMap<String, Object>();
            //存放数据json数组
            List dataArray = new ArrayList();
            if (null != dataList) {
                for (SystemConfig sysConfig : dataList) {
                    Map<String,Object> item=new HashMap<String, Object>();
                    item.put("id", sysConfig.getId());
                    item.put("type", sysConfig.getType());
                    item.put("name", sysConfig.getName());
                    item.put("value", sysConfig.getValue());
                    item.put("description", sysConfig.getDescription());
                    item.put("op", 1);
                    dataArray.add(item);
                }
            }
            outer.put("rows", dataArray);
            //回写查询结果
            return outer;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>>>>>>>查找系统配置信息异常");
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.out.println(">>>>>>>>>>>>>>>>>>>回写查询系统配置信息结果异常");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


}
