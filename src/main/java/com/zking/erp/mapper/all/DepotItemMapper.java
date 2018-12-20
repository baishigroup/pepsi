package com.zking.erp.mapper.all;

import com.zking.erp.model.all.DepotItem;
import org.springframework.stereotype.Repository;

@Repository
public interface DepotItemMapper {
    int deleteByPrimaryKey(String id);

    int insert(DepotItem record);

    int insertSelective(DepotItem record);

    DepotItem selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DepotItem record);

    int updateByPrimaryKey(DepotItem record);
}