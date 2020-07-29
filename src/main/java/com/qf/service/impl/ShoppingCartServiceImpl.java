package com.qf.service.impl;

import com.alibaba.fastjson.JSON;
import com.qf.config.RedisKeyConfig;
import com.qf.dao.ProductInfoDao;
import com.qf.dao.ShoppingCartDao;
import com.qf.dto.GetUpdateCartMsg;
import com.qf.dto.ShoppingCartDto;
import com.qf.dto.UpdateShoppingCartDto;
import com.qf.pojo.ProductInfo;
import com.qf.pojo.ShoppingCart;
import com.qf.pojo.User;
import com.qf.service.ShoppingCartService;
import com.qf.util.JedisCore;
import com.qf.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private ShoppingCartDao shoppingCartDao;
    @Autowired
    private ProductInfoDao productInfoDao;
    @Autowired
    private JedisCore jedisCore;

    @Override
    public R insertGoodIntoCart(Integer productId,String token) {
        System.out.println("service pid:"+productId);
        System.out.println(jedisCore.checkKey(RedisKeyConfig.TOKEN_USER + token));

        if (jedisCore.checkKey(RedisKeyConfig.TOKEN_USER + token)) {
            User user = JSON.parseObject(jedisCore.get(RedisKeyConfig.TOKEN_USER + token), User.class);
            ProductInfo goodById = productInfoDao.findGoodById(productId);

            System.out.println("serviceImpl good:"+goodById);
            ShoppingCart shoppingCart = new ShoppingCart();

            shoppingCart.setColor(goodById.getColor());
            shoppingCart.setCount(1);
            shoppingCart.setPrice(goodById.getProductPrice());
            shoppingCart.setProductImg(goodById.getPicture());
            shoppingCart.setProductName(goodById.getProductName());
            shoppingCart.setSize(goodById.getSize());
            shoppingCart.setUserId(user.getUserId());

            System.out.println("service cart:"+shoppingCart);

            int row = shoppingCartDao.insertGoodIntoCart(shoppingCart);
            if (row > 0) {
                return R.ok(null);
            } else {
                return R.error("添加购物车失败");
            }


        }else {
            return R.error("请先登录");
        }

    }



    @Override
    public R updateCountOfCart(GetUpdateCartMsg getUpdateCartMsg) {

        Integer count = getUpdateCartMsg.getCount();
        Integer productId = getUpdateCartMsg.getProductId();

        ProductInfo goodById = productInfoDao.findGoodById(productId);

        Double productPrice = goodById.getProductPrice();

        Double price=count*productPrice;


        UpdateShoppingCartDto cartDto = new UpdateShoppingCartDto();
        cartDto.setCount(count);
        cartDto.setPrice(price);
        cartDto.setShopCartId(getUpdateCartMsg.getShopCartId());
        int row = shoppingCartDao.updateCountOfCart(cartDto);
        if(row >0){
            return R.ok(null);
        }else {
            return R.error("更新数量失败");
        }
    }

    @Override
    public R deleteGoodFromCart(Integer shoppingCartId) {
        int row = shoppingCartDao.deleteGoodFromCart(shoppingCartId);
        if(row >0){
            return R.ok(null);
        }else {
            return R.error("删除购物车商品失败");
        }
    }

    @Override
    public R findCartByUser(Integer userId) {
//        System.out.println("id"+userId);
        List<ShoppingCart> cartByUser = shoppingCartDao.findCartByUser(userId);
//        System.out.println("========"+cartByUser);
        return R.ok(cartByUser);
    }
}
