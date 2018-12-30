package com.zking.erp.service.cao;

import com.zking.erp.model.cao.MaterialCategory;
import com.zking.erp.util.PageBean;

import java.util.List;

public interface IMaterialCategoryCService {

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
     * 批量刪除
     * @param materialCategory
     * @return
     */
    int deleteMaterialCateByIds(MaterialCategory materialCategory);

    /**
     * 根据条件查询商品信息
     * @param materialCategory
     * @return
     */
    List<MaterialCategory> queryMaterialCatePager(MaterialCategory materialCategory, PageBean pageBean);

    /**
     * 判断是否重名
     * @param filedName
     * @param filedValue
     * @param idFiled
     * @param objectID
     * @return
     */
    Boolean checkIsNameExist(String filedName,String filedValue,String idFiled,String objectID);

}