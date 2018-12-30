package com.zking.erp.service.jhui;

import com.zking.erp.model.jhui.Supplier;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ISupplierJService {

    /**
     * 查找客户信息-下拉框
     * 查找会员信息-下拉框
     * 查找供应商信息-下拉框
     *
     */
    List<Supplier> querySupplierByType(Supplier supplier);

    Supplier selectById(String id);

    int updateById(Supplier record);

    List<Supplier>  queryBySelect_cus(Supplier supplier);

    List<Supplier> queryById(Supplier supplier);

    List<Supplier> queryByTypeByIs(Supplier supplier);

    /**
     * 检查输入名称是否存在
     * @return
     */
    Boolean checkIsNameExist(String filedName, String filedVale, String idFiled, String objectID);

    int insert(Supplier record);





}