package com.zking.erp.controller.jhui;

import com.zking.erp.base.BaseController;
import com.zking.erp.model.jhui.DepotHead;
import com.zking.erp.model.jhui.DepotItem;
import com.zking.erp.model.jhui.Log;
import com.zking.erp.model.jhui.User;
import com.zking.erp.service.jhui.IDepotHeadJService;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/depotHead")
public class DepotHeadJController extends BaseController{

    @Autowired
    private IDepotHeadJService  depotHeadService;

    /**
     * 增加单据
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/create")
    public Map<String,Object> create(HttpServletRequest request,DepotHead model) {
        Map<String,Object> map=new HashMap<String, Object>();
        System.out.println("==================开始调用增加单据信息信息方法create()===================");
        Boolean flag = false;
        try {
//            if (model.getProjectid() != null) {
//                depotHead.setProjectId(new Depot(model.getProjectId()));
//            }
            //构造新的编号
            String dNumber = model.getDefaultnumber();
            String number = dNumber.substring(0, 12); //截取前缀
            String beginTime = Tools.getNow() + " 00:00:00";
            String endTime = Tools.getNow() + " 23:59:59";
            DepotHead buildNumber=new DepotHead();
            buildNumber.setType(model.getType());
            buildNumber.setSubtype(model.getSubtype());
            buildNumber.setBeginTime(beginTime);
            buildNumber.setEndTime(endTime);
            String newNumber = buildNumberFun(buildNumber);  //从数据库查询最新的编号+1,这样能防止重复
            String allNewNumber = number + newNumber;
//            depotHead.setNumber(model.getNumber()); //一直从前端文本框里面获取
            model.setDefaultnumber(allNewNumber); //初始编号，一直都从后台取值
            User user=(User) request.getSession().getAttribute("user");
            model.setOperpersonname(user.getUsername());
            model.setCreatetime(new Timestamp(System.currentTimeMillis()));
            if(model.getAllocationprojectid().equals("")){
                model.setAllocationprojectid(null);
            }
            if(model.getHandspersonid().equals("")){
                model.setHandspersonid(null);
            }
            if(model.getProjectid().equals(""))
                model.setProjectid(null);
            if(model.getAccountid().equals(""))
                model.setAccountid(null);
            try {
                model.setOpertime(new Timestamp(Tools.parse(model.getStrOpertime(), "yyyy-MM-dd HH:mm:ss").getTime()));
            } catch (ParseException e) {
                System.out.println(">>>>>>>>>>>>>>>解析购买日期格式异常");
                e.printStackTrace();
            }
//            if (model.getOrganId() != null) {
//                depotHead.setOrganId(new Supplier(model.getOrganId()));
//            }
//            if (model.getHandsPersonId() != null) {
//                depotHead.setHandsPersonId(new Person(model.getHandsPersonId()));
//            }
            if (model.getSalesman() != null) {
                model.setSalesman(model.getSalesman().toString());
            }
//            if (model.getAccountId() != null) {
//                depotHead.setAccountId(new Account(model.getAccountId()));
//            }
//            if (model.getAllocationprojectid() != null) {
//                depotHead.setAllocationProjectId(new Depot(model.getAllocationProjectId()));
//            }
//            if(model.getOthermoneyitem().indexOf("null")==-1||model.getOthermoneyitem().indexOf("undefined")==-1){
//                model.setOthermoneyitem(null);
//            }
//            if(model.getOthermoneylist().indexOf("null")==-1||model.getOthermoneylist().indexOf("undefined")==-1){
//                model.setOthermoneylist(null);
//            }
            model.setAccountidlist(null);
            model.setAccountmoneylist(null);

            model.setStatus(0);
            depotHeadService.insert(model);

            //========标识位===========
            flag = true;
            //记录操作日志使用
            tipMsg = "成功";
            tipType = 0;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>>>>>>>增加单据信息异常");
            flag = false;
            tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();
        }

        logService.create(new Log(getUser(request), "增加单据", model.getClientIp(),
                new Timestamp(System.currentTimeMillis())
                , tipType, "增加单据编号为  " + model.getNumber() + " " + tipMsg + "！", "增加单据" + tipMsg));
        map.put("flag",flag);
        System.out.println("==================结束调用增加单据方法create()===================");
        return map;
    }

    /**
     * 删除单据
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/delete")
    public Map<String,Object> delete(HttpServletRequest request,DepotHead model) {
        Map<String,Object> map=new HashMap<String, Object>();
        System.out.println("====================开始调用删除单据信息方法delete()================");
        try {
            depotHeadService.deleteById(model.getDepotHeadID());
            tipMsg = "成功";
            tipType = 0;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>删除ID为 " + model.getDepotHeadID() + "  的单据异常");
            tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();
        }
        map.put("message",tipMsg);
        logService.create(new Log(getUser(request), "删除单据", model.getClientIp(),
                new Timestamp(System.currentTimeMillis())
                , tipType, "删除单据ID为  " + model.getDepotHeadID() + " " + tipMsg + "！", "删除单据" + tipMsg));
        System.out.println("====================结束调用删除单据信息方法delete()================");
        return map;
    }

    /**
     * 更新单据
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/update")
    public Map<String,Object> update(HttpServletRequest request,DepotHead model) {
        System.out.println("====================开始调用 更新单据信息方法update()================");

        Map<String,Object> map=new HashMap<String, Object>();
        Boolean flag = false;
        try {
            if(model.getAllocationprojectid().equals("")){
                model.setAllocationprojectid(null);
            }
            if(model.getHandspersonid().equals("")){
                model.setHandspersonid(null);
            }
            if(model.getProjectid().equals(""))
                model.setProjectid(null);
            if(model.getAccountid().equals(""))
                model.setAccountid(null);
            if(model.getOthermoneyitem().indexOf("null")==-1||model.getOthermoneyitem().indexOf("undefined")==-1){
                model.setOthermoneyitem(null);
            }
            if(model.getOthermoneylist().indexOf("null")==-1||model.getOthermoneylist().indexOf("undefined")==-1){
                model.setOthermoneylist(null);
            }

            DepotHead depotHead = depotHeadService.selectById(model.getDepotHeadID());
            depotHead.setType(model.getType());
            depotHead.setSubtype(model.getSubtype());
//            if (model.getProjectId() != null) {
//                depotHead.setProjectId(new Depot(model.getProjectId()));
//            }
            depotHead.setProjectid(model.getProjectid());
            depotHead.setNumber(model.getNumber());
            User user=(User) request.getSession().getAttribute("user");
            model.setOperpersonname(user.getUsername());
            try {
                depotHead.setOpertime(new Timestamp(Tools.parse(model.getStrOpertime(), "yyyy-MM-dd HH:mm:ss").getTime()));
            } catch (ParseException e) {
                System.out.println(">>>>>>>>>>>>>>>解析入库时间格式异常");
                e.printStackTrace();
            }
//            if (model.getOrganId() != null) {
//                depotHead.setOrganId(new Supplier(model.getOrganId()));
//            }
            depotHead.setOrganid(model.getOrganid());
//            if (model.getHandsPersonId() != null) {
//                depotHead.setHandsPersonId(new Person(model.getHandsPersonId()));
//            }
            depotHead.setHandspersonid(model.getHandspersonid());
            depotHead.setSalesman(model.getSalesman());
//            if (model.getAccountId() != null) {
//                depotHead.setAccountId(new Account(model.getAccountId()));
//            } else {
//                depotHead.setAccountId(null);
//            }
            depotHead.setAccountid(model.getAccountid());
            depotHead.setChangeamount(model.getChangeamount());
            depotHead.setAccountidlist(model.getAccountidlist());
            depotHead.setAccountmoneylist(model.getAccountmoneylist());
            depotHead.setDiscount(model.getDiscount());
            depotHead.setDiscountmoney(model.getDiscountmoney());
            depotHead.setDiscountlastmoney(model.getDiscountlastmoney());
            depotHead.setOthermoney(model.getOthermoney());
//            if(model.getOthermoneyitem().indexOf("null")==-1||model.getOthermoneyitem().indexOf("undefined")==-1){
//                model.setOthermoneyitem(null);
//            }
//            if(model.getOthermoneylist().indexOf("null")==-1||model.getOthermoneylist().indexOf("undefined")==-1){
//                model.setOthermoneylist(null);
//            }
            depotHead.setOthermoneylist(model.getOthermoneylist());
            depotHead.setOthermoneyitem(model.getOthermoneyitem());
            depotHead.setAccountday(model.getAccountday());
//            if (model.getAllocationProjectId() != null) {
//                depotHead.setAllocationProjectId(new Depot(model.getAllocationProjectId()));
//            }
            depotHead.setAllocationprojectid(model.getAllocationprojectid());
            depotHead.setTotalprice(model.getTotalprice());
            depotHead.setPaytype(model.getPaytype());
            depotHead.setStatus(0);
            depotHead.setRemark(model.getRemark());
            depotHead.setAccountidlist(null);
            depotHead.setAccountmoneylist(null);
            depotHeadService.updateById(depotHead);

            flag = true;
            tipMsg = "成功";
            tipType = 0;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>修改单据ID为 ： " + model.getDepotHeadID() + "信息失败");
            flag = false;
            tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();
        }
        logService.create(new Log(getUser(request), "更新单据", model.getClientIp(),
                new Timestamp(System.currentTimeMillis())
                , tipType, "更新单据ID为  " + model.getDepotHeadID() + " " + tipMsg + "！", "更新单据" + tipMsg));
        map.put("flag",flag);
        System.out.println("====================结束调用更新单据信息方法update()================");
        return map;
    }

    /**
     * 批量删除指定ID单据
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/batchDelete")
    public Map<String,Object> batchDelete(HttpServletRequest request,DepotHead model) {
        Map<String,Object> map=new HashMap<String, Object>();
        try {
            depotHeadService.delete(model);
            map.put("message","成功");
            //记录操作日志使用
            tipMsg = "成功";
            tipType = 0;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>批量删除单据ID为：" + model.getDepotHeadIDs() + "信息异常");
            tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();
        }

        logService.create(new Log(getUser(request), "批量删除单据", model.getClientIp(),
                new Timestamp(System.currentTimeMillis())
                , tipType, "批量删除单据ID为  " + model.getDepotHeadIDs() + " " + tipMsg + "！", "批量删除单据" + tipMsg));
        return map;
    }

    /**
     * 批量设置状态-审核或者反审核
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/batchSetStatus")
    public Map<String,Object> batchSetStatus(HttpServletRequest request,DepotHead model) {
        Map<String,Object> map=new HashMap<String, Object>();
        try {
            depotHeadService.updatebatchStatus(model);
            map.put("message","成功");
            //记录操作日志使用
            tipMsg = "成功";
            tipType = 0;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>批量修改状态单据ID为：" + model.getDepotHeadIDs() + "信息异常");
            tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();
        }

        logService.create(new Log(getUser(request), "批量修改单据状态", model.getClientIp(),
                new Timestamp(System.currentTimeMillis())
                , tipType, "批量修改状态，单据ID为  " + model.getDepotHeadIDs() + " " + tipMsg + "！", "批量修改单据状态" + tipMsg));
        return map;
    }

    /**
     * 检查单据编号是否存在
     */
    @ResponseBody
    @RequestMapping("/checkIsNumberExist")
    public Map<String,Object> checkIsNumberExist(DepotHead model) {
        Map<String,Object> map=new HashMap<String, Object>();
        Boolean flag = false;
        try {
            flag = depotHeadService.checkIsNameExist("Number", model.getNumber(), "Id", model.getDepotHeadID());
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>>>>>检查单据编号为：" + model.getNumber() + " ID为： " + model.getDepotHeadID() + " 是否存在出现异常！");
        }
        map.put("flag",flag);
        return  map;
    }

    /**
     * 单据编号生成接口，规则：查找当前类型单据下的当天最大的单据号，并加1
     */
    @ResponseBody
    @RequestMapping("/buildNumber")
    public Map<String,Object> buildNumber(DepotHead model) {
        Map<String,Object> outer=new HashMap<String, Object>();
        try {
            String beginTime = model.getBeginTime();
            String endTime = model.getEndTime();
            String newNumber = buildNumberFun(model);
            outer.put("DefaultNumber", newNumber);
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>>>>>>>单据编号生成异常");
            e.printStackTrace();
        }
        return outer;
    }

    /**
     * 查找单据编号
     *
     * @return
     */
    public String buildNumberFun(DepotHead  model) {
        String newNumber = "0001"; //新编号
        try {
            List<DepotHead> dataList =depotHeadService.queryNumberByTypeByTime(model);
            //存放数据json数组
            if (null != dataList && dataList.size() > 0) {
                DepotHead depotHead = dataList.get(0);
                if (depotHead != null) {
                    String number = depotHead.getDefaultnumber(); //最大的单据编号
                    if (number != null) {
                        Integer lastNumber = Integer.parseInt(number.substring(12, 16)); //末四尾
                        lastNumber = lastNumber + 1;
                        Integer nLen = lastNumber.toString().length();
                        if (nLen == 1) {
                            newNumber = "000" + lastNumber.toString();
                        } else if (nLen == 2) {
                            newNumber = "00" + lastNumber.toString();
                        } else if (nLen == 3) {
                            newNumber = "0" + lastNumber.toString();
                        } else if (nLen == 4) {
                            newNumber = lastNumber.toString();
                        }
                    }
                }
            }
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>>>>>>>单据编号生成异常");
            e.printStackTrace();
        }
        return newNumber;
    }

    /**
     * 根据材料信息获取
     */
    @ResponseBody
    @RequestMapping("/getHeaderIdByMaterial")
    public Map<String,Object> getHeaderIdByMaterial(DepotHead model) {
        Map<String,Object> outer=new HashMap<String, Object>();
        try {
            String materialParam = model.getMaterialParam(); //商品参数
            String depotIds = model.getDepotIds(); //拥有的仓库信息
            List<DepotItem> dataList = depotHeadService.queryHeaderIdByMaterial(materialParam, depotIds);
            String allReturn = "";
            if (dataList != null) {
                for (Integer i = 0; i < dataList.size(); i++) {
                    Object dl = dataList.get(i).getHeaderid(); //获取对象
                    allReturn = allReturn + dl.toString() + ",";
                }
            }
            allReturn = allReturn.substring(0, allReturn.length() - 1);
            if (allReturn.equals("null")) {
                allReturn = "";
            }
            outer.put("ret", allReturn);
        } catch (Exception e) {
            System.out.println(">>>>>>>>>>>>>>>>>>>查找信息异常");
            e.printStackTrace();
        }
        return outer;
    }

    /**
     * 查找单据信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/findBy")
    public Map<String,Object> findBy(HttpServletRequest request,DepotHead model) {
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
//    @ResponseBody
//    @RequestMapping("/getDetailByNumber")
//    public Map<String,Object> getDetailByNumber() {
//        try {
//            pageUtil.setAdvSearch(getConditionByNumber());
//            depotHeadService.find(pageUtil);
//            List<DepotHead> dataList = pageUtil.getPageList();
//            JSONObject item = new JSONObject();
//            if (dataList != null && dataList.get(0) != null) {
//                DepotHead depotHead = dataList.get(0);
//                item.put("Id", depotHead.getId());
//                item.put("ProjectId", depotHead.getProjectId() == null ? "" : depotHead.getProjectId().getId());
//                item.put("ProjectName", depotHead.getProjectId() == null ? "" : depotHead.getProjectId().getName());
//                item.put("Number", depotHead.getNumber());
//                item.put("OperPersonName", depotHead.getOperPersonName());
//                item.put("CreateTime", Tools.getCenternTime(depotHead.getCreateTime()));
//                item.put("OperTime", Tools.getCenternTime(depotHead.getOperTime()));
//                item.put("OrganId", depotHead.getOrganId() == null ? "" : depotHead.getOrganId().getId());
//                item.put("OrganName", depotHead.getOrganId() == null ? "" : depotHead.getOrganId().getSupplier());
//                item.put("HandsPersonId", depotHead.getHandsPersonId() == null ? "" : depotHead.getHandsPersonId().getId());
//                item.put("Salesman", depotHead.getSalesman().toString());
//                item.put("HandsPersonName", depotHead.getHandsPersonId() == null ? "" : depotHead.getHandsPersonId().getName());
//                item.put("AccountId", depotHead.getAccountId() == null ? "" : depotHead.getAccountId().getId());
//                item.put("AccountName", depotHead.getAccountId() == null ? "" : depotHead.getAccountId().getName());
//                item.put("ChangeAmount", depotHead.getChangeAmount() == null ? "" : Math.abs(depotHead.getChangeAmount()));
//                item.put("AccountIdList", depotHead.getAccountIdList());
//                item.put("AccountMoneyList", depotHead.getAccountMoneyList());
//                item.put("Discount", depotHead.getDiscount());
//                item.put("DiscountMoney", depotHead.getDiscountMoney());
//                item.put("DiscountLastMoney", depotHead.getDiscountLastMoney());
//                item.put("OtherMoney", depotHead.getOtherMoney());
//                item.put("OtherMoneyList", depotHead.getOtherMoneyList()); //id列表
//                item.put("OtherMoneyItem", depotHead.getOtherMoneyItem()); //money列表
//                item.put("AccountDay", depotHead.getAccountDay()); //结算天数
//                item.put("AllocationProjectId", depotHead.getAllocationProjectId() == null ? "" : depotHead.getAllocationProjectId().getId());
//                item.put("AllocationProjectName", depotHead.getAllocationProjectId() == null ? "" : depotHead.getAllocationProjectId().getName());
//                item.put("TotalPrice", depotHead.getTotalPrice() == null ? "" : Math.abs(depotHead.getTotalPrice()));
//                item.put("payType", depotHead.getPayType() == null ? "" : depotHead.getPayType());
//                item.put("Status", depotHead.getStatus());
//                item.put("Remark", depotHead.getRemark());
//                item.put("MaterialsList", findMaterialsListByHeaderId(depotHead.getId()));
//            }
//            //回写查询结果
//            toClient(item.toString());
//        } catch (DataAccessException e) {
//            Log.errorFileSync(">>>>>>>>>>>>>>>>>>>查找单据信息异常", e);
//        } catch (IOException e) {
//            Log.errorFileSync(">>>>>>>>>>>>>>>>>>>回写查询单据信息结果异常", e);
//        }
//    }

    /**
     * 查找单据_根据月份(报表)
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/findByMonth")
    public Map<String,Object> findByMonth(DepotHead model) {
        try {
            List<DepotHead> dataList =depotHeadService.queryDepotHeadByMonth(model);
            Map<String,Object> outer=new HashMap<String, Object>();
            String headId = "";
            if (null != dataList) {
                for (DepotHead depotHead : dataList) {
                    headId = headId + depotHead.getId() + ",";
                }
            }
            if (headId != "") {
                headId = headId.substring(0, headId.lastIndexOf(","));
            }
            outer.put("HeadIds", headId);
            return outer;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>>>>>>>查找单据信息异常");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 查找统计信息_根据礼品卡(报表)
     *
     * @return
     */
//    public void findGiftReport() {
//        try {
//            PageUtil<DepotHead> pageUtil_in = new PageUtil<DepotHead>();
//            pageUtil_in.setPageSize(0);
//            pageUtil_in.setCurPage(0);
//            pageUtil_in.setAdvSearch(getConditionHead_Gift_In());
//            depotHeadService.find(pageUtil_in);
//            List<DepotHead> dataList_in = pageUtil_in.getPageList();
//            JSONObject outer = new JSONObject();
//            String headId = "";
//            if (null != dataList_in) {
//                for (DepotHead depotHead : dataList_in) {
//                    headId = headId + depotHead.getId() + ",";
//                }
//                PageUtil<DepotHead> pageUtil_out = new PageUtil<DepotHead>();
//                pageUtil_out.setPageSize(0);
//                pageUtil_out.setCurPage(0);
//                pageUtil_out.setAdvSearch(getConditionHead_Gift_Out());
//                depotHeadService.find(pageUtil_out);
//                List<DepotHead> dataList_out = pageUtil_out.getPageList();
//                if (null != dataList_out) {
//                    for (DepotHead depotHead : dataList_out) {
//                        headId = headId + depotHead.getId() + ",";
//                    }
//                }
//            }
//            if (headId != "") {
//                headId = headId.substring(0, headId.lastIndexOf(","));
//            }
//            outer.put("HeadIds", headId);
//            toClient(outer.toString());
//        } catch (DataAccessException e) {
//            Log.errorFileSync(">>>>>>>>>>>>>>>>>>>查找单据信息异常", e);
//        } catch (IOException e) {
//            Log.errorFileSync(">>>>>>>>>>>>>>>>>>>回写查询单据信息结果异常", e);
//        }
//    }

    /**
     * 查询单位的累计应收和累计应付，零售不能计入
     *
     * @return
     */
//    public void findTotalPay() {
//        try {
//            JSONObject outer = new JSONObject();
//            Double sum = 0.0;
//            String getS = model.getSupplierId();
//            String supType = model.getSupType(); //单位类型：客户、供应商
//            int i = 1;
//            if (supType.equals("customer")) { //客户
//                i = 1;
//            } else if (supType.equals("vendor")) { //供应商
//                i = -1;
//            }
//            //进销部分
//            sum = sum - (allMoney(getS, "入库", "采购", "合计") - allMoney(getS, "入库", "采购", "实际")) * i;
//            sum = sum - (allMoney(getS, "入库", "销售退货", "合计") - allMoney(getS, "入库", "销售退货", "实际")) * i;
//            sum = sum + (allMoney(getS, "出库", "销售", "合计") - allMoney(getS, "出库", "销售", "实际")) * i;
//            sum = sum + (allMoney(getS, "出库", "采购退货", "合计") - allMoney(getS, "出库", "采购退货", "实际")) * i;
//            outer.put("getAllMoney", sum);
//            toClient(outer.toString());
//        } catch (DataAccessException e) {
//            Log.errorFileSync(">>>>>>>>>>>>>>>>>>>查找异常", e);
//        } catch (IOException e) {
//            Log.errorFileSync(">>>>>>>>>>>>>>>>>>>回写查询结果异常", e);
//        }
//    }


    /**
     * 统计总金额
     *
     * @param type
     * @param subType
     * @param mode    合计或者金额
     * @return
     */
//    @SuppressWarnings({"unchecked", "rawtypes"})
//    public Double allMoney(String getS, String type, String subType, String mode) {
//        Log.infoFileSync("getS:" + getS);
//        Double allMoney = 0.0;
//        String allReturn = "";
//        PageUtil<DepotHead> pageUtil = new PageUtil<DepotHead>();
//        pageUtil.setPageSize(0);
//        pageUtil.setCurPage(0);
//        pageUtil.setAdvSearch(getConditionHead_byEndTime());
//        try {
//            Integer supplierId = Integer.valueOf(getS);
//            depotHeadService.findAllMoney(pageUtil, supplierId, type, subType, mode);
//            allReturn = pageUtil.getPageList().toString();
//            allReturn = allReturn.substring(1, allReturn.length() - 1);
//            if (allReturn.equals("null")) {
//                allReturn = "0";
//            }
//        } catch (JshException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        allMoney = Double.parseDouble(allReturn);
//        //返回正数，如果负数也转为正数
//        if (allMoney < 0) {
//            allMoney = -allMoney;
//        }
//        return allMoney;
//    }

    /**
     * 入库出库明细接口
     */
//    public void findInDetail() {
//        PageUtil pageUtil = new PageUtil();
//        pageUtil.setPageSize(model.getPageSize());
//        pageUtil.setCurPage(model.getPageNo());
//        Long pid = model.getProjectId();
//        String dids = model.getDepotIds();
//        Long oId = model.getOrganId();
//        String beginTime = model.getBeginTime();
//        String endTime = model.getEndTime();
//        String type = model.getType();
//        try {
//            depotHeadService.findInDetail(pageUtil, beginTime, endTime, type, pid, dids, oId);
//            List dataList = pageUtil.getPageList();
//            JSONObject outer = new JSONObject();
//            outer.put("total", pageUtil.getTotalCount());
//            //存放数据json数组
//            JSONArray dataArray = new JSONArray();
//            if (dataList != null) {
//                for (Integer i = 0; i < dataList.size(); i++) {
//                    JSONObject item = new JSONObject();
//                    Object dl = dataList.get(i); //获取对象
//                    Object[] arr = (Object[]) dl; //转为数组
//                    item.put("number", arr[0]); //单据编号
//                    item.put("materialName", arr[1]); //商品名称
//                    item.put("materialModel", arr[2]); //商品型号
//                    item.put("unitPrice", arr[3]); //单价
//                    item.put("operNumber", arr[4]); //入库出库数量
//                    item.put("allPrice", arr[5]); //金额
//                    item.put("supplierName", arr[6]); //供应商
//                    item.put("depotName", arr[7]); //仓库
//                    item.put("operTime", arr[8]); //入库出库日期
//                    item.put("type", arr[9]); //入库出库日期
//                    dataArray.add(item);
//                }
//            }
//            outer.put("rows", dataArray);
//            //回写查询结果
//            toClient(outer.toString());
//        } catch (JshException e) {
//            Log.errorFileSync(">>>>>>>>>>>>>>>>>>>查找信息异常", e);
//        } catch (IOException e) {
//            Log.errorFileSync(">>>>>>>>>>>>>>>>>>>回写查询信息结果异常", e);
//        }
//    }

    /**
     * 入库出库统计接口
     */
//    public void findInOutMaterialCount() {
//        PageUtil pageUtil = new PageUtil();
//        pageUtil.setPageSize(model.getPageSize());
//        pageUtil.setCurPage(model.getPageNo());
//        Long pid = model.getProjectId();
//        String dids = model.getDepotIds();
//        Long oId = model.getOrganId();
//        String beginTime = model.getBeginTime();
//        String endTime = model.getEndTime();
//        String type = model.getType();
//        try {
//            depotHeadService.findInOutMaterialCount(pageUtil, beginTime, endTime, type, pid, dids, oId);
//            List dataList = pageUtil.getPageList();
//            JSONObject outer = new JSONObject();
//            outer.put("total", pageUtil.getTotalCount());
//            //存放数据json数组
//            JSONArray dataArray = new JSONArray();
//            if (dataList != null) {
//                for (Integer i = 0; i < dataList.size(); i++) {
//                    JSONObject item = new JSONObject();
//                    Object dl = dataList.get(i); //获取对象
//                    Object[] arr = (Object[]) dl; //转为数组
//                    item.put("MaterialId", arr[0]); //商品Id
//                    item.put("mName", arr[1]); //商品名称
//                    item.put("Model", arr[2]); //商品型号
//                    item.put("categoryName", arr[3]); //商品类型
//                    item.put("numSum", arr[4]); //数量
//                    item.put("priceSum", arr[5]); //金额
//                    dataArray.add(item);
//                }
//            }
//            outer.put("rows", dataArray);
//            //回写查询结果
//            toClient(outer.toString());
//        } catch (JshException e) {
//            Log.errorFileSync(">>>>>>>>>>>>>>>>>>>查找信息异常", e);
//        } catch (IOException e) {
//            Log.errorFileSync(">>>>>>>>>>>>>>>>>>>回写查询信息结果异常", e);
//        }
//    }

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

    /**
     * 对账单接口
     */
//    public void findStatementAccount() {
//        PageUtil pageUtil = new PageUtil();
//        pageUtil.setPageSize(model.getPageSize());
//        pageUtil.setCurPage(model.getPageNo());
//        String beginTime = model.getBeginTime();
//        String endTime = model.getEndTime();
//        Long organId = model.getOrganId();
//        String supType = model.getSupType(); //单位类型：客户、供应商
//        int j = 1;
//        if (supType.equals("客户")) { //客户
//            j = 1;
//        } else if (supType.equals("供应商")) { //供应商
//            j = -1;
//        }
//        try {
//            depotHeadService.findStatementAccount(pageUtil, beginTime, endTime, organId, supType);
//            List dataList = pageUtil.getPageList();
//            JSONObject outer = new JSONObject();
//            outer.put("total", pageUtil.getTotalCount());
//            //存放数据json数组
//            JSONArray dataArray = new JSONArray();
//            if (dataList != null) {
//                for (Integer i = 0; i < dataList.size(); i++) {
//                    JSONObject item = new JSONObject();
//                    Object dl = dataList.get(i); //获取对象
//                    Object[] arr = (Object[]) dl; //转为数组
//                    item.put("number", arr[0]); //单据编号
//                    item.put("type", arr[1]); //类型
//                    String type = arr[1].toString();
//                    Double p1 = 0.0;
//                    Double p2 = 0.0;
//                    if (arr[2] != null) {
//                        p1 = Double.parseDouble(arr[2].toString());
//                    }
//                    if (arr[3] != null) {
//                        p2 = Double.parseDouble(arr[3].toString());
//                    }
//                    Double allPrice = 0.0;
//                    if (p1 < 0) {
//                        p1 = -p1;
//                    }
//                    if (p2 < 0) {
//                        p2 = -p2;
//                    }
//                    if (type.equals("采购入库")) {
//                        allPrice = -(p1 - p2);
//                    } else if (type.equals("销售退货入库")) {
//                        allPrice = -(p1 - p2);
//                    } else if (type.equals("销售出库")) {
//                        allPrice = p1 - p2;
//                    } else if (type.equals("采购退货出库")) {
//                        allPrice = p1 - p2;
//                    } else if (type.equals("付款")) {
//                        allPrice = p1 + p2;
//                    } else if (type.equals("收款")) {
//                        allPrice = -(p1 + p2);
//                    } else if (type.equals("收入")) {
//                        allPrice = p1 - p2;
//                    } else if (type.equals("支出")) {
//                        allPrice = -(p1 - p2);
//                    }
//                    item.put("discountLastMoney", p1); //金额
//                    item.put("changeAmount", p2); //金额
//                    item.put("allPrice", String.format("%.2f", allPrice * j)); //计算后的金额
//                    item.put("supplierName", arr[4]); //供应商
//                    item.put("operTime", arr[5]); //入库出库日期
//                    dataArray.add(item);
//                }
//            }
//            outer.put("rows", dataArray);
//            //回写查询结果
//            toClient(outer.toString());
//        } catch (JshException e) {
//            Log.errorFileSync(">>>>>>>>>>>>>>>>>>>查找信息异常", e);
//        } catch (IOException e) {
//            Log.errorFileSync(">>>>>>>>>>>>>>>>>>>回写查询信息结果异常", e);
//        }
//    }

}
