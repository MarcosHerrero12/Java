package com.tasks;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.tasks.persistence")
public class AppTasksApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppTasksApplication.class, args);
	}

}
