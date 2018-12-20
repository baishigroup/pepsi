package com.zking.erp.mapper.all;

import com.zking.erp.model.all.Material;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialMapper {
    int deleteByPrimaryKey(String id);

    int insert(Material record);

    int insertSelective(Material record);

    Material selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Material record);

    int updateByPrimaryKey(Material record);
}