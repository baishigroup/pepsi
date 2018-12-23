package com.zking.erp.service.cao;

import com.zking.erp.model.cao.Person;
import com.zking.erp.model.cao.Supplier;

import java.util.List;

public interface ISupplierService {
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


}