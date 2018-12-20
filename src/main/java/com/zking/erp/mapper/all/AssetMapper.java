package com.zking.erp.mapper.all;

import com.zking.erp.model.all.Asset;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetMapper {
    int deleteByPrimaryKey(String id);

    int insert(Asset record);

    int insertSelective(Asset record);

    Asset selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Asset record);

    int updateByPrimaryKey(Asset record);
}