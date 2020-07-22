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
    R showAddress();

    R insertAddress(AddressDto addressDto);

    R updateAddress(int addressId);

    R deleteAddress(int addressId);
}
