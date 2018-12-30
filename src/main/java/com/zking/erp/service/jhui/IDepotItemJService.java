package com.zking.erp.service.jhui;

import com.zking.erp.model.jhui.DepotItem;

import java.util.List;

public interface IDepotItemJService {

    List<DepotItem> queryDepotItemByMaterialId(DepotItem depotItem);
    List<DepotItem> queryByType(String type, String ProjectId, String MId, String MonthTime, Integer isPrev);

    List<DepotItem> queryDepotJoinTable(DepotItem depotItem);

    int insert(DepotItem record);

    DepotItem selectById(String id);

    int updateById(DepotItem record);

    int deleteById(String id);




}
