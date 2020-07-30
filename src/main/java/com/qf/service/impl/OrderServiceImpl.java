package com.qf.service.impl;

import com.alibaba.fastjson.JSON;
import com.qf.config.RedisKeyConfig;
import com.qf.dao.AddressDao;
import com.qf.dao.OrderDao;
import com.qf.dao.ProductInfoDao;
import com.qf.dto.OrderDto;
import com.qf.pojo.Address;
import com.qf.pojo.Order;
import com.qf.pojo.ProductInfo;
import com.qf.pojo.User;
import com.qf.service.OrderService;
import com.qf.util.BeanCopyUtil;
import com.qf.util.JedisCore;
import com.qf.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * @program: demo
 * @description:
 * @author: money
 * @create: 2020-07-22 22:54
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao dao;
    @Autowired
    private ProductInfoDao productInfoDao;
    @Autowired
    private AddressDao addressDao;
    @Autowired
   private JedisCore jedisCore;
    @Override
    public R selectAllOrders(String token) {
        if (jedisCore.checkKey(RedisKeyConfig.TOKEN_USER+token)){
            User user = JSON.parseObject(jedisCore.get(RedisKeyConfig.TOKEN_USER + token), User.class);
            System.out.println(user.getUserId());
            List<Order> orderList = dao.selectAllOrders(user.getUserId());
            System.out.println(orderList);
            if (orderList != null && !"".equals(orderList)){
                return R.ok(orderList);
            }else{
                return R.error("亲，您还未下单，赶紧来一单体验一下吧");
            }
        }
        return R.error("请先登录");
    }

    @Override
    public R addOrder(OrderDto orderDto, String token) {
        User user = JSON.parseObject(jedisCore.get(RedisKeyConfig.TOKEN_USER + token), User.class);
        if (jedisCore.checkKey(RedisKeyConfig.TOKEN_USER + token)){

//            Order order = BeanCopyUtil.copyDto(Order.class, orderDto, orderDto.getClass().getDeclaredFields());
            Order order = new Order();
//            order.setUserId(user.getUserId());
            order.setTotalPrice(orderDto.getTotalPrice());
            order.setOrderStatus(orderDto.getOrderStatus());
            order.setOrderNum(UUID.randomUUID().toString().replace("-",""));

            order.setUserId(user.getUserId());
            if (dao.addOrder(order)>0){
                return R.ok();
            }
        }
        return R.error("亲，登录失效 请重新登录");
    }

    @Override
    public R checkOrder(Integer productId, String token) {
        if (jedisCore.checkKey(RedisKeyConfig.TOKEN_USER+token)) {
            User user = JSON.parseObject(jedisCore.get(RedisKeyConfig.TOKEN_USER + token), User.class);
            System.out.println("用户id："+user.getUserId());
            Address address = addressDao.showDefaultAddress(user.getUserId());

            System.out.println("默认地址："+address);
            ProductInfo goodById = productInfoDao.findGoodById(productId);
            HashMap<String,Object> map=new HashMap<>();
            map.put("address",address);
            map.put("good",goodById);

            return R.ok(map);
        }else {
            return R.error("请先登录");
        }
    }
}
