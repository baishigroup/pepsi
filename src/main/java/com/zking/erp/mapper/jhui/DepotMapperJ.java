package com.zking.erp.mapper.jhui;

import com.zking.erp.model.jhui.Depot;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepotMapperJ {
    List<Depot> queryDepotByAll(Depot depot);

    List<Depot>  queryDepot();

    List<Depot> quertByType(Depot depot);



}