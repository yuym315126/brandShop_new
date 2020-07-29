package com.qf.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @PROJECT_NAME: brandshop
 * @DESCRIPTION:
 * @USER: 木子Lee
 * @DATE: 2020/7/21 19:23
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private Integer addressId;
    private String addressee;
    private String addPhone;
    private String area;
    private String street;
    private Integer userId;
    private Integer isDefault;
}
