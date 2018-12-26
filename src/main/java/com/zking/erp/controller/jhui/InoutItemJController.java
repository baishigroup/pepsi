package com.zking.erp.controller.jhui;

import com.zking.erp.model.jhui.InoutItem;
import com.zking.erp.service.jhui.IInoutItemJService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/inOutItem")
public class InoutItemJController {

    @Autowired
    private IInoutItemJService inoutItemService;

    /**
     * 查找收支项目信息-下拉框
     *
     * @return
     */
    @RequestMapping("/findBySelect")
    @ResponseBody
    public List<InoutItem> findBySelect(InoutItem model) {
        try {
            if (model.getType().equals("in")) {
                model.setType( "收入");
            } else if (model.getType().equals("out")) {
                model.setType( "支出");
            }
            List<InoutItem> dataList =inoutItemService.queryInoutItemBySelect(model);
            //存放数据json数组
            List dataArray = new ArrayList();
            if (null != dataList) {
                for (InoutItem inOutItem : dataList) {
                    Map<String,Object> item = new HashMap<String, Object>();
                    item.put("Id", inOutItem.getId());
                    //收支项目名称
                    item.put("InOutItemName", inOutItem.getName());
                    dataArray.add(item);
                }
            }
            return dataArray;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>查找收支项目信息异常");
            e.printStackTrace();
            throw  new RuntimeException(e);
        } catch (Exception e) {
            System.out.println(">>>>>>>>>回写查询收支项目信息结果异常");
            e.printStackTrace();
            throw  new RuntimeException(e);
        }
    }
}
