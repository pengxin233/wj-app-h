package com.px.questionnaireService;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.px")
@MapperScan("com.px.questionnaireService.mapper")
public class Boot {
    public static void main(String[] args) {
        SpringApplication.run(Boot.class,args);
    }
}
