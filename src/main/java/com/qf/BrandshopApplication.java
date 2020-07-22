package com.qf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement //启用事务
@MapperScan("com.qf.dao")
public class BrandshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(BrandshopApplication.class, args);
    }

}
