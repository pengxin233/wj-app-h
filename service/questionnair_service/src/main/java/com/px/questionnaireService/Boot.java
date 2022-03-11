package com.px.questionnaireService;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan("com.px")
@MapperScan("com.px.questionnaireService.mapper")
@EnableScheduling
public class Boot {
    public static void main(String[] args) {
        SpringApplication.run(Boot.class,args);
    }
}
