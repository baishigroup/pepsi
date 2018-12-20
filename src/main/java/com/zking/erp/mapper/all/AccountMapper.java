package com.zking.erp.mapper.all;

import com.zking.erp.model.all.Account;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountMapper {
    int deleteByPrimaryKey(String id);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);
}