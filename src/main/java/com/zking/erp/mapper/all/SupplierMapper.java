package com.zking.erp.mapper.all;

import com.zking.erp.model.all.Supplier;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierMapper {
    int deleteByPrimaryKey(String id);

    int insert(Supplier record);

    int insertSelective(Supplier record);

    Supplier selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Supplier record);

    int updateByPrimaryKey(Supplier record);
}