package com.qf.controller;

import com.qf.dto.AddressDto;
import com.qf.pojo.Address;
import com.qf.service.AddressService;
import com.qf.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @PROJECT_NAME: brandshop
 * @DESCRIPTION:
 * @USER: 木子Lee
 * @DATE: 2020/7/21 19:48
 */

@Api(tags = "地址相关操作")
@RestController
@CrossOrigin
public class AddressController {
    @Autowired
    private AddressService addressService;

    @ApiOperation("显示地址")
    @GetMapping("api/address/showAddress")
    public R showAddress(int userId){
        R r = addressService.showAddress(userId);
//        System.out.println(r);
        return r;

    }
    @ApiOperation("添加地址")
    @GetMapping("api/address/insertAddress")
    public R insertAddress(AddressDto addressDto){
        return addressService.insertAddress(addressDto);
    }

    @ApiOperation("更新地址")
    @GetMapping("api/address/updateAddress")
    public R updateAddress(Address address){
        return addressService.updateAddress(address);
    }

    @ApiOperation("删除地址")
    @GetMapping("api/address/deleteAddress")
    public R deleteAddress(int addressId){
        return addressService.deleteAddress(addressId);
    }

}
