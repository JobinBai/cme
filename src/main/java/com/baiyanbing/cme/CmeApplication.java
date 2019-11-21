package com.baiyanbing.cme;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.baiyanbing.cme.mapper")
public class CmeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmeApplication.class, args);
    }

}
