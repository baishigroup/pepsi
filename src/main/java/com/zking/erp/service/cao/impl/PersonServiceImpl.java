package com.zking.erp.service.cao.impl;

import com.zking.erp.mapper.cao.PersonMapperC;
import com.zking.erp.model.cao.Person;
import com.zking.erp.service.cao.IPersonService;
import com.zking.erp.service.cao.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PersonServiceImpl implements IPersonService {

    @Autowired
    private PersonMapperC personMapperC;

    @Transactional(readOnly = true)
    @Override
    public List<Person> queryPersonAll() {
        return personMapperC.queryPersonAll();
    }
}
