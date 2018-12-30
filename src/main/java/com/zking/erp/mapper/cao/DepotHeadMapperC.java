package com.zking.erp.mapper.cao;

import com.zking.erp.model.cao.DepotHead;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepotHeadMapperC {
    List<DepotHead> queryDepotHead(DepotHead depotHead);

    List<DepotHead> queryDepotHeadPager(DepotHead depotHead);

    List<DepotHead> queryDepotHeadByNumber(DepotHead depotHead);

    List<DepotHead> queryMaterialsListByHeaderId(DepotHead depotHead);

    /**
     * 单个账户的金额求和-入库和出库
     *
     * @param id 账户id
     * @param timeStr 查询时间范围
     * @param type  时间类型（month/date）
     * @return
     */
    List<DepotHead> queryAccountSumByAId(String id, String timeStr, String type);

    /**
     * 单个账户的金额求和-多账户的明细合计
     *
     * @param id
     * @param timeStr 查询时间范围
     * @param type  时间类型（month/date）
     * @return
     */
    List<DepotHead> queryManyAccountSum(String id, String timeStr, String type);

}