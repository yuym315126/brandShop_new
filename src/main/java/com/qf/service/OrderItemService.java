package com.qf.service;

import com.qf.dto.AddOrderItemDto;
import com.qf.pojo.OrderItems;
import com.qf.vo.R;

/**
 * @program: demo
 * @description:
 * @author: money
 * @create: 2020-07-23 21:00
 */
public interface OrderItemService {
    //根据订单id查询相应的订单信息
    R findOrderItems(Integer orderId);
    //添加订单详情（点击去支付后添加详情）
    R addOrderItem(AddOrderItemDto orderDto);
}
