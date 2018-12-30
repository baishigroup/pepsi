package com.zking.erp.service.cao;

import com.zking.erp.model.cao.Depot;
import com.zking.erp.util.PageBean;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface IDepotCService {

    /**
     * 添加仓库信息
     * @param depot
     * @return
     */
    int insertDepot(Depot depot);

    /**
     * 根据id修改仓库信息
     * @param depot
     * @return
     */
    int updateDepotById(Depot depot);

    /**
     * 根据ID删除仓库信息
     * @param id
     * @return
     */
    int deleteDepotById(String id);

    /**
     * 批量删除（根据ID）
     * @param depot
     * @return
     */
    int deleteDepotByIds(Depot depot);

    /**
     * 根据条件查询仓库信息
     * @param depot
     * @return
     */
    List<Depot> queryDepotPager(Depot depot, PageBean pageBean);

    /**
     * 查询所有
     * @return
     */
    List<Depot> queryDepotAll();

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