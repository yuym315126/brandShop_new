package com.qf.controller;

import com.qf.service.ProductInfoService;
import com.qf.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(tags = "商品展示操作")
@RestController
@CrossOrigin
public class ProductInfoController {
    @Autowired
    private ProductInfoService productInfoService;
    @ApiOperation("查询所有商品")
    @GetMapping("api/product/findAllProducts")
    public R findAllProducts(){
        return productInfoService.findAllGoods();
    }
    @ApiOperation("七日爆款")
    @GetMapping("api/product/findByHot")
    public R findByHot(){
        return productInfoService.findByHot();
    }
    @ApiOperation("季末清仓")
    @GetMapping("api/product/findByLowerOfMonth")
    public R findByLowerOfMonth(){
        return productInfoService.findByLowerOfMonth();
    }
    @ApiOperation("搜索商品")
    @GetMapping("api/product/findByProductLike")
    public R findByProductLike(HttpServletRequest request){
        String findCondition = request.getParameter("findCondition");
        System.out.println("搜索条件"+findCondition);
        return productInfoService.findByProductLike(findCondition);
    }
    @ApiOperation("根据商品id查询商品详情")
    @GetMapping("api/product/findGoodById")
    public R findGoodById(Integer productId){
        System.out.println("商品id"+productId);
        return productInfoService.findGoodById(productId);
    }
    @ApiOperation("根据厂家地址id查询")
    @GetMapping("api/product/findGoodByFactoryAddressId")
    public R findGoodByFactoryAddressId(Integer factoryAddressId){
        if(null == factoryAddressId){
           return productInfoService.findAllGoods();
        }else{
            return productInfoService.findByFactoryAddressId(factoryAddressId);

        }
    }
    @ApiOperation("根据商品类型id查询")
    @GetMapping("api/product/findGoodByTypeId")
    public R findGoodByTypeId(Integer typeId){
        System.out.println("typeId是:"+typeId);
        if(null == typeId){
            return productInfoService.findAllGoods();
        }else{
            return productInfoService.findByGoodsType(typeId);

        }
    }


}
