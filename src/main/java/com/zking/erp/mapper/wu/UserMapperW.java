package com.zking.erp.mapper.wu;

import com.zking.erp.model.wu.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapperW {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}