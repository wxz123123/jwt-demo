package com.wxz.service.impl;

import com.wxz.entity.User;
import com.wxz.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @ClassName: UserServiceImpl
 * @Description:
 * @Author: 王显政
 * @CreateDate: 2018/11/9 15:38
 * @UpdateUser:
 * @UpdateDate:
 * @Version: 0.0.1
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    /**
     * @Description:
     * @Author: 王显政
     * @CreateDate: 2018/11/9 15:38
     * @UpdateUser:
     * @UpdateDate:
     * @Version: 0.0.1
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @Override
    public User getUser(String username, String password) throws Exception {
        //1 验证用户是否存在
        if(!"abc".equals(username)){
            log.info("用户不存在");
            throw new Exception("用户不存在，请重新输入");
        }
        //2验证密码是否正确
        if ("123456".equals(password)){
            log.info("密码不正确");
            throw new Exception("密码不正确,请重新输入");
        }
        //3 此demo不涉及数据库，所以直接写死一个用户
        User user=new User();
        user.setUserId("2018110900005");
        user.setUserName("abc");
        user.setSex(1);
        user.setAge(20);
        return user;
    }
}
