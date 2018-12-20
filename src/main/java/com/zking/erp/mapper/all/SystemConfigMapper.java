package com.zking.erp.mapper.all;

import com.zking.erp.model.all.SystemConfig;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemConfigMapper {
    int deleteByPrimaryKey(String id);

    int insert(SystemConfig record);

    int insertSelective(SystemConfig record);

    SystemConfig selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SystemConfig record);

    int updateByPrimaryKey(SystemConfig record);
}