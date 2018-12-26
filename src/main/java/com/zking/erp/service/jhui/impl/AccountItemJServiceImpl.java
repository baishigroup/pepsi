package com.zking.erp.service.jhui.impl;

import com.zking.erp.mapper.jhui.AccountItemMapperJ;
import com.zking.erp.model.jhui.AccountItem;
import com.zking.erp.service.jhui.IAccountItemJService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AccountItemJServiceImpl implements IAccountItemJService {

    @Autowired
    private AccountItemMapperJ accountItemMapperJ;

    @Transactional(readOnly = true)
    @Override
    public List<AccountItem> queryAccountItemByHeaderId(AccountItem accountItem) {
        return accountItemMapperJ.queryAccountItemByHeaderId(accountItem);
    }

    @Transactional
    @Override
    public int insert(AccountItem record) {
        return accountItemMapperJ.insert(record);
    }

    @Transactional
    @Override
    public int updateById(AccountItem record) {
        return accountItemMapperJ.updateById(record);
    }

    @Transactional
    @Override
    public int deleteById(String id) {
        return accountItemMapperJ.deleteById(id);
    }

    @Override
    public AccountItem get(String id) {
        return accountItemMapperJ.get(id);
    }
}
