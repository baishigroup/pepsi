package com.zking.erp.controller.jhui;

import com.zking.erp.base.BaseController;
import com.zking.erp.model.jhui.Log;
import com.zking.erp.model.jhui.Supplier;
import com.zking.erp.service.jhui.ISupplierJService;
import com.zking.erp.service.jhui.IUserBusinessJService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

@Controller
@RequestMapping("/supplier")
public class SupplierJController extends BaseController {
    @Autowired
    private ISupplierJService supplierService;
    @Autowired
    private IUserBusinessJService userBusinessService;

    /**
     * 查找客户信息-下拉框
     * 查找会员信息-下拉框
     * 查找供应商信息-下拉框
     *
     */
    @RequestMapping("/findSupplierByType")
    @ResponseBody
    public List<Supplier> findSupplierByType(Supplier model) {
        try {
            List<Supplier> dataList = supplierService.querySupplierByType(model);
            //存放数据json数组
            List dataArray = new ArrayList();
            if (null != dataList) {
                for (Supplier supplier : dataList) {
                    Map<String,Object> item = new HashMap<String, Object>();
                    //勾选判断1
                    Boolean flag = false;
                    try {
                        flag = userBusinessService.checkIsUserBusinessExist("Type", model.getUBType(), "KeyId", model.getUBKeyId(), "Value", "[" + supplier.getId().toString() + "]");
                    } catch (DataAccessException e) {
                        System.out.println(">>>>>>>>>>>>>>>>>查询用户对应的客户：类型" + model.getUBType() + " KeyId为： " + model.getUBKeyId() + " 存在异常！");
                    }
                    if (flag == true) {
                        item.put("id", supplier.getId());
                        item.put("supplier", supplier.getSupplier()); //客户名称
                        dataArray.add(item);
                    }
                }
            }
           return dataArray;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>查找客户信息异常");
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.out.println(">>>>>>>>>回写查询客户信息结果异常");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 更新供应商-只更新预付款，其余用原来的值
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateAdvanceIn")
    public Map<String,Object> updateAdvanceIn(HttpServletRequest request, Supplier model) {
        Map<String,Object> map=new HashMap<String, Object>();
        Boolean flag = false;
        try {
            Supplier supplier = supplierService.selectById(model.getSupplierID());
            supplier.setContacts(supplier.getContacts());
            supplier.setType(supplier.getType());
            supplier.setDescription(supplier.getDescription());
            supplier.setEmail(supplier.getEmail());
            supplier.setAdvancein(supplier.getAdvancein() + model.getAdvancein()); //增加预收款的金额，可能增加的是负值
            supplier.setBeginneedget(supplier.getBeginneedget());
            supplier.setBeginneedpay(supplier.getBeginneedpay());
            supplier.setIsystem(1);
            supplier.setPhonenum(supplier.getPhonenum());
            supplier.setSupplier(supplier.getSupplier());

            supplier.setFax(supplier.getFax());
            supplier.setTelephone(supplier.getTelephone());
            supplier.setAddress(supplier.getAddress());
            supplier.setTaxnum(supplier.getTaxnum());
            supplier.setBankname(supplier.getBankname());
            supplier.setAccountnumber(supplier.getAccountnumber());
            supplier.setTaxrate(supplier.getTaxrate());

            supplier.setEnabled(supplier.getEnabled());
            supplierService.updateById(supplier);

            flag = true;
            tipMsg = "成功";
            tipType = 0;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>修改供应商ID为 ： " + model.getSupplierID() + "信息失败");
            flag = false;
            tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();
        } finally {
            try {
                map.put("flag",flag);
            } catch (Exception e) {
                System.out.println(">>>>>>>>>>>>修改供应商回写客户端结果异常");
                e.printStackTrace();
            }
        }
        logService.create(new Log(getUser(request), "更新供应商预付款", model.getClientIp(),
                new Timestamp(System.currentTimeMillis())
                , tipType, "更新供应商ID为  " + model.getSupplierID() + " " + tipMsg + "！", "更新供应商" + tipMsg));
        return map;

    }

    /**
     * 用户对应客户显示
     *
     * @return
     */
    @RequestMapping("/findUserCustomer")
    @ResponseBody
    public List findUserCustomer(Supplier model) {
        List lst=new ArrayList();
        try {
            model.setType("客户");
            List<Supplier> dataList =supplierService.querySupplierByType(model);

            //开始拼接json数据
            Map<String,Object> outer=new HashMap<String, Object>();
            outer.put("id", 1);
            outer.put("text", "客户列表");
            outer.put("state", "open");
            //存放数据json数组
            List dataArray = new ArrayList();
            if (null != dataList) {
                for (Supplier supplier : dataList) {
                    Map<String,Object> item=new HashMap<String, Object>();
                    item.put("id", supplier.getId());
                    item.put("text", supplier.getSupplier());
                    //勾选判断1
                    Boolean flag = false;
                    try {
                        flag = userBusinessService.checkIsUserBusinessExist("Type", model.getUBType(), "KeyId", model.getUBKeyId(), "Value", "[" + supplier.getId().toString() + "]");
                    } catch (DataAccessException e) {
                        System.out.println(">>>>>>>>>>>>>>>>>设置用户对应的客户：类型" + model.getUBType() + " KeyId为： " + model.getUBKeyId() + " 存在异常！");
                    }
                    if (flag == true) {
                        item.put("checked", true);
                    }
                    //结束
                    dataArray.add(item);
                }
            }
            outer.put("children", dataArray);
            //回写查询结果
            lst.add(outer);
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>>>>>>>查找客户异常");
            e.printStackTrace();
        }
        return lst;
    }

    /**
     * 查找客户信息-下拉框
     *
     * @return
     */
    @RequestMapping("/findBySelect_cus")
    @ResponseBody
    public List  findBySelect_cus(Supplier model) {
        try {

            List<Supplier> dataList =supplierService.queryBySelect_cus(model);
            //存放数据json数组
            List dataArray = new ArrayList();
            if (null != dataList) {
                for (Supplier supplier : dataList) {
                    Map<String,Object> item=new HashMap<String, Object>();
                    //勾选判断1
                    Boolean flag = false;
                    try {
                        flag = userBusinessService.checkIsUserBusinessExist("Type", model.getUBType(), "KeyId", model.getUBKeyId(), "Value", "[" + supplier.getId().toString() + "]");
                    } catch (DataAccessException e) {
                        System.out.println(">>>>>>>>>>>>>>>>>查询用户对应的客户：类型" + model.getUBType() + " KeyId为： " + model.getUBKeyId() + " 存在异常！");
                    }
                    if (flag == true) {
                        item.put("id", supplier.getId());
                        item.put("supplier", supplier.getSupplier()); //客户名称
                        dataArray.add(item);
                    }
                }
            }
            //回写查询结果
            return dataArray;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>查找客户信息异常");
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.out.println(">>>>>>>>>回写查询客户信息结果异常");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据id查找信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/findById")
    public  Map<String, Object> findById(Supplier model) {
        try {
            model.setId(model.getSupplierID());
            List<Supplier> dataList =supplierService.queryById(model);
            Map<String, Object> outer = new HashMap<String, Object>();
            //存放数据json数组
            List dataArray = new ArrayList();
            if (null != dataList) {
                for (Supplier supplier : dataList) {
                    Map<String, Object> item = new HashMap<String, Object>();
                    item.put("id", supplier.getId());
                    //名称
                    item.put("supplier", supplier.getSupplier());
                    item.put("type", supplier.getType());
                    item.put("contacts", supplier.getContacts());
                    item.put("phonenum", supplier.getPhonenum());
                    item.put("email", supplier.getEmail());
                    item.put("AdvanceIn", supplier.getAdvancein());
                    item.put("BeginNeedGet", supplier.getBeginneedget());
                    item.put("BeginNeedPay", supplier.getBeginneedpay());
                    item.put("isystem", supplier.getIsystem() == (short) 0 ? "是" : "否");
                    item.put("description", supplier.getDescription());

                    item.put("fax", supplier.getFax());
                    item.put("telephone", supplier.getTelephone());
                    item.put("address", supplier.getAddress());
                    item.put("taxNum", supplier.getTaxnum());
                    item.put("bankName", supplier.getBankname());
                    item.put("accountNumber", supplier.getAccountnumber());
                    item.put("taxRate", supplier.getTaxrate());

                    item.put("enabled", supplier.getEnabled());
                    item.put("op", supplier.getIsystem());
                    dataArray.add(item);
                }
            }
            outer.put("rows", dataArray);
            //回写查询结果
            return outer;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>查找信息异常");
            e.printStackTrace();
            throw new RuntimeException();
        } catch (Exception e) {
            System.out.println(">>>>>>>>>回写查询信息结果异常");
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    /**
     * 查找非会员的id//0==系统
     */
    @RequestMapping("/findBySelectRetailNoPeople")
    @ResponseBody
    public List findBySelectRetailNoPeople(Supplier model) {
        try {
            List<Supplier> dataList = supplierService.queryByTypeByIs(model);
            //存放数据json数组
            List dataArray = new ArrayList();
            if (null != dataList) {
                for (Supplier supplier : dataList) {
                    Map<String, Object> item = new HashMap<String, Object>();
                    item.put("id", supplier.getId());
                    //客户名称
                    item.put("supplier", supplier.getSupplier());
                    item.put("advanceIn", supplier.getAdvancein()); //预付款金额
                    dataArray.add(item);
                }
            }
            return dataArray;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>查找客户信息异常");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 检查输入名称是否存在
     */
    @ResponseBody
    @RequestMapping("/checkIsNameExist")
    public  Map<String, Object> checkIsNameExist(Supplier model) {
        Boolean flag = false;
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            flag = supplierService.checkIsNameExist("supplier", model.getSupplier(), "id", model.getSupplierID());
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>>>>>检查供应商名称为：" + model.getSupplier() + " ID为： " + model.getSupplierID() + " 是否存在异常！");
        }
        map.put("flag",flag);
        return  map;
    }

    /**
     * 增加供应商
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/create")
    public  Map<String, Object> create(HttpServletRequest request,Supplier model) {
        System.out.println("==================开始调用增加供应商方法===================");
        Boolean flag = false;
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Supplier supplier = new Supplier();
            supplier.setId(UUID.randomUUID().toString());
            supplier.setContacts(model.getContacts());
            supplier.setType(model.getType());
            supplier.setDescription(model.getDescription());
            supplier.setEmail(model.getEmail());
            supplier.setAdvancein(0.0);
            supplier.setBeginneedget(model.getBeginneedget());
            supplier.setBeginneedpay(model.getBeginneedpay());
            supplier.setIsystem(1);
            supplier.setEnabled(1);
            supplier.setPhonenum(model.getPhonenum());
            supplier.setSupplier(model.getSupplier());

            supplier.setFax(model.getFax());
            supplier.setTelephone(model.getTelephone());
            supplier.setAddress(model.getAddress());
            supplier.setTaxnum(model.getTaxnum());
            supplier.setBankname(model.getBankname());
            supplier.setAccountnumber(model.getAccountnumber());
            supplier.setTaxrate(model.getTaxrate());

            supplierService.insert(supplier);

            //========标识位===========
            flag = true;
            //记录操作日志使用
            tipMsg = "成功";
            tipType = 0;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>>>>>>>增加供应商异常");
            flag = false;
            tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();
        }

        logService.create(new Log(getUser(request), "增加供应商", model.getClientIp(),
                new Timestamp(System.currentTimeMillis())
                , tipType, "增加供应商名称为  " + model.getSupplier() + " " + tipMsg + "！", "增加供应商" + tipMsg));
        System.out.println("==================结束调用增加供应商方法===================");
        map.put("flag",flag);
        return  map;
    }



}
