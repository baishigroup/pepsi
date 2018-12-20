package com.zking.erp.controller.jhui;

import com.zking.erp.model.jhui.Functions;
import com.zking.erp.model.jhui.vo.FunctionsVo;
import com.zking.erp.service.jhui.IFunctionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/functions")
public class FunctionsController {

    @Autowired
    private IFunctionsService functionsService;

    /**
     * 页面显示菜单
     *
     * @return
     */
    @RequestMapping("/findMenu")
    @ResponseBody
    public List findMenu(FunctionsVo functionsVo) {
        try {
            String fc = functionsVo.getHasFunctions(); //当前用户所拥有的功能列表，格式如：[1][2][5]
            List<Functions> dataList = functionsService.queryMenuByPNumber(functionsVo);
            //存放数据json数组
            List dataArray = new ArrayList();
            if (null != dataList) {
                for (Functions functions : dataList) {
                    Map<String,Object> item = new HashMap<String,Object>();
                    item.put("id", functions.getId());
                    List<Functions> dataList1 = functionsService.queryMenuByPNumber(new FunctionsVo(functions.getNumber()));
                    List dataArray1 = new ArrayList();
                    if (dataList1.size() != 0) {
                        item.put("text", functions.getName()); //是目录就没链接
                        for (Functions functions1 : dataList1) {
                            item.put("state", "open");   //如果不为空，节点展开
                            Map<String,Object> item1 = new HashMap<String,Object>();
                            List<Functions> dataList2 = functionsService.queryMenuByPNumber(new FunctionsVo(functions1.getNumber()));

                            if (fc.indexOf("[" + functions1.getId().toString() + "]") != -1 || dataList2.size() != 0) {
                                item1.put("id", functions1.getId());
                                List dataArray2 = new ArrayList();
                                if (dataList2.size() != 0) {
                                    item1.put("text", functions1.getName());//是目录就没链接
                                    for (Functions functions2 : dataList2) {
                                        item1.put("state", "closed");   //如果不为空，节点不展开
                                        Map<String,Object> item2 = new HashMap<String,Object>();

                                        List<Functions> dataList3 = functionsService.queryMenuByPNumber(new FunctionsVo(functions2.getNumber()));


                                        if (fc.indexOf("[" + functions2.getId().toString() + "]") != -1 || dataList3.size() != 0) {
                                            item2.put("id", functions2.getId());
                                            List dataArray3 = new ArrayList();
                                            if (dataList3.size() != 0) {
                                                item2.put("text", functions2.getName());//是目录就没链接
                                                for (Functions functions3 : dataList3) {
                                                    item2.put("state", "closed");   //如果不为空，节点不展开
                                                    Map<String,Object> item3 = new HashMap<String,Object>();
                                                    item3.put("id", functions3.getId());
                                                    item3.put("text", functions3.getName());
                                                    //
                                                    dataArray3.add(item3);
                                                    item2.put("children", dataArray3);
                                                }
                                            } else {
                                                //不是目录，有链接
                                                item2.put("text", "<a onclick=\"NewTab('" + functions2.getName() + "','" + functions2.getUrl() + "','" + functions2.getId() + "')\">" + functions2.getName() + "</a>");
                                            }
                                        } else {
                                            //不是目录，有链接
                                            item2.put("text", "<a onclick=\"NewTab('" + functions2.getName() + "','" + functions2.getUrl() + "','" + functions2.getId() + "')\">" + functions2.getName() + "</a>");
                                        }
                                        dataArray2.add(item2);
                                        item1.put("children", dataArray2);
                                    }
                                } else {
                                    //不是目录，有链接
                                    item1.put("text", "<a onclick=\"NewTab('" + functions1.getName() + "','" + functions1.getUrl() + "','" + functions1.getId() + "')\">" + functions1.getName() + "</a>");
                                }
                            } else {
                                //不是目录，有链接
                                item1.put("text", "<a onclick=\"NewTab('" + functions1.getName() + "','" + functions1.getUrl() + "','" + functions1.getId() + "')\">" + functions1.getName() + "</a>");
                            }
                            dataArray1.add(item1);
                            item.put("children", dataArray1);
                        }
                    } else {
                        //不是目录，有链接
                        item.put("text", "<a onclick=\"NewTab('" + functions.getName() + "','" + functions.getUrl() + "','" + functions.getId() + "')\">" + functions.getName() + "</a>");
                    }
                    dataArray.add(item);
                }
            }
            return dataArray;
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }



}
