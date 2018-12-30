package com.zking.erp.service.cao.impl;

import com.zking.erp.mapper.cao.AccountHeadMapperC;
import com.zking.erp.model.cao.AccountHead;
import com.zking.erp.service.cao.IAccountHeadCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AccountHeadCServiceImpl implements IAccountHeadCService {

    @Autowired
    private AccountHeadMapperC accountHeadMapperC;

    @Override
    public List<AccountHead> queryAccountSumByHead(String id, String timeStr, String type) {
        return accountHeadMapperC.queryAccountSumByHead(id,timeStr,type);
    }

    @Override
    public List<AccountHead> queryAccountSumByItem(String timeStr, String type) {
        return accountHeadMapperC.queryAccountSumByItem(timeStr,type);
    }
}
