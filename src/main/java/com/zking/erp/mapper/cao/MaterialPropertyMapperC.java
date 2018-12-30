package com.zking.erp.mapper.cao;

import com.zking.erp.model.cao.MaterialProperty;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialPropertyMapperC {
    /**
     * 查询
     * @param materialProperty
     * @return
     */
    List<MaterialProperty> queryMaterialProPager(MaterialProperty materialProperty);

}