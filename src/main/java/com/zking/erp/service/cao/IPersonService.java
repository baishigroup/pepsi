package com.zking.erp.service.cao;

import com.zking.erp.model.cao.Person;

import java.util.List;

public interface IPersonService {

    /**
     * 查看所有经手人
     * @return
     */
    List<Person> queryPersonAll();

}