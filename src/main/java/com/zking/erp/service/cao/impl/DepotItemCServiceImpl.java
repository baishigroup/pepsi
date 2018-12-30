package com.zking.erp.service.cao.impl;

import com.zking.erp.mapper.cao.DepotItemMapperC;
import com.zking.erp.model.cao.DepotItem;
import com.zking.erp.service.cao.IDepotItemCService;
import com.zking.erp.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class DepotItemCServiceImpl implements IDepotItemCService {

    @Autowired
    private DepotItemMapperC depotItemMapperC;


    @Override
    public List<DepotItem> queryDepotJoinTable(DepotItem depotItem) {
        return depotItemMapperC.queryDepotJoinTable(depotItem);
    }

    @Override
    public List<DepotItem> queryDepotItemByMId(DepotItem depotItem) {
        return depotItemMapperC.queryDepotItemByMId(depotItem);
    }

    @Override
    public List<DepotItem> querySumByTypeByMId(String type, String MId) {
        return depotItemMapperC.querySumByTypeByMId(type,MId);
    }

    @Override
    public List<DepotItem> queryDetailByMIdPager(PageBean pageBean, String MId) {
        return depotItemMapperC.queryDetailByMIdPager(MId);
    }
}
