package com.qf.pojo;

import lombok.Data;

@Data
public class ShoppingCart {
    private Integer id;
    private String color;
    private String size;
    private Integer count;
    private Double price;
    private Integer userId;
//    private Integer goodId;
    private String productName;
    private String productImg;

}
