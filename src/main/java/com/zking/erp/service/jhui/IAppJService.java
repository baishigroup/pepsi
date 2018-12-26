package com.zking.erp.service.jhui;

import com.zking.erp.model.jhui.App;
import com.zking.erp.util.PageBean;

import java.util.List;

public interface IAppJService {

    /**
     * 桌面应用显示
     * @return
     */
    List<App> queryAppByfindDesk(App app);

    /**
     * 根据用户角色获取应用
     * @param app
     * @return
     */
    List<App> queryAppByRole(App app);

    List<App> queryAppByLikePager(App app, PageBean pageBean);

    /**
     * 检查是否重名
     * @param filedName
     * @param filedVale
     * @param idFiled
     * @param objectID
     */
    Boolean checkIsNameExist(String filedName, String filedVale, String idFiled, String objectID);

    int insert(App app);

    App selectById(String id);

    int updateById(App record);

    int deleteById(String id);

    int delete(App role);





}
