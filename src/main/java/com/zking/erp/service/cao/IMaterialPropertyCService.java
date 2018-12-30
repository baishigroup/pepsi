package com.zking.erp.service.cao;

import com.zking.erp.model.cao.MaterialProperty;

import java.util.List;

public interface IMaterialPropertyCService {
    /**
     * 查询
     * @param materialProperty
     * @return
     */
    List<MaterialProperty> queryMaterialProPager(MaterialProperty materialProperty);

}