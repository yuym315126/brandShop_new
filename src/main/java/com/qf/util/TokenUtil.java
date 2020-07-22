package com.qf.util;

import java.util.UUID;

/**
 * @program: brandshop
 * @description: 生成唯一的令牌 组成格式：uuid+userId
 * @author: money
 * @create: 2020-07-18 15:58
 */

public class TokenUtil {
    public static String createToken(int userId){
        return UUID.randomUUID().toString().replace("-","")+userId;
    }
    public static String createProductToken(int productId){
        return UUID.randomUUID().toString().replace("-","")+productId;
    }

}
