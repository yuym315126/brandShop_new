package com.qf.controller;

import com.qf.dto.GetUpdateCartMsg;
import com.qf.dto.ShoppingCartDto;
import com.qf.service.ShoppingCartService;
import com.qf.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "购物车操作")
@CrossOrigin
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;
    @ApiOperation("添加商品至购物车")
    @PostMapping("api/shoppingCart/addGoodForCart")
    public R addGoodForCart(ShoppingCartDto shoppingCartDto){
        return shoppingCartService.insertGoodIntoCart(shoppingCartDto);
    }

    @ApiOperation("修改购物车商品数量")
    @PostMapping("api/shoppingCart/updateGoodCount")
    public R updateGoodCount(GetUpdateCartMsg getUpdateCartMsg){
        return shoppingCartService.updateCountOfCart(getUpdateCartMsg);
    }

    @ApiOperation("移除购物车商品")
    @PostMapping("api/shoppingCart/deleteGoodFromCart")
    public R deleteGoodFromCart(Integer shoppingCartId){
        return shoppingCartService.deleteGoodFromCart(shoppingCartId);
    }

    @ApiOperation("查询指定用户的购物车列表")
    @PostMapping("api/shoppingCart/findCartsFromUser")
    public R findCartsFromUser(Integer userId){
        return shoppingCartService.findCartByUser(userId);
    }


}
