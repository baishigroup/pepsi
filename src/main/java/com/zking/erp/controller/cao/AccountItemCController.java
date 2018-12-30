package com.zking.erp.controller.cao;

import com.zking.erp.model.cao.AccountItem;
import com.zking.erp.service.cao.IAccountItemCService;
import com.zking.erp.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cao/accountItem")
public class AccountItemCController {

    @Autowired
    private IAccountItemCService accountItemService;

    /**
     * 查找财务信息
     *
     * @return
     */
    @RequestMapping("/findBy")
    @ResponseBody
    public Map<String,Object> findBy(HttpServletRequest request, AccountItem model) {
        try {
            List<AccountItem> dataList = accountItemService.queryAccountItemByHeaderId(model);
            PageBean pageBean=new PageBean();
            pageBean.setRequest(request);
            Map<String,Object> outer = new HashMap<String, Object>();
            outer.put("total", pageBean.getTotal());
            //存放数据json数组
            List dataArray = new ArrayList();
            if (null != dataList) {
                for (AccountItem accountItem : dataList) {
                    Map<String,Object> item = new HashMap<String, Object>();
                    item.put("Id", accountItem.getId());
                    item.put("AccountId", accountItem.getAccountid() == null ? "" : accountItem.getAccountid());
                    item.put("AccountName", accountItem.getAccountid() == null ? "" : accountItem.getAccountName());
                    item.put("InOutItemId", accountItem.getInoutitemid() == null ? "" : accountItem.getInoutitemid());
                    item.put("InOutItemName", accountItem.getInoutitemid() == null ? "" : accountItem.getInOutItemName());
                    Double eachAmount = accountItem.getEachamount();
                    item.put("EachAmount", eachAmount < 0 ? 0 - eachAmount : eachAmount);
                    item.put("Remark", accountItem.getRemark());
                    item.put("op", 1);
                    dataArray.add(item);
                }
            }
            outer.put("rows", dataArray);
            return  outer;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>>>>>>>查找财务信息异常");
            e.printStackTrace();
            throw  new RuntimeException(e);
        } catch (Exception e) {
            System.out.println(">>>>>>>>>>>>>>>>>>>回写查询财务信息结果异常");
            e.printStackTrace();
            throw  new RuntimeException(e);
        }
    }


}
