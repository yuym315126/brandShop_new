package com.qf.service;

import com.qf.pojo.ProductInfo;
import com.qf.vo.R;

import java.util.List;

public interface ProductInfoService {
    //根据条件查询(搜索商品)
    R findByProductLike(String findCondition);
    //查询所有商品
    R findAllGoods();
    //查询单个商品
    R findGoodById(Integer productId);
    //七日爆款(上架满七日,销量过指定数值的商品)
    R findByHot();
    //季末清仓()
    R findByLowerOfMonth();
    //根据厂家地址id查询
    R findByFactoryAddressId(Integer factoryAddressId);
    //根据商品类型查询
    R findByGoodsType(Integer goodsTypeId);

}
