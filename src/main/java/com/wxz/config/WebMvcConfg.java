package com.wxz.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: WebSecurityConfig
 * @Description:
 * @Author: 王显政
 * @CreateDate: 2018/11/9 15:55
 * @UpdateUser:
 * @UpdateDate:
 * @Version: 0.0.1
 */
@Configuration
public class WebMvcConfg implements WebMvcConfigurer {
    /**
     * 免登录url
     */
    @Value("${excludePathPatterns}")
    private String excludePathPatterns;
    @Autowired
    private LoginHandlerInterceptor loginHandlerInterceptor;
    /**
     * @Description:
     * addInterceptor添加拦截
     * excludePathPatterns排除拦截
     * @Author: 王显政
     * @CreateDate: 2018/11/9 17:53
     * @UpdateUser:
     * @UpdateDate:
     * @Version: 0.0.1
     * @param registry
     * @return
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //获取配置中所有的免登录url集合
        String[] pattens=excludePathPatterns.split(",");
        List<String> excludePathPatterns = Arrays.asList(pattens);
        /**
         * addInterceptor添加拦截
         * excludePathPatterns排除拦截
         */
        registry.addInterceptor(loginHandlerInterceptor).excludePathPatterns(excludePathPatterns);
    }
}
