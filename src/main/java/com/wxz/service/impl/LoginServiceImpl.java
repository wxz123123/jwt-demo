package com.wxz.service.impl;

import com.wxz.entity.User;
import com.wxz.service.JwtHelper;
import com.wxz.service.LoginService;
import com.wxz.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: LoginServiceImpl
 * @Description:
 * @Author: 王显政
 * @CreateDate: 2018/11/9 15:36
 * @UpdateUser:
 * @UpdateDate:
 * @Version: 0.0.1
 */
@Service
@Slf4j
public class LoginServiceImpl implements LoginService {
    @Autowired
    private JwtHelper jwtHelper;
    @Autowired
    private UserService userService;
    /**
     * @Description:
     * @Author: 王显政
     * @CreateDate: 2018/11/8 20:02
     * @UpdateUser:
     * @UpdateDate:
     * @Version: 0.0.1
     * @param username
     * @param password
     * @return
     */
    @Override
    public String login(String username, String password) throws Exception {
        //1 验证登陆用户信息
        User user=userService.getUser(username,password);
        //2创建JsonWebToken并返回给前段
        String token=jwtHelper.createJWT(user);
        return token;
    }
}
