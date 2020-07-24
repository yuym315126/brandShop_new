package com.qf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateShoppingCartDto {
    //价格
    private Double price;
    //商品数量
    private Integer count;
    //购物车Id
    private Integer shopCartId;

}
