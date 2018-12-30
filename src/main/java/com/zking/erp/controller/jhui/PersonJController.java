package com.zking.erp.controller.jhui;

import com.zking.erp.model.jhui.Person;
import com.zking.erp.service.jhui.IPersonJService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.util.ArrayList;
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

    /**
     * 根据类型获取经手人信息 1-业务员，2-仓管员，3-财务员
     *
     * @return
     */
    @RequestMapping("/getPersonByNumType")
    @ResponseBody
    public List getPersonByNumType(Person model) {
        try {
            String type = "";
            if (model.getType().equals("1")) {
                type = "业务员";
            } else if (model.getType().equals("2")) {
                type = "仓管员";
            } else if (model.getType().equals("3")) {
                type = "财务员";
            }
            model.setType(type);
            List<Person> dataList =personService.queryPersonByType(model);
            List dataArray = new ArrayList();
            if (null != dataList) {
                for (Person person : dataList) {
                    Map<String,Object> item=new HashMap<String, Object>();
                    item.put("id", person.getId());
                    item.put("name", person.getName());
                    dataArray.add(item);
                }
            }
            //回写查询结果
            return dataArray;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>查找信息异常");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @RequestMapping("/getBasicData")
    @ResponseBody
    public Map<String,Object> getBasicData() {
        Map<String, Object> mapData = new HashMap<String, Object>();
        try {
            List<Person> personList=personService.queryPersonAll();
            mapData.put("personList", personList);
        } catch (Exception e) {
            mapData.put("message","exceptoin");
            System.out.println(">>>>>>>>>>>>>查找系统基础数据信息异常");
            e.printStackTrace();
        }
        return mapData;
    }

    /**
     * 根据Id获取经手人信息
     *
     * @return
     */
    @RequestMapping("/getPersonByIds")
    @ResponseBody
    public Map<String,Object> getPersonByIds(Person model) {
        Map<String, Object> mapData = new HashMap<String, Object>();
        try {
            List<Person> dataList =personService.queryPersonByIds(model);
            StringBuffer sb = new StringBuffer();
            if (null != dataList) {
                for (Person person : dataList) {
                    sb.append(person.getName() + " ");
                }
            }
            mapData.put("sb",sb.toString());
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>查找信息异常");
            e.printStackTrace();
        }
        return  mapData;
    }

}
