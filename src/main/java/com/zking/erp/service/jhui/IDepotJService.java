package com.zking.erp.service.jhui;

import com.zking.erp.model.jhui.Depot;

import java.util.List;

public interface IDepotJService {

    List<Depot> queryDepotByAll(Depot depot);

    List<Depot>  queryDepot();

    List<Depot> quertByType(Depot depot);


}
