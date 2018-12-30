package com.zking.erp.mapper.cao;

import com.zking.erp.model.cao.InoutItem;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InoutItemMapperC {

    /**
     * 添加收支项目
     * @param inoutItem
     * @return
     */
    int insertInoutItem(InoutItem inoutItem);

    /**
     * 根据id修改收支项目
     * @param inoutItem
     * @return
     */
    int updateInoutItemById(InoutItem inoutItem);

    /**
     * 根据ID删除收支项目
     * @param id
     * @return
     */
    int deleteInoutItemById(String id);

    /**
     * 批量删除（根据ID）
     * @param inoutItem
     * @return
     */
    int deleteInoutItemByIds(InoutItem inoutItem);

    /**
     * 根据条件查询收支项目
     * @param inoutItem
     * @return
     */
    List<InoutItem> queryInoutItemPager(InoutItem inoutItem);

    /**
     * 判断是否重名
     * @param filedName
     * @param filedValue
     * @param idFiled
     * @param objectID
     * @return
     */
    List<InoutItem> checkIsNameExist(String filedName,String filedValue,String idFiled,String objectID);


    List<InoutItem> queryInoutItemBySelect(InoutItem inoutItem);



}