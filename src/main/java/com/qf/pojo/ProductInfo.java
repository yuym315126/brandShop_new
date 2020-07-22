package com.qf.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class ProductInfo {
    private Integer productId;
    //商品名称
    private String productName;
    //价格
    private Double productPrice;
    //进货日期
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String stockDate;
    //销量
    private String saleQuantity;
    //类型id
    private Integer typeId;
    //
    private ProductType productType;
    //颜色
    private String color;
    //图片
    private String picture;
    //购物车id
//    private Integer shopCar;
    //厂家地址id
    private Integer factoryAddressId;
    //
    private FactoryAddress factoryAddress;
    //面料
    private String material;
    //尺码
    private Integer size;
    //长度偏差
    private String length;
    //薄厚质数
    private String thickness;
    //弹性指数
    private String elasticity;
}
