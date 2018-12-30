package com.zking.erp.mapper.cao;

import com.zking.erp.model.cao.Supplier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierMapperC {
    /**
     * 新增供应商信息
     * @param supplier
     * @return
     */
    int insertSupplier(Supplier supplier);

    /**
     * 根据id修改供应商信息
     * @param supplier
     * @return
     */
    int updateSupplierById(Supplier supplier);

    /**
     * 根据ID删除供应商信息
     * @param id
     * @return
     */
    int deleteSupplierById(String id);

    /**
     * 批量删除（根据供应商ID）
     * @param supplier
     * @return
     */
    int deleteSupplierByIds(Supplier supplier);

    /**
     * 根据条件查询计量单位
     * @param supplier
     * @return
     */
    List<Supplier> querySupplierPager(Supplier supplier);

    /**
     * 判断是否重名
     * @param filedName
     * @param filedValue
     * @param idFiled
     * @param objectID
     * @return
     */
    List<Supplier> checkIsNameExist(String filedName,String filedValue,String idFiled,String objectID);

    /**
     * 批量启用、禁用
     * @param supplier
     * @return
     */
    int batchSetEnable(Supplier supplier);



}