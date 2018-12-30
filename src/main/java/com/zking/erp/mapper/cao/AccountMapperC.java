package com.zking.erp.mapper.cao;

import com.zking.erp.model.cao.Account;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountMapperC {

    int insertAccount(Account account);

    int deleteAccountById(String id);

    int deleteAcountByIds(Account account);

    int updateAccount(Account account);

    List<Account> queryAccountPager(Account account);

    List<Account> queryAccout(Account account);

    List<Account> queryAccountInOutListPager(Account account);

    /**
     * 判断是否重名
     * @param filedName
     * @param filedValue
     * @param idFiled
     * @param objectID
     * @return
     */
    List<Account> checkIsNameExist(String filedName,String filedValue,String idFiled,String objectID);

    Account selectById(String id);





}