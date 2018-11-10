package com.wxz.controller;

import com.wxz.entity.User;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: BaseController
 * @Description: 控制层基础类
 * @Author: 王显政
 * @CreateDate: 2018/11/10 10:52
 * @UpdateUser:
 * @UpdateDate:
 * @Version: 0.0.1
 */
public class BaseController {
    protected HttpServletRequest request;
    protected HttpServletResponse response;

    //@ModelAttribute 该注解的作用：该方法在类中每个方法执行前执行
    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request,HttpServletResponse response){
        this.request=request;
        this.response=response;
    }
}
