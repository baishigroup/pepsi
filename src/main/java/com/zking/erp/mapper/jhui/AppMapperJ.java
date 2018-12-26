package com.zking.erp.mapper.jhui;

import com.zking.erp.model.jhui.App;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppMapperJ {


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

    List<App> queryAppByLikePager(App app);

    /**
     * 检查是否重名
     * @param filedName
     * @param filedVale
     * @param idFiled
     * @param objectID
     */
    List<App> checkIsNameExist(String filedName, String filedVale, String idFiled, String objectID);

    int insert(App app);

    App selectById(String id);

    int updateById(App record);

    int deleteById(String id);

    int delete(App role);

}