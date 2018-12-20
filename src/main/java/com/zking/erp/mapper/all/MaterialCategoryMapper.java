package com.zking.erp.mapper.all;

import com.zking.erp.model.all.MaterialCategory;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialCategoryMapper {
    int deleteByPrimaryKey(String id);

    int insert(MaterialCategory record);

    int insertSelective(MaterialCategory record);

    MaterialCategory selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MaterialCategory record);

    int updateByPrimaryKey(MaterialCategory record);
}