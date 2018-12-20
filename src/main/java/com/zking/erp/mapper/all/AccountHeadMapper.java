package com.zking.erp.mapper.all;

import com.zking.erp.model.all.AccountHead;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountHeadMapper {
    int deleteByPrimaryKey(String id);

    int insert(AccountHead record);

    int insertSelective(AccountHead record);

    AccountHead selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AccountHead record);

    int updateByPrimaryKey(AccountHead record);
}