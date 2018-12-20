package com.zking.erp.mapper.all;

import com.zking.erp.model.all.MaterialProperty;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialPropertyMapper {
    int deleteByPrimaryKey(byte[] id);

    int insert(MaterialProperty record);

    int insertSelective(MaterialProperty record);

    MaterialProperty selectByPrimaryKey(byte[] id);

    int updateByPrimaryKeySelective(MaterialProperty record);

    int updateByPrimaryKey(MaterialProperty record);
}