package com.zking.erp.service.jhui;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.zking.erp.model.jhui.User;
import com.zking.erp.util.PageBean;

import java.util.List;

public interface IUserJService {

    /**
     * 判断用户名是否符合登录条件
     *
     * @param username 用户名 String password
     * @return int 1、用户名不存在  2、密码不正确  3、黑名单用户  4、符合条件  5、访问后台异常
     */
    int validateUser(String username, String password) ;

    /**
     * 获取用户信息
     *
     * @param username
     * @return 用户信息
     *
     */
    public User getUser(String username)  ;

    /**
     * 修改用户密码
     * @param user
     * @return
     */
    int updateById(User user);

    List<User> queryUserByLikePager(PageBean pageBean,User user);

    /**
     * 检查是否重名
     * @param field
     * @param username
     * @param userID
     */
   Boolean checkIsNameExist(String field, String username, String userID);

    int insert(User user);

    User selectById(String id);

    int deleteById(String id);

    int delete(User user);


}
