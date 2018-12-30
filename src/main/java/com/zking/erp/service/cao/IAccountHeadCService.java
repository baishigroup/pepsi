package com.zking.erp.service.cao;

import com.zking.erp.model.cao.AccountHead;

import java.util.List;

public interface IAccountHeadCService {
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
     * 单个账户的金额求和-收入、支出、转账的单据表头的合计
     *
     * @param timeStr 查询时间范围
     * @param type  时间类型（month/date）
     * @return
     */
    List<AccountHead> queryAccountSumByItem( String timeStr, String type);
}
