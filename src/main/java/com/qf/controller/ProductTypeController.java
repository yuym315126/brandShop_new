package com.qf.controller;

import com.qf.constant.SystemConstant;
import com.qf.dto.OrderDto;
import com.qf.service.OrderService;
import com.qf.service.ProductTypeService;
import com.qf.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
@Api(tags = "商品类型")
@RestController
@CrossOrigin
public class ProductTypeController {

    @Autowired
    private ProductTypeService typeService;

    @ApiOperation("全部商品类型")
    @GetMapping("api/good/findAllGoodType")
    public R findAllGoodType(){
        return typeService.findAllType();
    }



}
