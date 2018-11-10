package com.wxz.config;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @ClassName: MyControllerAdvice
 * @Description: 全局异常处理
 * @Author: 王显政
 * @CreateDate: 2018/11/9 19:06
 * @UpdateUser:
 * @UpdateDate:
 * @Version: 0.0.1
 */
@ControllerAdvice
public class MyControllerAdvice {
    /**
     * @Description: 系统异常处理
     * @Author: 王显政
     * @CreateDate: 2018/11/9 19:07
     * @UpdateUser:
     * @UpdateDate:
     * @Version: 0.0.1
     * @param
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public String exceptionHandle(Exception e){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("code",400);
        jsonObject.put("msg",e.getMessage());
        return jsonObject.toString();
    }
}
