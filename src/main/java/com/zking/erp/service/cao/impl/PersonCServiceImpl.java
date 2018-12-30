package com.zking.erp.service.cao.impl;

import com.zking.erp.mapper.cao.PersonMapperC;
import com.zking.erp.model.cao.Person;
import com.zking.erp.service.cao.IPersonCService;
import com.zking.erp.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PersonCServiceImpl implements IPersonCService {

    @Autowired
    private PersonMapperC personMapperC;


    @Override
    public int insertPerson(Person person) {
        return personMapperC.insertPerson(person);
    }

    @Override
    public int updatePersonById(Person person) {
        return personMapperC.updatePersonById(person);
    }

    @Override
    public int deletePersonById(String id) {
        return personMapperC.deletePersonById(id);
    }

    @Override
    public int deletePersonByIds(Person person) {
        return personMapperC.deletePersonByIds(person);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Person> queryPersonAllPager(Person person, PageBean pageBean) {
        return personMapperC.queryPersonAllPager(person);
    }

    @Override
    public Boolean checkIsNameExist(String filedName, String filedValue, String idFiled, String objectID) {
        List<Person> dataList = personMapperC.checkIsNameExist(filedName, filedValue, idFiled, objectID);
        if(null != dataList&& dataList.size()>0)
            return true;
        return false;
    }

    @Override
    public List<Person> queryPersonByIds(Person person) {
        String[] split = person.getPersonIDs().split(",");
        person.setIds(split);
        return personMapperC.queryPersonByIds(person);
    }
}
