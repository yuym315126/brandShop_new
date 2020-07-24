package com.qf.pojo;

import lombok.Data;

/**
 * @program: demo
 * @description:
 * @author: money
 * @create: 2020-07-23 20:41
 */
@Data
public class OrderItems {
    private String itemsId;
    private String itemsNum;
    private Integer userId;
    private Integer productId;
//    private Integer orderId;
    private Integer addressId;
}
