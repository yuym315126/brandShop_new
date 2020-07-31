package com.qf.service;

import com.qf.dto.GetUpdateCartMsg;
import com.qf.dto.ShoppingCartDto;
import com.qf.pojo.ShoppingCart;
import com.qf.vo.R;

import java.util.List;

public interface ShoppingCartService {
    //添加商品至购物车
    R insertGoodIntoCart(Integer productId,String token);

    //修改购物车商品数量
    R updateCountOfCart(GetUpdateCartMsg getUpdateCartMsg);

    //删除购物车商品
    R deleteGoodFromCart(Integer shoppingCartId);

    //查询指定用户的购物车
    R findCartByUser(String token);
}
