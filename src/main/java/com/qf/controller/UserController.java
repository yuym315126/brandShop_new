package com.qf.controller;

import com.qf.constant.SystemConstant;
import com.qf.dto.UserDto;
import com.qf.dto.UserLoginDto;
import com.qf.service.UserService;
import com.qf.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(tags = "用户相关操作")
@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation("校验手机号")
    @GetMapping("api/user/checkPhone/{userPhone}")
    public R checkPhone(@PathVariable("userPhone") String userPhone){
        return userService.checkPhone(userPhone);
    }

    @ApiOperation("注册")
    @PostMapping("api/user/register")
    public R save(@RequestBody UserDto userDto){
        return userService.register(userDto);
    }

    @ApiOperation("登录")
    @PostMapping("api/user/login")
    public R login(@RequestBody UserLoginDto userLoginDto){
        return userService.login(userLoginDto);
    }

    @ApiOperation("修改密码")
    @PostMapping("api/user/updatePass/{pass}")
    public R updatePass(@PathVariable String pass, HttpServletRequest request){
        String token = request.getHeader(SystemConstant.TOKEN_HEADER);
        return userService.updatePass(pass,token);
    }

    @ApiOperation("找回密码")
    @PostMapping("api/user/updatePass")
    public R findPass(@RequestBody UserLoginDto userLoginDto){
        return userService.findPass(userLoginDto);
    }

    @ApiOperation("注销")
    @PostMapping("api/user/loginOut")
    public R loginOut(HttpServletRequest request){
        return userService.loginOut(request.getHeader(SystemConstant.TOKEN_HEADER));
    }

    @ApiOperation("用户详情")
    @GetMapping("api/user/findUser")
    public R findUser(HttpServletRequest request){
        String token = request.getHeader(SystemConstant.TOKEN_HEADER);
        return userService.findUser(token);
    }
}
