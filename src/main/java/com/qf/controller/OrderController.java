package com.qf.controller;

import com.qf.constant.SystemConstant;
import com.qf.dto.OrderDto;
import com.qf.service.OrderService;
import com.qf.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: demo
 * @description:
 * @author: money
 * @create: 2020-07-23 07:44
 */
@Api(tags = "订单")
@RestController
@CrossOrigin
public class OrderController {
    @Autowired
    private OrderService orderService;

    @ApiOperation("全部订单")
    @GetMapping("api/order/showAllOrders")
    public R showAllOrders(HttpServletRequest request){
        String token = request.getHeader(SystemConstant.TOKEN_HEADER);
        System.out.println("controller"+"======="+token);
        return orderService.selectAllOrders(token);
    }

    @ApiOperation("新增订单")
    @PostMapping("api/order/addOrder")
    public R addOrder(@RequestBody OrderDto orderDto, HttpServletRequest request){
        String token = request.getHeader(SystemConstant.TOKEN_HEADER);
        return orderService.addOrder(orderDto,token);
    }
    @ApiOperation("确认订单")
    @GetMapping("api/order/checkOrder")
    public R checkOrder(Integer productId,HttpServletRequest request){
        String token = request.getHeader(SystemConstant.TOKEN_HEADER);
        return orderService.checkOrder(productId,token);
    }

}
