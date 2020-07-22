package com.qf.service.impl;

import com.qf.dao.AddressDao;
import com.qf.dto.AddressDto;
import com.qf.pojo.Address;
import com.qf.service.AddressService;
import com.qf.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public R showAddress() {
        List<Address> addresses = addressDao.showAddress();
        if (addresses != null){
            return R.ok(addresses);
        }
        return R.error("没有地址不知道吗");
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
    public R updateAddress(int addressId) {
        Integer integer = addressDao.updateAddress(addressId);
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
