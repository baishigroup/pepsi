package com.zking.erp.controller.jhui;

import com.zking.erp.base.BaseController;
import com.zking.erp.model.jhui.AccountItem;
import com.zking.erp.model.jhui.Log;
import com.zking.erp.service.jhui.IAccountItemJService;
import com.zking.erp.util.PageBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.*;

@Controller
@RequestMapping("/accountItem")
public class AccountItemJController extends BaseController{

    @Autowired
    private IAccountItemJService accountItemService;

    /**
     * 查找财务信息
     *
     * @return
     */
    @RequestMapping("/findBy")
    @ResponseBody
    public Map<String,Object> findBy(HttpServletRequest request,AccountItem model) {
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

    /**
     * 保存明细
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/saveDetials")
    public Map<String,Object> saveDetials(HttpServletRequest request,AccountItem model) {
        System.out.println("================开始调用保存财务明细信息方法saveDetials=====================");
        Boolean flag = false;
        Map<String,Object> map=new HashMap<String, Object>();
        try {
        String headerId = model.getHeaderid();
        String listType = model.getListType(); //单据类型
        String inserted = model.getInserted();
        String deleted = model.getDeleted();
        String updated = model.getUpdated();
        //转为json
        JSONArray insertedJson = JSONArray.fromObject(inserted);
        JSONArray deletedJson = JSONArray.fromObject(deleted);
        JSONArray updatedJson = JSONArray.fromObject(updated);


            if (null != insertedJson) {
                for (int i = 0; i < insertedJson.size(); i++) {
                    AccountItem accountItem = new AccountItem();
                    JSONObject tempInsertedJson = JSONObject.fromObject(insertedJson.get(i));
                    accountItem.setHeaderid(headerId);
                    if (tempInsertedJson.get("AccountId") != null && !tempInsertedJson.get("AccountId").equals("")) {
                        accountItem.setAccountid(tempInsertedJson.getString("AccountId"));
                    }
                    if (tempInsertedJson.get("InOutItemId") != null && !tempInsertedJson.get("InOutItemId").equals("")) {
                        accountItem.setInoutitemid(tempInsertedJson.getString("InOutItemId"));
                    }
                    if (tempInsertedJson.get("EachAmount") != null && !tempInsertedJson.get("EachAmount").equals("")) {
                        Double eachAmount = tempInsertedJson.getDouble("EachAmount");
                        if (listType.equals("付款")) {
                            eachAmount = 0 - eachAmount;
                        }
                        accountItem.setEachamount(eachAmount);
                    } else {
                        accountItem.setEachamount(0.0);
                    }
                    accountItem.setRemark(tempInsertedJson.getString("Remark"));
                    accountItem.setId(UUID.randomUUID().toString());
                    accountItemService.insert(accountItem);
                }
            }
            if (null != deletedJson) {
                for (int i = 0; i < deletedJson.size(); i++) {
                    JSONObject tempDeletedJson = JSONObject.fromObject(deletedJson.get(i));
                    accountItemService.deleteById(tempDeletedJson.getString("Id"));
                }
            }
            if (null != updatedJson) {
                for (int i = 0; i < updatedJson.size(); i++) {
                    JSONObject tempUpdatedJson = JSONObject.fromObject(updatedJson.get(i));
                    AccountItem accountItem = accountItemService.get(tempUpdatedJson.getString("Id"));
                    accountItem.setHeaderid(headerId);
                    if (tempUpdatedJson.get("AccountId") != null && !tempUpdatedJson.get("AccountId").equals("")) {
                        accountItem.setAccountid(tempUpdatedJson.getString("AccountId"));
                    }
                    if (tempUpdatedJson.get("InOutItemId") != null && !tempUpdatedJson.get("InOutItemId").equals("")) {
                        accountItem.setInoutitemid(tempUpdatedJson.getString("InOutItemId"));
                    }
                    if (tempUpdatedJson.get("EachAmount") != null && !tempUpdatedJson.get("EachAmount").equals("")) {
                        Double eachAmount = tempUpdatedJson.getDouble("EachAmount");
                        if (listType.equals("付款")) {
                            eachAmount = 0 - eachAmount;
                        }
                        accountItem.setEachamount(eachAmount);
                    } else {
                        accountItem.setEachamount(0.0);
                    }
                    accountItem.setRemark(tempUpdatedJson.getString("Remark"));
                    accountItemService.updateById(accountItem);
                }
            }

            //========标识位===========
            flag = true;
            //记录操作日志使用
            tipMsg = "成功";
            tipType = 0;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>>>>>>>保存财务明细信息异常");
            e.printStackTrace();
            flag = false;
            tipMsg = "失败";
            tipType = 1;
        } finally {
            try {
                map.put("flag",flag);
            } catch (Exception e) {
                System.out.println(">>>>>>>>>>>>>>>>>>>保存财务明细信息回写客户端结果异常");
                e.printStackTrace();
            }
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>结束调用保存财务明细方法saveDetials()===================");
        logService.create(new Log(getUser(request), "保存财务明细", model.getClientIp(),
                new Timestamp(System.currentTimeMillis())
                , tipType, "保存财务明细对应主表编号为  " + model.getHeaderid() + " " + tipMsg + "！", "保存财务明细" + tipMsg));
        return map;
    }




}

