package com.zking.erp.service.jhui;

import com.zking.erp.model.jhui.Role;
import com.zking.erp.util.PageBean;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface IRoleJService {

    List<Role> queryRolePager(Role role, PageBean pageBean);

    int insert(Role record);

    Role selectById(String id);

    int updateById(Role record);

    int deleteById(String id);

    int delete(Role role);

    /**
     * 检查输入名称是否存在
     * @return
     */
    Boolean checkIsNameExist(String filedName, String filedVale, String idFiled, String objectID);



}