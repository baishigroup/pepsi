package com.zking.erp.controller.jhui;

import com.zking.erp.base.BaseController;
import com.zking.erp.model.jhui.Log;
import com.zking.erp.model.jhui.User;
import com.zking.erp.service.jhui.IUserJService;
import com.zking.erp.util.PageBean;
import com.zking.erp.util.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.*;

@Controller
@RequestMapping("/user")
public class UserJController extends BaseController{

    @Autowired
    private IUserJService userService;

    private HttpSession getSession;

    /**
     * 需要判断用户状态，用户名密码错误不能登录 ，黑名单用户不能登录，如果已经登录过，不再进行处理，直接进入管理页面
     *
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public Map<String,Object> login(HttpServletRequest request, User user) {
        try {
            getSession = request.getSession();
            String message = "";
            System.out.println("============用户登录 login 方法调用开始==============");
            String username = user.getLoginame().trim();
            String password = user.getPassword().trim();
            //因密码用MD5加密，需要对密码进行转化
            try {
                password = Tools.md5Encryp(password);
                System.out.println(password);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                System.out.println(">>>>>>>>>>>>>>转化MD5字符串错误 ：");
            }

            //判断用户是否已经登录过，登录过不再处理
            if (null != getSession.getAttribute("user")) {
                User sessionUser = (User) getSession.getAttribute("user");
                if (null != sessionUser && username.equalsIgnoreCase(sessionUser.getLoginame())
                        && sessionUser.getPassword().equals(password)) {
                    message = "user already login";
                    System.out.println("====用户 " + username + "已经登录过, login 方法调用结束====");
                    /*return "login";*/
                }
            }

            //获取用户状态
            int userStatus = -1;
            try {
                userStatus = userService.validateUser(username, password);
            } catch (Exception e) {
                System.out.println(">>>>>>>>>>>>>用户  " + username + " 登录 login 方法 访问服务层异常====");
                message = "access service exception";
                e.printStackTrace();
            }
            switch (userStatus) {
                case 1:
                    message = "user is not exist";
                    System.out.println("user is not exist");
                    break;
                case 2:
                    message = "user password error";
                    System.out.println("user password error");
                    break;
                case 3:
                    message = "user is black";
                    System.out.println("user is black");
                    break;
                case 5:
                    message = "access service error";
                    System.out.println("access service error");
                    break;
                default:
                    try {
                        //验证通过 ，可以登录，放入session，记录登录日志
                        User uu = userService.getUser(username);
                    logService.create(new Log(uu.getId(), "登录系统", user.getClientIp(),
                            new Timestamp(System.currentTimeMillis()),  0, "管理用户：" + username + " 登录系统", username + " 登录系统"));
                        message = "user can login";
                        getSession.setAttribute("user", uu);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println(">>>>>>>>>>>>>>>查询用户名为:" + username + " ，用户信息异常");
                    }
                    break;
            }
		/*if(ExceptionCodeConstants.UserExceptionCode.USER_CONDITION_FIT == userStatus)
		    return "login";*/
            System.out.println("===============用户登录 login 方法调用结束===============");
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("message",message);
            return map;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 用户退出登录
     *
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request,User user) {
        getSession = request.getSession();
        logService.create(new Log(getUser(request), "退出系统", user.getClientIp(),
                new Timestamp(System.currentTimeMillis()), (short) 0,
                "管理用户：" + getUser2(request).getLoginame() + " 退出系统", getUser2(request).getLoginame() + " 退出系统"));
        getSession.removeAttribute("user");
        return "pages/common/admin";
    }



    /**
     * 修改密码
     */
    @RequestMapping("/updatePwd")
    @ResponseBody
    public Map<String,Object> updatePwd(HttpServletRequest request,User model) {
        getSession = request.getSession();
        Map<String,Object> map=new HashMap<String, Object>();
        Integer flag = 0;//1.修改成功  2.原始密码错误（失败）  3.修改失败
        try {
            //获取当前登陆用户的信息
            User user =(User)getSession.getAttribute("user");
            //原始密码
            String orgPassword = Tools.md5Encryp(model.getOrgpwd());
            //修改后的密码
            String md5Pwd = Tools.md5Encryp(model.getPassword());
            //必须和原始密码一致才可以更新密码
//            if(user.getLoginame().equals("jsh")){
//                flag = 3;
//                tipMsg = "管理员jsh不能修改密码";
//                tipType = 1;
//            } else
            //判断输入的原始密码是否与真实原始密码相等
            if (orgPassword.equalsIgnoreCase(user.getPassword())) {

                user.setPassword(md5Pwd);
                userService.updateById(user);

                //看是否需要更新seesion中user
//                if(getUser().getId() == model.getUserID())
//                {
//                    getSession().put("user", user);
//                }

                flag = 1;
                tipMsg = "成功";
                tipType = 0;
            } else {
                flag = 2;
                tipMsg = "失败";
                tipType = 1;
            }

        } catch (Exception e) {
            System.out.println(">>>>>>>>>>>>>修改用户ID为 ： " + model.getId() + "密码信息失败");
            flag = 3;
            tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();
        }
                map.put("flag",flag);
        logService.create(new Log(getUser(request), "更新用户", model.getClientIp(),
                new Timestamp(System.currentTimeMillis())
                , tipType, "更新用户ID为  " + model.getUserID() + "密码信息 " + tipMsg + "！", "更新用户" + tipMsg));
        return map;
    }

    /**
     * 增加用户
     *
     * @return
     */
    @RequestMapping("/create")
    @ResponseBody
    public Map<String,Object> create(HttpServletRequest request,User model) {
        System.out.println("==================开始调用增加用户方法===================");
        Boolean flag = false;
        Map<String,Object> map=new HashMap<String, Object>();
        try {
            model.setId(UUID.randomUUID().toString());
            model.setIsmanager(1);
            model.setIsystem(0);
            String password = "123456";
            //因密码用MD5加密，需要对密码进行转化
            try {
                password = Tools.md5Encryp(password);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                System.out.println(">>>>>>>>>>>>>>转化MD5字符串错误 ：" + e.getMessage());
            }
            model.setPassword(password);
            userService.insert(model);
            //========标识位===========
            flag = true;
            //记录操作日志使用
            tipMsg = "成功";
            tipType = 0;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>>>>>>>增加用户异常");
            flag = false;
            tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();
        }
        map.put("flag",flag);

        logService.create(new Log(getUser(request), "增加用户", model.getClientIp(),
                new Timestamp(System.currentTimeMillis())
                , tipType, "增加用户名称为  " + model.getUsername() + " " + tipMsg + "！", "增加用户" + tipMsg));
        System.out.println("==================结束调用增加用户方法===================");
        return map;

    }

    /**
     * 删除用户
     *
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Map<String,Object> delete(HttpServletRequest request,User model) {
        Map<String,Object> map=new HashMap<String, Object>();
        System.out.println("====================开始调用删除用户信息方法delete()================");
        try {
            userService.deleteById(model.getUserID());
            tipMsg = "成功";
            tipType = 0;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>删除ID为 " + model.getUserID() + "  的用户异常");
            tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();
        }
        map.put("message",tipMsg);
        logService.create(new Log(getUser(request), "删除用户", model.getClientIp(),
                new Timestamp(System.currentTimeMillis())
                , tipType, "删除用户ID为  " + model.getUserID() + " " + tipMsg + "！", "删除用户" + tipMsg));
        System.out.println("====================结束调用删除用户信息方法delete()================");
        return map;
    }

    /**
     * 更新用户
     *
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public Map<String,Object>  update(HttpServletRequest request,User model) {
        getSession = request.getSession();
        Boolean flag = false;
        Map<String,Object> map=new HashMap<String, Object>();
        try {
            User user = userService.selectById(model.getUserID());
            user.setDepartment(model.getDepartment());
            user.setDescription(model.getDescription());
            user.setEmail(model.getEmail());
            //user.setIsmanager(model.getIsmanager());
            user.setLoginame(model.getLoginame());
            //user.setPassword(model.getPassword());
            user.setPhonenum(model.getPhonenum());
            user.setPosition(model.getPosition());
            user.setUsername(model.getUsername());
            userService.updateById(user);
            User user1=(User) getSession.getAttribute("user");
            //看是否需要更新seesion中user
            if (user1.getId() == model.getUserID()) {
                getSession.setAttribute("user", user);
            }

            flag = true;
            tipMsg = "成功";
            tipType = 0;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>>>修改用户ID为 ： " + model.getUserID() + "信息失败");
            flag = false;
            tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();
        }
        map.put("flag",flag);

        logService.create(new Log(getUser(request), "更新用户", model.getClientIp(),
                new Timestamp(System.currentTimeMillis())
                , tipType, "更新用户ID为  " + model.getUserID() + " " + tipMsg + "！", "更新用户" + tipMsg));
        return map;
    }


    /**
     * 重置用户的密码
     */
    @RequestMapping("/resetPwd")
    @ResponseBody
    public Map<String,Object> resetPwd(HttpServletRequest request,User model) {
        Integer flag = 0;
        Map<String,Object> map=new HashMap<String, Object>();
        try {
            User user = userService.selectById(model.getUserID());
            String password = "123456";
            String md5Pwd = Tools.md5Encryp(password);
            user.setPassword(md5Pwd);
            userService.updateById(user);
            flag = 1;
            tipMsg = "成功";
            tipType = 0;
        } catch (Exception e) {
            System.out.println(">>>>>>>>>>>>>修改用户ID为 ： " + model.getUserID() + "密码信息失败");
            flag = 0;
            tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();
        }
            map.put("flag",flag);
        logService.create(new Log(getUser(request), "重置用户密码", model.getClientIp(),
                new Timestamp(System.currentTimeMillis()), tipType, "重置用户ID为  " + model.getUserID() + "密码信息 " + tipMsg + "！", "重置用户密码" + tipMsg));
            return map;
    }

    /**
     * 批量删除指定ID用户
     *
     * @return
     */
    @RequestMapping("/batchDelete")
    @ResponseBody
        public Map<String,Object>  batchDelete(HttpServletRequest request,User model) {
        Map<String,Object> map=new HashMap<String, Object>();
        try {
            userService.delete(model);
            map.put("message","成功");
            //记录操作日志使用
            tipMsg = "成功";
            tipType = 0;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>>>批量删除用户ID为：" + model.getUserIDs() + "信息异常");
            tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();
        }

        logService.create(new Log(getUser(request), "批量删除用户", model.getClientIp(),
                new Timestamp(System.currentTimeMillis())
                , tipType, "批量删除用户ID为  " + model.getUserIDs() + " " + tipMsg + "！", "批量删除用户" + tipMsg));
        return map;
    }

    /**
     * 检查输入名称是否存在
     */
    @RequestMapping("/checkIsNameExist")
    @ResponseBody
    public Map<String,Object> checkIsNameExist(User model) {
        Map<String,Object> map=new HashMap<String, Object>();
        Boolean flag = false;
        String fieldName = "";
        String fieldValue = "";
        try {
            if (0 == model.getCheckFlag()) {
                fieldName = "username";
                fieldValue = model.getUsername();
            } else {
                fieldName = "loginame";
                fieldValue = model.getLoginame();
            }
            flag = userService.checkIsNameExist(fieldName, fieldValue, model.getUserID());
        } catch (Exception e) {
            System.out.println(">>>>>>>>>>>>>>>>>检查用户名称为：" + fieldValue + " ID为： " + model.getUserID() + " 是否存在异常！");
        }
        map.put("flag",flag);
        return map;
    }

    /**
     * 查找用户信息
     *
     * @return
     */
    @RequestMapping("/findBy")
    @ResponseBody
    public Map<String,Object> findBy(HttpServletRequest request,User model) {
        try {
                PageBean pageBean=new PageBean();
                pageBean.setRequest(request);
            List<User> dataList = userService.queryUserByLikePager(pageBean,model);

            //开始拼接json数据
//            {"total":28,"rows":[
//                {"productid":"AV-CB-01","attr1":"Adult Male","itemid":"EST-18"}
//            ]}
            Map<String,Object> outer = new HashMap<String, Object>();
            outer.put("total", pageBean.getTotal());
            //存放数据json数组
            List dataArray = new ArrayList();
            if (null != dataList) {
                for (User user : dataList) {

                    Map<String,Object> item = new HashMap<String, Object>();
                    item.put("id", user.getId());
                    item.put("username", user.getUsername());
                    item.put("loginame", Tools.dealNullStr(user.getLoginame()));
                    item.put("password", Tools.dealNullStr(user.getPassword()));
                    item.put("position", Tools.dealNullStr(user.getPosition()));
                    item.put("department", Tools.dealNullStr(user.getDepartment()));
                    item.put("email", Tools.dealNullStr(user.getEmail()));
                    item.put("phonenum", Tools.dealNullStr(user.getPhonenum()));
                    item.put("ismanager", user.getIsmanager() ==  0 ? "是" : "否");
                    item.put("isystem", user.getIsystem() ==  0 ? "是" : "否");
                    item.put("status", user.getStatus());
                    item.put("description", Tools.dealNullStr(user.getDescription()));
                    item.put("remark", user.getRemark());
                    item.put("op", user.getIsmanager());
                    dataArray.add(item);
                }
            }
            outer.put("rows", dataArray);
            //回写查询结果
            return outer;
        } catch (DataAccessException e) {
            System.out.println(">>>>>>>>>查找用户信息异常");
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.out.println(">>>>>>>>>回写查询用户信息结果异常");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }




}
