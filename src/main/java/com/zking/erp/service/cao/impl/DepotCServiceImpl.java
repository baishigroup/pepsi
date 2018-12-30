package com.zking.erp.service.cao.impl;

import com.zking.erp.mapper.cao.DepotMapperC;
import com.zking.erp.model.cao.Depot;
import com.zking.erp.service.cao.IDepotCService;
import com.zking.erp.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DepotCServiceImpl implements IDepotCService {

    @Autowired
    private DepotMapperC depotMapperC;

    @Override
    public int insertDepot(Depot depot) {
        return depotMapperC.insertDepot(depot);
    }

    @Override
    public int updateDepotById(Depot depot) {
        return depotMapperC.updateDepotById(depot);
    }

    @Override
    public int deleteDepotById(String id) {
        return depotMapperC.deleteDepotById(id);
    }

    @Override
    public int deleteDepotByIds(Depot depot) {
        return depotMapperC.deleteDepotByIds(depot);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Depot> queryDepotPager(Depot depot, PageBean pageBean) {
        return depotMapperC.queryDepotPager(depot);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Depot> queryDepotAll() {
        return depotMapperC.queryDepotAll();
    }

    @Override
    public Boolean checkIsNameExist(String filedName, String filedValue, String idFiled, String objectID) {
        List<Depot> dataList = depotMapperC.checkIsNameExist(filedName, filedValue, idFiled, objectID);
        if(null != dataList&& dataList.size()>0)
            return true;
        return false;
    }
}
