package com.zyl.controller;

import com.github.pagehelper.PageInfo;
import com.zyl.model.UserDomain;
import com.zyl.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by z1761 on 2018/10/10.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;
    private final Logger log = LoggerFactory.getLogger(UserController.class);
    @ResponseBody
    @PostMapping("/add")
    public int addUser(UserDomain user){
        return userService.addUser(user);
    }

    @ResponseBody
    @GetMapping("/all")
    public Object findAllUser(
            @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                    int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10")
                    int pageSize) throws Exception {
        PageInfo<UserDomain> allUser = null;
        Exception ss= new Exception();
        List<String > str = new ArrayList<>();

        try {
            allUser = userService.findAllUser(pageNum, pageSize);
            String s = str.get(10);
        }catch (Exception e) {
            log.error("error","错误信息报错啦");

        }

        return allUser;
    }

    @ResponseBody
    @GetMapping("/checkObject")
    public void testchecked(@Valid UserDomain user, BindingResult result) throws  Exception{
        //当出现多个错误时候 怎么处理 待搜索
        if (result.hasErrors()) {


        }
        System.out.println(result.getFieldError().getDefaultMessage());
        Map<String, Object> model = result.getModel();

        System.out.println(model);

    }

}
