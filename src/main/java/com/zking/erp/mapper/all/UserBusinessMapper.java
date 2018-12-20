package com.zking.erp.mapper.all;

import com.zking.erp.model.all.UserBusiness;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBusinessMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserBusiness record);

    int insertSelective(UserBusiness record);

    UserBusiness selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserBusiness record);

    int updateByPrimaryKey(UserBusiness record);
}