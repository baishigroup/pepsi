package com.zking.erp.mapper.all;

import com.zking.erp.model.all.Functions;
import org.springframework.stereotype.Repository;

@Repository
public interface FunctionsMapper {
    int deleteByPrimaryKey(String id);

    int insert(Functions record);

    int insertSelective(Functions record);

    Functions selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Functions record);

    int updateByPrimaryKey(Functions record);
}