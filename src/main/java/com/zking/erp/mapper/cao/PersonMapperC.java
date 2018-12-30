package com.zking.erp.mapper.cao;

import com.zking.erp.model.cao.Person;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonMapperC {

    /**
     * 添加经手人管理
     * @param person
     * @return
     */
    int insertPerson(Person person);

    /**
     * 根据id修改经手人管理
     * @param person
     * @return
     */
    int updatePersonById(Person person);

    /**
     * 根据ID删除经手人管理
     * @param id
     * @return
     */
    int deletePersonById(String id);

    /**
     * 批量删除（根据ID）
     * @param person
     * @return
     */
    int deletePersonByIds(Person person);


    /**
     * 查看所有经手人
     * @return
     */
    List<Person> queryPersonAllPager(Person person);

    /**
     * 判断是否重名
     * @param filedName
     * @param filedValue
     * @param idFiled
     * @param objectID
     * @return
     */
    List<Person> checkIsNameExist(String filedName,String filedValue,String idFiled,String objectID);

    List<Person> queryPersonByIds(Person person);

}