package com.zking.erp.controller.jhui;

import com.zking.erp.model.jhui.UserBusiness;
import com.zking.erp.service.jhui.IUserBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/userBusiness")
public class UserBusinessController {

    @Autowired
    private IUserBusinessService userBusinessService;

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

}
