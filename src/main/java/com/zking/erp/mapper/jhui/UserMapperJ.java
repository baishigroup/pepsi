package com.zking.erp.mapper.jhui;

import com.zking.erp.model.jhui.User;
import com.zking.erp.model.jhui.UserBusiness;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserMapperJ {

    /**
     * 登陆
     * @param user
     * @return
     */
    User queryByLogin(User user);

    /**
     * 修改用户密码
     * @param user
     * @return
     */
    int updateById(User user);

    List<User> queryUserByLikePager(User user);

    /**
     * 检查是否重名
     * @param field
     * @param username
     * @param userID
     */
    List<User> checkIsNameExist(String field, String username, String userID);

    int insert(User user);

    User selectById(String id);

    int deleteById(String id);

    int delete(User user);




}