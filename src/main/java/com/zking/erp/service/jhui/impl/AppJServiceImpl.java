package com.zking.erp.service.jhui.impl;

import com.zking.erp.mapper.jhui.AppMapperJ;
import com.zking.erp.model.jhui.App;
import com.zking.erp.service.jhui.IAppJService;
import com.zking.erp.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AppJServiceImpl implements IAppJService {
    @Autowired
    private AppMapperJ appMapper;

    @Override
    @Transactional(readOnly = true)
    public List<App> queryAppByfindDesk(App app) {
        return appMapper.queryAppByfindDesk(app);
    }

    @Override
    @Transactional(readOnly = true)
    public List<App> queryAppByRole(App app) {
        return appMapper.queryAppByRole(app);
    }

    @Override
    public List<App> queryAppByLikePager(App app, PageBean pageBean) {
        return appMapper.queryAppByLikePager(app);
    }

    @Override
    public Boolean checkIsNameExist(String filedName, String filedVale, String idFiled, String objectID) {
        List<App> dataList = appMapper.checkIsNameExist(filedName, filedVale, idFiled, objectID);
        if (null != dataList && dataList.size() > 0)
            return true;
        return false;
    }

    @Override
    public int insert(App app) {
        return appMapper.insert(app);
    }

    @Override
    public App selectById(String id) {
        return appMapper.selectById(id);
    }

    @Override
    public int updateById(App record) {
        return appMapper.updateById(record);
    }

    @Transactional
    @Override
    public int deleteById(String id) {
        return appMapper.deleteById(id);
    }
    @Transactional
    @Override
    public int delete(App role) {
        String[] split = role.getAppIDs().split(",");
        role.setIds(split);
        return appMapper.delete(role);
    }
}
