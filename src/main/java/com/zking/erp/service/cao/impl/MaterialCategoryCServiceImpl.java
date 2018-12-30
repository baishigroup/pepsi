package com.zking.erp.service.cao.impl;

import com.zking.erp.mapper.cao.MaterialCategoryMapperC;
import com.zking.erp.model.cao.MaterialCategory;
import com.zking.erp.service.cao.IMaterialCategoryCService;
import com.zking.erp.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MaterialCategoryCServiceImpl implements IMaterialCategoryCService {
    @Autowired
    private MaterialCategoryMapperC materialCategoryMapperC;

    @Override
    public int insertMaterialCate(MaterialCategory materialCategory) {
        return materialCategoryMapperC.insertMaterialCate(materialCategory);
    }

    @Override
    public int updateMaterialCateById(MaterialCategory materialCategory) {
        return materialCategoryMapperC.updateMaterialCateById(materialCategory);
    }

    @Override
    public int deleteMaterialCateById(String id) {
        return materialCategoryMapperC.deleteMaterialCateById(id);
    }

    @Override
    public int deleteMaterialCateByIds(MaterialCategory materialCategory) {
        return materialCategoryMapperC.deleteMaterialCateByIds(materialCategory);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MaterialCategory> queryMaterialCatePager(MaterialCategory materialCategory, PageBean pageBean) {
        return materialCategoryMapperC.queryMaterialCatePager(materialCategory);
    }

    @Override
    public Boolean checkIsNameExist(String filedName, String filedValue, String idFiled, String objectID) {
        List<MaterialCategory> dataList = materialCategoryMapperC.checkIsNameExist(filedName, filedValue, idFiled, objectID);
        if(null != dataList&& dataList.size()>0)
            return true;
        return false;
    }
}
