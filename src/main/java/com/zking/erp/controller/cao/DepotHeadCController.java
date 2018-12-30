package com.zking.erp.controller.cao;

import com.zking.erp.model.cao.DepotHead;
import com.zking.erp.service.cao.IDepotHeadCService;
import com.zking.erp.util.PageBean;
import com.zking.erp.util.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cao/depotHead")
public class DepotHeadCController {

    @Autowired
    private IDepotHeadCService depotHeadService;

    /**
     * 查找单据信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/findBy")
    public Map<String,Object> findBy(HttpServletRequest request, DepotHead model) {
        Map<String,Object> outer=new HashMap<String, Object>();
        try {
            PageBean pageBean=new PageBean();
            pageBean.setRequest(request);
            List<DepotHead> dataList =depotHeadService.queryDepotHeadPager(pageBean,model);
            outer.put("total", pageBean.getTotal());
            //存放数据json数组
            List dataArray = new ArrayList();
            if (null != dataList) {
                for (DepotHead depotHead : dataList) {
                    Map<String,Object> item=new HashMap<String, Object>();
                    item.put("Id", depotHead.getId());
                    item.put("ProjectId", depotHead.getProjectid() == null ? "" : depotHead.getProjectid());
                    item.put("ProjectName", depotHead.getProjectid() == null ? "" : depotHead.getProjectName());
                    item.put("Number", depotHead.getNumber());
                    item.put("OperPersonName", depotHead.getOperpersonname());
                    item.put("CreateTime", Tools.getCenternTime(depotHead.getCreatetime()));
                    item.put("OperTime", Tools.getCenternTime(depotHead.getOpertime()));
                    item.put("OrganId", depotHead.getOrganid() == null ? "" : depotHead.getOrganid());
                    item.put("OrganName", depotHead.getOrganid() == null ? "" : depotHead.getOrganName());
                    item.put("HandsPersonId", depotHead.getHandspersonid() == null ? "" : depotHead.getHandspersonid());
                    item.put("Salesman", depotHead.getSalesman().toString());
                    item.put("HandsPersonName", depotHead.getHandspersonid() == null ? "" : depotHead.getHandsPersonName());
                    item.put("AccountId", depotHead.getAccountid() == null ? "" : depotHead.getAccountid());
                    item.put("AccountName", depotHead.getAccountid() == null ? "" : depotHead.getAccountName());
                    item.put("ChangeAmount", depotHead.getChangeamount() == null ? "" : Math.abs(depotHead.getChangeamount()));
                    item.put("AccountIdList", depotHead.getAccountidlist());
                    item.put("AccountMoneyList", depotHead.getAccountmoneylist());
                    item.put("Discount", depotHead.getDiscount());
                    item.put("DiscountMoney", depotHead.getDiscountmoney());
                    item.put("DiscountLastMoney", depotHead.getDiscountlastmoney());
                    item.put("OtherMoney", depotHead.getOthermoney());
                    item.put("OtherMoneyList", depotHead.getOthermoneylist()); //id列表
                    item.put("OtherMoneyItem", depotHead.getOthermoneyitem()); //money列表
                    item.put("AccountDay", depotHead.getAccountday()); //结算天数
                    item.put("AllocationProjectId", depotHead.getAllocationprojectid() == null ? "" : depotHead.getAllocationprojectid());
                    item.put("AllocationProjectName", depotHead.getAllocationprojectid() == null ? "" : depotHead.getAllocationProjectName());
                    item.put("TotalPrice", depotHead.getTotalprice() == null ? "" : Math.abs(depotHead.getTotalprice()));
                    item.put("payType", depotHead.getPaytype() == null ? "" : depotHead.getPaytype());
                    item.put("Status", depotHead.getStatus());
                    item.put("Remark", depotHead.getRemark());
                    item.put("MaterialsList", findMaterialsListByHeaderId(depotHead.getId()));
                    item.put("op", 1);
                    dataArray.add(item);
                }
            }
            outer.put("rows", dataArray);
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>>>>>>>查找单据信息异常");
            e.printStackTrace();
        }
        return  outer;
    }

    /**
     * 根据编号查询单据信息
     */
    @ResponseBody
    @RequestMapping("/getDetailByNumber")
    public Map<String,Object> getDetailByNumber(DepotHead model) {
        try {
            List<DepotHead> dataList = depotHeadService.queryDepotHeadByNumber(model);
            Map<String, Object> item = new HashMap<String, Object>();
            if (dataList != null && dataList.get(0) != null) {
                DepotHead depotHead = dataList.get(0);
                item.put("Id", depotHead.getId());
                item.put("ProjectId", depotHead.getProjectid() == null ? "" : depotHead.getProjectid());
                item.put("ProjectName", depotHead.getProjectid() == null ? "" : depotHead.getProjectName());
                item.put("Number", depotHead.getNumber());
                item.put("OperPersonName", depotHead.getOperpersonname());
                item.put("CreateTime", Tools.getCenternTime(depotHead.getCreatetime()));
                item.put("OperTime", Tools.getCenternTime(depotHead.getOpertime()));
                item.put("OrganId", depotHead.getOrganid() == null ? "" : depotHead.getOrganid());
                item.put("OrganName", depotHead.getOrganid() == null ? "" : depotHead.getOrganName());
                item.put("HandsPersonId", depotHead.getHandspersonid() == null ? "" : depotHead.getHandspersonid());
                item.put("Salesman", depotHead.getSalesman().toString());
                item.put("HandsPersonName", depotHead.getHandspersonid() == null ? "" : depotHead.getHandsPersonName());
                item.put("AccountId", depotHead.getAccountid() == null ? "" : depotHead.getAccountid());
                item.put("AccountName", depotHead.getAccountid() == null ? "" : depotHead.getAccountName());
                item.put("ChangeAmount", depotHead.getChangeamount() == null ? "" : Math.abs(depotHead.getChangeamount()));
                item.put("AccountIdList", depotHead.getAccountidlist());
                item.put("AccountMoneyList", depotHead.getAccountmoneylist());
                item.put("Discount", depotHead.getDiscount());
                item.put("DiscountMoney", depotHead.getDiscountmoney());
                item.put("DiscountLastMoney", depotHead.getDiscountlastmoney());
                item.put("OtherMoney", depotHead.getOthermoney());
                item.put("OtherMoneyList", depotHead.getOthermoneylist()); //id列表
                item.put("OtherMoneyItem", depotHead.getOthermoneyitem()); //money列表
                item.put("AccountDay", depotHead.getAccountday()); //结算天数
                item.put("AllocationProjectId", depotHead.getAllocationprojectid() == null ? "" : depotHead.getAllocationprojectid());
                item.put("AllocationProjectName", depotHead.getAllocationprojectid() == null ? "" : depotHead.getAllocationProjectName());
                item.put("TotalPrice", depotHead.getTotalprice() == null ? "" : Math.abs(depotHead.getTotalprice()));
                item.put("payType", depotHead.getPaytype() == null ? "" : depotHead.getPaytype());
                item.put("Status", depotHead.getStatus());
                item.put("Remark", depotHead.getRemark());
                item.put("MaterialsList", findMaterialsListByHeaderId(depotHead.getId()));
            }
            //回写查询结果
            return item;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>>>>>>>查找单据信息异常");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public String findMaterialsListByHeaderId(String headerId) {
        String allReturn = "";
        try {
            DepotHead depotHead=new DepotHead();
            depotHead.setDepotHeadID(headerId);
            List<DepotHead> depotHeads = depotHeadService.queryMaterialsListByHeaderId(depotHead);
            if (depotHeads != null) {
                for (Integer i = 0; i < depotHeads.size(); i++) {
                    Object dl = depotHeads.get(i).getmName(); //获取对象
                    allReturn = allReturn + dl.toString() + ",";
                }
            }
            allReturn = allReturn.substring(0, allReturn.length() - 1);
            if (allReturn.equals("null")) {
                allReturn = "";
            }
        } catch (Exception e) {
            System.out.println(">>>>>>>>>>>>>>>>>>>查找信息异常");
            e.printStackTrace();
        }
        return allReturn;
    }

}
