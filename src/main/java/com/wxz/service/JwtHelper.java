package com.wxz.service;

import com.wxz.entity.User;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: JwtHelper
 * @Description: JWT工具类
 * @Author: 王显政
 * @CreateDate: 2018/11/8 15:57
 * @UpdateUser:
 * @UpdateDate:
 * @Version: 0.0.1
 */
@Slf4j
@Component
public class JwtHelper {
    /**
     * 签名
     */
    @Value("${jwt.secret}")
    private String secret;
    /**
     * token过期时间
     */
    @Value("${jwt.expires}")
    private long expires;

    /**
     * @Description: JsonWebToken 解析
     * @Author: 王显政
     * @CreateDate: 2018/11/8 16:47
     * @UpdateUser:
     * @UpdateDate:
     * @Version: 0.0.1
     * @param token JsonWebToken
     * @return
     */
    public Claims parseJWT(String token) throws Exception {
       try {
           //通过签名解析token
           Claims claims= Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
           return  claims;
       }catch (ExpiredJwtException e){
           log.info("token已过期，请重新登陆："+e.getMessage());
           throw new Exception("token已过期，请重新登陆");
       }catch (UnsupportedJwtException e){
           log.info("token信息不能被解析，请重新登陆："+e.getMessage());
           throw new Exception("token信息不能被解析，请重新登陆");
       }catch (MalformedJwtException e){
           log.info("token格式错误，请重新登陆："+e.getMessage());
           throw new Exception("token格式错误，请重新登陆");
       }catch (SignatureException e){
           log.info("token签名错误，请重新登陆："+e.getMessage());
           throw new Exception("token签名错误，请重新登陆");
       }catch (IllegalArgumentException e){
           log.info("token为空，请重新登陆："+e.getMessage());
           throw new Exception("token为空，请重新登陆");
       }
    }
    /**
     * @Description: 创建JsonWebToken（jwt=header+payload+signature）
     * @Author: 王显政
     * @CreateDate: 2018/11/8 17:05
     * @UpdateUser:
     * @UpdateDate:
     * @Version: 0.0.1
     * @param user 用户基本信息
     * @return
     */
    public String createJWT(User user){
        /**
         * jwt=header+payload+signature
         *1 Header=base64UrlEncode(
         *     {
         *         "alg": "HS256",    #表示加密方式
         *         "typ": "JWT"       #类型为JWT
         *     }
         * #Payload就是Claim,用户存储用户登录信息
         *2 Payload =base64UrlEncode(
         *     {
         *         "iss": "lion1ou JWT",      #该JWT的签发者
         *         "iat": 1441593502,         #iat(issued at): 在什么时候签发的
         *         "exp": 1441594722,         #exp(expires): 什么时候过期，这里是一个Unix时间戳
         *         "aud": "www.example.com",  #接收该JWT的一方
         *         "sub": "lion1ou@163.com"   #主题
         *      })
         * #签名等于base65header+payload，然后和签名一起加密所得
         *3 Signature=HMACSHA256((base64UrlEncode(Header) + "." + base64UrlEncode(Payload)),secret)
         */
        //当前时间
        long currentTimeMillis=System.currentTimeMillis();
        //header
        Map header= new HashMap();
        header.put("alg","HS256");
        header.put("typ","JWT");
        //payload(可自定义加入自己想要存的用户信息，bash64编码是可以解析的，所以不能存密码等敏感信息，以免泄露)
        Map claims= new HashMap();
        claims.put("userId",user.getUserId());
        claims.put("username",user.getUserName());
        claims.put("sex",user.getSex());
        claims.put("age",user.getAge());
        claims.put("iat",new Date(currentTimeMillis));
        claims.put("exp",new Date(currentTimeMillis+expires));
        JwtBuilder builder = Jwts.builder()
                .setHeader(header)
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256,secret);
        return  builder.compact();
    }

}
