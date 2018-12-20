package com.zking.erp.mapper.all;

import com.zking.erp.model.all.AccountItem;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountItemMapper {
    int deleteByPrimaryKey(String id);

    int insert(AccountItem record);

    int insertSelective(AccountItem record);

    AccountItem selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AccountItem record);

    int updateByPrimaryKey(AccountItem record);
}