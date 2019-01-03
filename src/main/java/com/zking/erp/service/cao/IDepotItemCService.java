package com.zking.erp.service.cao;

import com.zking.erp.model.cao.DepotItem;
import com.zking.erp.util.PageBean;

import java.util.List;

public interface IDepotItemCService {
    List<DepotItem> queryDepotJoinTable(DepotItem depotItem);

    List<DepotItem> queryDepotItemByMId(DepotItem depotItem);

    List<DepotItem> querySumByTypeByMId(String type,String MId);

    List<DepotItem> queryDetailByMIdPager(PageBean pageBean, String MId);

    List<DepotItem> buyOrSale(String type, String subType, String MId, String MonthTime,String sumType);

    List<DepotItem> queryBuyInPager(DepotItem depotItem,PageBean pageBean);

    /**
     * 销量趋势
     * @param year
     * @return
     */
    List<DepotItem> salesTrendAnalysis(String year);
}
