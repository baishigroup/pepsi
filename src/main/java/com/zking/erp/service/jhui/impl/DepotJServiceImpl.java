package com.zking.erp.service.jhui.impl;

import com.zking.erp.mapper.jhui.DepotMapperJ;
import com.zking.erp.model.jhui.Depot;
import com.zking.erp.service.jhui.IDepotJService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DepotJServiceImpl implements IDepotJService {

    @Autowired
    private DepotMapperJ depotMapperJ;

    @Override
    public List<Depot> queryDepotByAll(Depot depot) {
        return depotMapperJ.queryDepotByAll(depot);
    }

    @Override
    public List<Depot> queryDepot() {
        return depotMapperJ.queryDepot();
    }

    @Override
    public List<Depot> quertByType(Depot depot) {
        return depotMapperJ.quertByType(depot);
    }
}
