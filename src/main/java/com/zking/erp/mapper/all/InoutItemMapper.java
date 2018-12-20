package com.zking.erp.mapper.all;

import com.zking.erp.model.all.InoutItem;
import org.springframework.stereotype.Repository;

@Repository
public interface InoutItemMapper {
    int deleteByPrimaryKey(String id);

    int insert(InoutItem record);

    int insertSelective(InoutItem record);

    InoutItem selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(InoutItem record);

    int updateByPrimaryKey(InoutItem record);
}