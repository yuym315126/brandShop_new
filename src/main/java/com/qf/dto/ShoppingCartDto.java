package com.qf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartDto {
    //购物车id
    private Integer shopCartId;
    //用户id
    private Integer userId;
    //商品id
    private Integer productId;
    //商品颜色
    private String color;
    //商品数量
    private Integer count;
    //尺寸
    private String size;
}
