package com.qf.service.impl;

import com.qf.dao.ProductInfoDao;
import com.qf.dao.ShoppingCartDao;
import com.qf.dto.GetUpdateCartMsg;
import com.qf.dto.ShoppingCartDto;
import com.qf.dto.UpdateShoppingCartDto;
import com.qf.pojo.ProductInfo;
import com.qf.pojo.ShoppingCart;
import com.qf.service.ShoppingCartService;
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

    @Override
    public R insertGoodIntoCart(ShoppingCartDto shoppingCart) {
        Integer productId = shoppingCart.getProductId();

        ProductInfo goodById = productInfoDao.findGoodById(productId);

        ShoppingCart cart = new ShoppingCart();

        cart.setColor(shoppingCart.getColor());
        cart.setCount(shoppingCart.getCount());
        cart.setPrice(shoppingCart.getCount()*goodById.getProductPrice());
        cart.setProductImg(goodById.getPicture());
        cart.setProductName(goodById.getProductName());
        cart.setSize(shoppingCart.getSize());
        cart.setUserId(shoppingCart.getUserId());


        int row = shoppingCartDao.insertGoodIntoCart(cart);
        if(row >0){
            return R.ok(null);
        }else {
            return R.error("添加购物车失败");
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
