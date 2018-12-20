package com.zking.erp.mapper.all;

import com.zking.erp.model.all.DepotHead;
import org.springframework.stereotype.Repository;

@Repository
public interface DepotHeadMapper {
    int deleteByPrimaryKey(String id);

    int insert(DepotHead record);

    int insertSelective(DepotHead record);

    DepotHead selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DepotHead record);

    int updateByPrimaryKey(DepotHead record);
}