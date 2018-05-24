package com.course.server;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @author: huangxiang
 * @create: 2018/5/23 14:46
 * @description:
 */
@RestController
@Api(value = "/",description = "全部的get方法")
public class MyGetMethod {

    @RequestMapping(value = "/getCookies", method = RequestMethod.GET)
    @ApiOperation(value = "通过这个方法可以获取到Cookies",httpMethod = "GET")
    public String getCookies(HttpServletResponse response) {
        Cookie cookie = new Cookie("login", "true");
        response.addCookie(cookie);
        return "获得cookies信息成功";
    }


    //要求客户端携带cookies访问
    @RequestMapping(value = "/getWithCookies", method = RequestMethod.GET)
    @ApiOperation(value = "要求客户端携带cookies访问",httpMethod = "GET")
    public String getWithCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies)) {
            return "必须携带cookies信息";
        }
        for (Cookie cookie : cookies) {
            if ("login".equals(cookie.getName()) && "true".equals(cookie.getValue())) {
                return "这是一个需要携带cookies才能访问的请求";
            }

        }
        return "访问信息中必须携带cookies";
    }
}
