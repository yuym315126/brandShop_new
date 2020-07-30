package com.qf.dao;

import com.qf.dto.AddressDto;
import com.qf.dto.AddressUpdateDto;
import com.qf.pojo.Address;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @PROJECT_NAME: brandshop
 * @DESCRIPTION:
 * @USER: 木子Lee
 * @DATE: 2020/7/21 19:30
 */
@Repository
public interface AddressDao {
    //显示地址
    List<Address> showAddress(int userId);

    //添加地址
    Integer insertAddress(Address address);

    //修改地址
    Integer updateAddress(AddressUpdateDto address);
    //Integer updateAddress(Integer addressId);

    //删除地址
    Integer deleteAddress(int addressId);

    //根据id查询地址
    Address selectAddressById(Integer addressId);

    //根据用户id查询默认地址
    Address showDefaultAddress(Integer userId);
}
