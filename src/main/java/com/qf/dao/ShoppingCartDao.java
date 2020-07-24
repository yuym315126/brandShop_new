package com.qf.dao;

import com.qf.dto.UpdateShoppingCartDto;
import com.qf.pojo.ShoppingCart;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShoppingCartDao {
    //添加商品至购物车
    int insertGoodIntoCart(ShoppingCart shoppingCart);

    //修改购物车商品数量
    int updateCountOfCart(UpdateShoppingCartDto updateShoppingCartDto);

    //删除购物车商品
    int deleteGoodFromCart(Integer shoppingCartId);

    //查询指定用户的购物车
    List<ShoppingCart> findCartByUser(Integer userId);








}
