package com.ylz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.ylz.*.mapper")
@SpringBootApplication
public class SpringbootpropertyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootpropertyApplication.class, args);
    }

}
