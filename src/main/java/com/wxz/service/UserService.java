package com.wxz.service;

import com.wxz.entity.User;

/**
 * @ClassName: UserService
 * @Description: 用户服务
 * @Author: 王显政
 * @CreateDate: 2018/11/9 15:29
 * @UpdateUser:
 * @UpdateDate:
 * @Version: 0.0.1
 */
public interface UserService {
    User getUser(String username, String password) throws Exception;
}
