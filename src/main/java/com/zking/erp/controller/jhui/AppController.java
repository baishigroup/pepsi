package com.zking.erp.controller.jhui;

import com.zking.erp.model.jhui.App;
import com.zking.erp.service.jhui.IAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/app")
public class AppController {
    @Autowired
    private IAppService appService;

    /**
     * 桌面应用显示
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/findDesk")
    public Map<String,Object> findDesk(HttpServletRequest req ,App App) {
        Map<String, Object> outer = new HashMap<String, Object>();
        try {
            //下面是dock
            App app1 = new App();
            app1.setZl("dock");
            List<App> dataList1 = appService.queryAppByfindDesk(app1);
            System.out.println(dataList1);
            //存放数据json数组
            List dataArray1 = new ArrayList();
            if (null != dataList1) {
                for (App app : dataList1) {
                    Map<String, Object> item = new HashMap<String, Object>();
                    item.put("id", app.getId());
                    item.put("title", app.getName());
                    item.put("type", app.getType());
                    item.put("icon", "../../upload/images/deskIcon/" + app.getIcon());
                    item.put("url", app.getUrl());
                    item.put("width", app.getWidth());
                    item.put("height", app.getHeight());
                    item.put("isresize", app.getResize());
                    item.put("isopenmax", app.getOpenmax());
                    item.put("isflash", app.getFlash());
                    dataArray1.add(item);


                }
            }
            outer.put("dock", dataArray1);

            //下面是desk
            App app2 = new App();
            app2.setZl("desk");
            List<App> dataList2 = appService.queryAppByfindDesk(app2);
            //存放数据json数组
            List dataArray2 = new ArrayList();
            if (null != dataList2) {
                for (App app : dataList2) {
                    Map<String, Object> item = new HashMap<String, Object>();
                    item.put("id", app.getId());
                    item.put("title", app.getName());
                    item.put("type", app.getType());
                    item.put("icon", "../../upload/images/deskIcon/" + app.getIcon());
                    item.put("url", "../../pages/common/menu.jsp?appID=" + app.getNumber() + "&id=" + app.getId());
                    item.put("width", app.getWidth());
                    item.put("height", app.getHeight());
                    item.put("isresize", app.getResize());
                    item.put("isopenmax", app.getOpenmax());
                    item.put("isflash", app.getFlash());
                    dataArray2.add(item);
                }
            }
            outer.put("desk", dataArray2);
            return outer;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


}
