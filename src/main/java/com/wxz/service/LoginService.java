package com.wxz.service;

/**
 * @ClassName: LoginService
 * @Description: 登陆服务
 * @Author: 王显政
 * @CreateDate: 2018/11/8 20:00
 * @UpdateUser:
 * @UpdateDate:
 * @Version: 0.0.1
 */
public interface LoginService {
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
     String login(String username,String password) throws Exception;
}
