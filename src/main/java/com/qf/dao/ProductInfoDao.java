package com.qf.dao;

import com.qf.pojo.ProductInfo;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductInfoDao {
    //根据条件查询(搜索商品)
    List<ProductInfo> findByProductLike(String findCondition);
    //查询所有商品
    List<ProductInfo> findAllGoods();
    //查询单个商品
    ProductInfo findGoodById(Integer productId);
    //七日爆款(上架满七日,销量过指定数值的商品)
    List<ProductInfo> findByHot();
    //季末清仓()
    List<ProductInfo> findByLowerOfMonth();

}
