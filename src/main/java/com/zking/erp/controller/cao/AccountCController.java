package com.zking.erp.controller.cao;

import com.zking.erp.base.BaseController;
import com.zking.erp.model.cao.Account;
import com.zking.erp.model.cao.AccountHead;
import com.zking.erp.model.cao.AccountItem;
import com.zking.erp.model.cao.DepotHead;
import com.zking.erp.service.cao.IAccountCService;
import com.zking.erp.service.cao.IAccountHeadCService;
import com.zking.erp.service.cao.IAccountItemCService;
import com.zking.erp.service.cao.IDepotHeadCService;
import com.zking.erp.util.PageBean;
import com.zking.erp.util.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.util.*;

@Controller
@RequestMapping("/cao/account")
public class AccountCController extends BaseController {
    @Autowired
    private IAccountCService accountService;
    @Autowired
    private IDepotHeadCService depotHeadService;
    @Autowired
    private IAccountHeadCService accountHeadService;
    @Autowired
    private IAccountItemCService accountItemService;



    //获取账户的信息
    @RequestMapping("/getAccount")
    @ResponseBody
    public Map<String, Object> getAccount(HttpServletRequest request, Account model) {
        Map<String, Object> mapData = new HashMap<String, Object>();
        try {
            List<Account> accountList = accountService.queryAccout(model);
            mapData.put("accountList", accountList);
            return mapData;
        } catch (Exception e) {
            System.out.println(">>>>>>>>>>>>>查找账户信息异常");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 查找结算账户信息
     *
     * @return
     */
    @RequestMapping("/findBy")
    @ResponseBody
    public Map<String, Object> findBy(HttpServletRequest request,Account model) {
        try {
            PageBean pageBean=new PageBean();
            pageBean.setRequest(request);
            List<Account> dataList =accountService.queryAccountPager(model,pageBean);
            Map<String, Object> outer = new HashMap<String, Object>();
            outer.put("total", pageBean.getTotal());
            //存放数据json数组
            List dataArray = new ArrayList();
            if (null != dataList) {
                for (Account account : dataList) {
                    DecimalFormat df = new DecimalFormat(".##");
                    Map<String, Object> item = new HashMap<String, Object>();
                    item.put("id", account.getId());
                    //结算账户名称
                    item.put("name", account.getName());
                    item.put("serialNo", account.getSerialno());
                    item.put("initialAmount", account.getInitialamount());
                    String timeStr = Tools.getCurrentMonth();//获取当前月份
                    Double thisMonthAmount = getAccountSum(account.getId(), timeStr, "month") + getAccountSumByHead(account.getId(), timeStr, "month") + getAccountSumByDetail(account.getId(), timeStr, "month") + getManyAccountSum(account.getId(), timeStr, "month");
                    String thisMonthAmountFmt = "0";
                    if (thisMonthAmount != 0) {
                        thisMonthAmountFmt = df.format(thisMonthAmount);
                    }
                    item.put("thisMonthAmount", thisMonthAmountFmt);  //本月发生额
                    Double currentAmount = getAccountSum(account.getId(), "", "month") + getAccountSumByHead(account.getId(), "", "month") + getAccountSumByDetail(account.getId(), "", "month") + getManyAccountSum(account.getId(), "", "month") + account.getInitialamount();
                    String currentAmountFmt = df.format(currentAmount);
                    item.put("currentAmount", currentAmountFmt);  //当前余额
                    item.put("isDefault", account.getIsdefault());  //是否默认
                    item.put("remark", account.getRemark());
                    item.put("op", 1);
                    dataArray.add(item);
                }
            }
            outer.put("rows", dataArray);
           return outer;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>查找结算账户信息异常");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 单个账户的金额求和-入库和出库
     *
     * @param id
     * @return
     */
    public Double getAccountSum(String id, String timeStr, String type) {
        Double accountSum = 0.0;
        try {
            List<DepotHead> dataList =depotHeadService.queryAccountSumByAId( id,  timeStr,  type);
            if (dataList != null) {
                for (DepotHead depotHead : dataList) {
                    accountSum = accountSum + depotHead.getChangeamount();
                }
            }
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>查找进销存信息异常");
            e.printStackTrace();
        }
        return accountSum;
    }

    /**
     * 单个账户的金额求和-收入、支出、转账的单据表头的合计
     *
     * @param id
     * @return
     */
    public Double getAccountSumByHead(String id, String timeStr, String type) {
        Double accountSum = 0.0;
        try {
            List<AccountHead> dataList =accountHeadService.queryAccountSumByHead(id,timeStr,type);
            if (dataList != null) {
                for (AccountHead accountHead : dataList) {
                    accountSum = accountSum + accountHead.getChangeamount();
                }
            }
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>查找进销存信息异常");
            e.printStackTrace();
        }
        return accountSum;
    }

    /**
     * 单个账户的金额求和-收款、付款、转账、收预付款的单据明细的合计
     *
     * @param id
     * @return
     */
    public Double getAccountSumByDetail(String id, String timeStr, String type) {
        Double accountSum = 0.0;
        try {
            List<AccountHead> dataList = accountHeadService.queryAccountSumByItem(timeStr,type);
            if (dataList != null) {
                String ids = "";
                for (AccountHead accountHead : dataList) {
                    ids = ids + accountHead.getId() + ",";
                }
                if (!ids.equals("")) {
                    ids = ids.substring(0, ids.length() - 1);
                }
                List<AccountItem> dataListOne = accountItemService.queryAccountItemByItem(id,ids);
                if (dataListOne != null) {
                    for (AccountItem accountItem : dataListOne) {
                        accountSum = accountSum + accountItem.getEachamount();
                    }
                }
            }
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>查找进销存信息异常");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(">>>>>>>>>异常信息");
            e.printStackTrace();
        }
        return accountSum;
    }

    /**
     * 单个账户的金额求和-多账户的明细合计
     *
     * @param id
     * @return
     */
    public Double getManyAccountSum(String id, String timeStr, String type) {
        System.out.println(id+timeStr+type);
        Double accountSum = 0.0;
        try {
            List<DepotHead> dataList =depotHeadService.queryManyAccountSum(id,timeStr,type);
            if (dataList != null) {
                for (DepotHead depotHead : dataList) {
                    String accountIdList = depotHead.getAccountidlist();
                    String accountMoneyList = depotHead.getAccountmoneylist();
                    accountIdList = accountIdList.replace("[", "").replace("]", "").replace("\"", "");
                    accountMoneyList = accountMoneyList.replace("[", "").replace("]", "").replace("\"", "");
                    String[] aList = accountIdList.split(",");
                    String[] amList = accountMoneyList.split(",");
                    for (int i = 0; i < aList.length; i++) {
                        if (aList[i].toString().equals(id.toString())) {
                            accountSum = accountSum + Double.parseDouble(amList[i].toString());
                        }
                    }
                }
            }
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>查找信息异常");
            e.printStackTrace();
        }
        return accountSum;
    }

    /**
     * 账户流水信息
     */
    @RequestMapping("/findAccountInOutList")
    @ResponseBody
    public Map<String, Object> findAccountInOutList(HttpServletRequest request,Account model) {
        PageBean pageBean=new PageBean();
        pageBean.setRequest(request);
        String accountId = model.getAccountID();
        Double initialAmount = model.getInitialamount();
        try {
            List<Account> dataList =accountService.queryAccountInOutListPager(pageBean, accountId);
            Map<String, Object> outer = new HashMap<String, Object>();
            outer.put("total", pageBean.getTotal());
            //存放数据json数组
            List dataArray = new ArrayList();
            if (dataList != null) {
                for (Account account : dataList) {
                    Map<String, Object> item = new HashMap<String, Object>();
                    item.put("number", account.getNumber()); //单据编号
                    item.put("type", account.getNewType()); //类型
                    item.put("supplierName", account.getSupplier()); //单位信息
                    item.put("changeAmount", account.getChangeAmount()); //金额
                    String timeStr = account.getoTime().toString();
                    Double balance = getAccountSum(accountId, timeStr, "date") + getAccountSumByHead(accountId, timeStr, "date")
                            + getAccountSumByDetail(accountId, timeStr, "date") + getManyAccountSum(accountId, timeStr, "date") + initialAmount;
                    item.put("balance", balance); //余额
                    item.put("operTime", account.getoTime()); //入库出库日期
                    item.put("aList", account.getAList()); //多账户的id列表
                    item.put("amList", account.getAMList() ); //多账户的金额列表
                    dataArray.add(item);
                }
            }
            outer.put("rows", dataArray);
            return outer;
        } catch (Exception e) {
            System.out.println(">>>>>>>>>>>>>>>>>>>查找信息异常");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 增加结算账户
     *
     * @return
     */
    @RequestMapping("/create")
    @ResponseBody
    public Map<String, Object> create(HttpServletRequest request,Account model) {
        System.out.println("==================开始调用增加结算账户方法===================");
        Map<String, Object> map = new HashMap<String, Object>();
        Boolean flag = false;
        try {
            Account Account = new Account();
            Account.setName(model.getName());
            Account.setSerialno(model.getSerialno());
            Account.setInitialamount(model.getInitialamount() != null ? model.getInitialamount() : 0);
            Account.setCurrentamount(model.getCurrentamount());
            Account.setRemark(model.getRemark());
            Account.setId(UUID.randomUUID().toString());
            accountService.insertAccount(Account);
            //========标识位===========
            flag = true;
            //记录操作日志使用
            tipMsg = "成功";
            tipType = 0;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>>>>>>>增加结算账户异常");
            flag = false;
            tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();
        }
        map.put("flag",flag);
//        logService.create(new Logdetails(getUser(), "增加结算账户", model.getClientIp(),
//                new Timestamp(System.currentTimeMillis())
//                , tipType, "增加结算账户名称为  " + model.getName() + " " + tipMsg + "！", "增加结算账户" + tipMsg));
        System.out.println("==================结束调用增加结算账户方法===================");
        return map;
    }

    /**
     * 删除结算账户
     *
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Map<String, Object> delete(HttpServletRequest request,Account model) {
        Map<String, Object> map = new HashMap<String, Object>();
        System.out.println("====================开始调用删除结算账户信息方法delete()================");
        try {
            accountService.deleteAccountById(model.getAccountID());
            tipMsg = "成功";
            tipType = 0;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>删除ID为 " + model.getAccountID() + "  的结算账户异常");
            tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();
        }
        map.put("message",tipMsg);
//        logService.create(new Logdetails(getUser(), "删除结算账户", model.getClientIp(),
//                new Timestamp(System.currentTimeMillis())
//                , tipType, "删除结算账户ID为  " + model.getAccountID() + ",名称为  " + model.getName() + tipMsg + "！", "删除结算账户" + tipMsg));
        System.out.println("====================结束调用删除结算账户信息方法delete()================");
        return map;
    }

    /**
     * 更新结算账户
     *
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public Map<String, Object> update(HttpServletRequest request,Account model) {
        Boolean flag = false;
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Account Account = accountService.selectById(model.getAccountID());
            Account.setName(model.getName());
            Account.setSerialno(model.getSerialno());
            Account.setInitialamount(model.getInitialamount() != null ? model.getInitialamount() : 0);
            Account.setCurrentamount(model.getCurrentamount());
            Account.setRemark(model.getRemark());
            accountService.updateAccount(Account);

            flag = true;
            tipMsg = "成功";
            tipType = 0;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>修改结算账户ID为 ： " + model.getAccountID() + "信息失败");
            flag = false;
            tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();
        }
        map.put("flag",flag);
//        logService.create(new Logdetails(getUser(), "更新结算账户", model.getClientIp(),
//                new Timestamp(System.currentTimeMillis())
//                , tipType, "更新结算账户ID为  " + model.getAccountID() + " " + tipMsg + "！", "更新结算账户" + tipMsg));
    return map;
    }


    /**
     * 更新结算账户-设置是否默认
     *
     * @return
     */
    @RequestMapping("/updateAmountIsDefault")
    @ResponseBody
    public Map<String, Object> updateAmountIsDefault(HttpServletRequest request,Account model) {
        Boolean flag = false;
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Account Account = accountService.selectById(model.getAccountID());
            Account.setIsdefault(model.getIsdefault());
            accountService.updateAccount(Account);
            flag = true;
            tipMsg = "成功";
            tipType = 0;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>修改结算账户ID为 ： " + model.getAccountID() + "信息失败");
            flag = false;
            tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();
        }
        map.put("flag",flag);
        //如果改为默认账户时记录日志
        if (model.getIsdefault().equals("1")) {
//            logService.create(new Logdetails(getUser(), "更新默认账户", model.getClientIp(), new Timestamp(System.currentTimeMillis()),
//                    tipType, "更新账户ID" + model.getAccountID() + "为默认账户" + tipMsg + "！", "更新默认账户" + tipMsg));
        }
    return map;
    }

    /**
     * 批量删除指定ID结算账户
     *
     * @return
     */
    @RequestMapping("/batchDelete")
    @ResponseBody
    public Map<String, Object> batchDelete(HttpServletRequest request,Account model) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            accountService.deleteAcountByIds(model);
            map.put("message","成功");
            //记录操作日志使用
            tipMsg = "成功";
            tipType = 0;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>批量删除结算账户ID为：" + model.getAccountIDs() + "信息异常");
            tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();
        }

//        logService.create(new Logdetails(getUser(), "批量删除结算账户", model.getClientIp(),
//                new Timestamp(System.currentTimeMillis())
//                , tipType, "批量删除结算账户ID为  " + model.getAccountIDs() + " " + tipMsg + "！", "批量删除结算账户" + tipMsg));
        return map;
    }

    /**
     * 检查输入名称是否存在
     */
    @RequestMapping("/checkIsNameExist")
    @ResponseBody
    public Map<String, Object> checkIsNameExist(HttpServletRequest request,Account model) {
        Map<String, Object> map = new HashMap<String, Object>();
        Boolean flag = false;
        try {
            flag = accountService.checkIsNameExist("name", model.getName(), "id", model.getAccountID());
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>>>>>检查结算账户名称为：" + model.getName() + " ID为： " + model.getAccountID() + " 是否存在异常！");
        }
        map.put("flag",flag);
        return map;
    }





}
