package com.zking.erp.controller.jhui;

import com.zking.erp.base.BaseController;
import com.zking.erp.model.jhui.Log;
import com.zking.erp.model.jhui.User;
import com.zking.erp.service.jhui.ILogJService;
import com.zking.erp.service.jhui.IUserJService;
import com.zking.erp.util.PageBean;
import com.zking.erp.util.Tools;
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
@RequestMapping("/log")
public class LogJController extends BaseController {
    @Autowired
    private ILogJService logService;
    @Autowired
    private IUserJService userService;

    @RequestMapping("/getBasicData")
    @ResponseBody
    public Map<String,Object> getBasicData(User model) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            PageBean pageBean=new PageBean();
            pageBean.setPagination(false);
            List<User> userList = userService.queryUserByLikePager(pageBean, model);
            map.put("userList", userList);
        } catch (Exception e) {
            System.out.println(">>>>>>>>>>>>>查找系统基础数据信息异常");
            map.put("message","exceptoin");
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 查找日志信息
     *
     * @return
     */
    @RequestMapping("/findBy")
    @ResponseBody
    public Map<String, Object> findBy(HttpServletRequest request,Log model) {
        try {
            PageBean pageBean=new PageBean();
            pageBean.setRequest(request);

            List<Log> dataList= logService.queryLogByLikePager(pageBean,model);//多个条件，连表，username

            //开始拼接json数据
//            {"total":28,"rows":[
//                {"productid":"AV-CB-01","attr1":"Adult Male","itemid":"EST-18"}
//            ]}
            Map<String, Object> outer = new HashMap<String, Object>();
            outer.put("total", pageBean.getTotal());
            //存放数据json数组
            List dataArray = new ArrayList();
            if (null != dataList) {
                for (Log log : dataList) {
                    Map<String, Object> item = new HashMap<String, Object>();
                    item.put("id", log.getId());
                    item.put("clientIP", log.getClientIp());
                    item.put("details", log.getContentdetails());
                    item.put("createTime", Tools.getCenternTime(log.getCreatetime()));
                    item.put("operation", log.getOperation());
                    item.put("remark", log.getRemark());
                    item.put("status", log.getStatus() == 0 ? "成功" : "失败");
                    item.put("statusShort", log.getStatus());
                    item.put("username", log.getUserid() == null ? "" : log.getUsername());
                    dataArray.add(item);
                }
            }
            outer.put("rows", dataArray);
            return outer;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>查找日志信息异常");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


}
