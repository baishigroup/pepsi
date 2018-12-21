package com.zking.erp.mapper.cao;

import com.zking.erp.model.cao.MaterialCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialCategoryMapperC {

    /**
     * 添加商品类型
     * @param materialCategory
     * @return
     */
    int insertMaterialCate(MaterialCategory materialCategory);

    /**
     * 根据ID修改商品类型
     * @param materialCategory
     * @return
     */
    int updateMaterialCateById(MaterialCategory materialCategory);

    /**
     * 根据ID删除商品类型
     * @param id
     * @return
     */
    int deleteMaterialCateById(String id);

    /**
     * 根据条件查询商品信息
     * @param materialCategory
     * @return
     */
    List<MaterialCategory> queryMaterialCate(MaterialCategory materialCategory);

}