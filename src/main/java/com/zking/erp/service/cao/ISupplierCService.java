package com.zking.erp.service.cao;

import com.zking.erp.model.cao.Supplier;
import com.zking.erp.util.PageBean;

import java.io.File;
import java.io.InputStream;
import java.util.List;

public interface ISupplierCService {
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
    List<Supplier> querySupplierPager(Supplier supplier, PageBean pageBean);

    /**
     * 判断是否重名
     * @param filedName
     * @param filedValue
     * @param idFiled
     * @param objectID
     * @return
     */
    Boolean checkIsNameExist(String filedName,String filedValue,String idFiled,String objectID);

    /**
     * 批量启用、禁用
     * @param supplier
     * @return
     */
    int batchSetEnable(Supplier supplier);

    /**
     * 导入
     * @param assetFile
     * @return
     */
    InputStream importExcel(File assetFile);

    /**
     * 导出
     * @param isAllPage
     * @param supplier
     * @return
     */
    InputStream exmportExcel(String isAllPage, List<Supplier> supplier);

}