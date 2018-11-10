package com.wxz.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: UserController
 * @Description: 用户控制器
 * @Author: 王显政
 * @CreateDate: 2018/11/9 19:23
 * @UpdateUser:
 * @UpdateDate:
 * @Version: 0.0.1
 */
@RestController
@RequestMapping("/userController")
public class UserController extends BaseController{
    @GetMapping("/getUser")
    public String getUser(){
        JSONObject jsonObject=new JSONObject();
        JSONObject data=new JSONObject();
        jsonObject.put("msg","success");
        jsonObject.put("code","0");
        jsonObject.put("data",data);
        //解析request中的用户信息
        String username=String.valueOf(request.getAttribute("username"));
        String userId=String.valueOf(request.getAttribute("userId"));
        Integer sex= (Integer) request.getAttribute("sex");
        Integer age= (Integer) request.getAttribute("age");
        data.put("username",username);
        data.put("userId",userId);
        data.put("sex",sex);
        data.put("age",age);
        return  jsonObject.toJSONString();
    }
}
