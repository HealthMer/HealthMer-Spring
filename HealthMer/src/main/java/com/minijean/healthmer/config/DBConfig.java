package com.minijean.healthmer.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.minijean.healthmer.model")
public class DBConfig {
	
}
