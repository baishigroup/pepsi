package com.zking.erp.controller.cao;

import com.zking.erp.model.cao.Unit;
import com.zking.erp.service.cao.IUnitService;
import com.zking.erp.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/cao/unit")
public class UnitController {
    @Autowired
    private IUnitService unitService;

    @RequestMapping("/create")
    public Map<String,Object> create(Unit unit){
        Map<String,Object> map = new HashMap<String, Object>();
        unit.setId(UUID.randomUUID().toString().replace("-"," "));

        System.out.println("==================开始调用增加计量单位方法create()===================");
        try {
            unitService.insertUnit(unit);
            map.put("message","新增计量单位成功");
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>>>>>>>增加计量单位异常");
            e.printStackTrace();
        } finally {
            System.out.println(">>>>>>>>>>>>增加计量单位回写客户端结果异常");
        }

        System.out.println("==================结束调用增加计量单位方法create()===================");
        return map;
    }

    @RequestMapping("/findBy")
    @ResponseBody
    public Map<String,Object> findBy(HttpServletRequest req, Unit unit){
        PageBean pageBean = new PageBean();
        pageBean.setRequest(req);
        Map<String,Object> map=new HashMap<String, Object>();
        List<Unit> unitsList = unitService.queryUnitPager(unit, pageBean);
        List dataArray = new ArrayList();
        if (null != unitsList) {
            for (Unit unit1 : unitsList) {
                Map<String,Object> item=new HashMap<String, Object>();
                item.put("id", unit1.getId());
                //名称
                item.put("UName", unit1.getUname());
                item.put("op", 1);
                dataArray.add(item);
            }
        }

        map.put("total",pageBean.getTotal());
        map.put("rows",dataArray);
        return map;
    }


}
