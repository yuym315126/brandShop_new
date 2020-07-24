package com.qf.controller;

import com.qf.dto.AddOrderItemDto;
import com.qf.service.OrderItemService;
import com.qf.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: demo
 * @description:
 * @author: money
 * @create: 2020-07-23 22:05
 */
@Api(tags = "订单详情")
@RestController
@CrossOrigin
public class OrderItemController {
    @Autowired
    private OrderItemService orderItemService;

    @ApiOperation("订单详细信息")
    @GetMapping("api/orderitem/showOrderItem/{orderId}")
    public R showOrderItem(@PathVariable Integer orderId){
        return orderItemService.findOrderItems(orderId);
    }
    @ApiOperation("添加订单详情")
    @GetMapping("api/orderitem/addOrderItem")
    public R addOrderItem(AddOrderItemDto addOrderItemDto){
        return orderItemService.addOrderItem(addOrderItemDto);
    }

}
