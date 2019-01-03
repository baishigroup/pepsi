package com.zking.erp.service.jhui.impl;

import com.zking.erp.mapper.jhui.MaterialMapperJ;
import com.zking.erp.model.jhui.Material;
import com.zking.erp.service.jhui.IMaterialJService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MaterialJServiceImpl implements IMaterialJService {

    @Autowired
    private MaterialMapperJ materialMapperJ;


    @Override
    public List<Material> queryById(Material material) {
        return materialMapperJ.queryById(material);
    }

    @Override
    public List<Material> queryBySelect(Material material) {
        return materialMapperJ.queryBySelect(material);
    }

    @Override
    public List<Material> queryUnitName(String mId) {
        return materialMapperJ.queryUnitName(mId);
    }

    @Override
    public List<Material> queryMaterialByOrder() {
        return materialMapperJ.queryMaterialByOrder();
    }
}
