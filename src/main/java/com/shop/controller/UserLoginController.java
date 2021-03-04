package com.shop.controller;

import com.shop.pojo.Auction;
import com.shop.pojo.User;
import com.shop.service.AuctionService;
import com.shop.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author 温明彬
 * @company cn.wyu
 * @Description : //描述
 * @ClassName : UserController  //类名
 * @date 2020/10/23 11:12
 */
@Controller
@Api(tags = "用户登录控制器")
public class UserLoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuctionService auctionService;
    @RequestMapping("/doLogin")
    @ApiOperation(value = "检测用户登录身份")
    public String checkLogin(@ApiParam(value = "用户姓名") String username,
                             @ApiParam("用户登录密码") String userPassword,
                             @ApiParam("验证码") String valideCode,
                             @ApiParam("session域") HttpSession session,
                             @ApiParam("model请求域") Model model){
        //取出系统的验证码
        String checkCode =  (String)session.getAttribute("vrifyCode");
        //校验验证码
        if(checkCode.equals(valideCode)){
            //验证码输入正确
            //验证用户名和密码
            if(username !=null && userPassword!=null){
                List<User> users = userService.checkLogin(username, userPassword);
                if(users!=null && users.size()>0){
                    session.setAttribute("user",users.get(0));
                    return "redirect:/queryAllUsers";
                }
                model.addAttribute("errorMsg","用户不存在");
                return "login";
            }

            return "index";
        }else{
            model.addAttribute("errorMsg","验证码不正确");
            return "login";
        }

    }
}
