package com.zking.erp.mapper.all;

import com.zking.erp.model.all.AssetName;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetNameMapper {
    int deleteByPrimaryKey(String id);

    int insert(AssetName record);

    int insertSelective(AssetName record);

    AssetName selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AssetName record);

    int updateByPrimaryKey(AssetName record);
}