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
}