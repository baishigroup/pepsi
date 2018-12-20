package com.zking.erp.service.jhui.impl;

import com.zking.erp.mapper.jhui.AppMapperJ;
import com.zking.erp.model.jhui.App;
import com.zking.erp.service.jhui.IAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AppServiceImpl implements IAppService {
    @Autowired
    private AppMapperJ appMapper;

    @Override
    @Transactional(readOnly = true)
    public List<App> queryAppByfindDesk(App app) {
        return appMapper.queryAppByfindDesk(app);
    }
}
