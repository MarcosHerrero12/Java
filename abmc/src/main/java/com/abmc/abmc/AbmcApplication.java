package com.abmc.abmc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.abmc.abmc.Persistence")
public class AbmcApplication {
	public static void main(String[] args) {
		SpringApplication.run(AbmcApplication.class, args);
	}
}
