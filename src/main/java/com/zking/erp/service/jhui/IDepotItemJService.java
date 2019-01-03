package com.zking.erp.service.jhui;

import com.zking.erp.model.jhui.DepotItem;
import com.zking.erp.util.PageBean;

import java.util.List;

public interface IDepotItemJService {

    List<DepotItem> queryDepotItemByMaterialId(DepotItem depotItem);
    List<DepotItem> queryByType(String type, String ProjectId, String MId, String MonthTime, Integer isPrev);

    List<DepotItem> queryDepotJoinTable(DepotItem depotItem);

    int insert(DepotItem record);

    DepotItem selectById(String id);

    int updateById(DepotItem record);

    int deleteById(String id);

    List<DepotItem> queryByPrice(String type, String ProjectId, String MId, String MonthTime, Integer isPrev);

    List<DepotItem> totalCountMoney(DepotItem depotItem);

    List<DepotItem> queryByAllPager(PageBean pageBean,DepotItem depotItem);






}
