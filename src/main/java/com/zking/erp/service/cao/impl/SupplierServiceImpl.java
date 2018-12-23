package com.zking.erp.service.cao.impl;

import com.zking.erp.mapper.cao.SupplierMapperC;
import com.zking.erp.model.cao.Supplier;
import com.zking.erp.service.cao.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SupplierServiceImpl implements ISupplierService {

    @Autowired
    private SupplierMapperC supplierMapperC;

    @Override
    public int insertSupplier(Supplier supplier) {
        return supplierMapperC.insertSupplier(supplier);
    }

    @Override
    public int updateSupplierById(Supplier supplier) {
        
        return 0;
    }

    @Override
    public int deleteSupplierById(String id) {
        return 0;
    }

    @Override
    public int deleteSupplierByIds(Supplier supplier) {
        return 0;
    }

    @Override
    public List<Supplier> querySupplierPager(Supplier supplier) {
        return null;
    }
}
