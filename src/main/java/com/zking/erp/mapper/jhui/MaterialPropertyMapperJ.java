package com.zking.erp.mapper.jhui;

import com.zking.erp.model.jhui.MaterialProperty;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialPropertyMapperJ {

    MaterialProperty selectById(String id);

    int updateById(MaterialProperty record);

    List<MaterialProperty> queryMaterialPropertyPager(MaterialProperty materialProperty);

}