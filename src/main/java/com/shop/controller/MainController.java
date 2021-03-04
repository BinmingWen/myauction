package com.shop.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 温明彬
 * @company cn.wyu
 * @Description : //描述
 * @ClassName : MainController  //类名
 * @date 2020/10/23 10:17
 */
@Controller
@Api(tags = "主控制器")
public class MainController {
    @RequestMapping("/login")
    @ApiOperation("跳转到用户注册页面")
    public String login(){
        return "login";
    }



}
