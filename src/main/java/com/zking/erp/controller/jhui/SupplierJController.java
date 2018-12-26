package com.zking.erp.controller.jhui;

import com.zking.erp.base.BaseController;
import com.zking.erp.model.jhui.Supplier;
import com.zking.erp.service.jhui.ISupplierJService;
import com.zking.erp.service.jhui.IUserBusinessJService;
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
    public Map<String,Object> updateAdvanceIn(Supplier model) {
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
//        logService.create(new Logdetails(getUser(), "更新供应商预付款", model.getClientIp(),
//                new Timestamp(System.currentTimeMillis())
//                , tipType, "更新供应商ID为  " + model.getSupplierID() + " " + tipMsg + "！", "更新供应商" + tipMsg));
        return map;

    }

}
