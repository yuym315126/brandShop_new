package com.qf.config;

/**
 * @program: brandshop
 * @description:
 * @author: money
 * @create: 2020-07-18 14:07
 */
public class RedisKeyConfig {
    //令牌
    public static final String PHONE_TOKEN="brandshop:phone";//后面追加手机号 值存储令牌

    public static final String TOKEN_USER="brandshop:token";//后面追加token  值存储用户信息

    public static final String PHONE_FREEZE="brandshop:freeze:phone";//后面追加手机号 冻结的账号

    public static final String PHONE_ERROR="brand:error:phone";//后面跟手机号和时间戳

    public static final String TOKEN_PRODUCT="brandshop:product";

    //设置令牌的有效期 单位秒
    public static final int TOKEN_TIME=30*60; //登录成功后令牌的有效期

    public static final int TOKEN_FREEZE_TIME=30*60;//用户账号密码输错三次后冻结的有效期

    public static final int PHONE_ERROR_TIME =10*60;//记录用户登录错误的有效期
}
