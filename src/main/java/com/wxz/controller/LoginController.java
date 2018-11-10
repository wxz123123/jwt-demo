package com.wxz.controller;

import com.alibaba.fastjson.JSONObject;
import com.wxz.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: LoginController
 * @Description:
 * @Author: 王显政
 * @CreateDate: 2018/11/9 16:00
 * @UpdateUser:
 * @UpdateDate:
 * @Version: 0.0.1
 */
@RestController
@RequestMapping("/loginController")
public class LoginController{
    @Autowired
    private LoginService loginService;

    @GetMapping("/login")
    public String login(String username,String password) throws Exception {
        //1创建返回json
        JSONObject jsonObject=new JSONObject();
        //2 验证登陆
        String token=loginService.login(username,password);
        JSONObject data=new JSONObject();
        data.put("token",token);
        jsonObject.put("msg","success");
        jsonObject.put("code","0");
        jsonObject.put("data",data);
        //3 返回登陆结果
        return jsonObject.toJSONString();
    }
}
