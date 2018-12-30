package com.zking.erp.service.jhui;

import com.zking.erp.model.jhui.SystemConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ISystemConfigJService {

    SystemConfig selectById(String id);

    int updateById(SystemConfig record);

    List<SystemConfig> querySystemConfig(SystemConfig systemConfig);



}
