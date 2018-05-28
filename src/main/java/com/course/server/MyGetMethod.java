package com.course.server;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author: huangxiang
 * @create: 2018/5/23 14:46
 * @description:
 */
@RestController
@Api(value = "/", description = "全部的get方法")
public class MyGetMethod {

    /**
     * 获取cookies信息
     * @param response
     * @return
     */
    @RequestMapping(value = "/getCookies", method = RequestMethod.GET)
    @ApiOperation(value = "通过这个方法可以获取到Cookies", httpMethod = "GET")
    public String getCookies(HttpServletResponse response) {
        Cookie cookie = new Cookie("login", "true");
        response.addCookie(cookie);
        return "获得cookies信息成功";
    }

    /**
     * 要求客户端携带cookies访问
     */
    @RequestMapping(value = "/getWithCookies", method = RequestMethod.GET)
    @ApiOperation(value = "要求客户端携带cookies访问", httpMethod = "GET")
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

    /**
     * 开发一个需要携带参数才能方位的get请求
     * 第一种实现方式：url：key=value&key=value
     */
    @RequestMapping(value = "/getWithParam",method = RequestMethod.GET)
    @ApiOperation(value = "要求客户端携带参数才能访问第一种方式",httpMethod = "GET")
    public Map<String,Integer> getList(@RequestParam Integer start,@RequestParam Integer end){
        Map<String,Integer> myList = new HashMap<>();
        myList.put("鞋",400);
        myList.put("衣服",200);
        myList.put("裤子",300);
        return myList;
    }

    /**
     * 第二种实现方式：url：ip:port/getWithParam/10/20
     */
    @RequestMapping(value = "/getWithParam/{start}/{end}",method = RequestMethod.GET)
    @ApiOperation(value = "要求客户端携带参数才能访问第二种方式",httpMethod = "GET")
    public Map<String,Integer> myGetList(@PathVariable Integer start,@PathVariable Integer end){

        Map<String,Integer> myList = new HashMap<>();
        myList.put("鞋",400);
        myList.put("衣服",200);
        myList.put("裤子",300);
        return myList;
    }

}
