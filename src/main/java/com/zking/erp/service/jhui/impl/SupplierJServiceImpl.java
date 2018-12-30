package com.zking.erp.service.jhui.impl;

import com.zking.erp.mapper.jhui.SupplierMapperJ;
import com.zking.erp.model.jhui.Supplier;
import com.zking.erp.service.jhui.ISupplierJService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SupplierJServiceImpl implements ISupplierJService {

    @Autowired
    private SupplierMapperJ supplierMapperJ;

    @Transactional(readOnly =  true)
    @Override
    public List<Supplier> querySupplierByType(Supplier supplier) {
        return supplierMapperJ.querySupplierByType(supplier);
    }

    @Transactional
    @Override
    public Supplier selectById(String id) {
        return supplierMapperJ.selectById(id);
    }

    @Transactional
    @Override
    public int updateById(Supplier record) {
        return supplierMapperJ.updateById(record);
    }

    @Override
    public List<Supplier> queryBySelect_cus(Supplier supplier) {
        return supplierMapperJ.queryBySelect_cus(supplier);
    }

    @Override
    public List<Supplier> queryById(Supplier supplier) {
        return supplierMapperJ.queryById(supplier);
    }

    @Override
    public List<Supplier> queryByTypeByIs(Supplier supplier) {
        return supplierMapperJ.queryByTypeByIs(supplier);
    }

    @Override
    public Boolean checkIsNameExist(String filedName, String filedVale, String idFiled, String objectID) {
        List<Supplier> dataList = supplierMapperJ.checkIsNameExist(filedName, filedVale, idFiled, objectID);
        if (null != dataList && dataList.size() > 0)
            return true;
        return false;
    }

    @Override
    public int insert(Supplier record) {
        return supplierMapperJ.insert(record);
    }
}
