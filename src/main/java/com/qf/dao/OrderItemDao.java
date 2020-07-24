package com.qf.dao;

import com.qf.dto.AddOrderItemDto;
import com.qf.pojo.OrderItems;
import org.springframework.stereotype.Repository;

/**
 * @program: demo
 * @description:
 * @author: money
 * @create: 2020-07-23 20:41
 */
@Repository
public interface OrderItemDao {
    //根据订单id查询相应的订单信息
    OrderItems findOrderItems(Integer orderId);
    //添加订单详情（点击去支付后添加详情）
    int addOrderItem(OrderItems orderItems);

}
