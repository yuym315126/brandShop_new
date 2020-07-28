package com.qf.service;

import com.qf.dto.AddressDto;
import com.qf.pojo.Address;
import com.qf.vo.R;

/**
 * @PROJECT_NAME: brandshop
 * @DESCRIPTION:
 * @USER: 木子Lee
 * @DATE: 2020/7/21 19:44
 */
public interface AddressService {
    R showAddress(String token);

    R insertAddress(AddressDto addressDto,String token);

//    R updateAddress(Address address);

    R updateAddress(Integer addressId , AddressDto addressDto);


    R deleteAddress(int addressId);

    R findAddressById(Integer addressId);

}
