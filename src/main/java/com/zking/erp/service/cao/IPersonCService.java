package com.zking.erp.service.cao;

import com.zking.erp.model.cao.Person;
import com.zking.erp.util.PageBean;

import java.util.List;

public interface IPersonCService {

    /**
     * 添加经手人
     * @param person
     * @return
     */
    int insertPerson(Person person);

    /**
     * 根据id修改经手人
     * @param person
     * @return
     */
    int updatePersonById(Person person);

    /**
     * 根据ID删除经手人
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
    List<Person> queryPersonAllPager(Person person, PageBean pageBean);

    /**
     * 判断是否重名
     * @param filedName
     * @param filedValue
     * @param idFiled
     * @param objectID
     * @return
     */
    Boolean checkIsNameExist(String filedName,String filedValue,String idFiled,String objectID);

    List<Person> queryPersonByIds(Person person);


}