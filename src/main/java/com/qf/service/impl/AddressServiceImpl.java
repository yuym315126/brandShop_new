package com.qf.service.impl;

import com.alibaba.fastjson.JSON;
import com.qf.config.RedisKeyConfig;
import com.qf.dao.AddressDao;
import com.qf.dto.AddressDto;
import com.qf.pojo.Address;
import com.qf.pojo.User;
import com.qf.service.AddressService;
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

//        if (jedisCore.checkKey(RedisKeyConfig.TOKEN_USER+token)){
//            User user = JSON.parseObject(jedisCore.get(RedisKeyConfig.TOKEN_USER+token),User.class);
//            Integer integer = userDao.UpdateUserPass(user.getUserId(), EncryptUtil.aesenc(key,pass));
//            if (integer>0){
//                //删除令牌
//                jedisCore.del(RedisKeyConfig.TOKEN_USER+token);
//                jedisCore.del(RedisKeyConfig.PHONE_TOKEN+user.getUserPhone());
//                return R.ok("修改成功，请重新登录");
//            }
//        }
//        return R.error("密码修改失败");
        if (jedisCore.checkKey(RedisKeyConfig.TOKEN_USER + token)) {
            User user = JSON.parseObject(jedisCore.get(RedisKeyConfig.TOKEN_USER + token), User.class);
            List<Address> addresses = addressDao.showAddress(user.getUserId());
            if (addresses != null) {
                return R.ok(addresses);
            }
            return R.error("没有地址不知道吗");
        }

        return null;

//        List<Address> addresses = addressDao.showAddress(userId);

    }

    @Override
    public R insertAddress(AddressDto addressDto) {
        Integer integer = addressDao.insertAddress(addressDto);
        if (integer == 1){
            return R.ok();
        }
        return R.error("添加信息错误");
    }

    @Override
    public R updateAddress(Address address) {
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
}
