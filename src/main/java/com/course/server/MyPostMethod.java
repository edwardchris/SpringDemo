package com.course.server;

import com.course.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: huangxiang
 * @create: 2018/5/28 14:20
 * @description:
 */
@RestController
@Api(value = "/", description = "全部的post方法")
public class MyPostMethod {
    /**
     * 静态变量用来存储cookies信息
     */
    private static Cookie cookie;
    String uName = "zhangsan";
    String pwd = "123456";

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "登录接口，成功后获取cookies", httpMethod = "POST")
    public String login(HttpServletResponse response, @RequestParam(value = "userName", required = true) String userName, @RequestParam(value = "password", required = true) String password) {
        if (uName.equals(userName) && pwd.equals(password)) {
            cookie = new Cookie("login", "true");
            response.addCookie(cookie);
            return "登录成功";
        }
        return "用户名或密码错误";
    }

    @RequestMapping(value = "/getUserList", method = RequestMethod.POST)
    @ApiOperation(value = "获取用户列表", httpMethod = "POST")
    public String getUserList(HttpServletRequest request, @RequestBody User user) {
        User u;
        String key = "login";
        String value = "true";
        String uName = "zhangsan";
        String pwd = "123456";
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (key.equals(cookie.getName()) && value.equals(cookie.getValue()) && uName.equals(user.getUserName()) && pwd.equals(user.getPassword())) {
                u = new User();
                u.setPassword(pwd);
                u.setUserName(uName);
                u.setName("lisi");
                u.setAge("18");
                u.setSex("man");
                return u.toString();
            }
        }
        return "参数不合法";
    }

}
