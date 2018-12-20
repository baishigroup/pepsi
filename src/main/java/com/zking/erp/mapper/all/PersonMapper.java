package com.zking.erp.mapper.all;

import com.zking.erp.model.all.Person;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonMapper {
    int deleteByPrimaryKey(String id);

    int insert(Person record);

    int insertSelective(Person record);

    Person selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Person record);

    int updateByPrimaryKey(Person record);
}