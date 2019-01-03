package com.zking.erp.controller.jhui;

import com.zking.erp.base.BaseController;
import com.zking.erp.model.jhui.AccountHead;
import com.zking.erp.model.jhui.Log;
import com.zking.erp.model.jhui.User;
import com.zking.erp.service.jhui.IAccountHeadJService;
import com.zking.erp.util.PageBean;
import com.zking.erp.util.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/accountHead")
public class AccountHeadJController extends BaseController {
    @Autowired
    private IAccountHeadJService accountHeadService;

    /**
     * 查找财务信息
     *
     * @return
     */
    @RequestMapping("/findBy")
    @ResponseBody
    public Map<String,Object> findBy(HttpServletRequest req,AccountHead model) {
        try {
            PageBean pageBean=new PageBean();
            pageBean.setRequest(req);
            List<AccountHead> dataList=accountHeadService.queryAccoutByLikePager(model,pageBean);
            Map<String,Object> outer = new HashMap<String, Object>();
            outer.put("total", pageBean.getTotal());
            List dataArray = new ArrayList();
            if (null != dataList) {
                for (AccountHead accountHead : dataList) {
                    Map<String,Object> item = new HashMap<String, Object>();
                    item.put("Id", accountHead.getId());
                    item.put("OrganId", accountHead.getOrganid() == null ? "" : accountHead.getOrganid());
                    item.put("OrganName", accountHead.getOrganid() == null ? "" : accountHead.getOrganName());
                    item.put("HandsPersonId", accountHead.getHandspersonid() == null ? "" : accountHead.getHandspersonid());
                    item.put("HandsPersonName", accountHead.getHandspersonid() == null ? "" : accountHead.getHandsPersonName());
                    item.put("AccountId", accountHead.getAccountid() == null ? "" : accountHead.getAccountid());
                    item.put("AccountName", accountHead.getAccountid() == null ? "" : accountHead.getAccountName());
                    item.put("BillNo", accountHead.getBillno());
                    item.put("BillTime", Tools.getCenternTime(accountHead.getBilltime()));
                    item.put("ChangeAmount", accountHead.getChangeamount() == null ? "" : Math.abs(accountHead.getChangeamount()));
                    item.put("TotalPrice", accountHead.getTotalprice() == null ? "" : Math.abs(accountHead.getTotalprice()));
                    item.put("Remark", accountHead.getRemark());
                    item.put("op", 1);//判断数据是否异常（与属性无关）
                    dataArray.add(item);
                }
            }
            outer.put("rows", dataArray);
            return outer;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>>>>>>>查找财务信息异常");
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.out.println(">>>>>>>>>>>>>>>>>>>返回查询财务信息结果异常");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 更新财务
     *
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public Map<String,Object> update(HttpServletRequest request,AccountHead model) {
        Boolean flag = false;
        Map<String,Object> map=new HashMap<String, Object>();
        try {
            //AccountHeadID即id
            AccountHead accountHead = accountHeadService.queryAccountHeadById(model.getAccountHeadID());
            accountHead.setType(model.getType());
            if (model.getOrganid() != null) {
                accountHead.setOrganid(model.getOrganid());
            }
            if (model.getHandspersonid() != null) {
                accountHead.setHandspersonid(model.getHandspersonid());
            }
            accountHead.setChangeamount(model.getChangeamount() == null ? 0 : model.getChangeamount());
            accountHead.setTotalprice(model.getTotalprice());
            if (model.getAccountid() != null) {
                accountHead.setAccountid(model.getAccountid());
            }
            accountHead.setBillno(model.getBillno());
            try {
                accountHead.setBilltime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(model.getBilltime2()));
            } catch (ParseException e) {
                System.out.println(">>>>>>>>>>>>>>>日期格式解析购买异常");
                e.printStackTrace();
            }
            accountHead.setRemark(model.getRemark());
            System.out.println(accountHead);
            accountHeadService.updateById(accountHead);
            flag = true;
            tipMsg = "成功";
            tipType = 0;
        } catch (DataAccessException e) {
            flag = false;
            tipMsg = "失败";
            tipType = 1;
            System.out.println(">>>>>>>>>>>>>>>修改财务ID为 ："+model.getAccountHeadID() + "信息失败");
            e.printStackTrace();
        }
        logService.create(new Log(getUser(request), "更新财务", model.getClientIp(),
                new Timestamp(System.currentTimeMillis())
                , tipType, "更新财务ID为  " + model.getAccountHeadID() + " " + tipMsg + "！", "更新财务" + tipMsg));
        map.put("flag",flag);
        return  map;
    }

    /**
     * 增加财务
     *
     * @return
     */
    @RequestMapping("/create")
    @ResponseBody
    public Map<String,Object> create(HttpServletRequest request,AccountHead model) {
        System.out.println("==================开始调用增加财务信息方法create()===================");
        Boolean flag = false;
        Map<String,Object> map=new HashMap<String, Object>();
        try {
//            AccountHead accountHead = new AccountHead();
//            accountHead.setType(model.getType());
//            if (model.getOrganid() != null) {
//                accountHead.setOrganid(model.getOrganId());
//            }
//            if (model.getHandsPersonId() != null) {
//                accountHead.setHandsPersonId(new Person(model.getHandsPersonId()));
//            }
            model.setChangeamount(model.getChangeamount() == null ? 0 : model.getChangeamount());
//            accountHead.setTotalPrice(model.getTotalPrice());
//            if (model.getAccountId() != null) {
//                accountHead.setAccountId(new Account(model.getAccountId()));
//            }
//            accountHead.setBillNo(model.getBillNo());
            try {
                model.setBilltime(new Timestamp(Tools.parse(model.getBilltime2(), "yyyy-MM-dd HH:mm:ss").getTime()));
            } catch (ParseException e) {
                System.out.println(">>>>>>>>>>>>>>>解析购买日期格式异常");
                e.printStackTrace();
            }
//            accountHead.setRemark(model.getRemark());
            accountHeadService.insert(model);

            //========标识位===========
            flag = true;
            //记录操作日志使用
            tipMsg = "成功";
            tipType = 0;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>>>>>>>增加财务信息异常");
            flag = false;
            tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();
        }
        logService.create(new Log(getUser(request), "增加财务", model.getClientIp(),
                new Timestamp(System.currentTimeMillis())
                , tipType, "增加财务编号为  " + model.getBillno() + " " + tipMsg + "！", "增加财务" + tipMsg));
        map.put("flag",flag);
        return map;

    }

    /**
     * 批量删除指定ID财务
     *
     * @return
     */
    @RequestMapping("/batchDelete")
    @ResponseBody
    public Map<String,Object> batchDelete(HttpServletRequest request,AccountHead model) {
        Map<String,Object> map=new HashMap<String, Object>();
        try {
            accountHeadService.delete(model);
            map.put("message","成功");
            //记录操作日志使用
            tipMsg = "成功";
            tipType = 0;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>批量删除财务ID为：" + model.getAccountHeadIDs() + "信息异常");
            tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();
        }

        logService.create(new Log(getUser(request), "批量删除财务", model.getClientIp(),
                new Timestamp(System.currentTimeMillis())
                , tipType, "批量删除财务ID为  " + model.getAccountHeadIDs() + " " + tipMsg + "！", "批量删除财务" + tipMsg));
        return map;
    }

    /**
     * 删除财务
     *
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Map<String,Object> delete(HttpServletRequest request,AccountHead model) {
        Map<String,Object> map=new HashMap<String, Object>();
        System.out.println("====================开始调用删除财务信息方法delete()================");
        try {
            accountHeadService.deleteById(model.getAccountHeadID());
            tipMsg = "成功";
            tipType = 0;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>删除ID为 " + model.getAccountHeadID() + "  的财务异常");
            tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();
        }
        map.put("message",tipMsg);

        logService.create(new Log(getUser(request), "删除财务", model.getClientIp(),
                new Timestamp(System.currentTimeMillis())
                , tipType, "删除财务ID为  " + model.getAccountHeadID() + " " + tipMsg + "！", "删除财务" + tipMsg));
        System.out.println("====================结束调用删除财务信息方法delete()================");
        return map;
    }

    /**
     * 根据编号查询单据信息
     */
    @RequestMapping("/getDetailByNumber")
    @ResponseBody
    public Map<String,Object> getDetailByNumber(HttpServletRequest request,AccountHead model) {
        try {
            List<AccountHead> dataList = accountHeadService.queryDetailByNumber(model);
            Map<String,Object> item=new HashMap<String, Object>();
            if (dataList != null && dataList.get(0) != null) {

                AccountHead accountHead = dataList.get(0);
                item.put("Id", accountHead.getId());
                item.put("OrganId", accountHead.getOrganid() == null ? "" : accountHead.getOrganid());
                item.put("OrganName", accountHead.getOrganid() == null ? "" : accountHead.getOrganName());
                item.put("HandsPersonId", accountHead.getHandspersonid() == null ? "" : accountHead.getHandspersonid());
                item.put("HandsPersonName", accountHead.getHandspersonid() == null ? "" : accountHead.getHandsPersonName());
                item.put("AccountId", accountHead.getAccountid() == null ? "" : accountHead.getAccountid());
                item.put("AccountName", accountHead.getAccountid() == null ? "" : accountHead.getAccountName());
                item.put("BillNo", accountHead.getBillno());
                item.put("BillTime", Tools.getCenternTime(accountHead.getBilltime()));
                item.put("ChangeAmount", accountHead.getChangeamount() == null ? "" : Math.abs(accountHead.getChangeamount()));
                item.put("TotalPrice", accountHead.getTotalprice() == null ? "" : Math.abs(accountHead.getTotalprice()));
                item.put("Remark", accountHead.getRemark());
            }
           return item;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>>>>>>>查找单据信息异常");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }




}
