package com.zking.erp.controller.jhui;

import com.zking.erp.base.BaseController;
import com.zking.erp.model.jhui.UserBusiness;
import com.zking.erp.service.jhui.IUserBusinessJService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/userBusiness")
public class UserBusinessJController extends BaseController{

    @Autowired
    private IUserBusinessJService userBusinessService;

    @ResponseBody
    @RequestMapping("/getBasicData")
    public Map<String,List> getBasicData(HttpServletRequest req , UserBusiness userBusiness) {
        Map<String, List> mapData = new HashMap<String,List>();
        try {
            List<UserBusiness> userBusinessList = userBusinessService.queryUserBusinessByTypeByKeyId(userBusiness);
            mapData.put("userBusinessList",userBusinessList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapData;
    }

    /**
     * 检查角色对应应用/功能是否存在
     */
    @ResponseBody
    @RequestMapping("/checkIsValueExist")
    public Map<String, Object> checkIsValueExist(UserBusiness model) {
        Boolean flag = false;
        Map<String, Object> map = new HashMap<String,Object>();
        try {
            flag = userBusinessService.checkIsUserBusinessExist("Type", model.getType(), "KeyId", model.getKeyid(),"","");
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>>>>>检查角色对应应用/功能的类型为：" + model.getType() + " KeyId为： " + model.getKeyid() + " 是否存在异常！");
        }
        map.put("flag",flag);
        return map;
    }

    /**
     * 增加UserBusiness
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/create")
    public  Map<String, Object> create(UserBusiness  model) {
        System.out.println("==================开始调用增加UserBusiness信息方法create()===================");
        Boolean flag = false;
        Map<String, Object> map = new HashMap<String,Object>();
        try {
            UserBusiness userBusiness = new UserBusiness();
            userBusiness.setType(model.getType());
            userBusiness.setKeyid(model.getKeyid());
            userBusiness.setValue(model.getValue());
            userBusiness.setId(UUID.randomUUID().toString());
            userBusinessService.insert(userBusiness);

            //========标识位===========
            flag = true;
            //记录操作日志使用
            tipMsg = "成功";
            tipType = 0;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>>>>>>>增加UserBusiness信息异常");
            flag = false;
            tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();
        }
        map.put("flag",flag);
//        logService.create(new Logdetails(getUser(), "增加UserBusiness", model.getClientIp(),
//                new Timestamp(System.currentTimeMillis())
//                , tipType, "增加UserBusiness为  " + model.getType() + " " + tipMsg + "！", "增加UserBusiness" + tipMsg));
        System.out.println("==================结束调用增加UserBusiness方法create()===================");
        return map;
    }

    /**
     * 更新UserBusiness
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/update")
    public Map<String, Object> update(UserBusiness  model) {
        Boolean flag = false;
        Map<String, Object> map = new HashMap<String,Object>();
        String id = "01";
        try {

            List<UserBusiness> dataList = userBusinessService.queryUserBusinessByTypeByKeyId(model);
            if (null != dataList) {
                for (UserBusiness userBusiness : dataList) {
                    id = userBusiness.getId();
                }
                UserBusiness userBusiness = userBusinessService.queryUserBusinessById(id);
                userBusiness.setType(model.getType());
                userBusiness.setKeyid(model.getKeyid());
                userBusiness.setValue(model.getValue());
                userBusinessService.updateByPrimaryKey(userBusiness);
            }

            flag = true;
            tipMsg = "成功";
            tipType = 0;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>修改UserBusiness的ID为 ： " + id + "信息失败");
            flag = false;
            tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();;
        }
        map.put("flag",flag);
//        logService.create(new Logdetails(getUser(), "更新UserBusiness", model.getClientIp(),
//                new Timestamp(System.currentTimeMillis())
//                , tipType, "更新UserBusiness的ID为  " + id + " " + tipMsg + "！", "更新UserBusiness" + tipMsg));
        return map;
    }
    /**
     * 更新角色的按钮权限
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateBtnStr")
    public Map<String, Object> updateBtnStr(UserBusiness model) {
        Boolean flag = false;
        Map<String, Object> map = new HashMap<String,Object>();
        try {
            UserBusiness userBusiness = userBusinessService.queryUserBusinessById(model.getUserBusinessID());
            userBusiness.setType(userBusiness.getType());
            userBusiness.setKeyid(userBusiness.getKeyid());
            userBusiness.setValue(userBusiness.getValue());
            userBusiness.setBtnstr(model.getBtnstr());
            userBusinessService.updateByPrimaryKey(userBusiness);
            flag = true;
            tipMsg = "成功";
            tipType = 0;
        }
        catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>修改角色按钮权限的ID为 ： " + model.getUserBusinessID() + "信息失败");
            flag = false;
            tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();
        }
        map.put("flag",flag);
        return map;
//        logService.create(new Logdetails(getUser(), "更新角色按钮权限", model.getClientIp(),
//                new Timestamp(System.currentTimeMillis()), tipType,
//                "角色按钮权限的ID为  "+ model.getUserBusinessID() + " " + tipMsg + "！", "更新角色按钮权限" + tipMsg));
    }

}
