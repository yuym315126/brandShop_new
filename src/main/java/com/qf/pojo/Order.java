package com.qf.pojo;

import lombok.Data;

/**
 * @program: demo
 * @description:
 * @author: money
 * @create: 2020-07-22 22:23
 */
@Data
public class Order {
    private Integer orderId;
    //订单编号
    private String orderNum;
    private String OrderStatus;
    //订单中的商品合计
    private Double totalPrice;
    private Integer userId;
    //订单详情id
    private String orderItemId;
}
