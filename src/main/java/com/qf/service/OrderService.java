package com.qf.service;

import com.qf.dto.OrderDto;
import com.qf.vo.R;

/**
 * @program: demo
 * @description:
 * @author: money
 * @create: 2020-07-22 22:52
 */
public interface OrderService {
    //获取全部订单
    R selectAllOrders(String token);

    //订单新增
    R addOrder(OrderDto orderDto, String token);
}
