package com.qf.dao;

import com.qf.pojo.FactoryAddress;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FactoryAddressDao {
    List<FactoryAddress> findFactoryAddress();
}
