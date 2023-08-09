package com.example.mybatispluslearn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//下面的代码放到了mybatis的配置类中  mybaitspulsconfig
//@MapperScan("com.example.mybatispluslearn.mapper")
public class MybatisPlusLearnApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusLearnApplication.class, args);
    }

}
