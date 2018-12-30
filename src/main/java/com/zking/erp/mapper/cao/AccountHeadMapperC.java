package com.zking.erp.mapper.cao;

import com.zking.erp.model.cao.AccountHead;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountHeadMapperC {

    /**
     * 单个账户的金额求和-收入、支出、转账的单据表头的合计
     *
     * @param id 账户id
     * @param timeStr 查询时间范围
     * @param type  时间类型（month/date）
     * @return
     */
    List<AccountHead> queryAccountSumByHead(String id, String timeStr, String type);

    /**
     * 单个账户的金额求和-收款、付款、转账、收预付款的单据明细的合计
     *
     * @param timeStr 查询时间范围
     * @param type  时间类型（month/date）
     * @return
     */
    List<AccountHead> queryAccountSumByItem( String timeStr, String type);


}