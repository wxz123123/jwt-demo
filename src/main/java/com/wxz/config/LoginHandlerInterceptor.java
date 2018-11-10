package com.wxz.config;

import com.wxz.service.JwtHelper;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;

/**
 * @ClassName: LoginHandlerInterceptor
 * @Description: 登陆拦截器
 * @Author: 王显政
 * @CreateDate: 2018/11/9 18:03
 * @UpdateUser:
 * @UpdateDate:
 * @Version: 0.0.1
 */
@Configuration
@Slf4j
public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtHelper jwtHelper;
    /**
     * @Description: 请求处理之前进行调用(Controller方法调用之前)// 只有返回true才会继续向下执行,返回false取消当前请求
     * @Author: 王显政
     * @CreateDate: 2018/11/9 18:11
     * @UpdateUser:
     * @UpdateDate:
     * @Version: 0.0.1
     * @param request
     * @param response
     * @param handler
     * @return
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1 取出token信息
        String token=request.getHeader(HttpHeaders.AUTHORIZATION);
        if (null ==token || StringUtils.isEmpty(token)) {
            throw new Exception("token为空");
        }
        try {
            //2 校验token
            Claims claims=jwtHelper.parseJWT(token);
            //取出用户信息
            String username= String.valueOf(claims.get("username"));
            String userId= String.valueOf(claims.get("userId"));
            Integer sex= (Integer) claims.get("sex");
            Integer age= (Integer) claims.get("sex");
            request.setAttribute("username",username);
            request.setAttribute("userId",userId);
            request.setAttribute("sex",sex);
            request.setAttribute("age",age);
            return true;
        }catch (Exception e){
            log.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }
    /**
     * @Description: 请求处理之后进行调用(Controller方法调用之后)
     * @Author: 王显政
     * @CreateDate: 2018/11/9 18:11
     * @UpdateUser:
     * @UpdateDate:
     * @Version: 0.0.1
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @return
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }
    /**
     * @Description: 在整个请求结束之后被调用,也就是在DispatcherServlet 渲染了对应的视图之后执行(主要是用于进行资源清理工作)");
     * @Author: 王显政
     * @CreateDate: 2018/11/9 18:14
     * @UpdateUser:
     * @UpdateDate:
     * @Version: 0.0.1
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @return
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
