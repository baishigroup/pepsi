package com.zking.erp.service.cao.impl;

import com.zking.erp.mapper.cao.AccountItemMapperC;
import com.zking.erp.model.cao.AccountItem;
import com.zking.erp.service.cao.IAccountItemCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AccountItemCServiceImpl implements IAccountItemCService {

    @Autowired
    private AccountItemMapperC accountItemMapperC;

    @Override
    public List<AccountItem> queryAccountItemByHeaderId(AccountItem accountItem) {
        return accountItemMapperC.queryAccountItemByHeaderId(accountItem);
    }

    @Override
    public List<AccountItem> queryAccountItemByItem(String id, String ids) {
        String[] split = ids.split(",");
        return accountItemMapperC.queryAccountItemByItem(id,split);
    }
}
