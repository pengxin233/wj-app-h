package com.px.userService;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author pengxin
 */
@SpringBootApplication
@ComponentScan("com.px")
@MapperScan("com.px.userService.mapper")
public class Boot {
    public static void main(String[] args) {
        SpringApplication.run(Boot.class,args);
    }
}
