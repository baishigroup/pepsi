package com.zking.erp.service.cao.impl;

import com.zking.erp.mapper.cao.UnitMapperC;
import com.zking.erp.model.cao.Unit;
import com.zking.erp.service.cao.IUnitCService;
import com.zking.erp.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UnitCServiceImpl implements IUnitCService {

    @Autowired
    private UnitMapperC unitMapperC;

    @Override
    public int insertUnit(Unit unit) {
        return unitMapperC.insertUnit(unit);
    }

    @Override
    public int updateUnitById(Unit unit) {
        return unitMapperC.updateUnitById(unit);
    }

    @Override
    public int deleteUnitById(String id) {
        return unitMapperC.deleteUnitById(id);
    }

    @Override
    public int deleteUnitByIds(Unit unit) {
        return unitMapperC.deleteUnitByIds(unit);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Unit> queryUnitPager(Unit unit, PageBean pageBean) {
        return unitMapperC.queryUnitPager(unit);
    }

    @Override
    public Boolean checkIsNameExist(String filedName, String filedValue, String idFiled, String objectID) {
        List<Unit> dataList = unitMapperC.checkIsNameExist(filedName, filedValue, idFiled, objectID);
        if(null != dataList&& dataList.size()>0)
            return true;
        return false;
    }
}
