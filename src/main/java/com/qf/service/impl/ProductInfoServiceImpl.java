package com.qf.service.impl;

import com.alibaba.fastjson.JSON;
import com.qf.config.RedisKeyConfig;
import com.qf.dao.ProductInfoDao;
import com.qf.pojo.ProductInfo;
import com.qf.service.ProductInfoService;
import com.qf.util.JedisCore;
import com.qf.util.TokenUtil;
import com.qf.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoDao productInfoDao;
    @Autowired
    private JedisCore jedisCore;




    @Override
    public R findAllGoods() {
        List<ProductInfo> allGoods = productInfoDao.findAllGoods();
        return R.ok(allGoods);
    }

    @Override
    public R findByProductLike(String findCondition) {
        List<ProductInfo> byProductLike = productInfoDao.findByProductLike(findCondition);
        String token = TokenUtil.createProductToken(123456);
        jedisCore.set(RedisKeyConfig.TOKEN_USER+token, JSON.toJSONString(byProductLike),RedisKeyConfig.TOKEN_TIME);
//        return R.ok(token);

        return R.ok(byProductLike);
    }

    @Override
    public R findGoodById(Integer productId) {
        ProductInfo goodById = productInfoDao.findGoodById(productId);

        String token = TokenUtil.createProductToken(productId);
        //存储商品信息到redis
        jedisCore.set(RedisKeyConfig.TOKEN_USER+token, JSON.toJSONString(goodById),RedisKeyConfig.TOKEN_TIME);
//        return R.ok(token);
        return R.ok(goodById);

    }

    @Override
    public R findByHot() {
        List<ProductInfo> byHot = productInfoDao.findByHot();
        return R.ok(byHot);
    }

    @Override
    public R findByLowerOfMonth() {
        List<ProductInfo> byLowerOfMonth = productInfoDao.findByLowerOfMonth();

        return R.ok(byLowerOfMonth);
    }
}
