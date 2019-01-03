package com.zking.erp.service.jhui.impl;

import com.zking.erp.mapper.jhui.AccountHeadMapperJ;
import com.zking.erp.model.jhui.AccountHead;
import com.zking.erp.service.jhui.IAccountHeadJService;
import com.zking.erp.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AccountHeadJServiceImpl implements IAccountHeadJService {

    @Autowired
    private AccountHeadMapperJ accountHeadMapperJ;

    @Transactional(readOnly = true)
    @Override
    public List<AccountHead> queryAccoutByLikePager(AccountHead accountHead, PageBean pageBean) {
        return accountHeadMapperJ.queryAccoutByLikePager(accountHead);
    }

    @Transactional(readOnly = true)
    @Override
    public AccountHead queryAccountHeadById(String id) {
        return accountHeadMapperJ.queryAccountHeadById(id);
    }

    @Transactional
    @Override
    public int updateById(AccountHead record) {
        return accountHeadMapperJ.updateById(record);
    }

    @Transactional
    @Override
    public int insert(AccountHead record) {
        return accountHeadMapperJ.insert(record);
    }

    @Override
    public int delete(AccountHead accountHead) {
        String[] split = accountHead.getAccountHeadIDs().split(",");
        accountHead.setIds(split);
        return accountHeadMapperJ.delete(accountHead);
    }

    @Override
    public int deleteById(String id) {
        return accountHeadMapperJ.deleteById(id);
    }

    @Override
    public List<AccountHead> queryDetailByNumber(AccountHead accountHead) {
        return accountHeadMapperJ.queryDetailByNumber(accountHead);
    }
}
