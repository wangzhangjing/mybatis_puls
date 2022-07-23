package com.wzj.mybatis_puls;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

//扫描mapper接口所在的包
@MapperScan("com/wzj/mybatis_puls/mapper")
public class MybatisPulsApplication {



    public static void main(String[] args) {
        SpringApplication.run(MybatisPulsApplication.class, args);
    }

}
