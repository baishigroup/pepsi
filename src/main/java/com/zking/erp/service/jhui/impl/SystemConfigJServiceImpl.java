package com.zking.erp.service.jhui.impl;

import com.zking.erp.mapper.jhui.SystemConfigMapperJ;
import com.zking.erp.model.jhui.SystemConfig;
import com.zking.erp.service.jhui.ISystemConfigJService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SystemConfigJServiceImpl implements ISystemConfigJService {

    @Autowired
    private SystemConfigMapperJ systemConfigMapperJ;

    @Override
    public SystemConfig selectById(String id) {
        return systemConfigMapperJ.selectById(id);
    }

    @Override
    public int updateById(SystemConfig record) {
        return systemConfigMapperJ.updateById(record);
    }

    @Override
    public List<SystemConfig> querySystemConfig(SystemConfig systemConfig) {
        return systemConfigMapperJ.querySystemConfig(systemConfig);
    }
}
