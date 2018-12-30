package com.zking.erp.service.cao;

import com.zking.erp.model.cao.Unit;
import com.zking.erp.util.PageBean;

import java.util.List;

public interface IUnitCService {
    /**
     * 添加计量单位
     * @param unit
     * @return
     */
    int insertUnit(Unit unit);

    /**
     * 根据id修改计量单位
     * @param unit
     * @return
     */
    int updateUnitById(Unit unit);

    /**
     * 根据ID删除计量单位
     * @param id
     * @return
     */
    int deleteUnitById(String id);

    /**
     * 批量删除（根据ID）
     * @param unit
     * @return
     */
    int deleteUnitByIds(Unit unit);

    /**
     * 根据条件查询计量单位
     * @param unit
     * @return
     */
    List<Unit> queryUnitPager(Unit unit, PageBean pageBean);

    /**
     * 判断是否重名
     * @param filedName
     * @param filedValue
     * @param idFiled
     * @param objectID
     * @return
     */
    Boolean checkIsNameExist(String filedName,String filedValue,String idFiled,String objectID);

}