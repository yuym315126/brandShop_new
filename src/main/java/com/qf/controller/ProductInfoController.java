package com.qf.controller;

import com.qf.service.ProductInfoService;
import com.qf.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "商品展示操作")
@RestController
public class ProductInfoController {
    @Autowired
    private ProductInfoService productInfoService;
    @ApiOperation("查询所有商品")
    @PostMapping("api/product/findAllProducts")
    public R findAllProducts(){
        return productInfoService.findAllGoods();
    }
    @ApiOperation("七日爆款")
    @PostMapping("api/product/findByHot")
    public R findByHot(){
        return productInfoService.findByHot();
    }
    @ApiOperation("季末清仓")
    @PostMapping("api/product/findByLowerOfMonth")
    public R findByLowerOfMonth(){
        return productInfoService.findByLowerOfMonth();
    }
    @ApiOperation("搜索商品")
    @PostMapping("api/product/findByProductLike")
    public R findByProductLike(String findCondition){
        return productInfoService.findByProductLike(findCondition);
    }
    @ApiOperation("根据商品id查询商品详情")
    @PostMapping("api/product/findGoodById")
    public R findGoodById(Integer productId){
        return productInfoService.findGoodById(productId);
    }
    @ApiOperation("根据厂家地址id查询")
    @PostMapping("api/product/findGoodByFactoryAddressId")
    public R findGoodByFactoryAddressId(Integer factoryAddressId){
        return productInfoService.findByFactoryAddressId(factoryAddressId);
    }


}
