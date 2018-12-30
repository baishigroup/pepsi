package com.zking.erp.controller.cao;

import com.zking.erp.base.BaseController;
import com.zking.erp.model.cao.MaterialProperty;
import com.zking.erp.service.cao.IMaterialPropertyCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cao/materialProperty")
public class MaterialPropertyCController extends BaseController{
    @Autowired
    private IMaterialPropertyCService materialPropertyCService;

    @RequestMapping("/findBy")
    @ResponseBody
    public Map<String,Object> findBy(MaterialProperty model){
        Map<String,Object> map=new HashMap<String, Object>();
        List<MaterialProperty> materialPropertyList = materialPropertyCService.queryMaterialProPager(model);
        List dataArray = new ArrayList();
        if (null != materialPropertyList) {
            for (MaterialProperty materialProperty : materialPropertyList) {
                Map<String,Object> item=new HashMap<String, Object>();
                item.put("id", materialProperty.getId());
                item.put("nativeName", materialProperty.getNativename());
                item.put("enabled", materialProperty.getEnabled());
                item.put("sort", materialProperty.getSort());
                item.put("anotherName", materialProperty.getAnothername());
                dataArray.add(item);
            }
        }

        map.put("rows",dataArray);
        return map;
    }
}
