package com.zking.erp.mapper.jhui;

import com.zking.erp.model.jhui.SystemConfig;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SystemConfigMapperJ {

   SystemConfig selectById(String id);

    int updateById(SystemConfig record);

    List<SystemConfig>  querySystemConfig(SystemConfig systemConfig);



}