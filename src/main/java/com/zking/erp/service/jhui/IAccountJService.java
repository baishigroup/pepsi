package com.zking.erp.service.jhui;

import com.zking.erp.model.jhui.Account;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface IAccountJService {

    //获取账户信息
    List<Account> queryAccout();

}