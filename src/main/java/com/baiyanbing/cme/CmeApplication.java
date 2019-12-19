package com.baiyanbing.cme;

import com.baiyanbing.cme.parser.Dh01Parser;
import com.baiyanbing.cme.parser.DhParser;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.baiyanbing.cme.mapper")
public class CmeApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CmeApplication.class, args);
    }

//    @Autowired
//    private DhParser dhParser;

    @Override
    public void run(String... args) throws Exception {
//        dhParser.parse();
    }
}
