package com.zking.erp.controller.cao;

import com.zking.erp.model.cao.Person;
import com.zking.erp.service.cao.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cao/person")
public class PersonController {

    @Autowired
    private IPersonService personService;

    @RequestMapping("getBasicData")
    @ResponseBody
    public Map<String,Object> getBasicData() {
        Map<String, Object> mapData = new HashMap<String, Object>();
        try {
            List<Person> personList = personService.queryPersonAll();
            mapData.put("personList", personList);
        } catch (Exception e) {
            System.out.println(">>>>>>>>>>>>>查找系统基础数据信息异常");
            mapData.put("message","exceptoin");
            e.printStackTrace();
        }
        return mapData;
    }


}
