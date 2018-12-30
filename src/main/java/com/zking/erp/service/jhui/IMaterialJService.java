package com.zking.erp.service.jhui;

import com.zking.erp.model.jhui.Material;

import java.util.List;

public interface IMaterialJService {

    List<Material> queryById(Material material);

    List<Material> queryBySelect(Material material);

    List<Material> queryUnitName(String mId);


}
