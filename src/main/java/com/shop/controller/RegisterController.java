package com.shop.controller;

import com.shop.mapper.UserMapper;
import com.shop.pojo.User;
import com.shop.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 温明彬
 * @company cn.wyu
 * @Description : //描述
 * @ClassName : RegisterController  //类名
 * @date 2020/10/23 14:16
 */
@Controller
@Api(tags = "用户注册控制器")
public class RegisterController {
    @Autowired
    private UserService userService;
    @RequestMapping("/toregesiter")
    @ApiOperation("跳转到注册页面")
    public String toregesiter(){
        return "register";
    }
    @RequestMapping("/registerUser")
    @ApiOperation("保存用户注册信息，并跳转到用户登录页面")
    public String registerUser(User user){
        user.setUserisadmin(0);
        userService.registerUser(user);
        return "redirect:/login";

    }



}
