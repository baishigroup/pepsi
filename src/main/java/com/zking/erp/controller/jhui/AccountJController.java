package com.zking.erp.controller.jhui;

import com.zking.erp.model.jhui.Account;
import com.zking.erp.service.jhui.IAccountJService;
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
@RequestMapping("/account")
public class AccountJController {
    @Autowired
    private IAccountJService accountService;

    //获取账户的信息
    @RequestMapping("/getAccount")
    @ResponseBody
    public Map<String, Object> getAccount(HttpServletRequest request, Account model) {
        Map<String, Object> mapData = new HashMap<String, Object>();
        try {
            List<Account> accountList = accountService.queryAccout();
            mapData.put("accountList", accountList);
            return mapData;
        } catch (Exception e) {
            System.out.println(">>>>>>>>>>>>>查找账户信息异常");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 查找结算账户信息-下拉框
     *
     * @return
     */
    @RequestMapping("/findBySelect")
    @ResponseBody
    public List findBySelect(HttpServletRequest request, Account model) {
        try {

            List<Account> dataList= accountService.queryAccout();
            List dataArray = new ArrayList();
            if (null != dataList) {
                for (Account account : dataList) {
                    Map<String,Object> item = new HashMap<String, Object>();
                    item.put("Id", account.getId());
                    //结算账户名称
                    item.put("AccountName", account.getName());
                    dataArray.add(item);
                }
            }
           return dataArray;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>查找结算账户信息异常");
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.out.println(">>>>>>>>>>>>>回写查询结算账户信息结果异常");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


}
