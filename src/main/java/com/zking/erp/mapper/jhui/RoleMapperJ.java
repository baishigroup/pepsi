package com.zking.erp.mapper.jhui;

import com.zking.erp.model.jhui.App;
import com.zking.erp.model.jhui.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapperJ {

    List<Role> queryRolePager(Role role);

    int insert(Role record);

    Role selectById(String id);

    int updateById(Role record);

    int deleteById(String id);

    int delete(Role role);

    /**
     * 检查输入名称是否存在
     * @return
     */
    List<Role> checkIsNameExist(String filedName, String filedVale, String idFiled, String objectID);







}