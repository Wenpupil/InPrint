package com.example.inprint.util;

import com.example.inprint.bean.User;

import org.litepal.LitePal;

/*
 * @Author Wenpupil
 * @Time 2019.09.02
 * @Description 获取手机的用户信息
 */
public class InfoUtil {
    public static User getUserInfo(){
        User user= LitePal.findFirst(User.class);
        return user;
    }
    public static User testUserInfo(){
        User user=new User();
        user.setAid("admin");
        user.setAtoken("123456");
        return user;
    }
}
