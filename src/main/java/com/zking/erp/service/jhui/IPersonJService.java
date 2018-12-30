package com.zking.erp.service.jhui;

import com.zking.erp.model.jhui.Person;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface IPersonJService {

    /**
     * 获取不同类型的经手人
     * @param person
     * @return
     */
    List<Person> queryPersonByType(Person person);

    List<Person> queryPersonAll();

    List<Person> queryPersonByIds(Person person);



}