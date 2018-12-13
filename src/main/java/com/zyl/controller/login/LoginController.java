package com.zyl.controller.login;

import com.mysql.jdbc.StringUtils;
import com.zyl.common.CustomResult;
import com.zyl.common.URLMapping;
import com.zyl.model.UserDomain;
import com.zyl.service.UserService;
import com.zyl.vo.Res.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.transform.Result;
import java.util.Arrays;
import java.util.List;

/** 登陆controller
 * Created by z1761 on 2018/11/16.
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Value("${SMB.domain}")
    private String domain;
    @Value("${SMB.path}")
    private String path;
    @Value("${SMB.cookies}")
    private String smbCookies;
    /**
     * 跳转到登陆界面
     */
    @RequestMapping(URLMapping.DEMO_LOGIN_TOLOGIN)
    public String toLogin(){

        return "login";
    }
    /**
     * 跳转到注册界面
     */
    @RequestMapping(URLMapping.DEMO_LOGIN_TOREGISTERED)
    public String toRegistered(){
        return "registered";
    }

    /**
     * 登陆
     * @param userVo
     * @return
     */
    @RequestMapping(URLMapping.DEMO_LOGIN_ENTER)
    @ResponseBody
    public CustomResult login(HttpServletRequest request, HttpServletResponse response, UserVo userVo){
        CustomResult<Object> res = new CustomResult<>();
        if (null !=userVo&& !StringUtils.isNullOrEmpty(userVo.getPhone())) {
            UserDomain userDomain = new UserDomain();
            BeanUtils.copyProperties(userVo,userDomain);
            UserDomain user = userService.findByUserObject(userDomain);
            if (null!=user) {
                if (!userVo.getPhone().equals(user.getPhone())) {
                    res.setCode(1);
                    res.setMsg("手机号码错误");
                    return res;
                }
                if (!userVo.getPassword().equals(user.getPassword())) {
                    res.setCode(1);
                    res.setMsg("密码错误");
                    return res;
                }
                HttpSession session = request.getSession();
                String id = session.getId();
                Cookie[] cookies = request.getCookies();

                boolean userCookie = false;
                if (null != cookies) {
                    List<Cookie> cookies1 = Arrays.asList(cookies);
                    userCookie = cookies1.contains(smbCookies);
                }
                if (userCookie) {
                    for (int i = 0; i <cookies.length ; i++) {
                        Cookie cookie = cookies[i];
                        if (smbCookies.equals(cookie.getName())) {
                            cookie.setDomain(domain);
                            cookie.setPath("/");
                            cookie.setValue(id);
                            response.addCookie(cookie);
                        }
                    }
                } else {
                    Cookie cookie = new Cookie(smbCookies,id);
                    cookie.setDomain(domain);
                    cookie.setPath(path);
                    response.addCookie(cookie);
                }
                session.setAttribute("_user",user);
                session.setMaxInactiveInterval(900);//设置过期时间 单位/秒
                return res;
            }else {
                res.setCode(1);
                res.setMsg("没有此用户 建议注册");
            }

        }
        return res;
    }

    /**
     * 提交注册信息
     * @param userVo
     * @return
     */
    @RequestMapping(URLMapping.DEMO_LOGIN_SAVEREGISTERED)
    @ResponseBody
    public CustomResult saveUser(UserVo userVo){
        CustomResult customResult = new CustomResult();
        if (null !=userVo&& !StringUtils.isNullOrEmpty(userVo.getPhone())) {
            UserDomain userDomain = new UserDomain();
            BeanUtils.copyProperties(userVo, userDomain);
            int i = userService.addUser(userDomain);

        }
        return customResult;
    }

    /**
     * 退出
     * @param request
     * @param response
     */
    @RequestMapping(URLMapping.DEMO_LOGIN_LOGOUT)
    public void logout(HttpServletRequest request, HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            Cookie cookie = cookies[i];
            if (cookie.getName().equals(smbCookies)) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
    }

}
