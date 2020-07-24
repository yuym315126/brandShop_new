package com.qf.dto;

import lombok.Data;

/**
 * @program: demo
 * @description:
 * @author: money
 * @create: 2020-07-23 21:05
 */
@Data
public class OrderItemsDto {
    private String addressee;
    private String addPhone;
    private String area;
    private String street;
    private String productName;
    //颜色
    private String color;
    //价格
    private Double productPrice;
    //图片
    private String picture;
    //订单的总价格
    private Double totalPrice;
    //订单编号
    private String orderNum;

}
