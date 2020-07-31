package com.qf.controller;

import com.qf.constant.SystemConstant;
import com.qf.dto.GetUpdateCartMsg;
import com.qf.dto.ShoppingCartDto;
import com.qf.service.ShoppingCartService;
import com.qf.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Api(tags = "购物车操作")
@CrossOrigin
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;
    @ApiOperation("添加商品至购物车")
    @GetMapping("api/shoppingCart/addGoodForCart")
    public R addGoodForCart(Integer productId, HttpServletRequest request){
        System.out.println("商品id："+productId);
        String token = request.getHeader(SystemConstant.TOKEN_HEADER);
        return shoppingCartService.insertGoodIntoCart(productId,token);
    }

    @ApiOperation("修改购物车商品数量")
    @GetMapping("api/shoppingCart/updateGoodCount")
    public R updateGoodCount(GetUpdateCartMsg getUpdateCartMsg){
        return shoppingCartService.updateCountOfCart(getUpdateCartMsg);
    }

    @ApiOperation("移除购物车商品")
    @GetMapping("api/shoppingCart/deleteGoodFromCart")
    public R deleteGoodFromCart(Integer shoppingCartId){
        return shoppingCartService.deleteGoodFromCart(shoppingCartId);
    }

    @ApiOperation("查询指定用户的购物车列表")
    @GetMapping("api/shoppingCart/findCartsFromUser")
    public R findCartsFromUser(HttpServletRequest request){
        String token = request.getHeader(SystemConstant.TOKEN_HEADER);
        System.out.println("token："+token);

        return shoppingCartService.findCartByUser(token);
    }


}
