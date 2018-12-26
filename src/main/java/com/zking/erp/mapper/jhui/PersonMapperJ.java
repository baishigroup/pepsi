package com.zking.erp.mapper.jhui;

import com.zking.erp.model.jhui.Person;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonMapperJ {

    /**
     * 获取不同类型的经手人
     * @param person
     * @return
     */
    List<Person> queryPersonByType(Person person);

}