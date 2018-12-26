package com.zking.erp.mapper.jhui;

import com.zking.erp.model.jhui.Account;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountMapperJ {



    /**
     * 1.获取账户信息
     * 2.查找结算账户信息-下拉框
     * @return
     */
    List<Account> queryAccout();


}