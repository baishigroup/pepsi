package com.zking.erp.service.jhui.impl;

import com.zking.erp.mapper.jhui.PersonMapperJ;
import com.zking.erp.model.jhui.Person;
import com.zking.erp.service.jhui.IPersonJService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PersonJServiceImpl implements IPersonJService {

    @Autowired
    private PersonMapperJ personMapperJ;

    @Transactional(readOnly = true)
    @Override
    public List<Person> queryPersonByType(Person person) {
        return personMapperJ.queryPersonByType(person);
    }

    @Override
    public List<Person> queryPersonAll() {
        return personMapperJ.queryPersonAll();
    }

    @Override
    public List<Person> queryPersonByIds(Person person) {
        String[] split = person.getPersonIDs().split(",");
        person.setIds(split);
        return personMapperJ.queryPersonByIds(person);
    }
}
