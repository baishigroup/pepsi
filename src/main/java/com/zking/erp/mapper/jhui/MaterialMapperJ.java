package com.zking.erp.mapper.jhui;

import com.zking.erp.model.jhui.Material;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialMapperJ {

    List<Material> queryById(Material material);

    List<Material> queryBySelect(Material material);

    List<Material> queryUnitName(String mId);



}