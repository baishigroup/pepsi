package com.zking.erp.service.cao.impl;

import com.zking.erp.mapper.cao.MaterialPropertyMapperC;
import com.zking.erp.model.cao.MaterialProperty;
import com.zking.erp.service.cao.IMaterialPropertyCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MaterialPropertyCServiceImpl implements IMaterialPropertyCService {
    @Autowired
    private MaterialPropertyMapperC materialPropertyMapperC;

    @Override
    @Transactional(readOnly = true)
    public List<MaterialProperty> queryMaterialProPager(MaterialProperty materialProperty) {
        return materialPropertyMapperC.queryMaterialProPager(materialProperty);
    }
}
