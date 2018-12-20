package com.zking.erp.mapper.all;

import com.zking.erp.model.all.Dep;
import org.springframework.stereotype.Repository;

@Repository
public interface DepMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(Dep record);

    int insertSelective(Dep record);

    Dep selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(Dep record);

    int updateByPrimaryKey(Dep record);
}