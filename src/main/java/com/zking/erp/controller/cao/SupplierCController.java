package com.zking.erp.controller.cao;

import com.zking.erp.base.BaseController;
import com.zking.erp.model.cao.Supplier;
import com.zking.erp.model.jhui.Log;
import com.zking.erp.service.cao.ISupplierCService;
import com.zking.erp.util.PageBean;
import com.zking.erp.util.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.*;

@Controller
@RequestMapping("/cao/supplier")
public class SupplierCController extends BaseController{
    public static final String EXCEL = "excel";  //action返回excel结果

    @Autowired
    private ISupplierCService supplierService;

    @RequestMapping("/create")
    @ResponseBody
    public Map<String,Object> create(HttpServletRequest request,Supplier model){
        Map<String,Object> map = new HashMap<String, Object>();
        model.setId(UUID.randomUUID().toString().replace("-"," "));
        System.out.println("==================开始调用增加供应商方法===================");
        Boolean flag = false;
        try {
            model.setAdvancein(0.0);
            model.setIsystem(1);
            //是否启用（1 启用 0不启用）
            model.setEnabled(0);

            supplierService.insertSupplier(model);

            //========标识位===========
            flag = true;
            //记录操作日志使用
            map.put("message","成功");
            tipMsg = "成功";
            tipType = 0;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>>>>>>>增加供应商异常");
            flag = false;
            map.put("message","失败");
            tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();
        } finally {
            try {
                map.put("message",flag);
            } catch (Exception e) {
                System.out.println(">>>>>>>>>>>>增加供应商回写客户端结果异常");
                e.printStackTrace();
            }
        }

        logService.create(new Log(getUser(request), "增加供应商", model.getClientIp(),
                new Timestamp(System.currentTimeMillis())
                , tipType, "增加供应商名称为  " + model.getSupplier() + " " + tipMsg + "！", "增加供应商" + tipMsg));
        System.out.println("==================结束调用增加供应商方法===================");

        return map;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Map<String,Object> delete(HttpServletRequest request,Supplier model){
        Map<String,Object> m = new HashMap<>();

        System.out.println("====================开始调用删除供应商信息方法delete()================");
        try {
            supplierService.deleteSupplierById(model.getId());
            m.put("message","成功");
            //tipMsg = "成功";
            tipType = 0;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>删除ID为 " + model.getId() + "  的供应商异常");
            m.put("message","失败");
            //tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();
        }

        logService.create(new Log(getUser(request), "删除供应商", model.getClientIp(),
                new Timestamp(System.currentTimeMillis())
                , tipType, "删除供应商ID为  " + model.getId() + " " + tipMsg + "！", "删除供应商" + tipMsg));
        System.out.println("====================结束调用删除供应商信息方法delete()================");

        return m;
    }

    @RequestMapping("/update")
    @ResponseBody
    public Map<String,Object> update(HttpServletRequest request,Supplier model){
        Map<String,Object> m = new HashMap<>();

        System.out.println("====================开始调用修改供应商信息方法update()================");
        Boolean flag = false;
        try {
            supplierService.updateSupplierById(model);

            flag = true;
            m.put("message","成功");
            //tipMsg = "成功";
            tipType = 0;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>修改供应商ID为 ： " + model.getId() + "信息失败");
            flag = false;
            m.put("message","失败");
            //tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();
        } finally {
            try {
                m.put("message",flag);
            } catch (Exception e) {
                System.out.println(">>>>>>>>>>>>修改计量单位回写客户端结果异常");
                e.printStackTrace();
            }
        }

        logService.create(new Log(getUser(request), "更新供应商", model.getClientIp(),
                new Timestamp(System.currentTimeMillis())
                , tipType, "更新供应商ID为  " + model.getId() + " " + tipMsg + "！", "更新供应商" + tipMsg));

        System.out.println("====================结束调用修改供应商信息方法update()================");
        return m;
    }

    @RequestMapping("/findBy")
    @ResponseBody
    public Map<String,Object> findBy(HttpServletRequest req, Supplier supplier){
        PageBean pageBean = new PageBean();
        pageBean.setRequest(req);
        System.out.println("====================开始调用查询供应商方法findBy()================");
        Map<String,Object> map=new HashMap<String, Object>();
        List<Supplier> suppliersList = supplierService.querySupplierPager(supplier, pageBean);

        String sName = "";
        if ((supplier.getType()).equals("供应商")) {
            sName = "pageUtilVendor";
        } else if ((supplier.getType()).equals("客户")) {
            sName = "pageUtilCustomer";
        } else if ((supplier.getType()).equals("会员")) {
            sName = "pageUtilMember";
        }

        List dataArray = new ArrayList();
        if (null != suppliersList) {
            for (Supplier supplier1 : suppliersList) {
                Map<String,Object> item=new HashMap<String, Object>();
                item.put("id", supplier1.getId());
                //供应商名称
                item.put("supplier", supplier1.getSupplier());
                //item.put("type", supplier1.getType());
                item.put("contacts", supplier1.getContacts());
                item.put("phonenum", supplier1.getPhonenum());
                item.put("email", supplier1.getEmail());
                item.put("AdvanceIn", supplier1.getAdvancein());
                item.put("BeginNeedGet", supplier1.getBeginneedget());
                item.put("BeginNeedPay", supplier1.getBeginneedpay());
                //item.put("isystem", supplier1.getIsystem() == (short) 0 ? "是" : "否");
                //item.put("description", supplier1.getDescription());
                item.put("fax", supplier1.getFax());
                item.put("telephone", supplier1.getTelephone());
               // item.put("address", supplier1.getAddress());
               // item.put("taxNum", supplier1.getTaxnum());
                //item.put("bankName", supplier1.getBankname());
                //item.put("accountNumber", supplier1.getAccountnumber());
                item.put("taxRate", supplier1.getTaxrate());

                item.put("enabled", supplier1.getEnabled()==0?false:true);
                item.put("op", supplier1.getIsystem());

                dataArray.add(item);
            }
        }

        map.put("total",pageBean.getTotal());
        map.put("rows",dataArray);
        System.out.println("====================结束调用查询供应商方法findBy()================");
        return map;
    }

    @RequestMapping("/batchDelete")
    @ResponseBody
    public Map<String,Object> batchDelete(HttpServletRequest request,Supplier model){
        Map<String,Object> m = new HashMap<>();

        System.out.println("====================开始调用批量删除供应商信息方法batchDelete()================");
        try {
            supplierService.deleteSupplierByIds(model);
            m.put("message","成功");
            //记录操作日志使用
            tipMsg = "成功";
            tipType = 0;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>批量删除供应商ID为：" + model.getSupplierIDs() + "信息异常");
            tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();
        }

        logService.create(new Log(getUser(request), "批量删除供应商信息", model.getClientIp(),
                new Timestamp(System.currentTimeMillis())
                , tipType, "批量删除供应商ID为  " + model.getSupplierIDs() + " " + tipMsg + "！", "批量删除供应商信息" + tipMsg));
        System.out.println("====================结束调用批量删除供应商信息方法batchDelete()================");
        return m;
    }

    @RequestMapping("/checkIsNameExist")
    @ResponseBody
    public Map<String,Object> checkIsNameExist(Supplier model){
        Boolean flag=false;
        Map<String,Object> m = new HashMap<>();

        System.out.println("检查输入名称是否存在");
        try {
            flag = supplierService.checkIsNameExist("supplier", model.getSupplier(), "id", model.getId());
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>>>>>检查供应商名称为：" + model.getSupplier() + " ID为： " + model.getId() + " 是否存在异常！");
        } finally {
            try {
                m.put("flag",flag);
            } catch (Exception e) {
                System.out.println(">>>>>>>>>>>>回写检查供应商名称为：" + model.getSupplier() + " ID为： " + model.getId() + " 是否存在异常！");
                e.printStackTrace();
            }
        }
        return m;
    }

    @RequestMapping("/batchSetEnable")
    @ResponseBody
    public Map<String,Object> batchSetEnable(HttpServletRequest request,Supplier model) {
        System.out.println("====================进入调用批量启用、禁用方法batchSetEnable()================");
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            supplierService.batchSetEnable(model);
            map.put("message","成功");
            //记录操作日志使用
            tipMsg = "成功";
            tipType = 0;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>批量修改状态，单位ID为：" + model.getSupplierIDs() + "信息异常");
            map.put("message","失败");
            tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();
        }

        logService.create(new Log(getUser(request), "批量修改单位状态", model.getClientIp(),
                new Timestamp(System.currentTimeMillis())
                , tipType, "批量修改状态，单位ID为  " + model.getSupplierIDs() + " " + tipMsg + "！", "批量修改单位状态" + tipMsg));
        System.out.println("====================结束调用批量启用、禁用方法batchSetEnable()================");
        return map;
    }


    public String exportExcel(Supplier model) {
        System.out.println("===================调用导出信息action方法exportExcel开始=======================");
        Map<String,Object> map = new HashMap<String, Object>();

        try {

            String sName = "Supplier" + model.getType();
            List<Supplier> supplier = (List<Supplier>)map.get(sName);
//            PageUtil<Supplier> pageUtil = (PageUtil<Supplier>) getSession().get(sName);

//
//            pageUtil.setPageSize(model.getPageSize());
//            pageUtil.setCurPage(model.getPageNo());
            String isCurrentPage = "allPage";
//            model.setFileName(Tools.changeUnicode("report" + System.currentTimeMillis() + ".xls", model.getBrowserType()));
//            model.setExcelStream(supplierService.exmportExcel(isCurrentPage, pageUtil));
            model.setFileName(Tools.changeUnicode("report"+System.currentTimeMillis()+".xls",model.getBrowserType()));
            model.setExcelStream(supplierService.exmportExcel(isCurrentPage,supplier));
        } catch (Exception e) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>调用导出信息action方法exportExcel异常");
            map.put("message","export excel exception");
            e.printStackTrace();
        }
        System.out.println("===================调用导出信息action方法exportExcel结束==================");
        return EXCEL;
    }



}
