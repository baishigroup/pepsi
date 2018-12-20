package com.zking.erp.mapper.wu;

import com.zking.erp.model.wu.Dep;
import org.springframework.stereotype.Repository;

@Repository
public interface DepMapperW {
    int deleteByPrimaryKey(String uuid);

    int insert(Dep record);

    int insertSelective(Dep record);

    Dep selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(Dep record);

    int updateByPrimaryKey(Dep record);
}