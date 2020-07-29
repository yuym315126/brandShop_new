package com.qf.dao;

import com.qf.pojo.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: demo
 * @description:
 * @author: money
 * @create: 2020-07-22 22:28
 */
@Repository
public interface OrderDao {
    //获取全部订单
    List<Order> selectAllOrders(Integer userId);

    //订单新增
    Integer addOrder(Order order);

    //根据订单id查询相应订单
    Order selectOrder(Integer orderId);

}
