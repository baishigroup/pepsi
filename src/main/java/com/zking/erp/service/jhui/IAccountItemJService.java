package com.zking.erp.service.jhui;

import com.zking.erp.model.jhui.AccountItem;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface IAccountItemJService {

    /**
     * 根据表头Id查询财务信息
     * @param accountItem
     * @return
     */
    List<AccountItem> queryAccountItemByHeaderId(AccountItem accountItem);

    /**
     * 添加明细
     * @param record
     * @return
     */
    int insert(AccountItem record);

    int updateById(AccountItem record);

    int deleteById(String id);

    AccountItem get(String id);



}