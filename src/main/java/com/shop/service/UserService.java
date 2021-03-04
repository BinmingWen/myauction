package com.shop.service;

import com.shop.pojo.Auction;
import com.shop.pojo.User;

import java.util.List;

/**
 * @author 温明彬
 * @company cn.wyu
 * @Description : //描述
 * @ClassName : UserService  //类名
 * @date 2020/10/23 11:24
 */
public interface UserService {
    List<User> checkLogin(String username,String password);
    void registerUser(User user);
}
