package com.wxz.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: User
 * @Description:
 * @Author: 王显政
 * @CreateDate: 2018/11/9 15:30
 * @UpdateUser:
 * @UpdateDate:
 * @Version: 0.0.1
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -5647042190198956810L;
    /**
     *编号
     */
    private String userId;

    /**
     *姓名
     */
    private String userName;

    /**
     *密码
     */
    private String password;

    /**
     *性别(1男 2女 -1未知)
     */
    private Integer sex;

    /**
     *年龄
     */
    private Integer age;
}
