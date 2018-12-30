package com.zking.erp.service.cao;

import com.zking.erp.model.cao.Account;
import com.zking.erp.util.PageBean;

import java.util.List;

public interface IAccountCService {

    int insertAccount(Account account);

    int deleteAccountById(String id);

    int deleteAcountByIds(Account account);

    int updateAccount(Account account);

    List<Account> queryAccountPager(Account account, PageBean pageBean);

    List<Account> queryAccout(Account account);

    List<Account> queryAccountInOutListPager(PageBean pageBean,String accountId);

    /**
     * 判断是否重名
     * @param filedName
     * @param filedValue
     * @param idFiled
     * @param objectID
     * @return
     */
    Boolean checkIsNameExist(String filedName,String filedValue,String idFiled,String objectID);

    Account selectById(String id);






}