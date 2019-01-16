package com.cmaple.honeycomb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.cmaple.honeycomb.mapper")
public class HoneycombApplication {

	public static void main(String[] args) {
		SpringApplication.run(HoneycombApplication.class, args);
	}

}

