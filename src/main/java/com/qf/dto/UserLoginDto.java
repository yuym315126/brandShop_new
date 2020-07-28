package com.qf.dto;

import lombok.Data;

/**
 * @program: brandshop
 * @description: 用户登录
 * @author: money
 * @create: 2020-07-18 11:31
 */
@Data
public class UserLoginDto {
    private String userPhone;
    private String userPass;
}
