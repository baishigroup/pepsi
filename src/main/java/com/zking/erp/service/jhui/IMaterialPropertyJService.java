package com.zking.erp.service.jhui;

import com.zking.erp.model.jhui.MaterialProperty;
import com.zking.erp.util.PageBean;

import java.util.List;

public interface IMaterialPropertyJService {

    MaterialProperty selectById(String id);

    int updateById(MaterialProperty record);

    List<MaterialProperty> queryMaterialPropertyPager(PageBean pageBean,MaterialProperty materialProperty);
}
