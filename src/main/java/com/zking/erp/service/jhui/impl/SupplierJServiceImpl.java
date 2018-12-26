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
}
