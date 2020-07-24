package com.qf.service.impl;

import com.qf.dao.AddressDao;
import com.qf.dao.OrderDao;
import com.qf.dao.OrderItemDao;
import com.qf.dao.ProductInfoDao;
import com.qf.dto.AddOrderItemDto;
import com.qf.dto.OrderItemsDto;
import com.qf.pojo.Address;
import com.qf.pojo.Order;
import com.qf.pojo.OrderItems;
import com.qf.pojo.ProductInfo;
import com.qf.service.OrderItemService;
import com.qf.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: demo
 * @description:
 * @author: money
 * @create: 2020-07-23 21:00
 */
@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    private OrderItemDao itemDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private AddressDao addressDao;
    @Autowired
    private ProductInfoDao productInfoDao;

    @Override
    public R findOrderItems(Integer orderId) {
        OrderItems orderItems = itemDao.findOrderItems(orderId);
        OrderItemsDto itemsDto = new OrderItemsDto();

        Order order = orderDao.selectOrder(orderId);
        itemsDto.setTotalPrice(order.getTotalPrice());
        itemsDto.setOrderNum(order.getOrderNum());

        Address address = addressDao.selectAddressById(orderItems.getAddressId());
        itemsDto.setAddressee(address.getAddressee());
        itemsDto.setArea(address.getArea());
        itemsDto.setAddPhone(address.getAddPhone());
        itemsDto.setStreet(address.getStreet());

        ProductInfo productInfo = productInfoDao.findGoodById(orderItems.getProductId());
        itemsDto.setProductName(productInfo.getProductName());
        itemsDto.setColor(productInfo.getColor());
        itemsDto.setProductPrice(productInfo.getProductPrice());
        itemsDto.setPicture(productInfo.getPicture());
        return R.ok(itemsDto);
    }

    @Override
    public R addOrderItem(AddOrderItemDto orderDto) {


        Order order = orderDao.selectOrder(orderDto.getOrderId());


        OrderItems orderItems = new OrderItems();

        orderItems.setAddressId(orderDto.getAddressId());
        orderItems.setItemsNum(order.getOrderNum());
        orderItems.setProductId(orderDto.getProductId());
        orderItems.setUserId(orderDto.getUserId());

        int i = itemDao.addOrderItem(orderItems);
        if (i > 0){
            return R.ok();
        }
        return R.error("添加订单详情失败");
    }
}
