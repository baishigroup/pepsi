package com.zking.erp.mapper.cao;

import com.zking.erp.model.cao.Material;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialMapperC {
    /**
     * 添加商品信息
     * @param material
     * @return
     */
    int insertMaterial(Material material);

    /**
     * 根據ID刪除商品信息
     * @param id
     * @return
     */
    int deleteMaterialById(String id);

    /**
     * 批量删除
     * @param material
     * @return
     */
    int deleteMaterialByIds(Material material);

    /**
     * 根据ID修改商品信息
     * @param material
     * @return
     */
    int updateMaterial(Material material);

    /**
     * 获取扩展信息
     *
     * @return
     */
    List<Material> queryById(Material material);

    /**
     * 查询所有 分页、条件
     * @param material
     * @return
     */
    List<Material> queryMaterialPager(Material material);

    /**
     * 判断是否重名
     * @param filedName
     * @param filedValue
     * @param idFiled
     * @param objectID
     * @return
     */
    List<Material> checkIsExist(String filedName,String filedValue,String idFiled,String objectID);

    /**
     * 批量启用、禁用
     * @param material
     * @return
     */
    int batchSetEnable(Material material);

}