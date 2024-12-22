package com.ls.checkin.config;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.ls.checkin.mapper") // 确保路径正确
public class MyBatisPlusConfig {
}
