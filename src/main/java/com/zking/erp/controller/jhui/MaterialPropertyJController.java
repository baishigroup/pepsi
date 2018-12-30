package com.zking.erp.controller.jhui;

import com.zking.erp.base.BaseController;
import com.zking.erp.model.jhui.MaterialProperty;
import com.zking.erp.service.jhui.IMaterialPropertyJService;
import com.zking.erp.util.PageBean;
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
@RequestMapping("/materialProperty")
public class MaterialPropertyJController extends BaseController{

    @Autowired
    private IMaterialPropertyJService materialPropertyService;

    /**
     * 更新商品属性
     *
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public Map<String,Object> update(MaterialProperty model) {
        Boolean flag = false;
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            MaterialProperty materialProperty = materialPropertyService.selectById(model.getId());
            materialProperty.setNativename(model.getNativename());
            materialProperty.setEnabled(model.getEnabled());
            materialProperty.setSort(model.getSort());
            materialProperty.setAnothername(model.getAnothername());
            materialPropertyService.updateById(materialProperty);

            flag = true;
            tipMsg = "成功";
            tipType = 0;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>修改商品属性ID为 ： " + model.getId() + "失败");
            flag = false;
            tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();
        }
        map.put("flag",flag);
//        logService.create(new Logdetails(getUser(), "更新商品属性", model.getClientIp(),
//                new Timestamp(System.currentTimeMillis())
//                , tipType, "更新商品属性ID为  " + model.getId() + " " + tipMsg + "！", "更新商品属性" + tipMsg));
        return map;
    }

    /**
     * 查找商品属性
     *
     * @return
     */
    @RequestMapping("/findBy")
    @ResponseBody
    public Map<String,Object> findBy(HttpServletRequest request,MaterialProperty model) {
        try {
            PageBean pageBean=new PageBean();
            pageBean.setRequest(request);
            List<MaterialProperty> dataList = materialPropertyService.queryMaterialPropertyPager(pageBean,model);
            Map<String,Object> outer = new HashMap<String, Object>();
            outer.put("total", pageBean.getTotal());
            //存放数据json数组
            List dataArray = new ArrayList();
            if (null != dataList) {
                for (MaterialProperty materialProperty : dataList) {
                    Map<String,Object> item = new HashMap<String, Object>();
                    item.put("id", materialProperty.getId());
                    item.put("nativeName", materialProperty.getNativename());
                    item.put("enabled", materialProperty.getEnabled());
                    item.put("sort", materialProperty.getSort());
                    item.put("anotherName", materialProperty.getAnothername());
                    dataArray.add(item);
                }
            }
            outer.put("rows", dataArray);
            return outer;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>>>>>>>查找商品属性异常");
            e.printStackTrace();
            throw  new RuntimeException(e);
        }
    }


}
