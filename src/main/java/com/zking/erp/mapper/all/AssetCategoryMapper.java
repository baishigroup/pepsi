package com.zking.erp.mapper.all;

import com.zking.erp.model.all.AssetCategory;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetCategoryMapper {
    int deleteByPrimaryKey(String id);

    int insert(AssetCategory record);

    int insertSelective(AssetCategory record);

    AssetCategory selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AssetCategory record);

    int updateByPrimaryKey(AssetCategory record);
}