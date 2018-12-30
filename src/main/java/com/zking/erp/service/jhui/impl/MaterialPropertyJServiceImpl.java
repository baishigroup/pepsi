package com.zking.erp.service.jhui.impl;

import com.zking.erp.mapper.jhui.MaterialPropertyMapperJ;
import com.zking.erp.model.jhui.MaterialProperty;
import com.zking.erp.service.jhui.IMaterialPropertyJService;
import com.zking.erp.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MaterialPropertyJServiceImpl implements IMaterialPropertyJService {

    @Autowired
    private MaterialPropertyMapperJ materialPropertyMapperJ;

    @Override
    public MaterialProperty selectById(String id) {
        return materialPropertyMapperJ.selectById(id);
    }

    @Override
    public int updateById(MaterialProperty record) {
        return materialPropertyMapperJ.updateById(record);
    }

    @Override
    public List<MaterialProperty> queryMaterialPropertyPager(PageBean pageBean, MaterialProperty materialProperty) {
        return materialPropertyMapperJ.queryMaterialPropertyPager(materialProperty);
    }
}
