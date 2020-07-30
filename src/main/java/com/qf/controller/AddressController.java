package com.qf.controller;

import com.qf.constant.SystemConstant;
import com.qf.dto.AddressDto;
import com.qf.dto.AddressUpdateDto;
import com.qf.pojo.Address;
import com.qf.service.AddressService;
import com.qf.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    public R showAddress(HttpServletRequest request){

        String token = request.getHeader(SystemConstant.TOKEN_HEADER);
        return addressService.showAddress(token);

//        R r = addressService.showAddress(userId);
////        System.out.println(r);
//        return r;

    }
    @ApiOperation("添加地址")
    @PostMapping("api/address/insertAddress")
    public R insertAddress(@RequestBody AddressDto addressDto, HttpServletRequest request){
        System.out.println("添加地址："+addressDto);
        String token = request.getHeader(SystemConstant.TOKEN_HEADER);
        return addressService.insertAddress(addressDto,token);
    }

//    @ApiOperation("更新地址")
//    @GetMapping("api/address/updateAddress")
//    public R updateAddress(Address address){
//        return addressService.updateAddress(address);
//    }


    @ApiOperation("更新地址")
    @PostMapping("api/address/updateAddress")
    public R updateAddress(@RequestBody AddressUpdateDto addressDto){
        System.out.println("跟新地址："+addressDto);
        return addressService.updateAddress(addressDto);
    }

    @ApiOperation("地址详情")
    @GetMapping("api/address/showOneAddress")
    public R showOneAddress(Integer addressId){
        return addressService.findAddressById(addressId);
    }

    @ApiOperation("删除地址")
    @GetMapping("api/address/deleteAddress")
    public R deleteAddress(int addressId){
        return addressService.deleteAddress(addressId);
    }

}
