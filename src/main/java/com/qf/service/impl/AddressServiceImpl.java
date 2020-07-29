package com.qf.service.impl;

import com.alibaba.fastjson.JSON;
import com.qf.config.RedisKeyConfig;
import com.qf.dao.AddressDao;
import com.qf.dto.AddressDto;
import com.qf.pojo.Address;
import com.qf.pojo.User;
import com.qf.service.AddressService;
import com.qf.util.BeanCopyUtil;
import com.qf.util.EncryptUtil;
import com.qf.util.JedisCore;
import com.qf.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @PROJECT_NAME: brandshop
 * @DESCRIPTION:
 * @USER: 木子Lee
 * @DATE: 2020/7/21 19:46
 */
@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressDao addressDao;
    @Value("${brandshop.aes.passkey}")
    private String key;
    @Autowired
    private JedisCore jedisCore;
    @Override
    public R showAddress(String token) {

        if (jedisCore.checkKey(RedisKeyConfig.TOKEN_USER + token)) {
            User user = JSON.parseObject(jedisCore.get(RedisKeyConfig.TOKEN_USER + token), User.class);
            List<Address> addresses = addressDao.showAddress(user.getUserId());
            if (addresses != null) {
                return R.ok(addresses);
            }
            return R.error("没有地址不知道吗");
        }

        return null;


    }



    @Override
    public R insertAddress(AddressDto addressDto,String token) {
        if (jedisCore.checkKey(RedisKeyConfig.TOKEN_USER+token)){
            User user = JSON.parseObject(jedisCore.get(RedisKeyConfig.TOKEN_USER + token), User.class);
            Address address = BeanCopyUtil.copyDto(Address.class, addressDto, addressDto.getClass().getDeclaredFields());
            address.setUserId(user.getUserId());
            if (addressDao.insertAddress(address)>0){
                return R.ok();
            }
        }
//        Integer integer = addressDao.insertAddress(addressDto);
//        if (integer == 1){
//            return R.ok();
//        }
//        return R.error("添加信息错误");
        return R.error("亲，登录失效，请重新登录后再进行地址的添加");
    }

//    @Override
//    public R updateAddress(Address address) {
//        Integer integer = addressDao.updateAddress(address);
//        if (integer == 1){
//            return R.ok();
//        }
//        return R.error("更新失败");
//    }

    //更新地址的修改
    @Override
    public R updateAddress(Integer addressId , AddressDto addressDto) {
        Address address = BeanCopyUtil.copyDto(Address.class, addressDto, addressDto.getClass().getDeclaredFields());
        address.setAddressId(addressId);
        Integer integer = addressDao.updateAddress(address);
        if (integer == 1){
            return R.ok();
        }
        return R.error("更新失败");
    }

    @Override
    public R deleteAddress(int addressId) {
        Integer integer = addressDao.deleteAddress(addressId);
        if (addressId == 1){
            return R.error("删除失败");
        }
        return R.ok();
    }

    @Override
    public R findAddressById(Integer addressId) {
        Address address = addressDao.selectAddressById(addressId);
        if (address  != null && !"".equals(address)){
            AddressDto addressDto = new AddressDto();
            addressDto.setAddPhone(address.getAddPhone());
            addressDto.setAddressee(address.getAddressee());
            addressDto.setArea(address.getArea());
            addressDto.setStreet(address.getStreet());
            System.out.println(addressDto);
            return R.ok(addressDto);
        }
        return R.error("地址加载失败");
    }
}
