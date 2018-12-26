package com.zking.erp.mapper.jhui;

import com.zking.erp.model.jhui.AccountHead;
import com.zking.erp.model.jhui.Account;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountHeadMapperJ {

    List<AccountHead> queryAccoutByLikePager(AccountHead accountHead);

    AccountHead queryAccountHeadById(String id);

    int updateById(AccountHead record);

    int insert(AccountHead record);

    int delete(AccountHead accountHead);

    int deleteById(String id);


}