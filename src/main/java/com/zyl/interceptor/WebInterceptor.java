package com.zyl.interceptor;

import com.zyl.model.UserDomain;
import com.zyl.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

/**
 * Created by z1761 on 2018/10/26.
 */
@Component
public class WebInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        if (null !=cookies&& cookies.length>0) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if ("userCookie".equals(cookie.getName())){
                    String name = cookie.getValue(); //暂定当作userId
                    HttpSession session = request.getSession();
                    UserDomain attribute = (UserDomain)session.getAttribute("_user");
                    //获取session id 和cookie 的存放的id 进行对比

                    if (null !=session && session.getId().equals(name)) {
                        //判断当前用户信息
                        if (null != attribute) {
                            return true;
                        }
                    }
                    //HttpSession session1 = session.getSessionContext().getSession(name);

                   // UserDomain user = userService.findByUserID(name);
                    response.sendRedirect(request.getContextPath()+"/login/login.htm");
                    return false;
                }
            }
        }
        response.sendRedirect(request.getContextPath()+"/login/login.htm");
        System.out.println("登录接口在这");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("返回接口在这");

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("监听也走了");

    }

}
