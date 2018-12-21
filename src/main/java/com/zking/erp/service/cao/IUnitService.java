package com.zking.erp.service.cao;

import com.zking.erp.model.cao.Unit;
import com.zking.erp.util.PageBean;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface IUnitService {
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
     * 根据条件查询计量单位
     * @param unit
     * @return
     */
    List<Unit> queryUnitPager(Unit unit, PageBean pageBean);

}