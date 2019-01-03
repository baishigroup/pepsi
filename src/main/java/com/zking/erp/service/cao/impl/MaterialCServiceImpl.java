package com.zking.erp.service.cao.impl;

import com.zking.erp.mapper.cao.MaterialMapperC;
import com.zking.erp.model.cao.Material;
import com.zking.erp.service.cao.IMaterialCService;
import com.zking.erp.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MaterialCServiceImpl implements IMaterialCService {

    @Autowired
    private MaterialMapperC materialMapperC;

    @Override
    public List<Material> queryById(Material material) {
        return materialMapperC.queryById(material);
    }

    @Override
    public int insertMaterial(Material material) {
        return materialMapperC.insertMaterial(material);
    }

    @Override
    public int deleteMaterialById(String id) {
        return materialMapperC.deleteMaterialById(id);
    }

    @Override
    public int deleteMaterialByIds(Material material) {
        return materialMapperC.deleteMaterialByIds(material);
    }

    @Override
    public int updateMaterial(Material material) {
        return materialMapperC.updateMaterial(material);
    }

    @Override
    public List<Material> queryMaterialPager(Material material, PageBean pageBean) {
        if(!material.getCategoryid().equals("1")) {
            String[] split = material.getCategoryIds().split(",");
            material.setIds(split);
        }
        return materialMapperC.queryMaterialPager(material);
    }

    @Override
    public Boolean checkIsExist(String filedName, String filedValue, String idFiled, String objectID) {
        List<Material> dataList = materialMapperC.checkIsExist(filedName, filedValue, idFiled, objectID);
        if(null != dataList&& dataList.size()>0)
            return true;
        return false;
    }

    @Override
    public int batchSetEnable(Material material) {
        return materialMapperC.batchSetEnable(material);
    }

    @Override
    public List<Material> queryByOrder(Material material) {
        return materialMapperC.queryByOrder(material);
    }
}
