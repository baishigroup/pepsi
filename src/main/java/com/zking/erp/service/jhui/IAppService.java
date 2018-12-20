package com.zking.erp.service.jhui;

import com.zking.erp.model.jhui.App;

import java.util.List;

public interface IAppService  {

    /**
     * 桌面应用显示
     * @return
     */
    List<App> queryAppByfindDesk(App app);


}
