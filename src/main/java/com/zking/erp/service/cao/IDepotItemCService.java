package com.zking.erp.service.cao;

import com.zking.erp.model.cao.DepotItem;
import com.zking.erp.util.PageBean;

import java.util.List;

public interface IDepotItemCService {
    List<DepotItem> queryDepotJoinTable(DepotItem depotItem);

    List<DepotItem> queryDepotItemByMId(DepotItem depotItem);

    List<DepotItem> querySumByTypeByMId(String type,String MId);

    List<DepotItem> queryDetailByMIdPager(PageBean pageBean, String MId);




}
