package com.qf.dto;

import lombok.Data;

@Data
public class GetUpdateCartMsg {
    private Integer count;
    private Integer productId;
    private Integer shopCartId;
}
