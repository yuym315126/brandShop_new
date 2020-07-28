package com.qf.service.impl;

import com.qf.dao.ProductTypeDao;
import com.qf.pojo.ProductType;
import com.qf.service.ProductTypeService;
import com.qf.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {
    @Autowired
    private ProductTypeDao productTypeDao;

    @Override
    public R findAllType() {
        List<ProductType> allType = productTypeDao.findAllType();
        return R.ok(allType);
    }
}
