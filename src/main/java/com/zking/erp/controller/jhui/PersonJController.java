package com.zking.erp.controller.jhui;

import com.zking.erp.model.jhui.Person;
import com.zking.erp.service.jhui.IPersonJService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/person")
public class PersonJController {

    @Autowired
    private IPersonJService personService;

    /**
     * 获取不同类型的经手人
     */
    @RequestMapping("/getPersonByType")
    @ResponseBody
    public Map<String,Object> getPersonByType(HttpServletRequest request, Person person) {
        Map<String,Object> mapData=new HashMap<String, Object>();
        try {
            List<Person> people = personService.queryPersonByType(person);
            mapData.put("personList", people);
            return mapData;
        } catch (Exception e) {
            System.out.println(">>>>>>>>>>>>>查找系统基础数据信息异常");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
