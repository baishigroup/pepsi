package com.zking.erp.mapper.all;

import com.zking.erp.model.all.Depot;
import org.springframework.stereotype.Repository;

@Repository
public interface DepotMapper {
    int deleteByPrimaryKey(String id);

    int insert(Depot record);

    int insertSelective(Depot record);

    Depot selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Depot record);

    int updateByPrimaryKey(Depot record);
}