package com.qf.service;

import com.qf.dto.UserDto;
import com.qf.dto.UserLoginDto;
import com.qf.vo.R;

public interface UserService {

    //校验手机号是否存在
    R checkPhone(String phone);

    //注册
    R register(UserDto userDto);

    //登录
    R login(UserLoginDto userLoginDto);

    //修改密码
    R updatePass(String pass, String token);

    //找回密码
    R findPass(UserLoginDto userLoginDto);

    //退出登录
    R loginOut(String token);


}
