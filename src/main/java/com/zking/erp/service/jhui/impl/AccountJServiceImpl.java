package com.zking.erp.service.jhui.impl;

import com.zking.erp.mapper.jhui.AccountMapperJ;
import com.zking.erp.model.jhui.Account;
import com.zking.erp.service.jhui.IAccountJService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AccountJServiceImpl implements IAccountJService {

    @Autowired
    private AccountMapperJ accountMapperJ;

    @Transactional(readOnly = true)
    @Override
    public List<Account> queryAccout() {
        return accountMapperJ.queryAccout();
    }
}
