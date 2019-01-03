package com.zking.erp.service.cao.impl;

import com.zking.erp.mapper.cao.AccountMapperC;
import com.zking.erp.model.cao.Account;
import com.zking.erp.service.cao.IAccountCService;
import com.zking.erp.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AccountCServiceImpl implements IAccountCService {
    @Autowired
    private AccountMapperC accountMapperC;

    @Override
    public int insertAccount(Account account) {
        return accountMapperC.insertAccount(account);
    }

    @Override
    public int deleteAccountById(String id) {
        return accountMapperC.deleteAccountById(id);
    }

    @Override
    public int deleteAcountByIds(Account account) {
        String[] split = account.getAccountIDs().split(",");
        account.setIds(split);
        return accountMapperC.deleteAcountByIds(account);
    }

    @Override
    public int updateAccount(Account account) {
        return accountMapperC.updateAccount(account);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Account> queryAccountPager(Account account, PageBean pageBean) {
        return accountMapperC.queryAccountPager(account);
    }

    @Override
    public List<Account> queryAccout(Account account) {
        return accountMapperC.queryAccout(account);
    }

    @Override
    public List<Account> queryAccountInOutListPager(PageBean pageBean,String accountId) {
        Account account = new Account();
        if(null!=accountId && ""!=accountId) {
            account.setAccountID(accountId);
        }
        return accountMapperC.queryAccountInOutListPager( account);
    }

    @Override
    public Boolean checkIsNameExist(String filedName, String filedValue, String idFiled, String objectID) {
        List<Account> dataList = accountMapperC.checkIsNameExist(filedName, filedValue, idFiled, objectID);
        if(null != dataList&& dataList.size()>0)
            return true;
        return false;
    }

    @Override
    public Account selectById(String id) {
        return accountMapperC.selectById(id);
    }
}
