package com.zking.erp.mapper.cao;

import com.zking.erp.model.cao.DepotItem;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepotItemMapperC {

    List<DepotItem> queryDepotJoinTable(DepotItem depotItem);

    List<DepotItem> queryDepotItemByMId(DepotItem depotItem);

    List<DepotItem> querySumByTypeByMId(String type,String MId);

    List<DepotItem> queryDetailByMIdPager(String MId);

    //统计相关
    List<DepotItem> buyOrSale(String type, String subType, String MId, String MonthTime,String sumType);

    List<DepotItem> queryBuyInPager(DepotItem depotItem);

    /**
     * 销量趋势
     * @param year
     * @return
     */
    List<DepotItem> salesTrendAnalysis(String year);

}