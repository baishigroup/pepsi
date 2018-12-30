package com.zking.erp.service.cao.impl;

import com.zking.erp.mapper.cao.DepotHeadMapperC;
import com.zking.erp.model.cao.DepotHead;
import com.zking.erp.service.cao.IDepotHeadCService;
import com.zking.erp.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DepotHeadCServiceImpl implements IDepotHeadCService {
    @Autowired
    private DepotHeadMapperC depotHeadMapperC;

    @Override
    public List<DepotHead> queryDepotHead(DepotHead depotHead) {
        return depotHeadMapperC.queryDepotHead(depotHead);
    }

    @Override
    public List<DepotHead> queryDepotHeadPager(PageBean pageBean, DepotHead depotHead) {
        String[] split = depotHead.getDhIds().split(",");
        depotHead.setIds(split);
        return depotHeadMapperC.queryDepotHeadPager(depotHead);
    }

    @Override
    public List<DepotHead> queryDepotHeadByNumber(DepotHead depotHead) {
        return depotHeadMapperC.queryDepotHeadByNumber(depotHead);
    }

    @Override
    public List<DepotHead> queryMaterialsListByHeaderId(DepotHead depotHead) {
        return depotHeadMapperC.queryMaterialsListByHeaderId(depotHead);
    }

    @Override
    public List<DepotHead> queryAccountSumByAId(String id, String timeStr, String type) {
        return depotHeadMapperC.queryAccountSumByAId(id,timeStr,type);
    }

    @Override
    public List<DepotHead> queryManyAccountSum(String id, String timeStr, String type) {
        return depotHeadMapperC.queryManyAccountSum(id,timeStr,type);
    }
}
